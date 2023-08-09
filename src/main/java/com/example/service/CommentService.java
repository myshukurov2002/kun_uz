package com.example.service;import com.example.config.CustomUserDetails;import com.example.dto.ApiResponse;import com.example.dto.CommentDTO;import com.example.dto.CommentFilterDTO;import com.example.dto.ProfileDTO;import com.example.entity.CommentEntity;import com.example.entity.ProfileEntity;import com.example.exception.AppBadRequestException;import com.example.repository.ArticleRepository;import com.example.repository.CommentRepository;import com.example.repository.CustomRepository;import com.example.repository.ProfileRepository;import com.example.util.SpringSecurityUtil;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.*;import org.springframework.stereotype.Service;import java.time.LocalDate;import java.time.LocalDateTime;import java.util.List;import java.util.Optional;@Servicepublic class CommentService {    @Autowired    private CommentRepository commentRepository;    @Autowired    private ProfileRepository profileRepository;    @Autowired    private ArticleRepository articleRepository;    @Autowired    private CustomRepository customRepository;    private boolean check(CommentDTO commentDTO) {        if (commentDTO.getContent() == null) {            throw new AppBadRequestException("Content is null!");        }        if (commentDTO.getArticleId() == null) {            throw new AppBadRequestException("Article is null!");        }        return true;    }    public CommentDTO ENTITY_TO_DTO(CommentEntity commentEntity) {        CommentDTO commentDTO = new CommentDTO();        commentDTO.setProfileId(commentEntity.getProfileId());        commentDTO.setProfileEntity(commentEntity.getProfileEntity());        commentDTO.setContent(commentEntity.getContent());        commentDTO.setArticleId(commentEntity.getArticleId());        commentDTO.setArticleEntity(commentEntity.getArticleEntity());        commentDTO.setReplyCommentId(commentEntity.getReplyCommentId());        commentDTO.setReplyComment(commentEntity.getReplyCommentEntity());        return commentDTO;    }    public CommentEntity DTO_TO_ENTITY(CommentDTO commentDTO) {        CommentEntity commentEntity = new CommentEntity();        commentEntity.setContent(commentDTO.getContent());        commentEntity.setArticleId(commentDTO.getArticleId());        commentEntity.setArticleEntity(commentDTO.getArticleEntity());        commentEntity.setReplyCommentId(commentDTO.getReplyCommentId());        commentEntity.setReplyCommentEntity(commentDTO.getReplyComment());        return commentEntity;    }    public ApiResponse create(CommentDTO commentDTO) {        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();        if (check(commentDTO)) {            CommentEntity commentEntity = DTO_TO_ENTITY(commentDTO);            commentEntity.setProfileId(customUserDetails.getProfileEntity().getId());//            commentEntity.setProfileEntity(profileRepository.getById(customUserDetails.getProfileEntity().getId()));            CommentEntity savedComment = commentRepository.save(commentEntity);            return new ApiResponse(true, ENTITY_TO_DTO(savedComment));        }        return new ApiResponse(false, "Comment an error to created!");    }    public ApiResponse update(String commentId, CommentDTO commentDTO) {        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);        if (check(commentDTO) &&                optionalComment.isPresent()) {            CommentEntity updatedComment = optionalComment.get();            updatedComment.setContent(commentDTO.getContent());            updatedComment.setArticleId(commentDTO.getArticleId());            updatedComment.setArticleEntity(articleRepository.getById(commentDTO.getArticleId()));            CommentEntity savedComment = commentRepository.save(updatedComment);            return new ApiResponse(true, ENTITY_TO_DTO(savedComment));        }        return new ApiResponse(false, "Comment an error to updated!");    }    public ApiResponse delete(String id, CommentDTO commentDTO) {        if (commentRepository.existsById(commentDTO.getId())) {            commentRepository.deleteById(id);            return new ApiResponse(true, id + " comment is SUCCESS DELETED!");        }        return new ApiResponse(false, "DELETED FAILED!");    }    public Page<CommentDTO> getCommentListByArticleId(String articleId, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").ascending();        Pageable pageable = PageRequest.of(page - 1, size, sort);        Page<CommentEntity> commentEntityPage = commentRepository                .findAllByArticleId(articleId, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::ENTITY_TO_DTO)                .toList();        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> getCommentListById(String articleId, String id, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").ascending();        Pageable pageable = PageRequest.of(page - 1, size, sort);        Page<CommentEntity> commentEntityPage = commentRepository                .findAllByIdAndArticleId(id, articleId, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::ENTITY_TO_DTO)                .toList();        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> getCommentListByCreatedDate(String articleId, LocalDate localDate, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").ascending();        Pageable pageable = PageRequest.of(page - 1, size, sort);        LocalDateTime startingDate = localDate.atStartOfDay();        LocalDateTime endingDate = startingDate.plusDays(1).minusNanos(1);        Page<CommentEntity> commentEntityPage = commentRepository                .findAllByArticleIdAndCreatedDateBetween(articleId, startingDate, endingDate, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::ENTITY_TO_DTO)                .toList();        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> getCommentListByUpdatedDate(String articleId, LocalDate localDate, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").ascending();        Pageable pageable = PageRequest.of(page - 1, size, sort);        LocalDateTime startingDate = localDate.atStartOfDay();        LocalDateTime endingDate = startingDate.plusDays(1).minusNanos(1);        Page<CommentEntity> commentEntityPage = commentRepository                .findAllByArticleIdAndUpdatedDateBetween(articleId, startingDate, endingDate, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::ENTITY_TO_DTO)                .toList();        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> getCommentListByProfileId(String articleId, Long profileId, Integer page, Integer size) {            Sort sort = Sort.by("createdDate").ascending();            Pageable pageable = PageRequest.of(page - 1, size, sort);            Page<CommentEntity> commentEntityPage = commentRepository                    .findAllByProfileIdAndArticleId(profileId, articleId, pageable);            List<CommentDTO> commentDTOList = commentEntityPage                    .stream()                    .map(this::ENTITY_TO_DTO)                    .toList();            return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());        }    public Page<CommentDTO> getCommentListByProfileName(String articleId, String name, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").ascending();        Pageable pageable = PageRequest.of(page - 1, size, sort);        Page<CommentEntity> commentEntityPage = commentRepository                .findAllByProfileEntityNameAndArticleId(name, articleId, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::ENTITY_TO_DTO)                .toList();        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> getCommentListByProfileSurname(String articleId, String surname, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").ascending();        Pageable pageable = PageRequest.of(page - 1, size, sort);        Page<CommentEntity> commentEntityPage = commentRepository                .findAllByProfileEntitySurnameAndArticleId(surname, articleId, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::ENTITY_TO_DTO)                .toList();        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> getCommentPageById(String id, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").ascending();        Pageable pageable = PageRequest.of(page - 1, size, sort);        Page<CommentEntity> commentEntityPage = commentRepository                .getCommentPageById(id, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::ENTITY_TO_DTO)                .toList();        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> getCommentPageByCreatedDate(LocalDate localDate, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").ascending();        Pageable pageable = PageRequest.of(page - 1, size, sort);        LocalDateTime startingDate = localDate.atStartOfDay();        LocalDateTime endingDate = startingDate.plusDays(1).minusNanos(1);        Page<CommentEntity> commentEntityPage = commentRepository                .findAllByCreatedDateBetween(startingDate, endingDate, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::ENTITY_TO_DTO)                .toList();        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> getCommentPageByUpdatedDate(LocalDate localDate, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").ascending();        Pageable pageable = PageRequest.of(page - 1, size, sort);        LocalDateTime startingDate = localDate.atStartOfDay();        LocalDateTime endingDate = startingDate.plusDays(1).minusNanos(1);        Page<CommentEntity> commentEntityPage = commentRepository                .findAllByUpdatedDateBetween(startingDate, endingDate, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::ENTITY_TO_DTO)                .toList();        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> getCommentPageByProfileId(Long profileId, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").ascending();        Pageable pageable = PageRequest.of(page - 1, size, sort);        Page<CommentEntity> commentEntityPage = commentRepository                .findAllByProfileId(profileId, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::ENTITY_TO_DTO)                .toList();        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> getCommentPageByProfileName(String name, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").ascending();        Pageable pageable = PageRequest.of(page - 1, size, sort);        Page<CommentEntity> commentEntityPage = commentRepository                .findAllByProfileEntityName(name, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::ENTITY_TO_DTO)                .toList();        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> getCommentPageByProfileSurname(String surname, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").ascending();        Pageable pageable = PageRequest.of(page - 1, size, sort);        Page<CommentEntity> commentEntityPage = commentRepository                .findAllByProfileEntitySurname(surname, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::ENTITY_TO_DTO)                .toList();        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> filtering(CommentFilterDTO filterDTO, Integer page, Integer size) {        Sort sort = Sort.by("id").ascending();        Pageable pageable = PageRequest.of(page - 1, size, sort);        List<CommentEntity> commentEntityList = customRepository.filteringComment(filterDTO);        List<CommentDTO> commentDTOList = commentEntityList                .stream()                .map(this::ENTITY_TO_DTO)                .toList();        return new PageImpl<>(commentDTOList, pageable, commentEntityList.size());    }    public Page<CommentDTO> getAllRepliesCommentPageById(String id, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").ascending();        Pageable pageable = PageRequest.of(page - 1, size, sort);        Page<CommentEntity> commentEntityPage = commentRepository                .getAllRepliesCommentPageById(id, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::ENTITY_TO_DTO)                .toList();        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }}