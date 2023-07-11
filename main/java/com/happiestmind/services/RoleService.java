package com.happiestmind.services;

import com.happiestmind.payload.RoleDTO;

public interface RoleService {

    // Creating Role record with Employee Id
    RoleDTO createRoleWithEmployeeId(long employeeId, RoleDTO roleDTO);

    // Get Role Single Record by Id
    RoleDTO getRoleSingleRecordById(long employeeId, long roleId);

    // Update Role Via Employee Id
    RoleDTO updateRoleViaEmployeeId(long employeeId, long roleId, RoleDTO roleDTO);

    // Delete Role Via Employee Id
    void deleteRoleViaEmployeeId(long employeeId, long roleId);
}
