package com.example.StudentFluxA;


import com.example.StudentFluxA.handler.StudentHandler;
import com.example.StudentFluxA.service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;

@Configuration
@EnableWebFlux
public class Router implements WebFluxConfigurer {


    @Bean
    public RouterFunction<ServerResponse> studentRouter (StudentHandler studentHandler){
        return route()
                    .GET("/student/{studentId}",studentHandler::getStudentByStudentId, ops -> ops.beanClass(StudentService.class).beanMethod("getStudentByStudentId") )
                    .POST("/student",studentHandler::createStudent,ops -> ops.beanClass(StudentService.class).beanMethod("createStudent"))
                    .GET("/student",studentHandler::getStudent,ops -> ops.beanClass(StudentService.class).beanMethod("getStudent"))
                    .GET("/student/firstName/{firstName}",studentHandler::getStudentByFirstName,ops -> ops.beanClass(StudentService.class).beanMethod("getStudentByFirstName"))
                    .DELETE("/student/{studentId}",studentHandler::deleteStudent,ops -> ops.beanClass(StudentService.class).beanMethod("deleteStudent"))
                    .PUT("/student/{studentId}",studentHandler::updateStudent,ops -> ops.beanClass(StudentService.class).beanMethod("updateStudent"))
                    .build();


    }
}
