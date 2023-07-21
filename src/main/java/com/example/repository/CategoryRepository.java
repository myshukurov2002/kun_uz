package com.example.repository;import com.example.entity.CategoryEntity;import com.example.mapper.CategoryMapper;import com.example.mapper.RegionMapper;import org.springframework.data.domain.Page;import org.springframework.data.domain.Pageable;import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.data.jpa.repository.Query;import org.springframework.data.repository.query.Param;import java.util.List;public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {    Page<CategoryEntity> findAllByNameUZ(String lang, Pageable pageable);    Page<CategoryEntity> findAllByNameEN(String lang, Pageable pageable);    Page<CategoryEntity> findAllByNameRU(String lang, Pageable pageable);    @Query(value = "select id, order_number, " +            " case :lang" +            " when 'en' then name_en" +            " when 'ru' then name_ru" +            " else name_uz" +            " end as name" +            " from category" +            " where visibility = true", nativeQuery = true)    Page<CategoryMapper> getAllByLang(@Param("lang") String lang, Pageable pageable);}