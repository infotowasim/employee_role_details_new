package com.happiestmind.services.impl;

import com.happiestmind.entities.Employee;
import com.happiestmind.entities.Role;
import com.happiestmind.payload.RoleDTO;
import com.happiestmind.repositories.EmployeeRepository;
import com.happiestmind.repositories.RoleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Optional;



@RunWith(MockitoJUnitRunner.class)
 class RoleServiceImplTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testCreateRoleWithEmployeeId() {
        long employeeId = 1L;

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        roleDTO.setRoleName("Engineer");
        roleDTO.setEmail("abc@example.com");
        roleDTO.setBody("SoftWare Engineer");


        Employee employee = new Employee();
        employee.setId(employeeId);


        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setRoleName(roleDTO.getRoleName());
        role.setEmail(roleDTO.getEmail());
        role.setBody(roleDTO.getBody());

        role.setEmployee(employee);


        Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        Mockito.when(modelMapper.map(roleDTO, Role.class)).thenReturn(role);
        Mockito.when(roleRepository.save(role)).thenReturn(role);
        Mockito.when(modelMapper.map(role, RoleDTO.class)).thenReturn(roleDTO);

        RoleDTO result = roleService.createRoleWithEmployeeId(employeeId, roleDTO);

        Assert.assertEquals(roleDTO.getId(), result.getId());
        Assert.assertEquals(roleDTO.getRoleName(), result.getRoleName());
        Assert.assertEquals(roleDTO.getEmail(), result.getEmail());
        Assert.assertEquals(roleDTO.getBody(), result.getBody());


        Mockito.verify(employeeRepository, Mockito.times(1)).findById(employeeId);
        Mockito.verify(modelMapper, Mockito.times(1)).map(roleDTO, Role.class);
        Mockito.verify(roleRepository, Mockito.times(1)).save(role);
        Mockito.verify(modelMapper, Mockito.times(1)).map(role, RoleDTO.class);

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