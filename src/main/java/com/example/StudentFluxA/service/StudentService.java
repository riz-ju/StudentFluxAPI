package com.example.StudentFluxA.service;

import com.example.StudentFluxA.entity.Student;
import com.example.StudentFluxA.model.StudentDTO;
import com.example.StudentFluxA.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService implements IStudentService{


   private final IStudentRepository studentRepository;

    @Autowired
    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Mono<Student> getStudentByStudentId(@PathVariable("studentId") String id) {
        return studentRepository.findById(id);
    }

    @Override
    public Flux<Student> getStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Mono<Student> createStudent(@RequestBody StudentDTO studentDTO) {
      var student = new Student();
        student.setId(UUID.randomUUID().toString());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setAge(studentDTO.getAge());
        student.setClassSection(studentDTO.getClassSection());
        student.setEmail(studentDTO.getEmail());

        return studentRepository.save(student);
    }

    @Override
    public Mono<Void> deleteStudent(@PathVariable("studentId") String id) {
        return studentRepository.deleteById(id);
    }

    @Override
    public Mono<Student> updateStudent(@PathVariable("studentId") String id,@RequestBody StudentDTO studentDTO) {
        //Mono<Student> stud = studentRepository.findById(id);

      return studentRepository.findById(id)
              .flatMap( student -> {
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setAge(studentDTO.getAge());
            student.setClassSection(studentDTO.getClassSection());
            student.setEmail(studentDTO.getEmail());

            return studentRepository.save(student);
        });

    }

    @Override
    public Flux<Student> getStudentByFirstName(String firstName) {
        return studentRepository.getStudentByFirstName(firstName);
    }
}
