package fiit.mtaa.publisher.bl.service;

import fiit.mtaa.publisher.dto.ArticleDetailedDTO;
import fiit.mtaa.publisher.dto.ArticleInsertDTO;
import fiit.mtaa.publisher.dto.ArticleSimpleDTO;
import fiit.mtaa.publisher.dto.FilterCriteria;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ArticleService {

    ArticleDetailedDTO getById(UUID id);

    Page<ArticleSimpleDTO> getFiltered(FilterCriteria filterCriteria);

    UUID insertArticle(ArticleInsertDTO articleInsertDTO);

    void updateArticle(ArticleInsertDTO articleInsertDTO);

    void deleteArticle(UUID id);

}
