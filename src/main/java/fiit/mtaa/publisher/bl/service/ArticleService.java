package fiit.mtaa.publisher.bl.service;

import fiit.mtaa.publisher.dto.ArticleDetailedDTO;
import fiit.mtaa.publisher.dto.ArticleInsertDTO;
import fiit.mtaa.publisher.dto.ArticleSimpleDTO;
import fiit.mtaa.publisher.dto.FilterCriteria;
import fiit.mtaa.publisher.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface ArticleService {

    ArticleDetailedDTO getById(UUID id, @Nullable AppUser currentUser);

    Page<ArticleSimpleDTO> getFiltered(FilterCriteria filterCriteria, @Nullable AppUser currentUser);

    UUID insertArticle(ArticleInsertDTO articleInsertDTO);

    void updateArticle(ArticleInsertDTO articleInsertDTO);

    void deleteArticle(UUID id);

    void likeArticle(UUID id, AppUser user);

    void unlikeArticle(UUID id, AppUser user);

}
