package com.fusionbeam.database.repository;

import com.fusionbeam.database.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: MikeChen
 * Date: 9/10/12
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
