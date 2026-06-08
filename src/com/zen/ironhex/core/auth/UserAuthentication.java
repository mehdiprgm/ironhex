package com.zen.ironhex.core.auth;

import com.zen.ironhex.domain.entity.User;
import com.zen.ironhex.domain.service.UserService;
import com.zen.ironhex.shared.Result;
import com.zen.lib.system.software.OS;

import java.util.Optional;

import static com.zen.ironhex.shared.Variables.*;

public class UserAuthentication {
    public Result login(String username, String password) {
        try {
            UserService service = new UserService();
            Optional<User> userOptional = service.select(username, password);

            if (userOptional.isEmpty()) {
                return new Result(false, "no user found in the database");
            }

            User user = userOptional.get();
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                vars.put("username", username);
                vars.put("user_id", String.valueOf(user.getId()));

                vars.put("password", password);
                vars.put("salt", user.getSalt());

                return new Result(true, "Login was successful");
            }

            return new Result(false, "Password is not correct");
        } catch (Exception ex) {
            return new Result(false, ex.getMessage());
        }
    }

    public Result register(String username, String password) {
        try {
            UserService service = new UserService();
            if (service.exists(username)) {
                return new Result(false, "User already exists, select another name");
            }

            User newUser = new User(username, password, null, null,
                    OS.getTimeDate(OS.SystemTimeDate.DATE)
            );

            return service.insert(newUser);
        } catch (Exception ex) {
            return new Result(false, ex.getMessage());
        }
    }
}
