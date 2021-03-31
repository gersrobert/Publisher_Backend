package fiit.mtaa.publisher.bl.impl;

import fiit.mtaa.publisher.bl.service.UserService;
import fiit.mtaa.publisher.dto.AppUserDTO;
import fiit.mtaa.publisher.dto.ArticleSimpleDTO;
import fiit.mtaa.publisher.dto.CategoryDTO;
import fiit.mtaa.publisher.dto.UserinfoDTO;
import fiit.mtaa.publisher.entity.AppUser;
import fiit.mtaa.publisher.entity.Article;
import fiit.mtaa.publisher.repository.ArticleRepository;
import fiit.mtaa.publisher.repository.UserRepository;
import org.modelmapper.ModelMapper;
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
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArticleRepository articleRepository;

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

        List<Article> articles = articleRepository.findByAuthor(entity);

        return parseDto(entity, articles);
    }

    @Override
    @Transactional
    public AppUserDTO getUser(String accessToken) {
        var entity = checkIfUserExists(accessToken);

        List<Article> articles = articleRepository.findByAuthor(entity);

        return parseDto(entity, articles);
    }

    @Override
    @Transactional
    public void updatePhoto(AppUser user, String photo) {
        var content = photo.split(",")[1];
        content = content.substring(0, content.length() - 2);

        user.setPhoto(content.getBytes(StandardCharsets.UTF_8));
        userRepository.save(user);
    }

    private AppUserDTO parseDto(AppUser userEntity, List<Article> articleEntity) {
        var dto = new AppUserDTO();
        dto.setId(String.valueOf(userEntity.getId()));
        dto.setFirstName(userEntity.getFirstName());
        dto.setLastName(userEntity.getLastName());
        dto.setUserName(userEntity.getUserName());

        if (userEntity.getPhoto() != null) {
            dto.setPhoto(new String(userEntity.getPhoto(), StandardCharsets.UTF_8));
        }

        dto.setArticles(articleEntity.stream().map(article -> {
            var articleSimpleDTO = new ArticleSimpleDTO();
            articleSimpleDTO.setId(article.getId().toString());
            articleSimpleDTO.setTitle(article.getTitle());
            articleSimpleDTO.setCreatedAt(article.getCreatedAt());
            articleSimpleDTO.setCategories(article.getCategories().stream().map(c -> modelMapper.map(c, CategoryDTO.class)).collect(Collectors.toList()));
            articleSimpleDTO.setLikeCount(article.getLikeCount());

            return articleSimpleDTO;
        }).collect(Collectors.toList()));

        return dto;
    }
}