package de.seuhd.campuscoffee.domain.impl;

import de.seuhd.campuscoffee.domain.ports.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import de.seuhd.campuscoffee.domain.model.User;
import de.seuhd.campuscoffee.domain.ports.UserDataService;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    // DONE: Implement user service
    private final UserDataService userDataService;
    @Override
    public void clear() {
        log.warn("Clearing all user data");
        userDataService.clear();
    }

    @Override
    public List<User> getAllUsers() {

        log.debug("Retrieving all users");
        return userDataService.getAll();
    }

    @Override
    public User getUserById(Long id) {
        log.debug("Retrieving user with ID: {}", id);
        return userDataService.getById(id);
    }
    
    @Override
    public User getUserByLoginName(String loginName) {
        log.debug("Retrieving user with login name: {}", loginName);
        return userDataService.getByLoginName(loginName);

    }

            
    @Override
    public void deleteUserById(Long id) {
        log.info("Deleting user with ID: {}", id);
        userDataService.delete(id);
    }
    @Override
    public User upsert(User user) {
        log.info("Upserting user: {}", user.loginName());
        return userDataService.upsert(user);

    }
}


