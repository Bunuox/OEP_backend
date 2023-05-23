package com.iuc.cerrahpasa.onlineexamplatform.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.iuc.cerrahpasa.onlineexamplatform.data.model.Exam;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.*;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.EyeTrackingResponse;
import com.iuc.cerrahpasa.onlineexamplatform.service.*;
import com.iuc.cerrahpasa.onlineexamplatform.util.EyeTrackerUtil;
import org.apache.commons.io.FileUtils;
import com.iuc.cerrahpasa.onlineexamplatform.data.model.Course;
import com.iuc.cerrahpasa.onlineexamplatform.data.model.Take;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.FaceIdentificationResponse;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.StudentFindResponse;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.SuccessCreationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private ExamService examService;

	@Autowired
	private TakeService takeService;

	@Autowired
	private EmailService emailService;

	@Value("${file.name.python}")
	String fileNamePython;

	@Value("${python.command}")
	String pythonCommand;

	@Value("${python.file.path.face.identification}")
	String fileFaceIdentificationPath;

	@Value("${python.file.path.eye.tracking}")
	String fileEyeTrackingPath;

	@Value("${percantange.thresh.hold}")
	Double percantageThresHold;


	@PostMapping("/createStudent")
	public ResponseEntity<SuccessCreationResponse> createStudent(@RequestBody StudentCreationRequest studentRequest) {

		Boolean success = studentService.createStudent(studentRequest);
		return new ResponseEntity<>(SuccessCreationResponse.builder().success(success).build(), HttpStatus.OK);
	}

	@PostMapping("/findStudent")
	public ResponseEntity<StudentFindResponse> findStudent(@RequestBody StudentFindRequest studentFindRequest){
		Student student = studentService.findStudent(studentFindRequest);
		return new ResponseEntity<>(StudentFindResponse.builder().student(student).build(), HttpStatus.OK);
	}

	@PostMapping("/findStudentExams")
	public ResponseEntity<List> studentExams(@RequestBody TakeFindRequest takeFindRequest){
		Take[] takes = takeService.findTake(takeFindRequest);
		List<Exam> exams = new ArrayList<>();

		for(Take t: takes){
			Exam[] courseExams = examService.findMultipleExams(ExamFindRequest.builder().courseId(t.getCourseId()).build());
			exams.addAll(Arrays.asList(courseExams));
		}
		return new ResponseEntity<>(exams, HttpStatus.OK);
	}


	@PostMapping("/studentCourses")
	public ResponseEntity<List> studentCourses(@RequestBody TakeFindRequest takeFindRequest){
		Take[] takes = takeService.findTake(takeFindRequest);
		List<Course> courses = new ArrayList<>();

		for(Take t: takes){
			courses.add(courseService.findCourse(CourseFindRequest.builder().courseId(t.getCourseId()).build()));
		}

		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	@PostMapping("/sendEmail")
	public ResponseEntity<String> sendEmail(@RequestBody String email) {

		// E-posta gönderimi için gerekli kodları burada yazın
		emailService.sendSimpleMessage(email, "Konu", "XD");
		return ResponseEntity.ok("E-posta başarıyla gönderildi.");
	}

	@PostMapping("/faceIdentification")
	public ResponseEntity<FaceIdentificationResponse> identityFace(@RequestParam("images")MultipartFile[] files) {
		uploadImages(files);
		Long trueCount = executeFaceIdentification(files[0].getOriginalFilename());
		deleteImages(files[0].getOriginalFilename());

		Double truePersonPercentage = ((double)trueCount/files.length) * 100;
		Boolean isThisTruePerson = truePersonPercentage >=percantageThresHold ? Boolean.TRUE: Boolean.FALSE;
		System.out.println(truePersonPercentage);

		return ResponseEntity.ok(FaceIdentificationResponse.builder().isThisTruePerson(isThisTruePerson).build());
	}

	@PostMapping("/eyeTracking")
	public ResponseEntity<EyeTrackingResponse> followEyeMotion(@RequestParam("images") MultipartFile[] files){
		uploadEyes(files);
		Boolean isCheating = executeEyeTracking(files[0].getOriginalFilename());
		deleteEyes(files[0].getOriginalFilename());
		return ResponseEntity.ok(EyeTrackingResponse.builder().isCheating(isCheating).build());
	}

	private void uploadEyes(MultipartFile[] files){
		for(MultipartFile file : files){
			try {
				// Set the directory where the image will be saved
				Path rootLocation = Paths.get( fileNamePython,"eyes", file.getOriginalFilename());
				if (Files.notExists(rootLocation)) {
					Files.createDirectories(rootLocation);
				}
				// Save the image
				Path destinationFile = rootLocation.resolve(LocalDateTime.now().toString() + ".jpeg");
				Files.copy(file.getInputStream(), destinationFile);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void uploadImages(MultipartFile[] files){
		for(MultipartFile file : files){
			try {
				// Set the directory where the image will be saved
				Path rootLocation = Paths.get( fileNamePython,"uploads", file.getOriginalFilename());
				if (Files.notExists(rootLocation)) {
					Files.createDirectories(rootLocation);
				}
				// Save the image
				Path destinationFile = rootLocation.resolve(LocalDateTime.now().toString() + ".jpeg");
				Files.copy(file.getInputStream(), destinationFile);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Boolean executeEyeTracking(String folderId){
		List<Boolean> isEyeSlipped = new ArrayList<>();
		try {
			String[] command = {pythonCommand, fileEyeTrackingPath, folderId};
			ProcessBuilder processBuilder = new ProcessBuilder(command);
			Process process = processBuilder.start();

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
				String line;
				while ((line = reader.readLine()) != null) {
					Character returnType = line.charAt(0);
					if(returnType.equals('c') || returnType.equals('C')){
						isEyeSlipped.add(Boolean.TRUE);
					} else{
						isEyeSlipped.add(Boolean.FALSE);
					}
				}
			}
			int exitCode = process.waitFor();
			if (exitCode != 0) {
				System.out.println("Python script execution failed with exit code: " + exitCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isEyeSlipped.stream().allMatch(t -> t.equals(Boolean.TRUE));
	}
	private  Long executeFaceIdentification(String folderId){//return true count.
		List<Boolean> identityList = new ArrayList<>();
		try {
			String[] command = {pythonCommand, fileFaceIdentificationPath, folderId};
			ProcessBuilder processBuilder = new ProcessBuilder(command);
			Process process = processBuilder.start();

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
				String line;
				while ((line = reader.readLine()) != null) {
					Character returnType = line.split(" ")[1].charAt(0);
					if(returnType.equals('t') || returnType.equals('T')){
						identityList.add(Boolean.TRUE);
					} else{
						identityList.add(Boolean.FALSE);
					}
				}
			}
			int exitCode = process.waitFor();
			if (exitCode != 0) {
				System.out.println("Python script execution failed with exit code: " + exitCode);
			}
		} catch(Exception e){
			System.out.println("exceptiona dustu.");
		}
		return identityList.stream().filter(t -> t.equals(Boolean.TRUE)).count();
	}

	private void deleteEyes(String folderName){
		Path rootLocation = Paths.get( fileNamePython,"eyes", folderName);
		File imagesDirectory = new File(rootLocation.toString());
		try{
			FileUtils.cleanDirectory(imagesDirectory);
		} catch (Exception e){

		}
	}
	private void deleteImages(String folderName){
		Path rootLocation = Paths.get( fileNamePython,"uploads", folderName);
		File imagesDirectory = new File(rootLocation.toString());
		try{
			FileUtils.cleanDirectory(imagesDirectory);
		} catch (Exception e){

		}
	}
}
