package com.example.service;import com.example.config.CustomUserDetails;import com.example.dto.ApiResponse;import com.example.dto.ArticleDTO;import com.example.entity.ArticleEntity;import com.example.entity.AttachEntity;import com.example.entity.CategoryEntity;import com.example.entity.RegionEntity;import com.example.repository.ArticleRepository;import com.example.repository.AttachRepository;import com.example.repository.CategoryRepository;import com.example.repository.RegionRepository;import com.example.util.SpringSecurityUtil;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.stereotype.Service;import java.util.Optional;@Servicepublic class ArticleService {    @Autowired    private ArticleRepository articleRepository;    @Autowired    private AttachRepository attachRepository;    @Autowired    private RegionRepository regionRepository;    @Autowired    private CategoryRepository categoryRepository;    public ApiResponse create(ArticleDTO articleDTO) {        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();        if (check(articleDTO)) {            ArticleEntity createdArticle = DTO_TO_ENTITY(articleDTO);            createdArticle.setModeratorId(customUserDetails.getProfileEntity().getId()); //moderator id            ArticleEntity savedArticle = articleRepository.save(createdArticle);            ArticleDTO savedArticleDTO = ENTITY_TO_DTO(savedArticle);            return new ApiResponse(true, savedArticleDTO);        }        return new ApiResponse(false, "Article not created!");    }    public ApiResponse update(String id, ArticleDTO articleDTO) {        if (articleRepository.existsById(id) &&                check(articleDTO)) {            Optional<ArticleEntity> optionalArticle = articleRepository.findById(id);            Optional<AttachEntity> optionalAttach = attachRepository.findById(articleDTO.getAttachId());            Optional<RegionEntity> optionalRegion = regionRepository.findById(articleDTO.getRegionId());            Optional<CategoryEntity> optionalCategory = categoryRepository.findById(articleDTO.getCategoryId());            if (optionalArticle.isPresent() &&                    optionalAttach.isPresent() &&                    optionalCategory.isPresent() &&                    optionalRegion.isPresent()) {                ArticleEntity articleEntity = optionalArticle.get();                articleEntity.setTitle(articleEntity.getTitle());                articleEntity.setDescription(articleEntity.getDescription());                articleEntity.setContent(articleEntity.getContent());                articleEntity.setAttachId(articleDTO.getAttachId());                articleEntity.setAttachEntity(optionalAttach.get());                articleEntity.setRegionId(articleDTO.getRegionId());                articleEntity.setRegionEntity(optionalRegion.get());                articleEntity.setCategoryId(articleEntity.getCategoryId());                articleEntity.setCategoryEntity(optionalCategory.get());                articleEntity.setStatus(ArticleEntity.Status.NOT_PUBLISHED);                ArticleEntity updatedArticle = articleRepository.save(articleEntity);                return new ApiResponse(true, ENTITY_TO_DTO(updatedArticle));            }        }        return new ApiResponse(false, "An error occurred while updating!");    }    public ApiResponse updateStatus(String articleId) {        Optional<ArticleEntity> optionalArticle = articleRepository.findById(articleId);        if (optionalArticle.isPresent()) {            ArticleEntity updatedArticle = optionalArticle.get();            if (updatedArticle.getStatus().equals(ArticleEntity.Status.NOT_PUBLISHED)) {                updatedArticle.setStatus(ArticleEntity.Status.PUBLISHED);            } else {                updatedArticle.setStatus(ArticleEntity.Status.NOT_PUBLISHED);            }            articleRepository.save(updatedArticle);            return new ApiResponse(true, updatedArticle.getId() +                    " Article status updated to " +                    updatedArticle.getStatus());        }        return new ApiResponse(false, "Failed update!");    }    public String delete(Long id) {        return null;    }    public Page<ArticleDTO> getAll(Integer page, Integer size) {        return null;    }    public ArticleDTO getById(Long id) {        return null;    }    private boolean check(ArticleDTO articleDTO) {        return articleDTO.getTitle() != null && !articleDTO.getTitle().isEmpty() &&                articleDTO.getDescription() != null && !articleDTO.getDescription().isEmpty() &&                articleDTO.getContent() != null && !articleDTO.getContent().isEmpty();    }    private ArticleEntity DTO_TO_ENTITY(ArticleDTO articleDTO) {        ArticleEntity articleEntity = new ArticleEntity();        articleEntity.setTitle(articleDTO.getTitle());        articleEntity.setDescription(articleDTO.getDescription());        articleEntity.setContent(articleDTO.getContent());        articleEntity.setSharedCount(0); // Default value for sharedCount        articleEntity.setAttachId(articleDTO.getAttachId());        articleEntity.setRegionId(articleDTO.getRegionId());        articleEntity.setCategoryId(articleDTO.getCategoryId());        articleEntity.setModeratorId(articleDTO.getModeratorId());        articleEntity.setPublisherId(articleDTO.getPublisherId());        articleEntity.setStatus(articleDTO.getStatus());        articleEntity.setPublishedDate(articleDTO.getPublishedDate());        articleEntity.setViewCount(0); // Default value for viewCount        return articleEntity;    }    private ArticleDTO ENTITY_TO_DTO(ArticleEntity articleEntity) {        ArticleDTO articleDTO = new ArticleDTO();        articleDTO.setTitle(articleEntity.getTitle());        articleDTO.setDescription(articleEntity.getDescription());        articleDTO.setContent(articleEntity.getContent());        articleDTO.setAttachId(articleEntity.getAttachId());        articleDTO.setRegionId(articleEntity.getRegionId());        articleDTO.setCategoryId(articleEntity.getCategoryId());        articleDTO.setModeratorId(articleEntity.getModeratorId());        articleDTO.setPublisherId(articleEntity.getPublisherId());        articleDTO.setStatus(articleEntity.getStatus());        articleDTO.setPublishedDate(articleEntity.getPublishedDate());        // viewCount and sharedCount are not mapped to the DTO, so they are not set        return articleDTO;    }}