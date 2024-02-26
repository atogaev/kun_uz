package com.example.util;

import com.example.dto.JwtDTO;
import com.example.enums.ProfileRoleEnum;
import com.example.exp.AppMethodNotAllowedExeption;
import jakarta.servlet.http.HttpServletRequest;

public class SecurityUtil {
    public static JwtDTO getJwtDTO(String authToken) {
        if (authToken.startsWith("Bearer")) {
            String jwt = authToken.substring(7);
            return JWTUtil.decode(jwt);
        }
        return null;
    }

    public static JwtDTO hasRole(HttpServletRequest request, ProfileRoleEnum... requiredRole) {

       Long id = (Long) request.getAttribute("id");
       ProfileRoleEnum role = (ProfileRoleEnum) request.getAttribute("role");
        if(requiredRole == null){
            return new JwtDTO(id,role);
        }
        boolean found = false;
        for (ProfileRoleEnum r : requiredRole) {
            if (r.equals(role)) {
                found = true;
                break;
            }
        }
        if (!found){
            throw new AppMethodNotAllowedExeption();
        }
        return new JwtDTO(id,role);
    }
    public static JwtDTO hasRole(String authToken, ProfileRoleEnum... requiredRole) {

        JwtDTO jwtDTO = getJwtDTO(authToken);
        if(requiredRole == null){
            return jwtDTO;
        }
        boolean found = false;
        for (ProfileRoleEnum role : requiredRole) {
            assert jwtDTO != null;
            if (jwtDTO.getRole().equals(role)) {
                found = true;
                break;
            }
        }
        if (!found){
            throw new AppMethodNotAllowedExeption();
        }
        return jwtDTO;
    }
}
