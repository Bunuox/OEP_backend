package com.iuc.cerrahpasa.onlineexamplatform.repository;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.ExamResults;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamResultRepository extends JpaRepository<ExamResults, Long> {

    ExamResults findExamResultByExamIdAndStudentId(Long examId, Long studentId);

    ExamResults[] findExamResultsByExamId(Long examId);
}
