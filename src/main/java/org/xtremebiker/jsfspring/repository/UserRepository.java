package org.xtremebiker.jsfspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xtremebiker.jsfspring.model.User;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String username);


}
