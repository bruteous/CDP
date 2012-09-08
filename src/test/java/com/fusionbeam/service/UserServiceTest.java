package com.fusionbeam.service;

import com.fusionbeam.config.DatabaseContext;
import com.fusionbeam.database.entity.User;
import com.fusionbeam.mvc.model.UserDTO;
import com.fusionbeam.service.exceptions.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * Created with IntelliJ IDEA.
 * User: MikeChen
 * Date: 9/7/12
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes = DatabaseContext.class)
public class UserServiceTest {
    @Resource
    private UserService userService;

    private static int idCount = 0;

    private UserDTO constructUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Mike");
        userDTO.setLastName("Chen");
        userDTO.setUserName("mchen");
        userDTO.setPassword("12345");
        return userDTO;
    }
    @Test
    public void testFindAllUsers() {
        UserDTO user1 = constructUser();
        user1.setUserName("mchen4");
        UserDTO user2 = constructUser();
        user2.setUserName("mchen5");

        userService.create(user1);
        userService.create(user2);

        List<User> foundUsers = userService.findAll();
        assertEquals(2, foundUsers.size());
    }
    @Test
    public void testCreateUser() {
        UserDTO userDTO = constructUser();
        userDTO.setUserName("mchen1");
        User user = userService.create(userDTO);
        assertNotNull(user.getId());
        assertEquals("Mike", user.getFirstName());
        assertEquals("Chen", user.getLastName());
        assertEquals("mchen1", user.getUserName());
        assertEquals("12345", user.getPassword());
    }

    @Test
    public void testFindUser() {
        UserDTO userDTO = constructUser();
        userDTO.setUserName("mchen2");
        User user = userService.create(userDTO);
        User foundUser = userService.findById(user.getId());

        assertEquals("Mike", foundUser.getFirstName());
        assertEquals("Chen", foundUser.getLastName());
        assertEquals("mchen2", foundUser.getUserName());
        assertEquals("12345", foundUser.getPassword());
    }
    @Test
    public void testDeleteUser() throws UserNotFoundException {
        UserDTO userDTO = constructUser();
        userDTO.setUserName("mchen3");
        User user = userService.create(userDTO);
        Long id = user.getId();
        userService.delete(user.getId());

        assertNull(userService.findById(id));
    }
    @Test
    public void testUpdateUser() throws UserNotFoundException {
        UserDTO user = constructUser();
        user.setUserName("mchen6");
        User savedUser = userService.create(user);
        user.setId(savedUser.getId());
        user.setFirstName("James");

        userService.update(user);

        User foundUser = userService.findById(user.getId());
        assertEquals("James", foundUser.getFirstName());
    }

}
