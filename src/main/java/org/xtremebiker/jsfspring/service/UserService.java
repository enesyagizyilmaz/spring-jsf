package org.xtremebiker.jsfspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xtremebiker.jsfspring.model.User;
import org.xtremebiker.jsfspring.repository.UserRepository;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;

    public void saveUser(User user)
    {
        userRepository.save(user);
    }
}
