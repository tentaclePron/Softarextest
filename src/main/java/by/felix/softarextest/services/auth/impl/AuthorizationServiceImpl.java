package by.felix.softarextest.services.auth.impl;

import by.felix.softarextest.config.jwt.JwtProvider;
import by.felix.softarextest.controllers.InOutModels.AuthResponse;
import by.felix.softarextest.dao.UserDao;
import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.User;
import by.felix.softarextest.services.auth.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private UserDao userDao;

    private JwtProvider jwtProvider;

    private PasswordEncoder encoder;

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }
    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Autowired
    public void setUserCrud(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public AuthResponse auth(String username, String password) throws APPException {
        User user = userDao.getByUsername(username);
        if (!encoder.matches(password, user.getPassword()))
            throw new APPException("Wrong password");

        String token = jwtProvider.generateToken(user.getUsername());

        return new AuthResponse(token);
    }

    @Override
    public void regUser(User user) throws APPException {
        userDao.regUser(user);
    }
}
