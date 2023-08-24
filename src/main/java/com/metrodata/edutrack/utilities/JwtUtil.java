package com.metrodata.edutrack.utilities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final String SECRET = "xmkkosigspiPIifepi3kjajfbfbbesfsf7f8gfiubfbiufbabu7fgfbuwibwui3";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public List<String> extractRoles(String token) {
        final Claims claims = extractAllClaims(token);
        LinkedHashMap<String, Object> userJson = claims.get("user", LinkedHashMap.class);
        return (List<String>) userJson.get("roles");
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    public String generateToken(String accessToken, String username) {
        return createRefreshToken(accessToken, username);
    }

    public String generateRefreshToken(String accessToken, String username) {
        return createRefreshToken(accessToken, username);
    }

    private String createToken(String oldToken, String subject) {
        Claims oldClaims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(oldToken).getBody();
        Claims newClaims = new DefaultClaims(oldClaims);

        return Jwts.builder()
                .setClaims(newClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
    }

    private String createRefreshToken(String oldToken, String subject) {
        Claims oldClaims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(oldToken).getBody();
        Claims newClaims = new DefaultClaims(oldClaims);

        return Jwts.builder()
                .setClaims(newClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
    }

    public Boolean validateToken(String token) {
        try {
            String cleanToken = token.replace("Bearer ", "");
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(cleanToken);
            return true;
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    public Boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    private Date extractExpirationDate(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }
}
