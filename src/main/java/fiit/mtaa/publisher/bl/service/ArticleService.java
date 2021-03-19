package fiit.mtaa.publisher.bl.service;

import fiit.mtaa.publisher.dto.ArticleDetailedDTO;
import fiit.mtaa.publisher.dto.ArticleSimpleListDTO;
import fiit.mtaa.publisher.dto.FilterCriteria;

import java.util.UUID;

public interface ArticleService {
    
    ArticleDetailedDTO getById(UUID id);
    ArticleSimpleListDTO getFiltered(FilterCriteria filterCriteria);
    
}
