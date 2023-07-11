package com.happiestmind.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class RoleDTO {

    private long id;
    private String roleName;
    private String email;
    private String body;

}
