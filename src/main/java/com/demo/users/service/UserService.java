package com.demo.users.service;

import com.demo.users.exception.ResourceNotFoundException;
import com.demo.users.model.User;
import com.demo.users.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer for user management operations.
 */
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Returns all users in the system.
     *
     * @return list of users, never null
     */
    @Transactional(readOnly = true)
    public List<User> findAll() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    /**
     * Finds a user by id.
     *
     * @param id user identifier
     * @return the user
     * @throws ResourceNotFoundException when no user exists for the id
     */
    @Transactional(readOnly = true)
    public User findById(Long id) {
        log.debug("Looking up user {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
    }

    /**
     * Creates a new user.
     *
     * @param user user to persist
     * @return saved user with generated id
     */
    @Transactional
    public User create(User user) {
        log.info("Creating user with email {}", user.getEmail());
        return userRepository.save(user);
    }
}
