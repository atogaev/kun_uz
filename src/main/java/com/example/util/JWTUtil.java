package com.example.util;

import com.example.dto.JwtDTO;
import com.example.enums.ProfileRoleEnum;
import com.example.exp.UnAuthorizedException;
import io.jsonwebtoken.*;

import java.util.Date;

public class JWTUtil {
    private static final String secretKey = "mazgiKey";
    private static final int tokenLiveTime = 1000*3600*24; //1 day * 30
    public static String encode(Long profileId, ProfileRoleEnum role) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);

        jwtBuilder.claim("id", profileId);
        jwtBuilder.claim("role", role.toString());

        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (tokenLiveTime)));
        jwtBuilder.setIssuer("kun_uz test portali");
        return jwtBuilder.compact();
    }
    public static JwtDTO decode(String token) {
        try{
            JwtParser jwtParser = Jwts.parser();
            jwtParser.setSigningKey(secretKey);
            Jws<Claims> jws = jwtParser.parseClaimsJws(token);
            Claims claims = jws.getBody();
            Integer id = (Integer) claims.get("id");
            String role = (String) claims.get("role");
            ProfileRoleEnum profileRole = ProfileRoleEnum.valueOf(role);

            return new JwtDTO(Long.valueOf(id), profileRole);
        }catch (JwtException e){
           throw new UnAuthorizedException("Your session expired");
        }

    }
}
