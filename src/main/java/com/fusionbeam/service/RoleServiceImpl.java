package com.fusionbeam.service;

import com.fusionbeam.database.entity.Role;
import com.fusionbeam.database.repository.RoleRepository;
import com.fusionbeam.mvc.model.RoleDTO;
import com.fusionbeam.service.exceptions.RoleNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MikeChen
 * Date: 9/10/12
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Resource
    RoleRepository roleRepository;

    @Transactional
    @Override
    public Role create(RoleDTO dto) {
        Role role = constructRole();
        BeanUtils.copyProperties(dto, role);
        return roleRepository.save(role);

    }
    protected Role constructRole() {
        return new Role();
    }

    @Transactional(rollbackFor = RoleNotFoundException.class)
    @Override
    public Role update(RoleDTO dto) {
        Role role = roleRepository.findOne(dto.getId());
        if (role != null) {
            BeanUtils.copyProperties(dto, role);
        }
        return roleRepository.save(role);
    }

    @Transactional(rollbackFor = RoleNotFoundException.class)
    @Override
    public Role delete(Long id) {
        Role role = roleRepository.findOne(id);
        if (role != null) {
            roleRepository.delete(role);
        }
        return role;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Role findById(Long id) {
        return roleRepository.findOne(id);
    }
}
