package com.happiestmind.services.impl;

import com.happiestmind.entities.Employee;
import com.happiestmind.entities.Role;
import com.happiestmind.exception.EmployeeRoleAPIException;
import com.happiestmind.exception.ResourceNotFoundException;
import com.happiestmind.payload.RoleDTO;
import com.happiestmind.repositories.EmployeeRepository;
import com.happiestmind.repositories.RoleRepository;
import com.happiestmind.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;


    // DTO to Entity
    private Role mapToEntity(RoleDTO roleDTO){
        Role role = modelMapper.map(roleDTO, Role.class);
        return role;
    }


    // Entity to DTO
    private RoleDTO mapToDTO(Role role){
        RoleDTO roleDTO = modelMapper.map(role, RoleDTO.class);
        return roleDTO;
    }




    // Creating Role record with the Employee Id
    @Override
    public RoleDTO createRoleWithEmployeeId(long employeeId, RoleDTO roleDTO) {

        // Retrieve Employee entity by id
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("employee","id",employeeId));

        // DTO to Entity
        Role role = mapToEntity(roleDTO); // Convert Dto to Entity.
        role.setEmployee(employee); // employee save the role.
        Role newRole = roleRepository.save(role); // save into the Database.

        // Entity to DTO
        RoleDTO roleDTO1 = mapToDTO(newRole);
        return roleDTO1;
    }


    // Get Role Single Record by Id
    @Override
    public RoleDTO getRoleSingleRecordById(long employeeId, long roleId) {

        // Retrieve Employee entity by id
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("employee", "id", employeeId));

        // Retrieve Role entity by id
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("role", "id", roleId));

        // Handling EmployeeRoleAPIException when Roles does not belongs to Employee
       if(role.getEmployee().getId()!= employee.getId()){
           throw new EmployeeRoleAPIException(HttpStatus.BAD_REQUEST, "Roles does not belongs to Employee");
       }

        RoleDTO roleDTO = mapToDTO(role);

        return roleDTO;
    }



    // Update Role Via Employee Id
    @Override
    public RoleDTO updateRoleViaEmployeeId(long employeeId, long roleId, RoleDTO roleDTO) {

        // Retrieve Employee entity by id
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("employee", "id", employeeId));

        // Retrieve Role entity by id
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("role", "id", roleId));


        // Handling EmployeeRoleAPIException when Employee does not matching with Role
        if(role.getEmployee().getId()!= employee.getId()){
            throw new EmployeeRoleAPIException(HttpStatus.BAD_REQUEST, "Employee does not matching with Role");
        }

        // DTO to Entity
        role.setId(roleId);
        role.setRoleName(roleDTO.getRoleName());
        role.setEmail(roleDTO.getEmail());
        role.setBody(roleDTO.getBody());

        // Saving the Entity into the Database.
        Role newRole1 = roleRepository.save(role);

        // Entity to DTO
        RoleDTO roleDTO1 = mapToDTO(newRole1);

        return roleDTO1;
    }


    // Delete Role Via Employee Id
    @Override
    public void deleteRoleViaEmployeeId(long employeeId, long roleId) {

        // Retrieve Employee Entity by id
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("employee", "id", employeeId));

        //Retrieve Role Entity by id
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("role", "id", roleId));

        // Handling EmployeeRoleAPIException when Employee does not matching with Role
        if(role.getEmployee().getId()!= employee.getId()){
            throw new EmployeeRoleAPIException(HttpStatus.BAD_REQUEST, "Employee does not matching with Role");
        }

        roleRepository.delete(role);
    }

}
