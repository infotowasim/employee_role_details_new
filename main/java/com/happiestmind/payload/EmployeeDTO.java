package com.happiestmind.payload;

import lombok.Data;

@Data
public class EmployeeDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long mobile;
    private float salary;
    private int age;



}
