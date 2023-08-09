package com.example.controller;import com.example.dto.ApiResponse;import com.example.dto.CommentDTO;import com.example.dto.CommentFilterDTO;import com.example.service.CommentService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.http.ResponseEntity;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.web.bind.annotation.*;import java.time.LocalDate;@RestController@RequestMapping("/api/v1/comment")public class CommentController {    @Autowired    CommentService commentService;    @PostMapping("/close/create")    public ResponseEntity<ApiResponse> create(@RequestBody CommentDTO commentDTO) {        return ResponseEntity.ok(commentService.create(commentDTO));    }    @PutMapping("/close/update/by-id/{id}")    public ResponseEntity<ApiResponse> update(@PathVariable String id,                                              @RequestBody CommentDTO commentDTO) {        return ResponseEntity.ok(commentService.update(id, commentDTO));    }    @DeleteMapping("/close/delete/by-id/{id}")    public ResponseEntity<ApiResponse> delete(@PathVariable String id,                                              @RequestBody CommentDTO commentDTO) {        return ResponseEntity.ok(commentService.delete(id, commentDTO));    }    @GetMapping("/open/all/by-articleId/{articleId}")    public ResponseEntity<Page<CommentDTO>> getCommentListByArticleId(@PathVariable String articleId,                                                                      @RequestParam(defaultValue = "1") Integer page,                                                                      @RequestParam(defaultValue = "10") Integer size) {        Page<CommentDTO> commentDTOPage = commentService.getCommentListByArticleId(articleId, page, size);        return ResponseEntity.ok(commentDTOPage);    }    @GetMapping("/open/all/by-article_id-and-id/{id}/{articleId}")    public ResponseEntity<Page<CommentDTO>> getCommentListById(@PathVariable String id,                                                               @PathVariable String articleId,                                                               @RequestParam(defaultValue = "1") Integer page,                                                               @RequestParam(defaultValue = "10") Integer size) {        Page<CommentDTO> commentDTOPage = commentService.getCommentListById(articleId, id, page, size);        return ResponseEntity.ok(commentDTOPage);    }    @GetMapping("/open/all/by-date-and-article_id/{date}/{articleId}")    public ResponseEntity<Page<CommentDTO>> getCommentListByCreatedDate(@PathVariable String articleId,                                                                        @PathVariable LocalDate date,                                                                        @RequestParam(defaultValue = "1") Integer page,                                                                        @RequestParam(defaultValue = "10") Integer size) {        Page<CommentDTO> commentDTOPage = commentService.getCommentListByCreatedDate(articleId, date, page, size);        return ResponseEntity.ok(commentDTOPage);    }    @GetMapping("/open/all/updated-date/by-date-and-article_id/{date}/{articleId}")    public ResponseEntity<Page<CommentDTO>> getCommentListByUpdatedDate(@PathVariable String articleId,                                                                        @PathVariable LocalDate date,                                                                        @RequestParam(defaultValue = "1") Integer page,                                                                        @RequestParam(defaultValue = "10") Integer size) {        Page<CommentDTO> commentDTOPage = commentService.getCommentListByUpdatedDate(articleId, date, page, size);        return ResponseEntity.ok(commentDTOPage);    }    @GetMapping("/open/all/by-profile_id-and-article_id/{profileId}/{articleId}")    public ResponseEntity<Page<CommentDTO>> getCommentListByProfileId(@PathVariable Long profileId,                                                                      @PathVariable String articleId,                                                                      @RequestParam(defaultValue = "1") Integer page,                                                                      @RequestParam(defaultValue = "10") Integer size) {        Page<CommentDTO> commentDTOPage = commentService.getCommentListByProfileId(articleId, profileId, page, size);        return ResponseEntity.ok(commentDTOPage);    }    @GetMapping("/open/all/by-name-and-article_id/{name}/{articleId}")    public ResponseEntity<Page<CommentDTO>> getCommentListByProfileName(@PathVariable String name,                                                                        @PathVariable String articleId,                                                                        @RequestParam(defaultValue = "1") Integer page,                                                                        @RequestParam(defaultValue = "10") Integer size) {        Page<CommentDTO> commentDTOPage = commentService.getCommentListByProfileName(articleId, name, page, size);        return ResponseEntity.ok(commentDTOPage);    }    @GetMapping("/open/all/by-surname-and-article_id/{surname}/{articleId}")    public ResponseEntity<Page<CommentDTO>> getCommentListByProfileSurname(@PathVariable String surname,                                                                           @PathVariable String articleId,                                                                           @RequestParam(defaultValue = "1") Integer page,                                                                           @RequestParam(defaultValue = "10") Integer size) {        Page<CommentDTO> commentDTOPage = commentService.getCommentListByProfileSurname(articleId, surname, page, size);        return ResponseEntity.ok(commentDTOPage);    }    @PreAuthorize("hasRole('ADMIN')")    @GetMapping("/admin/paging/by-id/{id}")    public ResponseEntity<Page<CommentDTO>> getCommentPageById(@PathVariable String id,                                                               @RequestParam(defaultValue = "1") Integer page,                                                               @RequestParam(defaultValue = "10") Integer size) {        Page<CommentDTO> commentDTOPage = commentService.getCommentPageById(id, page, size);        return ResponseEntity.ok(commentDTOPage);    }    @PreAuthorize("hasRole('ADMIN')")    @GetMapping("/admin/paging/by-date-and-article_id/{date}/{articleId}")    public ResponseEntity<Page<CommentDTO>> getCommentPageByCreatedDate(@PathVariable LocalDate date,                                                                        @RequestParam(defaultValue = "1") Integer page,                                                                        @RequestParam(defaultValue = "10") Integer size) {        Page<CommentDTO> commentDTOPage = commentService.getCommentPageByCreatedDate(date, page, size);        return ResponseEntity.ok(commentDTOPage);    }    @PreAuthorize("hasRole('ADMIN')")    @GetMapping("/admin/paging/by-date/{updatedDate}")    public ResponseEntity<Page<CommentDTO>> getCommentPageByUpdatedDate(@PathVariable LocalDate updatedDate,                                                                        @RequestParam(defaultValue = "1") Integer page,                                                                        @RequestParam(defaultValue = "10") Integer size) {        Page<CommentDTO> commentDTOPage = commentService.getCommentPageByUpdatedDate(updatedDate, page, size);        return ResponseEntity.ok(commentDTOPage);    }    @PreAuthorize("hasRole('ADMIN')")    @GetMapping("/admin/paging/by-profile_id/{profileId}")    public ResponseEntity<Page<CommentDTO>> getCommentPageByProfileId(@PathVariable Long profileId,                                                                      @RequestParam(defaultValue = "1") Integer page,                                                                      @RequestParam(defaultValue = "10") Integer size) {        Page<CommentDTO> commentDTOPage = commentService.getCommentPageByProfileId(profileId, page, size);        return ResponseEntity.ok(commentDTOPage);    }    @PreAuthorize("hasRole('ADMIN')")    @GetMapping("/admin/paging/by-name/{name}")    public ResponseEntity<Page<CommentDTO>> getCommentPageByProfileName(@PathVariable String name,                                                                        @RequestParam(defaultValue = "1") Integer page,                                                                        @RequestParam(defaultValue = "10") Integer size) {        Page<CommentDTO> commentDTOPage = commentService.getCommentPageByProfileName(name, page, size);        return ResponseEntity.ok(commentDTOPage);    }    @PreAuthorize("hasRole('ADMIN')")    @GetMapping("/admin/paging/by-surname/{surname}")    public ResponseEntity<Page<CommentDTO>> getCommentPageByProfileSurname(@PathVariable String surname,                                                                           @RequestParam(defaultValue = "1") Integer page,                                                                           @RequestParam(defaultValue = "10") Integer size) {        Page<CommentDTO> commentDTOPage = commentService.getCommentPageByProfileSurname(surname, page, size);        return ResponseEntity.ok(commentDTOPage);    }    @PreAuthorize("hasRole('MODERATOR')")    @GetMapping("/admin/filtering")    public ResponseEntity<Page<CommentDTO>> filtering(@RequestBody CommentFilterDTO filterDTO,                                                      @RequestParam(defaultValue = "1") Integer page,                                                      @RequestParam(defaultValue = "10") Integer size) {        Page<CommentDTO> commentDTOPage = commentService.filtering(filterDTO, page, size);        return ResponseEntity.ok(commentDTOPage);    }    @GetMapping("/all-replies/{id}")    public ResponseEntity<Page<CommentDTO>> getAllRepliesCommentPageById(@PathVariable String id,                                                                         @RequestParam(defaultValue = "1") Integer page,                                                                         @RequestParam(defaultValue = "10") Integer size) {        Page<CommentDTO> commentDTOPage = commentService.getAllRepliesCommentPageById(id, page, size);        return ResponseEntity.ok(commentDTOPage);    }}