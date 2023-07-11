package com.happiestmind.controller;

import com.happiestmind.payload.EmployeeDTO;
import com.happiestmind.services.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;


    @BeforeEach
    void setUp(){

    }

    @AfterEach
    void cleanUp(){

    }




    @Test
    void createEmployee_ReturnsCreatedEmployeeDTO() {
        // Arrange
        EmployeeDTO employeeDTO  = new EmployeeDTO();
        employeeDTO.setFastName("John");
        employeeDTO.setLastName("Doe");
        employeeDTO.setEmail("john@gmail.com");
        employeeDTO.setSalary(33333);
        employeeDTO.setAge(25);

//

        EmployeeDTO expectedEmployeeDTO = new EmployeeDTO();
        expectedEmployeeDTO.setId(1L);
        expectedEmployeeDTO.setFastName("John");
        expectedEmployeeDTO.setLastName("Doe");
        expectedEmployeeDTO.setEmail("john@gmail.com");
        expectedEmployeeDTO.setSalary(33333);
        expectedEmployeeDTO.setAge(25);

//

        when(employeeService.createEmployee(employeeDTO)).thenReturn(expectedEmployeeDTO);

       // Act
        ResponseEntity<EmployeeDTO> response = employeeController.createEmployee(employeeDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedEmployeeDTO, response.getBody());
        verify(employeeService, times(1)).createEmployee(employeeDTO);
    }




    @Test
    void getAllEmployeeRecords_ReturnsListOfEmployeeDTOs() {

        // Arrange
        List<EmployeeDTO> expectedEmployeeDTOList = new ArrayList<>();


        // Create and add expected EmployeeDTO objects to the list
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setId(1L);
        employeeDTO1.setFastName("John");
        employeeDTO1.setLastName("Doe");
        employeeDTO1.setEmail("john@gmail.com");
        employeeDTO1.setSalary(33333);
        employeeDTO1.setAge(25);

        // Set other employeeDTO1 properties
        expectedEmployeeDTOList.add(employeeDTO1);

        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        employeeDTO2.setId(2L);
        employeeDTO2.setFastName("Jane");
        employeeDTO2.setLastName("Smith");

        // Set other employeeDTO2 properties
        expectedEmployeeDTOList.add(employeeDTO2);

        // Mock the employeeService to return the expectedEmployeeDTOList
        when(employeeService.getAllEmployeeRecords()).thenReturn(expectedEmployeeDTOList);

        // Act
        ResponseEntity<List<EmployeeDTO>> response = employeeController.getAllEmployeeRecords();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedEmployeeDTOList, response.getBody());
        verify(employeeService, times(1)).getAllEmployeeRecords();

    }


    @Test
    void getEmployeeSingleRecordById_ReturnsEmployeeDTO() {

        // Arrange
        long employeeId = 1L;
        EmployeeDTO expectedEmployeeDTO = new EmployeeDTO();
        expectedEmployeeDTO.setId(employeeId);
        expectedEmployeeDTO.setFastName("John");
        expectedEmployeeDTO.setLastName("Doe");

        // Set other expectedEmployeeDTO properties
        when(employeeService.getEmployeeSingleRecordById(employeeId)).thenReturn(expectedEmployeeDTO);

        // Act
        ResponseEntity<EmployeeDTO> response = employeeController.getEmployeeSingleRecordById(employeeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedEmployeeDTO, response.getBody());
        verify(employeeService, times(1)).getEmployeeSingleRecordById(employeeId);

    }


    @Test
    void updateEmployeeRecordById_ReturnsUpdatedEmployeeDTO() {

        // Arrange
        long employeeId = 2L;
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFastName("John");
        employeeDTO.setLastName("Doe");
        employeeDTO.setEmail("john@gmail.com");
        employeeDTO.setSalary(33333);
        employeeDTO.setAge(25);

        // Set other employeeDTO properties

        EmployeeDTO expectedEmployeeDTO = new EmployeeDTO();
        expectedEmployeeDTO.setId(1L);
        expectedEmployeeDTO.setFastName("John");
        expectedEmployeeDTO.setLastName("Doe");
        expectedEmployeeDTO.setEmail("john@gmail.com");
        expectedEmployeeDTO.setSalary(33333);
        expectedEmployeeDTO.setAge(25);

        // Set other expectedEmployeeDTO properties

        when(employeeService.updateEmployeeRecordById(employeeId, employeeDTO)).thenReturn(expectedEmployeeDTO);

        // Act
        ResponseEntity<EmployeeDTO> response = employeeController.updateEmployeeRecordById(employeeId, employeeDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedEmployeeDTO, response.getBody());
        verify(employeeService, times(1)).updateEmployeeRecordById(employeeId, employeeDTO);

    }


    @Test
    void deleteSingleEmployeeRecordById_ReturnsSuccessMessage() {

        // Arrange
        long employeeId = 9L;

        // Act
        ResponseEntity<String> response = employeeController.deleteSingleEmployeeRecordById(employeeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("From Employee Entity single record successfully deleted", response.getBody());
        verify(employeeService, times(1)).deleteSingleEmployeeRecordById(employeeId);

    }
}