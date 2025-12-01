package de.seuhd.campuscoffee.domain.ports;

import java.util.List;

import org.jspecify.annotations.NonNull;
import de.seuhd.campuscoffee.domain.model.User;
public interface UserService {
    //DONE: Define user service interface
        void clear();

        @NonNull List<User> getAllUsers();

        @NonNull User getUserById(@NonNull Long id);

        @NonNull User getUserByLoginName(@NonNull String loginName);

        @NonNull User upsert(@NonNull User user);

        void deleteUserById(@NonNull Long id);



    
}
