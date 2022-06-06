package com.example.StudentFluxA;

import com.example.StudentFluxA.entity.Student;
import com.example.StudentFluxA.model.StudentDTO;
import com.example.StudentFluxA.repository.IStudentRepository;
import com.example.StudentFluxA.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentServiceTest {

    @Mock
    IStudentRepository studentRepository;

    @Autowired
    StudentService studentService;

    @BeforeAll
    void init(){
        ReflectionTestUtils.setField(studentService,"studentRepository",studentRepository);}


    @Test
    void createStudentTest(){
        var mockStudentRecordId= UUID.randomUUID().toString();
        var mockStudentDTO = new StudentDTO(
                "Rizwan",
                "Khan",
                18,
                "10A",
                "riz@gmail.com"
        );

        var mockStudentRecord = new Student(
                mockStudentRecordId,
                "Rizwan",
                "Khan",
                18,
                "10A",
                "riz@gmail.com"
        );
        var mockStudentRecordMono = Mono.just(mockStudentRecord);

        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(mockStudentRecordMono);

        StepVerifier.create(studentService.createStudent(mockStudentDTO))
                .expectNextMatches(employee -> employee.getId().equals(mockStudentRecordId))
                .verifyComplete();
    }

    @Test
    void getStudentByStudentIdTest(){
        var mockStudentRecordId =UUID.randomUUID().toString();

        var mockStudentRecord = new Student(
                mockStudentRecordId,
                "Rizwan",
                "Khan",
                18,
                "10A",
                "riz@gmail.com"
        );

        var mockStudentRecordMono = Mono.just(mockStudentRecord);

        Mockito.when(studentRepository.findById(mockStudentRecordId)).thenReturn(mockStudentRecordMono);

        StepVerifier.create(studentService.getStudentByStudentId(mockStudentRecordId))
                .consumeNextWith(student -> {
                    Assertions.assertEquals(mockStudentRecordId,student.getId());
                })
                .verifyComplete();
    }


    @Test
    void updateStudentTest(){
        var mockStudentRecordId =UUID.randomUUID().toString();

        var mockStudentRecord = new Student(
                mockStudentRecordId,
                "Rizwan",
                "Khan",
                18,
                "10A",
                "riz@gmail.com"
        );

        Mono<Student> mockStudentMono = Mono.just(mockStudentRecord);
        Student savedStudent =mockStudentMono.block();
        savedStudent.setEmail("riz@gmail.com");

        Mono<Student> updateStudent = Mono.just(mockStudentRecord);

        StepVerifier.create(updateStudent)
                .expectNextMatches(student -> student.getEmail().equals("riz@gmail.com"))
                .verifyComplete();
    }

}
