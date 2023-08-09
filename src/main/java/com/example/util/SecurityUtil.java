package com.example.util;import com.example.dto.JwtDTO;import com.example.entity.ProfileEntity;import com.example.exception.AppMethodNotAllowedException;import com.example.exception.UnAuthorizedException;import jakarta.servlet.http.HttpServletRequest;import lombok.experimental.UtilityClass;@UtilityClasspublic class SecurityUtil {    public JwtDTO getJwtDTO(String authToken) {        if (authToken.startsWith("Bearer ")) {            String jwt = authToken.substring(7);            return JWTUtil.decode(jwt);        }        throw new UnAuthorizedException("Not authorized");    }//    public JwtDTO hasRole(HttpServletRequest request, ProfileEntity.Role... requiredRoles) {//        Long id = (Long) request.getAttribute("id");//        ProfileEntity.Role role = (ProfileEntity.Role) request.getAttribute("role");////        if (requiredRoles == null) {//            return new JwtDTO(id, role);//        }////        boolean found = false;//        for (ProfileEntity.Role required : requiredRoles) {//            if(role.equals(required)) {//                found = true;//                break;//            }//        }//        if (!found) {//            throw new AppMethodNotAllowedException("method not allowed!");//        }//        return new JwtDTO(id, role);//    }     public JwtDTO hasRole(HttpServletRequest request, ProfileEntity.Role... requiredRoles) {        String phone = (String) request.getAttribute("phone");         if (requiredRoles == null) {             return new JwtDTO(phone, null);         }         ProfileEntity.Role role = (ProfileEntity.Role) request.getAttribute("role");        boolean found = false;        for (ProfileEntity.Role required : requiredRoles) {            if(role.equals(required)) {                found = true;                break;            }        }        if (!found) {            throw new AppMethodNotAllowedException("method not allowed!");        }        return new JwtDTO(phone, role);    }}