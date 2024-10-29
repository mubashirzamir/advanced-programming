package com.workshop_05.users_mysql.repository;

import com.workshop_05.users_mysql.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
