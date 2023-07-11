package com.happiestmind.controller;

import com.happiestmind.payload.EmployeeDTO;
import com.happiestmind.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    // Creating Employee record
    // POST:  http://localhost:8080/api/employees
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO employeeDTO1 = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(employeeDTO1, HttpStatus.CREATED);
    }

    // Get All Employees Records
    // GET:- http://localhost:8080/api/employees
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeeRecords(){
        List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployeeRecords();
        return ResponseEntity.ok(employeeDTOList);
    }

    //Get Employee single Record by id
    // GET:- http://localhost:8080/api/employees/1
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeSingleRecordById(@PathVariable("employeeId") long employeeId){
        EmployeeDTO employeeDTO = employeeService.getEmployeeSingleRecordById(employeeId);
        return ResponseEntity.ok(employeeDTO);
    }


    // Update Employee Record by Id
    // PUT:- http://localhost:8080/api/employees/2
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeRecordById(@PathVariable("employeeId") long employeeId, @RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO employeeDTO1 = employeeService.updateEmployeeRecordById(employeeId, employeeDTO);
        return new ResponseEntity<>(employeeDTO1,HttpStatus.CREATED);
    }


    // Delete Single Employee Record By Id
    // DELETE:- http://localhost:8080/api/employees/9
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteSingleEmployeeRecordById(@PathVariable("employeeId") long employeeId){
        employeeService.deleteSingleEmployeeRecordById(employeeId);
        return ResponseEntity.ok("From Employee Entity single record successfully deleted");
    }





}
