package by.felix.softarextest.dao.impl;

import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.User;
import by.felix.softarextest.repository.UserRepository;
import by.felix.softarextest.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDaoImpl implements UserDao {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void regUser(User user) throws APPException {
        user.setPassword(encoder.encode(user.getPassword()));
        try {
            userRepository.saveAndFlush(user);
        } catch (Exception ex) {
            throw new APPException("This username is taken");
        }
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getByUsername(String username) throws APPException {
        return userRepository.findByUsername(username).orElseThrow(() -> new APPException("User not found %s", username));
    }

    @Override
    public User updateUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
