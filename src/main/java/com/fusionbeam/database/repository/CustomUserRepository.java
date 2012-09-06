package com.fusionbeam.database.repository;

import com.fusionbeam.database.entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenm
 * Date: 3/09/12
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CustomUserRepository {
    public List<User> findAllUsers();

    public long findUserCount(String lastName);

    public List<User> findUserForPage(String lastname, int page);
}
