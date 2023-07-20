package com.happiestmind.services.impl;

import com.happiestmind.entities.Employee;
import com.happiestmind.exception.ResourceNotFoundException;
import com.happiestmind.payload.EmployeeDTO;
import com.happiestmind.repositories.EmployeeRepository;
import com.happiestmind.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

//    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
//       this.employeeRepository=employeeRepository;
//
//    }


    // Converting DTO to Entity
    public Employee mapToEntity(EmployeeDTO employeeDTO){
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        return employee;
    }

    // Converting Entity to DTO
    public EmployeeDTO mapToDTO(Employee employee){
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        return employeeDTO;
    }


    // Creating Employee Record.
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        // DTO to Entity
        Employee employee = mapToEntity(employeeDTO); // convert DTO to Entity
        Employee newEmployee = employeeRepository.save(employee);// save into the database.

        // Entity to DTO
        EmployeeDTO employeeDTO1 = mapToDTO(newEmployee); // Again Convert Entity to DTO.

        return employeeDTO1; // DTO return into the Postman
    }


    // Get All Employees Records
    @Override
    public List<EmployeeDTO> getAllEmployeeRecords() {

        List<Employee> employeeList = employeeRepository.findAll();

        // converting employeeList to employeeDTOList
        List<EmployeeDTO> employeeDTOList = employeeList.stream().map(employee -> mapToDTO(employee)).collect(Collectors.toList());

        return employeeDTOList;
    }


    //Get Employee single Record by id
    @Override
    public EmployeeDTO getEmployeeSingleRecordById(long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        // Converting Entity to DTO
        EmployeeDTO employeeDTO = mapToDTO(employee);
        return employeeDTO;
    }



    // Update Employee Record by Id
    @Override
    public EmployeeDTO updateEmployeeRecordById(long employeeId, EmployeeDTO employeeDTO) {

        // Retrieve Employee entity by id
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("employee", "id", employeeId));

        // DTO to Entity
        // Converting  EmployeeDTO to EmployeeEntity.
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setMobile(employeeDTO.getMobile());
        employee.setAge(employeeDTO.getAge());
        employee.setSalary(employeeDTO.getSalary());

//        Employee employee1 = mapToEntity(employeeDTO);  // This line not working I have to work for this line later.

        Employee newEmployee1 = employeeRepository.save(employee); // Saving the Entity into Database.

        // Entity to DTO
        EmployeeDTO employeeDTO1 = mapToDTO(newEmployee1);

        return employeeDTO1;
    }



    // Delete Single Employee Record By Id
    @Override
    public void deleteSingleEmployeeRecordById(long employeeId) {

        // Retrieve Employee entity by id
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("employee", "id", employeeId));

        employeeRepository.delete(employee);

    }


}
