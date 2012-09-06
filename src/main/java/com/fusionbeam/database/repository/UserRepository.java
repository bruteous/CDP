package com.fusionbeam.database.repository;

import com.fusionbeam.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenm
 * Date: 3/09/12
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository, QueryDslPredicateExecutor<User> {

    /**
     * Finds a user by using the last name as a search criteria.
     * @param lastName
     * @return  A list of users whose last name is an exact match with the given last name.
     *          If no persons is found, this method returns an empty list.
     */
    @Query("SELECT u FROM User u WHERE LOWER(u.lastName) = LOWER(:lastName)")
    public List<User> find(@Param("lastName") String lastName);
}
