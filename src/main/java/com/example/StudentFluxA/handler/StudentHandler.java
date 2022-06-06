package com.example.StudentFluxA.handler;


import com.example.StudentFluxA.entity.Student;
import com.example.StudentFluxA.model.StudentDTO;
import com.example.StudentFluxA.repository.IStudentRepository;
import com.example.StudentFluxA.service.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
@Slf4j
public class StudentHandler {


    private final IStudentService studentService;

    @Autowired
    public StudentHandler(IStudentService studentService) {
        this.studentService = studentService;
    }

    public Mono<ServerResponse> getStudentByStudentId(ServerRequest serverRequest){
        var studentId = serverRequest.pathVariable("studentId");
        log.info("getStudentByStudentId");
        return ServerResponse
                .ok()
                .body(studentService.getStudentByStudentId(studentId), Student.class);

    }

    public Mono<ServerResponse> getStudentByFirstName(ServerRequest serverRequest){
        var firstName = serverRequest.pathVariable("firstName");
        log.info("firstName{}",firstName);

        log.info("getStudentByFirstName");
        return ServerResponse
                .ok()
                .body(studentService.getStudentByFirstName(firstName),Student.class);

    }

    public Mono<ServerResponse> getStudent(ServerRequest serverRequest){
        return ServerResponse.ok().body(studentService.getStudent(),Student.class);

    }

    public Mono<ServerResponse> createStudent(ServerRequest serverRequest){
        var studentDTOMono = serverRequest.bodyToMono(StudentDTO.class);
        return studentDTOMono.flatMap(studentDTO -> {
            return ServerResponse
                    .status(HttpStatus.CREATED)
                    .body(studentService.createStudent(studentDTO),Student.class);
        });
    }

    public Mono<ServerResponse> deleteStudent(ServerRequest serverRequest){
        var studentId = serverRequest.pathVariable("studentId");
        return ServerResponse
                .ok()
                .body(studentService.deleteStudent(studentId),Student.class);


    }


    public Mono<ServerResponse> updateStudent(ServerRequest serverRequest){
        var studentDTOMono = serverRequest.bodyToMono(StudentDTO.class);
        var studentId = serverRequest.pathVariable("studentId");
        return studentDTOMono.flatMap(studentDTO -> {
            return ServerResponse
                    .status(HttpStatus.CREATED)
                    .body(studentService.updateStudent(studentId,studentDTO),Student.class);
        });
    }
}
