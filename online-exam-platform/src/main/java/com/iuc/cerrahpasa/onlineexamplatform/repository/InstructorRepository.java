package com.iuc.cerrahpasa.onlineexamplatform.repository;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Instructor findByEmail(String email);
}
