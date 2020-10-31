package by.felix.softarextest.controllers.authorization;

import by.felix.softarextest.config.jwt.JwtProvider;
import by.felix.softarextest.controllers.InOutModels.AuthResponse;
import by.felix.softarextest.controllers.InOutModels.AuthorizationRequest;
import by.felix.softarextest.crud.UserCrud;
import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController implements Authorization {

    private UserCrud userCrud;

    private JwtProvider jwtProvider;

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Autowired
    public void setUserCrud(UserCrud userCrud) {
        this.userCrud = userCrud;
    }

    /**
     * simple registration no validations
     *
     * @param user user entity
     * @return ok on success
     */
    @Override
    @PostMapping("/register")
    public String regUser(@RequestBody User user) {
        userCrud.regUser(user);
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
        User user = userCrud.getByUsername(authorizationRequest.getUsername());
        if (!user.getPassword().equals(authorizationRequest.getPassword())) throw new APPException("Wrong password");
        String token = jwtProvider.generateToken(user.getUsername());

        return new AuthResponse(token);
    }
}
