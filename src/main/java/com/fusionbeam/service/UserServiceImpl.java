package com.fusionbeam.service;

import com.fusionbeam.database.entity.User;
import com.fusionbeam.database.repository.UserRepository;
import com.fusionbeam.mvc.model.UserDTO;
import com.fusionbeam.service.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Declare methods to use to obtain and modify person information
 * User: chenm
 * Date: 5/09/12
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    protected static final int NUMBER_OF_USERS_PER_PAGE = 5;

    @Resource
    private UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User create(UserDTO userDTO) {
        LOGGER.debug("Creating a new user with information: " + userDTO);
        User user = constructUser();
        BeanUtils.copyProperties(userDTO, user);
        return getUserRepository().save(user);
    }

    protected User constructUser() {
        return new User();
    }

    @Transactional(rollbackFor = UserNotFoundException.class)
    @Override
    public User delete(Long userId) throws UserNotFoundException {
        LOGGER.debug("Deleting user with id: " + userId);

        User deleted = userRepository.findOne(userId);

        if (deleted == null) {
            LOGGER.debug("No user found with id: " + userId);
            throw new UserNotFoundException();
        }

        userRepository.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        LOGGER.debug("Finding all users");
        return userRepository.findAllUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) {
        LOGGER.debug("Finding user by id: " + id);
        return userRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> search(String lastName, int pageIndex) {
        LOGGER.debug("Searching users with search term: " + lastName);
        return userRepository.findUserForPage(lastName, pageIndex);
    }

    @Transactional(rollbackFor = UserNotFoundException.class)
    @Override
    public User update(UserDTO updated) throws UserNotFoundException {
        LOGGER.debug("Updating user with information: " + updated);

        User user = userRepository.findOne(updated.getId());

        if (user == null) {
            LOGGER.debug("No user found with id: " + updated.getId());
            throw new UserNotFoundException();
        }
        BeanUtils.copyProperties(updated, user);
        return userRepository.save(user);
    }

}
