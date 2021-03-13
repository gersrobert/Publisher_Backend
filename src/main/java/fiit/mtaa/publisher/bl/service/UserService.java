package fiit.mtaa.publisher.bl.service;

import fiit.mtaa.publisher.dto.AppUserDTO;
import fiit.mtaa.publisher.dto.AppUserWithPasswordDTO;

import java.util.Collection;
import java.util.UUID;

public interface UserService {
    /**
     * Create a new user
     * @param user dto containing user data
     * @return true is successful
     */
    boolean registerAppUser(AppUserWithPasswordDTO user);
}