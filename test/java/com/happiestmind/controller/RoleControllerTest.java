package com.happiestmind.controller;

import com.happiestmind.entities.Employee;
import com.happiestmind.payload.EmployeeDTO;
import com.happiestmind.payload.RoleDTO;
import com.happiestmind.services.RoleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RoleControllerTest {

    @InjectMocks
    private RoleController roleController;

    @Mock
    RoleService roleService;



    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createRoleWithEmployeeId() {

    }

    @Test
    void getRoleSingleRecordById() {
    }

    @Test
    void updateRoleViaEmployeeId() {
    }

    @Test
    void deleteRoleViaEmployeeId() {
    }
}