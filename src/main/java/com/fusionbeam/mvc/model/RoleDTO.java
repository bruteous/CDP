package com.fusionbeam.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created with IntelliJ IDEA.
 * User: MikeChen
 * Date: 9/6/12
 * Time: 4:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoleDTO{
    private Long id;

    @NotEmpty
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
