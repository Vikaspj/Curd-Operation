package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.beans.StudentWrapper;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.service.StudentService;

@Controller
public class StudentController 
{
	@Autowired
	private StudentService studentService;


	@PostMapping("/saveStudent1")
	public ResponseEntity<StudentWrapper> saveStudent(@RequestBody StudentWrapper studentWrapper )
	{
		studentWrapper = studentService.saveStudent(studentWrapper);
		return ResponseEntity.ok(studentWrapper);
	}
	@GetMapping("/getstudent/{id}")
	public  ResponseEntity<StudentWrapper> getStudentById(@PathVariable Long id)throws RecordNotFoundException
	{
		StudentWrapper studentWrapper = studentService.getStudentById(id);
		return ResponseEntity.ok(studentWrapper);
	}

	@PutMapping("/updateStudent1")
	public ResponseEntity<StudentWrapper> updateStudent(@RequestBody StudentWrapper studentWrapper )throws RecordNotFoundException
	{
		studentWrapper = studentService.updateStudentDetailes(studentWrapper);
		return ResponseEntity.ok(studentWrapper);
	}

	@DeleteMapping("/deletestudent/{id}")
	public  ResponseEntity<StudentWrapper> deleteStudentById(@PathVariable Long id)throws RecordNotFoundException
	{
		studentService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
