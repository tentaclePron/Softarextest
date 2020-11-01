package by.felix.softarextest.services.auth.impl;

import by.felix.softarextest.config.jwt.JwtProvider;
import by.felix.softarextest.controllers.InOutModels.AuthResponse;
import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.User;
import by.felix.softarextest.repository.UserRepository;
import by.felix.softarextest.services.auth.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * JWT implementation
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private UserRepository userRepository;

    private JwtProvider jwtProvider;

    private PasswordEncoder encoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public AuthResponse auth(String username, String password) throws APPException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new APPException("User not found %s", username));
        if (!encoder.matches(password, user.getPassword()))
            throw new APPException("Wrong password");

        String token = jwtProvider.generateToken(user.getUsername());

        return new AuthResponse(token);
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
    public void deleteUser(long userId){
        userRepository.deleteById(userId);
    }


}
