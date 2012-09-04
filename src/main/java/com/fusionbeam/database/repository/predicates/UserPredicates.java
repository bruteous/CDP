package com.fusionbeam.database.repository.predicates;

import com.fusionbeam.database.entity.QUser;
import com.mysema.query.types.Predicate;

/**
 * Created with IntelliJ IDEA.
 * User: chenm
 * Date: 3/09/12
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserPredicates {
    public static Predicate lastNameIsLike(final String searchTerm) {
        QUser user = QUser.user;
        return user.lastName.startsWithIgnoreCase(searchTerm);
    }
}
