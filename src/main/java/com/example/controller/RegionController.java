package com.example.controller;import com.example.dto.RegionDTO;import com.example.entity.RegionEntity;import com.example.service.RegionService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;@RestController@RequestMapping("/region")public class RegionController {    @Autowired    RegionService regionService;    @PostMapping    public ResponseEntity<?> create(@RequestBody RegionDTO regionDTO) {        RegionDTO createdRegionDTO = regionService.create(regionDTO);        return ResponseEntity.status(HttpStatus.CREATED).body(createdRegionDTO);    }    @PutMapping("/{id}")    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RegionDTO regionDTO) {        Boolean resp = regionService.update(id, regionDTO);        return ResponseEntity.ok(resp);    }    @GetMapping()    public ResponseEntity<?> getList(@RequestParam(defaultValue = "0") Integer page,                                     @RequestParam(defaultValue = "5") Integer size) {        Page<RegionDTO> regionDTOPage = regionService.getList(page, size);        return ResponseEntity.ok(regionDTOPage);    }    @DeleteMapping("/{id}")    public ResponseEntity<?> deleteById(@PathVariable Long id) {        regionService.deleteById(id);        return ResponseEntity.noContent().build();    }    @GetMapping("/lang")    public ResponseEntity<?> getByLang(@RequestParam(defaultValue = "uz") RegionEntity.Language lang,                                       @RequestParam(defaultValue = "0") Integer page,                                       @RequestParam(defaultValue = "5") Integer size) {        Page<RegionDTO> regionDTOPage = regionService.getByLang(lang, page, size);        return ResponseEntity.ok(regionDTOPage);    }}