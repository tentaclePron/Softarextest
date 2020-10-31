package by.felix.softarextest.crud;

import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.User;

import java.util.List;

public interface UserCrud {

    User regUser(User user);
    void deleteUser(long id);
    User getByUsername(String username) throws APPException;
    User updateUser(User user);
    List<User> getAllUsers();

}
