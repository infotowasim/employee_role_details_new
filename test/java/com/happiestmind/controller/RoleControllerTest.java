package com.happiestmind.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.happiestmind.payload.RoleDTO;
import com.happiestmind.services.RoleService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@WebMvcTest(RoleController.class)
class RoleControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;

    @Test
    public void testCreateRoleWithEmployeeId() throws Exception {

        long employeeId = 1L;

        // Create a RoleDTO object
        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId(1L);
        roleDTO.setRoleName("RoleName");
        roleDTO.setEmail("email@example.com");
        roleDTO.setBody("Role body");


        // Mock the behavior of the RoleService.createRoleWithEmployeeId() method
        Mockito.when(roleService.createRoleWithEmployeeId(Mockito.eq(employeeId), Mockito.any(RoleDTO.class)))
                .thenReturn(roleDTO);


        // Perform the POST request to create a role
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employees/{employeeId}/roles", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(roleDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roleName").value("RoleName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("Role body"));
    }



    @Test
    public void testGetRoleSingleRecordById() throws Exception {

        long employeeId = 1L;
        long roleId = 1L;

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(roleId);
        roleDTO.setRoleName("RoleName");
        roleDTO.setEmail("email@example.com");
        roleDTO.setBody("Role body");

        Mockito.when(roleService.getRoleSingleRecordById(Mockito.eq(employeeId), Mockito.eq(roleId)))
                .thenReturn(roleDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{employeeId}/roles/{roleId}", employeeId, roleId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roleName", Matchers.is("RoleName")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("email@example.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body", Matchers.is("Role body")));

    }


    @Test
    public void testUpdateRoleViaEmployeeId() throws Exception {

        long employeeId = 1L;
        long roleId = 1L;

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(roleId);
        roleDTO.setRoleName("NewRoleName");
        roleDTO.setEmail("newemail@example.com");
        roleDTO.setBody("New role body");

        Mockito.when(roleService.updateRoleViaEmployeeId(Mockito.eq(employeeId), Mockito.eq(roleId), Mockito.any(RoleDTO.class)))
                .thenReturn(roleDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{employeeId}/roles/{roleId}", employeeId, roleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(roleDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roleName", Matchers.is("NewRoleName")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("newemail@example.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body", Matchers.is("New role body")));

    }

    @Test
    public void testDeleteRoleViaEmployeeId() throws Exception {

        long employeeId = 1L;
        long roleId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/{employeeId}/roles/{roleId}", employeeId, roleId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Roles Deleted Successfully"));

        Mockito.verify(roleService, Mockito.times(1)).deleteRoleViaEmployeeId(Mockito.eq(employeeId), Mockito.eq(roleId));
    }
}