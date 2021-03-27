package fiit.mtaa.publisher.bl.impl;

import fiit.mtaa.publisher.bl.service.UserService;
import fiit.mtaa.publisher.dto.UserinfoDTO;
import fiit.mtaa.publisher.entity.AppUser;
import fiit.mtaa.publisher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private RestTemplate restTemplate;
    @Autowired
    public void RestTemplateBuilder(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public void checkIfUserExists(Jwt accessToken) {
        AppUser alreadyExists = userRepository.getFirstBySubject(accessToken.getSubject());
    	if (alreadyExists == null) {
            createUser(accessToken);
        }
    }

    @Transactional
    private void createUser(Jwt accessToken) {
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
    }
}