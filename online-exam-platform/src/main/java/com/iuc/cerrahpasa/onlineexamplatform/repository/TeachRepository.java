package com.iuc.cerrahpasa.onlineexamplatform.repository;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Teach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachRepository extends JpaRepository<Teach, Long> {
    Teach[] findAllByInstructorId(Long instructorId);
}
