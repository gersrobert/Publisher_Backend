package fiit.mtaa.publisher.bl.impl;

import fiit.mtaa.publisher.entity.AppUser;
import fiit.mtaa.publisher.exception.ConflictException;
import fiit.mtaa.publisher.repository.ArticleRepository;
import fiit.mtaa.publisher.bl.service.ArticleService;
import fiit.mtaa.publisher.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;
import java.util.stream.Collectors;

import static fiit.mtaa.publisher.repository.ArticleRepository.*;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public ArticleDetailedDTO getById(UUID id, AppUser currentUser) {
        var entity = articleRepository.getOne(id);

        ArticleDetailedDTO dto = new ArticleDetailedDTO();
        dto.setAuthor(modelMapper.map(entity.getAuthor(), AppUserDTO.class));
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setId(entity.getId().toString());
        dto.setLikeCount(entity.getLikeCount());
        dto.setComments(entity.getComments().stream().map(c -> modelMapper.map(c, CommentDTO.class)).collect(Collectors.toList()));
        dto.setCategories(entity.getCategories().stream().map(c -> modelMapper.map(c, CategoryDTO.class)).collect(Collectors.toList()));

        if (currentUser != null) {
            dto.setLiked(
                    entity.getLikedUsers().contains(currentUser)
            );
        }

        return dto;
    }

    @Override
    public Page<ArticleSimpleDTO> getFiltered(FilterCriteria filterCriteria, AppUser currentUser) {
        var entities = articleRepository.findAll(
                hasAuthor(filterCriteria.getAuthor())
                .and(hasTitle(filterCriteria.getTitle()))
                .and(hasCategory(filterCriteria.getCategory())),
                PageRequest.of(filterCriteria.getPage(), filterCriteria.getPageSize(), Sort.by("likeCount").descending())
        );

        return entities.map(entity -> {
            var article = new ArticleSimpleDTO();
            article.setAuthor(modelMapper.map(entity.getAuthor(), AppUserDTO.class));
            article.setTitle(entity.getTitle());
            article.setCreatedAt(entity.getCreatedAt());
            article.setId(entity.getId().toString());
            article.setLikeCount(entity.getLikeCount());
            article.setCategories(entity.getCategories().stream().map(c -> modelMapper.map(c, CategoryDTO.class)).collect(Collectors.toList()));

            if (currentUser != null) {
                article.setLiked(
                        entity.getLikedUsers().contains(currentUser)
                );
            }

            return article;
        });
    }

    @Override
    public UUID insertArticle(ArticleInsertDTO articleInsertDTO) {
        return null;
    }

    @Override
    public void updateArticle(ArticleInsertDTO articleInsertDTO) {

    }

    @Override
    public void deleteArticle(UUID id) {
        articleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void likeArticle(UUID id, AppUser user) {
        var article = articleRepository.getOne(id);
        if (article.getLikedUsers().contains(user)) {
            throw new ConflictException();
        }

        article.getLikedUsers().add(user);
        article.setLikeCount(article.getLikeCount() + 1);
        articleRepository.save(article);
    }

    @Override
    public void unlikeArticle(UUID id, AppUser user) {
        var article = articleRepository.getOne(id);
        if (!article.getLikedUsers().contains(user)) {
            throw new ConflictException();
        }

        article.getLikedUsers().remove(user);
        article.setLikeCount(article.getLikeCount() - 1);
        articleRepository.save(article);
    }
}
