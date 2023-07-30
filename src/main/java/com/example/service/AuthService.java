package com.example.service;import com.example.dto.*;import com.example.entity.EmailHistory;import com.example.entity.ProfileEntity;import com.example.exception.AppBadRequestException;import com.example.repository.EmailAddressRepository;import com.example.repository.EmailHistoryRepository;import com.example.repository.ProfileRepository;import com.example.util.JWTUtil;import com.example.util.MD5Util;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.beans.factory.annotation.Value;import org.springframework.http.ResponseEntity;import org.springframework.stereotype.Service;import javax.imageio.ImageIO;import java.awt.image.BufferedImage;import java.io.ByteArrayOutputStream;import java.io.File;import java.io.IOException;import java.util.Optional;@Servicepublic class AuthService {    @Autowired    ProfileRepository profileRepository;    @Autowired    private MailSanderService mailSanderService;    @Autowired    private EmailHistoryRepository emailHistoryRepository;    @Value("${server.url}")    private String serverUrl;    public byte[] loadImage(String fileName) {        try {            BufferedImage originalImage = ImageIO.read(new File("attaches/" + fileName));            ByteArrayOutputStream baos = new ByteArrayOutputStream();            ImageIO.write(originalImage, "png", baos);            baos.flush();            byte[] imageInByte = baos.toByteArray();            baos.close();            return imageInByte;        } catch (Exception e) {            return new byte[0];        }    }    public ApiResponse login(AuthDTO authDTO) {        Optional<ProfileEntity> optionalProfile = profileRepository                .findByPhone(authDTO.getPhone());        if (optionalProfile.isEmpty()) {            return new ApiResponse(false, "login or phone not found!");        }        ProfileEntity profileEntity = optionalProfile.get();        if (!profileEntity.getPassword().equals(MD5Util.encode(authDTO.getPassword()))) {            return new ApiResponse(false, "login or phone not found!");        }        if (profileEntity.getStatus().equals(ProfileEntity.Status.INACTIVE)) {            return new ApiResponse(false, "your status inactive!");        }        ProfileDTO response = new ProfileDTO();        response.setName(profileEntity.getName());        response.setSurname(profileEntity.getSurname());        response.setId(profileEntity.getId());        response.setRole(profileEntity.getRole());        response.setPhone(profileEntity.getPhone());        response.setJwt(JWTUtil.encode(profileEntity.getId(), profileEntity.getRole()));        return new ApiResponse(true, response);    }    public ApiResponse registration(RegistrationDTO registrationDTO) {        Optional<ProfileEntity> optionalProfile = profileRepository.findByEmail(registrationDTO.getEmail());        if (optionalProfile.isPresent()) {            if (optionalProfile.get().getStatus().equals(ProfileEntity.Status.REGISTRATION)) {                profileRepository.delete(optionalProfile.get()); // delete            } else {                return new ApiResponse(false, "Email already exists.");            }        }        ProfileEntity entity = new ProfileEntity();        entity.setName(registrationDTO.getName());        entity.setSurname(registrationDTO.getSurname());        entity.setEmail(registrationDTO.getEmail());        entity.setPassword(MD5Util.encode(registrationDTO.getPassword()));        entity.setRole(ProfileEntity.Role.USER);        entity.setStatus(ProfileEntity.Status.REGISTRATION);        entity.setPhone(registrationDTO.getPhone());        profileRepository.save(entity);        String jwt = JWTUtil.encodeEmailJwt(entity.getId());        String url = serverUrl + "/api/v1/auth/verification/email/" + jwt;        EmailHistory history = new EmailHistory();        history.setMessage(url);        history.setProfileId(entity.getId());        emailHistoryRepository.save(history);//        mailSenderService.sendEmail(dto.getEmail(), "Kun uz registration compilation",//                "To complete your registration please click following link: " + url);//        return new ApiResponseDTO(true, "The verification link was send to email.");//        mailSanderService.sendEmail(registrationDTO.getEmail(), "Kun uz ", "dunyosanitogangmasdanassslom");        mailSanderService.sendEmailVerification(registrationDTO.getEmail(), registrationDTO.getName(), entity.getId());        return new ApiResponse(true, "SUCCESS CREATED PROFILE!");    }    public ApiResponse emailVerification(String jwt) {        JwtDTO jwtDTO = JWTUtil.decodeEmailJwt(jwt);        Optional<ProfileEntity> exists = profileRepository.findById(jwtDTO.getId());        if (exists.isEmpty()) {            throw new AppBadRequestException("Profile not found");        }        ProfileEntity entity = exists.get();        if (!entity.getStatus().equals(ProfileEntity.Status.REGISTRATION)) {            throw new AppBadRequestException("Wrong status");        }        entity.setStatus(ProfileEntity.Status.ACTIVE);        entity.setVisibility(true);        profileRepository.save(entity); // update        return new ApiResponse(true, "Registration completed");    }    public ApiResponse sendMessage() {        String resp = mailSanderService.sendMessage();        return new ApiResponse(true, resp);    }    public ApiResponse sendToGmail(String email) {        mailSanderService.sendEmailToAccount(email);        return new ApiResponse(true, "SUCCESS");    }}