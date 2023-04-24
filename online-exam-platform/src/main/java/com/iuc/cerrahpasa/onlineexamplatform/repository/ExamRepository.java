package com.iuc.cerrahpasa.onlineexamplatform.repository;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    Exam findByCourseId(Long courseId);
}
