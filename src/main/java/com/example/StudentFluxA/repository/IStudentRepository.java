package com.example.StudentFluxA.repository;

import com.example.StudentFluxA.entity.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IStudentRepository extends ReactiveMongoRepository<Student, String> {

    Flux<Student> getStudentByFirstName(String firstName);
}
