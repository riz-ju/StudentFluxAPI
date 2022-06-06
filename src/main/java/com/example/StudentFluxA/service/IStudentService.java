package com.example.StudentFluxA.service;

import com.example.StudentFluxA.entity.Student;
import com.example.StudentFluxA.model.StudentDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface IStudentService {
    Mono<Student> getStudentByStudentId(String id);
    Flux<Student> getStudent();
    Mono<Student> createStudent(StudentDTO studentDTO);

    Mono<Void> deleteStudent(String id);

    Mono<Student> updateStudent(String id, StudentDTO studentDTO);

    Flux<Student> getStudentByFirstName(String firstName);

}
