package com.example.st12.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.st12.model.Student;
import com.example.st12.repo.StudentRepo;
import com.example.st12.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;//Inject StudentRepository

    //save student in database
    @Override
    public Student saveStudent(Student student){
        return studentRepo.save(student);
    }

    //get all students from database
    @Override
    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }
    
    //get student using id
    @Override
    public Student getStudentById(long id){
        Optional<Student> student = studentRepo.findById(id);
        if (student.isPresent()){
            return student.get();
        }else {
            throw new RuntimeException("Student not found with id: " + id);
        }
    }

    //update student 
    @Override
    public Student updateStudent(Student student,long id){
        Student existingStudent = studentRepo.findById(id).orElseThrow(
            () -> new RuntimeException("Student not found with id: " + id)
        );
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        //save
        studentRepo.save(existingStudent);
        return existingStudent;
    }

    //Delete Student
    @Override
    public void deleteStudent(long id){
        //check if student exists
        studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        //Delete student
        studentRepo.deleteById(id);
    }
}
