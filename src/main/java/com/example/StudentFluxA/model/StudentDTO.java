package com.example.StudentFluxA.model;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    String firstName;
    String lastName;
    Integer age;
    String classSection;
    String email;
}
