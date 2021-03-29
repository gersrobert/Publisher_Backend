package fiit.mtaa.publisher.bl.impl;

import fiit.mtaa.publisher.bl.service.UserService;
import fiit.mtaa.publisher.dto.AppUserDTO;
import fiit.mtaa.publisher.dto.UserinfoDTO;
import fiit.mtaa.publisher.entity.AppUser;
import fiit.mtaa.publisher.repository.UserRepository;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Base64;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtDecoder jwtDecoder;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private RestTemplate restTemplate;

    @Autowired
    public void RestTemplateBuilder(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public AppUser checkIfUserExists(String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) {
            return null;
        }

        Jwt token;
        if (accessToken.startsWith("Bearer")) {
            token = jwtDecoder.decode(accessToken.split(" ")[1]);
        } else {
            token = jwtDecoder.decode(accessToken);
        }

        return checkIfUserExists(token);
    }

    @Override
    public AppUser checkIfUserExists(Jwt accessToken) {
        AppUser alreadyExists = userRepository.getFirstBySubject(accessToken.getSubject());
        if (alreadyExists == null) {
            alreadyExists = createUser(accessToken);
        }
        return alreadyExists;
    }

    @Transactional
    protected AppUser createUser(Jwt accessToken) {
        String url = "https://a3cle-publisher.eu.auth0.com/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken.getTokenValue());

        HttpEntity<HttpHeaders> request = new HttpEntity(headers);

        ResponseEntity<UserinfoDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                UserinfoDTO.class,
                1
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful.");
            System.out.println(response.getBody().getFamilyName());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }

        UserinfoDTO userinfo = response.getBody();

        AppUser newUser = new AppUser();
        newUser.setSubject(userinfo.getSub());
        newUser.setFirstName(userinfo.getGivenName());
        newUser.setLastName(userinfo.getFamilyName());
        newUser.setUserName(userinfo.getNickname());

        userRepository.save(newUser);
        return newUser;
    }

    @Override
    @Transactional
    public AppUserDTO getUser(UUID uuid) {
        var entity = userRepository.getOne(uuid);
        return parseDto(entity);
    }

    @Override
    @Transactional
    public AppUserDTO getUser(String accessToken) {
        var entity = checkIfUserExists(accessToken);
        return parseDto(entity);
    }

    @Override
    @Transactional
    public void updatePhoto(AppUser user, byte[] photo) {
        user.setPhoto(photo);
    }

    private AppUserDTO parseDto(AppUser entity) {
        var dto = new AppUserDTO();
        dto.setId(String.valueOf(entity.getId()));
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setUserName(entity.getUserName());
        dto.setPhoto(Base64.getEncoder().encode(entity.getPhoto()));

        return dto;
    }
}