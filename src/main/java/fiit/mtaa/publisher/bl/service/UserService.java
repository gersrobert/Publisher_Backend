package fiit.mtaa.publisher.bl.service;

import fiit.mtaa.publisher.dto.AppUserDTO;
import fiit.mtaa.publisher.entity.AppUser;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public interface UserService {

    AppUser checkIfUserExists(String accessToken);

    AppUser checkIfUserExists(Jwt accessToken);

    AppUserDTO getUser(UUID uuid);

    AppUserDTO getUser(String accessToken);

    void updatePhoto(AppUser user, String photo);
}