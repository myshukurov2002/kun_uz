package com.example.controller;import com.example.dto.AttachDTO;import com.example.entity.AttachEntity;import com.example.service.AttachService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.MediaType;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import org.springframework.web.multipart.MultipartFile;@RestController@RequestMapping("api/v1/attach")public class AttachController {    @Autowired    AttachService attachService;    @PostMapping()    public ResponseEntity<?> saveToSystem(@RequestParam MultipartFile file) {//        String fileName = attachService.saveToSystem(file);        AttachDTO attachDTO = attachService.save(file);        return ResponseEntity.ok(attachDTO);    }    @GetMapping(value = "/open/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)    public byte[] open(@PathVariable("fileName") String fileName) {        return this.attachService.loadImage(fileName);    }    @GetMapping(value = "/open/{id}/img", produces = MediaType.IMAGE_PNG_VALUE)    public byte[] openImageById(@PathVariable("id") String id) {        return attachService.loadImageById(id);    }    @GetMapping(value = "/open/{id}/general", produces = MediaType.ALL_VALUE)    public byte[] openByIdGeneral(@PathVariable("id") String id) {        return attachService.loadByIdGeneral(id);    }}