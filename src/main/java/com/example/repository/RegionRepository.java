package com.example.repository;import com.example.entity.RegionEntity;import com.example.mapper.RegionMapper;import jakarta.transaction.Transactional;import org.springframework.data.domain.Page;import org.springframework.data.domain.Pageable;import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.data.jpa.repository.Modifying;import org.springframework.data.jpa.repository.Query;import org.springframework.data.repository.query.Param;public interface RegionRepository extends JpaRepository<RegionEntity, Long> {    @Modifying    @Transactional    @Query("update RegionEntity r set r.orderNumber = :orderNumber," +            " r.nameUZ = :nameUZ, r.nameRU = :nameRU," +            " r.nameEN = :nameEN" +            " where r.id = :id")    Integer updateById(@Param("id") Long id,                            @Param("orderNumber") Integer orderNumber,                            @Param("nameUZ") String nameUZ,                            @Param("nameRU") String nameRU,                            @Param("nameEN") String nameEN);    @Query(value = "select id, order_number, " +            " case :lang" +            " when 'en' then name_en" +            " when 'ru' then name_ru" +            " else name_uz" +            " end as name" +            " from region" +            " where visibility = true", nativeQuery = true)    Page<RegionMapper> getAllByLang(@Param("lang") String lang, Pageable pageable);}