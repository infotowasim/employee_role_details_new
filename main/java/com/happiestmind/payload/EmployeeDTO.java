package com.happiestmind.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

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
