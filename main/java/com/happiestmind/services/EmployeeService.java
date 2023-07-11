package com.happiestmind.services;

import com.happiestmind.payload.EmployeeDTO;

import java.util.List;

public interface EmployeeService {


    // Creating Employee record
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);


    // Get All Employees Records
    List<EmployeeDTO> getAllEmployeeRecords();


    //Get Employee single Record by id
    EmployeeDTO getEmployeeSingleRecordById(long employeeId);


    // Update Employee Record by Id
    EmployeeDTO updateEmployeeRecordById(long employeeId, EmployeeDTO employeeDTO);


    // Delete Single Employee Record By Id
    void deleteSingleEmployeeRecordById(long employeeId);
}
