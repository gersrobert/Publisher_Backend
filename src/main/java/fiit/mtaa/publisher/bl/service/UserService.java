package fiit.mtaa.publisher.bl.service;

import org.springframework.security.oauth2.jwt.Jwt;

public interface UserService {
    // /**
    //  * Create a new user
    //  * @param user dto containing user data
    //  * @return true is successful
    //  */
    void checkIfUserExists(Jwt accessToken);
}