package project.service;

import project.entity.User;

public interface UserService {

    User register(User user);

    User getByLogin(String login);

    User getCurrentUser();

    Long getCurrentUserId();

}
