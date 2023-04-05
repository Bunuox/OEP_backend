package com.iuc.cerrahpasa.onlineexamplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	Student findByEmailAndPassword(String email, String password);
}
