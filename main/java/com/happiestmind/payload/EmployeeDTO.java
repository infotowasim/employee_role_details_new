package com.happiestmind.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class EmployeeDTO {

    private long id;
    private String fastName;
    private String lastName;
    private String email;
    private long mobile;
    private float salary;
    private int age;


}