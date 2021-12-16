package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.StudentWrapper;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService 
{
	@Autowired
	StudentRepository repository;
	public StudentWrapper saveStudent(StudentWrapper inputstudent) 
	{
		Student student = new Student();
		//student.setId(inputstudent.getId());
		student.setName(inputstudent.getName());
		student = repository.save(student);

		inputstudent.setId(student.getId());
		inputstudent.setName(student.getName());
		return inputstudent;
	}
	public StudentWrapper getStudentById(Long id) throws RecordNotFoundException
	{
		StudentWrapper studentWrapperOutput = null;
		Optional< Student> studentOptionalData = repository.findById(id);

		if(studentOptionalData.isPresent())

		{
			studentWrapperOutput = new StudentWrapper();
			Student student = studentOptionalData.get();
			studentWrapperOutput.setId(student.getId());
			studentWrapperOutput.setName(student.getName());
		}
		else
		{
			throw new RecordNotFoundException("Student Record not Found");
		}
		return studentWrapperOutput;
	}

	public StudentWrapper updateStudentDetailes(StudentWrapper inStudent) throws RecordNotFoundException
	{
		Optional< Student> studentOptionalData = repository.findById(inStudent.getId());

		if(studentOptionalData.isPresent())
		{
			Student student = studentOptionalData.get();
			//update name
			student.setName(inStudent.getName());
			repository.save(student);

			//Populate the update details in the output object 	
			StudentWrapper studentWrapper = new StudentWrapper();
			studentWrapper.setId(student.getId());
			studentWrapper.setName(student.getName());
			return studentWrapper;
		}
		else
		{
			throw new RecordNotFoundException("Student Record not Found");
		}
	}
	public void deleteById(Long id)throws RecordNotFoundException
	{
		Optional< Student> studentOptionalData = repository.findById(id);
		if(studentOptionalData.isPresent())
		{
			Student student = studentOptionalData.get();
			repository.delete(student);
		}	
		else
		{
			throw new RecordNotFoundException("Student Record not Found");
		}
	}
}
