package by.felix.softarextest.controllers.authorization;

import by.felix.softarextest.controllers.InOutModels.AuthResponse;
import by.felix.softarextest.controllers.InOutModels.AuthorizationRequest;
import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.User;
import by.felix.softarextest.services.auth.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController implements Authorization {

    private AuthorizationService authorizationService;

    @Autowired
    public void setAuthorizationService(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    /**
     * simple registration no validations
     *
     * @param user user entity
     * @return ok on success
     */
    @Override
    @PostMapping("/register")
    public String regUser(@RequestBody User user) throws APPException {
        authorizationService.regUser(user);
        return "OK";
    }

    /**
     * login password authorization
     *
     * @param authorizationRequest
     * @return jwt token
     * @throws APPException
     */
    @Override
    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthorizationRequest authorizationRequest) throws APPException {
        return authorizationService.auth(authorizationRequest.getUsername(), authorizationRequest.getPassword());
    }
}
