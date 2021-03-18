package fiit.mtaa.publisher.initDb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.App;
import com.github.javafaker.Faker;
import fiit.mtaa.publisher.entity.AppUser;
import fiit.mtaa.publisher.entity.Article;
import fiit.mtaa.publisher.entity.Category;
import fiit.mtaa.publisher.initDb.dto.InitDbArticleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Profile("initDb")
public class InitDbService {

    private Logger logger = LoggerFactory.getLogger(InitDbService.class);

    @Value("classpath:articles.json")
    private Resource articlesFile;

    @Autowired
    private ObjectMapper objectMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private Faker faker = new Faker();

    private List<InitDbArticleDto> articles = new ArrayList<>();
    private List<String> categoryNames = Arrays.asList("business", "politics", "sport", "computer-science", "health", "agriculture", "nature", "news");

    @PostConstruct
    protected void initialize() {
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            articles = Arrays.asList(
                    objectMapper.readValue(articlesFile.getFile(), InitDbArticleDto[].class)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<AppUser> users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            AppUser user = new AppUser();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            users.add(user);
            entityManager.persist(user);
        }

        List<Category> categories = new ArrayList<>();
        for (var name : categoryNames) {
            Category category = new Category();
            category.setName(name);
            categories.add(category);
            entityManager.persist(category);
        }

        for (var article : articles) {
            Article a = new Article();
            a.setAuthor(users.get(faker.random().nextInt(0, users.size() - 1)));

            int catCount = faker.random().nextInt(0, 3);
            List<Category> artCategories = new ArrayList<>();
            for (int i = 0; i < catCount; i++) {
                var c = categories.get(faker.random().nextInt(0, categories.size() - 1));
                if (!artCategories.contains(c)) {
                    artCategories.add(c);
                }
            }
            a.setCategories(artCategories);

            a.setTitle(article.getTitle());
            a.setContent(article.getContent());
            a.setLikeCount(article.getLikeCount());
            a.setCreatedAt(LocalDateTime.parse(article.getPublishedAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.of("UTC"))));
            entityManager.persist(a);
        }

        entityManager.flush();
        transactionManager.commit(transaction);
    }

}
