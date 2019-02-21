package org.softuni.sboj.repository;

import org.softuni.sboj.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {

    User findByUsername(String name);
}
