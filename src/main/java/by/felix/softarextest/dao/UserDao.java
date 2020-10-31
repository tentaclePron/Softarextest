package by.felix.softarextest.dao;

import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.User;

import java.util.List;

public interface UserDao {

    void regUser(User user) throws APPException;
    void deleteUser(long id);
    User getByUsername(String username) throws APPException;
    User updateUser(User user);
    List<User> getAllUsers();

}
