package com.example.st12.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.st12.model.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

}
