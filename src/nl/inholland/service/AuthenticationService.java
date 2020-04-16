package nl.inholland.service;

import nl.inholland.model.User;

import java.util.List;

public class AuthenticationService {
    private final UserService userService;
    private User currentUser;

    public AuthenticationService() {
        userService = new UserService();
    }

    public Boolean login(String username, String password) {
        this.currentUser = null;

        List<User> users = userService.getAllUsers();

        try {
            for (User user : users) {
                if (username.equals(user.getEmail())) {
                    if (password.equals(user.getPassword())) {
                        this.currentUser = user;
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public UserService getUserService(){
        return this.userService;
    }
}
