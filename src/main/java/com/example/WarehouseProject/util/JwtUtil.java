package com.example.WarehouseProject.util;

import com.example.WarehouseProject.dto.JwtDTO;
import com.example.WarehouseProject.enums.UserRole;
import com.example.WarehouseProject.exceptions.MethodNotAllowedException;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

public class JwtUtil {
    private static final int tokenLiveTime = 1000 * 3600 * 24; // 1-day
    private static final String secretKey = "dasda143mazgi";


   /* public static String encode(Integer profileId, UserRole role) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);

        jwtBuilder.claim("id", profileId);
        jwtBuilder.claim("role", role);

        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (tokenLiveTime)));
        jwtBuilder.setIssuer("Omborxona");
        return jwtBuilder.compact();
    }
    public static JwtDTO decode(String token) {
            JwtParser jwtParser = Jwts.parser();
            jwtParser.setSigningKey(secretKey);
            Jws<Claims> jws = jwtParser.parseClaimsJws(token);
            Claims claims = jws.getBody();
            Integer profileId = (Integer) claims.get("id");
            String role = (String) claims.get("role");
            UserRole profileRole = UserRole.valueOf(role);
            return new JwtDTO(profileId, profileRole);
    }*/

    public static String encode(String phone, UserRole role) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);

        jwtBuilder.claim("phone", phone);
        jwtBuilder.claim("role", role);

        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (tokenLiveTime)));
        jwtBuilder.setIssuer("Kunuz test portali");
        return jwtBuilder.compact();
    }
    public static JwtDTO decode(String token) {
        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(secretKey);
        Jws<Claims> jws = jwtParser.parseClaimsJws(token);
        Claims claims = jws.getBody();
        String phone = (String) claims.get("phone");
        String role = (String) claims.get("role");
        UserRole profileRole = UserRole.valueOf(role);
        return new JwtDTO(phone, profileRole);
    }



    public static JwtDTO getJwtDTO(String authorization) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        return JwtUtil.decode(jwt);
    }

    public static JwtDTO getJwtUtil(String authorization, UserRole... roleList) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        boolean roleFound = false;
        for (UserRole role : roleList) {
            if (jwtDTO.getRole().equals(role)) {
                roleFound = true;
                break;
            }
        }
        if (!roleFound) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return jwtDTO;
    }
    public static void checkForRequiredRole(HttpServletRequest request, UserRole... roleList) {
        UserRole jwtRole = (UserRole) request.getAttribute("role");
        boolean roleFound = false;
        for (UserRole role : roleList) {
            if (jwtRole.equals(role)) {
                roleFound = true;
                break;
            }
        }
        if (!roleFound) {
            throw new MethodNotAllowedException("Method not allowed");
        }
    }

}
