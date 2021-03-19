package fiit.mtaa.publisher.bl.impl;

import fiit.mtaa.publisher.bl.repo.ArticleRepository;
import fiit.mtaa.publisher.bl.service.ArticleService;
import fiit.mtaa.publisher.dto.*;
import fiit.mtaa.publisher.entity.Article;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public ArticleDetailedDTO getById(UUID id) {
        var entity = articleRepository.getOne(id);

        ArticleDetailedDTO dto = new ArticleDetailedDTO();
        dto.setAuthor(modelMapper.map(entity.getAuthor(), AppUserDTO.class));
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setId(entity.getId().toString());
        dto.setLikeCount(entity.getLikeCount() + entity.getLikedUsers().size());
        dto.setComments(entity.getComments().stream().map(c -> modelMapper.map(c, CommentDTO.class)).collect(Collectors.toList()));
        dto.setCategories(entity.getCategories().stream().map(c -> modelMapper.map(c, CategoryDTO.class)).collect(Collectors.toList()));

        return dto;
    }

    @Override
    public ArticleSimpleListDTO getFiltered(FilterCriteria filterCriteria) {
        Article exampleArticle = new Article();
        exampleArticle.setTitle("15 inspiring 'Game of Thrones' quotes to live by");

        var entities = articleRepository.findAll();

        var dtos = entities.stream().map(entity -> {
            var article = new ArticleSimpleDTO();
            article.setAuthor(modelMapper.map(entity.getAuthor(), AppUserDTO.class));
            article.setTitle(entity.getTitle());
            article.setCreatedAt(entity.getCreatedAt());
            article.setId(entity.getId().toString());
            article.setLikeCount(entity.getLikeCount() + entity.getLikedUsers().size());
            article.setCategories(entity.getCategories().stream().map(c -> modelMapper.map(c, CategoryDTO.class)).collect(Collectors.toList()));
            return article;
        }).collect(Collectors.toList());

        var response = new ArticleSimpleListDTO();
        response.setArticles(dtos);
        response.setHasMore(false);

        return response;
    }
}
