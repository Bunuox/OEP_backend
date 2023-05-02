package com.iuc.cerrahpasa.onlineexamplatform.service.impl;

import com.iuc.cerrahpasa.onlineexamplatform.service.ImagePreProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
@EnableScheduling
public class ImagePreProcessingImpl implements ImagePreProcessing {

    @Value("${python.file.path.image.pre.processing}")
    private String filePathDeneme;

    @Value("${python.command}")
    private String pythonCommand;

    @Override
    @Scheduled(cron = "${cron.expression.image.pre.processing}")
    public void runImagePreProcessing() {
        executePythonScript();
    }

    private void executePythonScript() {
        try {
            String[] command = {pythonCommand, filePathDeneme};
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.out.println("Python script execution failed with exit code: " + exitCode);
            } else {
                System.out.println("Python script executed successfully.");
            }
        } catch(Exception e){
            System.out.println("exceptiona dustu.");
        }
    }
}
