package fiit.mtaa.publisher.bl.impl;

import fiit.mtaa.publisher.bl.service.UserService;
import fiit.mtaa.publisher.dto.AppUserDTO;
import fiit.mtaa.publisher.dto.AppUserWithPasswordDTO;
import fiit.mtaa.publisher.entity.AppUser;
import fiit.mtaa.publisher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public boolean registerAppUser(AppUserWithPasswordDTO user) {
    	AppUser alreadyExists = userRepository.getFirstByUserName(user.getUserName());
    	if (alreadyExists != null) {
    	    return false;
        }

        AppUser newUser = new AppUser();
	    newUser.setFirstName(user.getFirstName());
	    newUser.setLastName(user.getLastName());
        newUser.setUserName(user.getUserName());
        newUser.setPasswordHash(user.getPasswordHash());

        userRepository.save(newUser);

        return true;
    }
}