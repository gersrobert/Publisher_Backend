package fiit.mtaa.publisher.bl.service;

import fiit.mtaa.publisher.entity.AppUser;
import org.springframework.security.oauth2.jwt.Jwt;

public interface UserService {

    AppUser checkIfUserExists(String accessToken);
    AppUser checkIfUserExists(Jwt accessToken);
}