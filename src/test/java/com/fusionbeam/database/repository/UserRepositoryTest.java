package com.fusionbeam.database.repository;

import com.fusionbeam.database.config.DatabaseContext;
import com.fusionbeam.database.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.util.List;

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
        user.setLastName("Chen");

        userRepository.save(user);

        List<User> users = userRepository.findAllUsers();
        assertEquals(1, users.size());


    }
}
