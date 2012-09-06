package com.fusionbeam.database.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: chenm
 * Date: 28/08/12
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="role")
public class Role extends AuditableImpl {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="role_name", nullable = false)
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
