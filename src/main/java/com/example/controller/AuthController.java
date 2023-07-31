package com.example.controller;import com.example.dto.ApiResponse;import com.example.dto.AuthDTO;import com.example.dto.RegistrationDTO;import com.example.service.AuthService;import com.example.service.ProfileService;import com.example.util.HTMLUtil;import jakarta.servlet.http.HttpServletResponse;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import java.io.IOException;@RestController@RequestMapping("/api/v1/auth")public class AuthController {    @Autowired    AuthService authService;    @Autowired    ProfileService profileService;    @PostMapping(value = "/login")    public ResponseEntity<ApiResponse> login(@RequestBody AuthDTO authDTO) {        return ResponseEntity.ok(authService.login(authDTO));    }    @PostMapping(value = "/registration")    public ResponseEntity<ApiResponse> registration(@RequestBody RegistrationDTO registrationDTO) {        return ResponseEntity.ok(authService.registration(registrationDTO));    }//    @GetMapping(value = "/verification/email/{jwt}")//    public ResponseEntity<ApiResponse> registration2(@PathVariable String jwt) {//        return ResponseEntity.ok(authService.emailVerification(jwt));//    }    @GetMapping(value = "/verification/email/{jwt}")    public void registration(@PathVariable String jwt,                                                    HttpServletResponse response) {        ApiResponse apiResponse = authService.emailVerification(jwt);        if (apiResponse.getStatus()) {            try {                response.setContentType("text/html");                response.setCharacterEncoding("UTF-8");                response.getWriter().write(HTMLUtil.getSuccessRegistration());                response.getWriter().flush();            } catch (IOException e) {                e.printStackTrace();            }        } else {            ResponseEntity<ApiResponse> error = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);        }    }    @PostMapping(value = "/save/350-gmail")    public ResponseEntity<?> saveGmail() {        return ResponseEntity.ok(authService.sendMessage());    }    @PostMapping(value = "/save/send-to-gmail")    public ResponseEntity<?> sendToGmail() {        return ResponseEntity.ok(authService.sendMessage());    }}