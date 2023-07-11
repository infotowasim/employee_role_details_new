package com.happiestmind.controller;

import com.happiestmind.payload.RoleDTO;
import com.happiestmind.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class RoleController {

    @Autowired
    private RoleService roleService;


    // Creating Role record with Employee Id
    // POST:- http://localhost:8080/api/employees/1/roles
    // POST:- http://localhost:8080/api/employees/2/roles
    @PostMapping("/{employeeId}/roles")
    public ResponseEntity<RoleDTO> createRoleWithEmployeeId(@PathVariable("employeeId") long employeeId, @RequestBody RoleDTO roleDTO){
        RoleDTO roleDTO1 = roleService.createRoleWithEmployeeId(employeeId, roleDTO);
        return new ResponseEntity<>(roleDTO1, HttpStatus.CREATED);
    }


    // Get Role Single Record by Id
    // GET:- http://localhost:8080/api/employees/1/roles/1
    @GetMapping("/{employeeId}/roles/{roleId}")
    public ResponseEntity<RoleDTO> getRoleSingleRecordById(@PathVariable("employeeId") long employeeId, @PathVariable("roleId") long roleId){
        RoleDTO roleDTO = roleService.getRoleSingleRecordById(employeeId, roleId);
        return ResponseEntity.ok(roleDTO);
    }


    // Update Role Via Employee Id
    // PUT:- http://localhost:8080/api/employees/1/roles/1
    @PutMapping("/{employeeId}/roles/{roleId}")
    public ResponseEntity<RoleDTO> updateRoleViaEmployeeId(@PathVariable("employeeId") long employeeId, @PathVariable("roleId") long roleId, @RequestBody RoleDTO roleDTO){
        RoleDTO roleDTO1 = roleService.updateRoleViaEmployeeId(employeeId, roleId, roleDTO);
        return new ResponseEntity<>(roleDTO1,HttpStatus.CREATED);
    }


    // Delete Role Via Employee Id
    // DELETE:- http://localhost:8080/api/employees/3/roles/5
    @DeleteMapping("/{employeeId}/roles/{roleId}")
    public ResponseEntity<String> deleteRoleViaEmployeeId(@PathVariable("employeeId") long employeeId, @PathVariable("roleId") long roleId){
        roleService.deleteRoleViaEmployeeId(employeeId,roleId);
        return new ResponseEntity<>( "Roles Deleted Successfully", HttpStatus.OK);
    }
}
