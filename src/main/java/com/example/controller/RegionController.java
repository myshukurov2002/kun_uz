package com.example.controller;import com.example.dto.RegionDTO;import com.example.entity.RegionEntity;import com.example.service.RegionService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.web.bind.annotation.*;@RestController@RequestMapping("/api/v1/region")public class RegionController {    @Autowired    RegionService regionService;    @PreAuthorize("hasRole('ADMIN')")    @PostMapping("/admin/create")    public ResponseEntity<?> create(@RequestBody RegionDTO regionDTO) {        RegionDTO createdRegionDTO = regionService.create(regionDTO);        return ResponseEntity.status(HttpStatus.CREATED).body(createdRegionDTO);    }    @PreAuthorize("hasRole('ADMIN')")    @PutMapping("/admin/update/by-id/{id}")    public ResponseEntity<?> update(@PathVariable Long id,                                    @RequestBody RegionDTO regionDTO) {        Boolean resp = regionService.update(id, regionDTO);        return ResponseEntity.ok(resp);    }    @PreAuthorize("hasRole('ADMIN')")    @GetMapping("/admin/get-all")    public ResponseEntity<?> getList(@RequestParam(defaultValue = "0") Integer page,                                     @RequestParam(defaultValue = "10") Integer size) {        Page<RegionDTO> regionDTOPage = regionService.getList(page, size);        return ResponseEntity.ok(regionDTOPage);    }    @PreAuthorize("hasRole('ADMIN')")    @DeleteMapping("/admin/delete/by-id/{id}")    public ResponseEntity<?> deleteById(@PathVariable Long id) {        regionService.deleteById(id);        return ResponseEntity.noContent().build();    }    @GetMapping("/open/lang")    public ResponseEntity<?> getByLang(@RequestParam(defaultValue = "uz") RegionEntity.Language lang,                                       @RequestParam(defaultValue = "0") Integer page,                                       @RequestParam(defaultValue = "10") Integer size) {        Page<RegionDTO> regionDTOPage = regionService.getByLang(lang, page, size);        return ResponseEntity.ok(regionDTOPage);    }}