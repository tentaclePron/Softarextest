package by.felix.softarextest.controllers.authorization;

import by.felix.softarextest.controllers.InOutModels.AuthResponse;
import by.felix.softarextest.controllers.InOutModels.AuthorizationRequest;
import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.User;

public interface Authorization {
    public String regUser(User user) throws APPException;

    public AuthResponse auth(AuthorizationRequest authorizationRequest) throws APPException;
}
