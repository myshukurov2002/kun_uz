package com.example.repository;import com.example.dto.CommentFilterDTO;import com.example.entity.CommentEntity;import jakarta.persistence.EntityManager;import jakarta.persistence.Query;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Repository;import java.time.LocalDateTime;import java.time.LocalTime;import java.util.HashMap;import java.util.List;import java.util.Map;@Repositorypublic class CustomRepository {    @Autowired    private EntityManager entityManager;    public List<CommentEntity> filteringComment(CommentFilterDTO filterDTO) {        StringBuilder selectQueryBuilder = new StringBuilder("select s from CommentEntity as s where 1=1");        StringBuilder whereQuery = new StringBuilder();        Map<String, Object> params = new HashMap<>();        if (filterDTO.getId() != null) {            whereQuery.append(" and s.id =:id");            params.put("id", filterDTO.getId());        }        if (filterDTO.getProfileId() != null) {            whereQuery.append(" and s.profileId =:profileId");            params.put("profileId", filterDTO.getProfileId());        }        if (filterDTO.getArticleId() != null) {            whereQuery.append(" and s.articleId =:articleId");            params.put("articleId", filterDTO.getArticleId());        }        if (filterDTO.getFromDate() != null) {            whereQuery.append(" and s.createdDate >=:fromDate");            params.put("fromDate", LocalDateTime.of(filterDTO.getFromDate(), LocalTime.MIN));        }        if (filterDTO.getFromDate() != null) {            whereQuery.append(" and s.createdDate <=:ToDate");            params.put("ToDate", LocalDateTime.of(filterDTO.getToDate(), LocalTime.MAX));        }        Query selectQuery = entityManager.createQuery(selectQueryBuilder.append(whereQuery).toString());        for (Map.Entry<String, Object> param : params.entrySet()) {            selectQuery.setParameter(param.getKey(), param.getValue());        }        return selectQuery.getResultList();    }}