package com.fusionbeam.service;

import com.fusionbeam.database.entity.User;
import com.fusionbeam.mvc.model.UserDTO;
import com.fusionbeam.service.exceptions.UserNotFoundException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenm
 * Date: 5/09/12
 * Time: 3:54 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService extends GenericService {
    /**
     * Creates a new User.
     * @param userDTO   The information of the created user.
     * @return  The created person.
     */
    public User create(UserDTO userDTO);


    /**
     * Deletes a user.
     * @param userId  The id of the deleted user.
     * @return  The deleted person.
     * @throws UserNotFoundException  if no person is found with the given id.
     */
    public User delete(Long userId) throws UserNotFoundException;

    /**
     * Finds all users.
     * @return  A list of users.
     */
    public List<User> findAll();

    /**
     * Finds usser by id.
     * @param id    The id of the wanted user.
     * @return  The found user. If no user is found, this method returns null.
     */
    public User findById(Long id);

    /**
     * Searches users for a given page by using the given search term.
     * @param lastName
     * @param pageIndex
     * @return  A list of users whose last name begins with the given search term and who are belonging to the given page.
     *          If no persons is found, this method returns an empty list. This search is case insensitive.
     */
    public List<User> search(String lastName, int pageIndex);

    /**
     * Updates the information of a person.
     * @param updated   The information of the updated person.
     * @return  The updated person.
     * @throws UserNotFoundException  if no person is found with given id.
     */
    public User update(UserDTO updated) throws UserNotFoundException;

}
