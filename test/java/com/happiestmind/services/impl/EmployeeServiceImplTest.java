package com.happiestmind.services.impl;


import com.happiestmind.entities.Employee;
import com.happiestmind.payload.EmployeeDTO;
import com.happiestmind.repositories.EmployeeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@SpringBootTest
class EmployeeServiceImplTest {


    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper modelMapper;



    @Test
    void getAllEmployeeRecords_ReturnsListOfEmployeeDTOs() {

        // Arrange
        // Create a list of Employee entities
        List<Employee> employeeList = new ArrayList<>();

        // Set employee1 properties
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setFastName("John");
        employee1.setLastName("Doe");


        employeeList.add(employee1);

        // Set employee2 properties
        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setFastName("Jane");
        employee2.setLastName("Smith");


        employeeList.add(employee2);

        // Create a list of corresponding EmployeeDTOs
        List<EmployeeDTO> expectedEmployeeDTOList = new ArrayList<>();

        // Set employeeDTO1 properties
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setId(1L);
        employeeDTO1.setFastName("John");
        employeeDTO1.setLastName("Doe");


        expectedEmployeeDTOList.add(employeeDTO1);

        // Set employeeDTO2 properties
        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        employeeDTO2.setId(2L);
        employeeDTO2.setFastName("Jane");
        employeeDTO2.setLastName("Smith");


        expectedEmployeeDTOList.add(employeeDTO2);

        when(employeeRepository.findAll()).thenReturn(employeeList);
        when(modelMapper.map(Mockito.any(Employee.class), Mockito.eq(EmployeeDTO.class)))
                .thenReturn(employeeDTO1)
                .thenReturn(employeeDTO2);

        // Act
        List<EmployeeDTO> actualEmployeeDTOList = employeeService.getAllEmployeeRecords();

        // Assert
        assertEquals(expectedEmployeeDTOList.size(), actualEmployeeDTOList.size());
        assertEquals(expectedEmployeeDTOList.get(0), actualEmployeeDTOList.get(0));
        assertEquals(expectedEmployeeDTOList.get(1), actualEmployeeDTOList.get(1));
        verify(employeeRepository, times(1)).findAll();
        verify(modelMapper, times(2)).map(Mockito.any(Employee.class), Mockito.eq(EmployeeDTO.class));

    }







    @Test
    void createEmployee_ReturnsCreatedEmployeeDTO() {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1L);
        employeeDTO.setFastName("John");
        employeeDTO.setLastName("Doe");


        // Set employee properties
        Employee employee = new Employee();

        employee.setId(1L);
        employee.setFastName("John");
        employee.setLastName("Doe");


        // Set savedEmployee properties
        Employee savedEmployee = new Employee();

        savedEmployee.setId(1L);
        savedEmployee.setFastName("John");
        savedEmployee.setLastName("Doe");


        // Set expectedEmployeeDTO properties
        EmployeeDTO expectedEmployeeDTO = new EmployeeDTO();

        expectedEmployeeDTO.setId(1L);
        expectedEmployeeDTO.setFastName("John");
        expectedEmployeeDTO.setLastName("Doe");


        when(modelMapper.map(employeeDTO, Employee.class)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(savedEmployee);
        when(modelMapper.map(savedEmployee, EmployeeDTO.class)).thenReturn(expectedEmployeeDTO);

        // Act
        EmployeeDTO createdEmployeeDTO = employeeService.createEmployee(employeeDTO);

        // Assert
        assertEquals(expectedEmployeeDTO, createdEmployeeDTO);
        verify(employeeRepository, times(1)).save(employee);
        verify(modelMapper, times(1)).map(employeeDTO, Employee.class);
        verify(modelMapper, times(1)).map(savedEmployee, EmployeeDTO.class);

    }


    @Test
    void getEmployeeSingleRecordById_ReturnsEmployeeDTO_WhenFound() {

        // Arrange
        long employeeId = 1L;

        // Set employee properties
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFastName("John");
        employee.setLastName("Doe");


        // Set expectedEmployeeDTO properties
        EmployeeDTO expectedEmployeeDTO = new EmployeeDTO();
        expectedEmployeeDTO.setId(1L);
        expectedEmployeeDTO.setFastName("John");
        expectedEmployeeDTO.setLastName("Doe");


        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(modelMapper.map(employee, EmployeeDTO.class)).thenReturn(expectedEmployeeDTO);

        // Act
        EmployeeDTO actualEmployeeDTO = employeeService.getEmployeeSingleRecordById(employeeId);

        // Assert
        assertEquals(expectedEmployeeDTO, actualEmployeeDTO);
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(modelMapper, times(1)).map(employee, EmployeeDTO.class);

    }


    @Test
    void updateEmployeeRecordById_ReturnsUpdatedEmployeeDTO() {

        // Arrange
        long employeeId = 2L;

        // Set employeeDTO properties
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1L);
        employeeDTO.setFastName("John");
        employeeDTO.setLastName("Doe");



        // Set employee properties
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFastName("John");
        employee.setLastName("Doe");



        // Set updatedEmployee properties
        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(1L);
        updatedEmployee.setFastName("wasim");
        updatedEmployee.setLastName("akram");


        // Set expectedEmployeeDTO properties
        EmployeeDTO expectedEmployeeDTO = new EmployeeDTO();
        expectedEmployeeDTO.setId(1L);
        expectedEmployeeDTO.setFastName("John");
        expectedEmployeeDTO.setLastName("Doe");


        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(updatedEmployee);
        when(modelMapper.map(updatedEmployee, EmployeeDTO.class)).thenReturn(expectedEmployeeDTO);


        // Act
        EmployeeDTO actualEmployeeDTO = employeeService.updateEmployeeRecordById(employeeId, employeeDTO);


        // Assert
        assertEquals(expectedEmployeeDTO, actualEmployeeDTO);
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeRepository, times(1)).save(employee);
        verify(modelMapper, times(1)).map(updatedEmployee, EmployeeDTO.class);

    }


        @Test
        void deleteSingleEmployeeRecordById_ExistingEmployee_SuccessfullyDeleted() {

            MockitoAnnotations.openMocks(this);
            // Arrange
            long employeeId = 1L;
            Employee employee = new Employee();
            employee.setId(employeeId);

            when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
            // Act
            employeeService.deleteSingleEmployeeRecordById(employeeId);

            // Assert
            verify(employeeRepository, times(1)).findById(employeeId);
            verify(employeeRepository, times(1)).delete(employee);
        }

}