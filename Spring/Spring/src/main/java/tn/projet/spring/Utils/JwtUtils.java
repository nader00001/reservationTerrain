package tn.projet.spring.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtUtils {

    private SecretKey key;
    private static final long EXPIRATION_TIME = 86400000L; //24 hours or 86400000 milliseconds

    // Key initialization in constructor
    public JwtUtils(){
        String secretString = "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R3";
        byte[] keyBytes = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
        this.key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    // Generate JWT Token
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // Set the username as the subject
                .setIssuedAt(new Date(System.currentTimeMillis())) // Set the issue time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set the expiration time
                .signWith(key) // Sign with the specified key
                .compact();
    }

    // Generate Refresh Token with Claims
    public String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(claims) // Add custom claims if needed
                .setSubject(userDetails.getUsername()) // Set the subject
                .setIssuedAt(new Date(System.currentTimeMillis())) // Issue time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiration time
                .signWith(key) // Sign with the same key
                .compact();
    }

    // Extract Username from Token
    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    // Extract claims from token (works with any claim)
    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key) // Set the signing key to validate the token
                .build()
                .parseClaimsJws(token) // Parse the token to get the claims
                .getBody();

        return claimsTFunction.apply(claims);
    }

    // Validate the token
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Check if the token is expired
    public boolean isTokenExpired(String token){
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
