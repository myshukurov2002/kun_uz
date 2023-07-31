package com.example.repository;import com.example.entity.EmailHistoryEntity;import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;import org.springframework.data.domain.Page;import org.springframework.data.domain.Pageable;import org.springframework.data.jpa.repository.JpaRepository;import java.time.LocalDateTime;import java.util.List;public interface EmailHistoryRepository extends JpaRepository<EmailHistoryEntity, String> {    boolean existsByEmail(String email);    void deleteByEmail(String email);    List<EmailHistoryEntity> findAllByEmail(String email);    List<EmailHistoryEntity> findAllByMessage(String message);    List<EmailHistoryEntity> findAllByCreatedDateBetween(LocalDateTime startingDate, LocalDateTime endingDate);    Page<EmailHistoryEntity> getAllById(String id, Pageable pageable);    Page<EmailHistoryEntity> getAllByEmail(String id, Pageable pageable);    Page<EmailHistoryEntity> getAllByCreatedDateBetween(LocalDateTime startingDate, LocalDateTime endingDate, Pageable pageable);}