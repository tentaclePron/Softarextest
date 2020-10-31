package by.felix.softarextest.services.auth;

import by.felix.softarextest.controllers.InOutModels.AuthResponse;
import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.User;

public interface AuthorizationService {

    AuthResponse auth(String username, String password) throws APPException;

    void regUser(User user) throws APPException;
}
