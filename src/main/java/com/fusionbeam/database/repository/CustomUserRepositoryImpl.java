package com.fusionbeam.database.repository;

import com.fusionbeam.database.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.fusionbeam.database.repository.predicates.UserPredicates.lastNameIsLike;

/**
 * Created with IntelliJ IDEA.
 * User: chenm
 * Date: 3/09/12
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserRepositoryImpl.class);

    protected static final int NUMBER_OF_USERS_PER_PAGE = 5;

    @PersistenceContext
    private EntityManager entityManager;

    private QueryDslJpaRepository<User, Long> userRepository;

    @Override
    public List<User> findAllUsers() {
        LOGGER.debug("Finding all persons");

        //Passes the Sort object to the repository
        return userRepository.findAll(sortByLastNameAsc());
    }

    @Override
    public long findUserCount(String lastName) {
        LOGGER.debug("Finding user count with lastname " + lastName);
        return userRepository.count(lastNameIsLike(lastName));
    }

    @Override
    public List<User> findUserForPage(String lastname, int page) {
        LOGGER.debug("Finding users for page " + page + " with last name " + lastname);
        Page requestPage = userRepository.findAll(lastNameIsLike(lastname), constructPageSpecification(page));
        return requestPage.getContent();
    }

    /**
     * Returns a new object which specifies the the wanted result page.
     * @param pageIndex The index of the wanted result page
     * @return
     */
    private Pageable constructPageSpecification(int pageIndex) {
        Pageable pageSpecification = new PageRequest(pageIndex, NUMBER_OF_USERS_PER_PAGE, sortByLastNameAsc());
        return pageSpecification;
    }
    /**
     * Returns a Sort object which sorts persons in ascending order by using the last name.
     * @return
     */
    private Sort sortByLastNameAsc() {
        return new Sort(Sort.Direction.ASC, "lastName");
    }
    /**
     * An initialization method which is run after the bean has been constructed.
     * This ensures that the entity manager is injected before we try to use it.
     */
    @PostConstruct
    public void init() {
        JpaEntityInformation<User, Long> personEntityInfo = new JpaMetamodelEntityInformation<User, Long>(User.class, entityManager.getMetamodel());
        userRepository = new QueryDslJpaRepository<User, Long>(personEntityInfo, entityManager);
    }

    /**
     * This setter method should be used only by unit tests
     * @param personRepository
     */
    protected void setPersonRepository(QueryDslJpaRepository<User, Long> personRepository) {
        this.userRepository = personRepository;
    }
}
