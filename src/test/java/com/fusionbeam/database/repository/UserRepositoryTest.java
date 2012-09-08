package com.fusionbeam.database.repository;

import com.fusionbeam.config.DatabaseContext;
import com.fusionbeam.database.entity.Role;
import com.fusionbeam.database.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

import static com.fusionbeam.database.repository.predicates.UserPredicates.lastNameIsLike;
import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: chenm
 * Date: 3/09/12
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes = DatabaseContext.class)
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;

    @Test
    public void testCustomMethodCall() {
        User user = new User();
        user.setUserName("mchen");
        user.setFirstName("Mike");
        user.setLastName("Chen");
        user.setPassword("1234");

        userRepository.save(user);

        List<User> users = userRepository.findAllUsers();
        assertEquals(1, users.size());
    }
    @Test
    public void testCreateUser() {
        User user = createUser();
        user.setUserName("mchena");
        assertNull(user.getId());
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());
    }

    private User createUser() {
        User user = new User();
        user.setFirstName("Mike");
        user.setLastName("Chen");
        user.setUserName("mchen");
        user.setPassword("12345");
        return user;
    }
    private Role createAdminRole() {
        Role role = new Role();
        role.setRoleName("Admin");
        return role;
    }
    private Role createUserRole() {
        Role role = new Role();
        role.setRoleName("User");
        return role;
    }
    private Role createSupportRole() {
        Role role = new Role();
        role.setRoleName("Support");
        return role;
    }

    @Test
    public void testFindUser() {
        User user = createUser();
        user.setUserName("mchenb");
        userRepository.save(user);
        User foundUser = userRepository.findOne(user.getId());
        assertEquals("Mike", foundUser.getFirstName());
    }

    @Test
    public void testUpdateUser() {
        User user = createUser();
        user.setUserName("mchenc");
        user = userRepository.save(user);
        user.setLastName("ChingChing");
        userRepository.save(user);
        User foundUser = userRepository.findOne(user.getId());
        assertEquals("ChingChing", foundUser.getLastName());
    }

    @Test
    public void testDeleteUser() {
        User user = createUser();
        user.setUserName("mchene");
        user = userRepository.save(user);
        userRepository.delete(user.getId());
        assertFalse(userRepository.exists(user.getId()));
    }

    @Test
    public void testAddAdminRole() {
        User user = createUser();
        user.setUserName("mchenf");
        Role admin = createAdminRole();
        user.getRoles().add(admin);
        userRepository.save(user);

        User foundUser = userRepository.findOne(user.getId());
        assertEquals(1, foundUser.getRoles().size());
        Set<Role> roles = foundUser.getRoles();
        Role foundRole = roles.iterator().next();
        assertEquals("Admin", foundRole.getRoleName());
    }

    @Test
    public void testFindByLastName() {
        User user = createUser();
        user.setUserName("mcheng");
        userRepository.save(user);

        List<User> foundUsers = userRepository.find("Chen");
        assertEquals(foundUsers.get(0).getFirstName(), user.getFirstName());
        assertEquals(foundUsers.get(0).getLastName(), "Chen");

    }

    @Test
    public void testFindAllWithSimilarLastNames() {
        User user = createUser();
        user.setUserName("mchenh");
        userRepository.save(user);

        Iterable<User> users = userRepository.findAll(lastNameIsLike("Ch"));
        assertEquals(users.iterator().next().getLastName(), "Chen");

    }

    @Test
    public void testPagination() {
        User user = createUser();
        user.setUserName("mcheni");
        user.setLastName("ABC");
        userRepository.save(user);

        List<User> users = userRepository.findUserForPage("ABC", 0);
        assertEquals(1, users.size());
    }
}
