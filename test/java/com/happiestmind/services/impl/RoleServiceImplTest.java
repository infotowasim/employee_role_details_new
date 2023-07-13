package com.happiestmind.services.impl;


import com.happiestmind.entities.Employee;
import com.happiestmind.entities.Role;
import com.happiestmind.payload.RoleDTO;
import com.happiestmind.repositories.EmployeeRepository;
import com.happiestmind.repositories.RoleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class RoleServiceImplTest {

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
        roleDTO.setRoleName("RoleName");
        roleDTO.setEmail("email@example.com");
        roleDTO.setBody("Role body");

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
    public void testGetRoleSingleRecordById() {

        long employeeId = 1L;
        long roleId = 1L;

        Employee employee = new Employee();
        employee.setId(employeeId);

        Role role = new Role();
        role.setId(roleId);
        role.setRoleName("RoleName");
        role.setEmail("email@example.com");
        role.setBody("Role body");

        role.setEmployee(employee);

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        roleDTO.setRoleName("RoleName");
        roleDTO.setEmail("email@example.com");
        roleDTO.setBody("Role body");

        Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        Mockito.when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));
        Mockito.when(modelMapper.map(role, RoleDTO.class)).thenReturn(roleDTO);

        RoleDTO result = roleService.getRoleSingleRecordById(employeeId, roleId);

        Assert.assertEquals(role.getId(), result.getId());
        Assert.assertEquals(role.getRoleName(), result.getRoleName());
        Assert.assertEquals(role.getEmail(), result.getEmail());
        Assert.assertEquals(role.getBody(), result.getBody());

        Mockito.verify(employeeRepository, Mockito.times(1)).findById(employeeId);
        Mockito.verify(roleRepository, Mockito.times(1)).findById(roleId);
        Mockito.verify(modelMapper, Mockito.times(1)).map(role, RoleDTO.class);
    }


    @Test
    public void testUpdateRoleViaEmployeeId() {
        long employeeId = 1L;
        long roleId = 1L;

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(roleId);
        roleDTO.setRoleName("NewRoleName");
        roleDTO.setEmail("newemail@example.com");
        roleDTO.setBody("New role body");

        Employee employee = new Employee();
        employee.setId(employeeId);

        Role role = new Role();
        role.setId(roleId);
        role.setRoleName("OldRoleName");
        role.setEmail("oldemail@example.com");
        role.setBody("Old role body");

        role.setEmployee(employee);


        Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        Mockito.when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));
        Mockito.when(modelMapper.map(roleDTO, Role.class)).thenReturn(role);
        Mockito.when(roleRepository.save(role)).thenReturn(role);
        Mockito.when(modelMapper.map(role, RoleDTO.class)).thenReturn(roleDTO);

        RoleDTO result = roleService.updateRoleViaEmployeeId(employeeId, roleId, roleDTO);

        Assert.assertEquals(roleDTO.getId(), result.getId());
        Assert.assertEquals(roleDTO.getRoleName(), result.getRoleName());
        Assert.assertEquals(roleDTO.getEmail(), result.getEmail());
        Assert.assertEquals(roleDTO.getBody(), result.getBody());

        Mockito.verify(employeeRepository, Mockito.times(1)).findById(employeeId);
        Mockito.verify(roleRepository, Mockito.times(1)).findById(roleId);
        Mockito.verify(modelMapper, Mockito.times(1)).map(roleDTO, Role.class);
        Mockito.verify(roleRepository, Mockito.times(1)).save(role);
        Mockito.verify(modelMapper, Mockito.times(1)).map(role, RoleDTO.class);

    }



    @Test

    public void testDeleteRoleViaEmployeeId() {

        long employeeId = 1L;
        long roleId = 1L;

        Employee employee = new Employee();
        employee.setId(employeeId);

        Role role = new Role();
        role.setId(roleId);
        role.setRoleName("RoleName");
        role.setEmail("email@example.com");
        role.setBody("Role body");

        role.setEmployee(employee);

        Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        Mockito.when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        roleService.deleteRoleViaEmployeeId(employeeId, roleId);

        Mockito.verify(employeeRepository, Mockito.times(1)).findById(employeeId);
        Mockito.verify(roleRepository, Mockito.times(1)).findById(roleId);
        Mockito.verify(roleRepository, Mockito.times(1)).delete(role);
    }
}