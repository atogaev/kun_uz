package com.example.util;

import com.example.dto.JwtDTO;
import com.example.enums.ProfileRole;
import com.example.exp.AppMethodNotAllowedExeption;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;

public class SecurityUtil {
    public static JwtDTO getJwtDTO(String authToken) {
        if (authToken.startsWith("Bearer")) {
            String jwt = authToken.substring(7);
            return JWTUtil.decode(jwt);
        }
        return null;
    }

    public static JwtDTO hasRole(HttpServletRequest request, ProfileRole... requiredRole) {

       Long id = (Long) request.getAttribute("id");
       ProfileRole role = (ProfileRole) request.getAttribute("role");
        if(requiredRole == null){
            return new JwtDTO(id,role);
        }
        boolean found = false;
        for (ProfileRole r : requiredRole) {
            if (r.equals(role)) {
                found = true;
            }
        }
        if (!found){
            throw new AppMethodNotAllowedExeption();
        }
        return new JwtDTO(id,role);
    }
    public static JwtDTO hasRole(String authToken, ProfileRole... requiredRole) {

        JwtDTO jwtDTO = getJwtDTO(authToken);
        if(requiredRole == null){
            return jwtDTO;
        }
        boolean found = false;
        for (ProfileRole role : requiredRole) {
            if (jwtDTO.getRole().equals(role)) {
                found = true;
            }
        }
        if (!found){
            throw new AppMethodNotAllowedExeption();
        }
        return jwtDTO;
    }
}
