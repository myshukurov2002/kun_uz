package com.example.controller;import com.example.dto.ApiResponse;import com.example.dto.ArticleLikeDTO;import com.example.dto.ArticleLikeDTO;import com.example.dto.JwtDTO;import com.example.service.ArticleLikeService;import com.example.service.CommentLikeService;import com.example.util.SecurityUtil;import jakarta.servlet.http.HttpServletRequest;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RestController;@RestController@RequestMapping("/api/v1/article-like")public class ArticleLikeController {    @Autowired    private ArticleLikeService articleLikeService;    @PostMapping("/close/create/like")    public ResponseEntity<ApiResponse> createLike(@RequestBody ArticleLikeDTO articleLike,                                                  HttpServletRequest request) {        JwtDTO jwtDTO = SecurityUtil.hasRole(request, null);        ApiResponse apiResponse = articleLikeService.createLike(jwtDTO.getId(), articleLike);        return ResponseEntity.ok(apiResponse);    }    @PostMapping("/close/create/disLike")    public ResponseEntity<ApiResponse> createdLike(@RequestBody ArticleLikeDTO articleLikeDTO,                                                     HttpServletRequest request) {        JwtDTO jwtDTO = SecurityUtil.hasRole(request, null);        ApiResponse apiResponse = articleLikeService.createdDisLike(jwtDTO.getId(), articleLikeDTO);        return ResponseEntity.ok(apiResponse);    }}