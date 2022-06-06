package com.example.StudentFluxA.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    String id;
    String firstName;
    String lastName;
    Integer age;
    String classSection;
    String email;

}
