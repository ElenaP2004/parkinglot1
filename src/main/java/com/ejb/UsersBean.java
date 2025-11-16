package com.ejb;
import com.common.UsersDto;
import entities.Users;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UsersBean {
    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public List<UsersDto> findAllUsers() {
        LOG.info("findAllUsers");
        try {
            TypedQuery<Users> typedQuery = entityManager.createQuery("SELECT u FROM Users u", Users.class);
            List<Users> users = typedQuery.getResultList();
            return copyUsersToDto(users);
        } catch (Exception ex) {
            LOG.severe(ex.getMessage());
            return new ArrayList<>();
        }
    }

    private List<UsersDto> copyUsersToDto(List<Users> users) {
        List<UsersDto> usersDtos = new ArrayList<>();
        for (Users user : users) {
            UsersDto usersDto = new UsersDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail()
            );
            usersDtos.add(usersDto);
        }
        return usersDtos;
}
}

