package com.petprojects.webapi;

import com.petprojects.webapi.entity.Student;
import com.petprojects.webapi.entity.Teacher;
import com.petprojects.webapi.repository.StudentRepository;
import com.petprojects.webapi.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class WebapiApplication {

	public static void main(String[] args) {

			SpringApplication.run(WebapiApplication.class, args);
	}



}
