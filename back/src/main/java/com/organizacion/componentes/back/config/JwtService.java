package com.organizacion.componentes.back.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY="36ed358b672215f98c1e7a3c7fd44d651987372155bc694d114b1b80a6fe0c59611718d18eb9d2a73b190e962ef7e7dbbca4399f8a15e9f1cafc0ec0ca6614f62ef12f340a1d307396b89053494e561ea67a614979779eb2bcc7227858eabe1352076f59ad5985fd7c3ad8cf3671a97d0c52f402499354fd179569a57d03ad504691dd5bc354031627a73c6a03651bef781899f419e4952ebaaff1456ae893496c5558180dc2c4baaf30a1925f239ce65b3b0b84b34cdeabcf0f35049c35ba3918ff94329d3ea733964170bd839bb1195d9cbba7112d5b417e6c2d1d445d4ab9f69cc4f801c7f5ad760b4a015841ea58face04ac43695d787ef8c4e3fa750171fd9186d750ae53a76b493727e52559d6fb74f46f86bfca6613b7239fb97183a3aa1ecc32aceabf9381fa8595d6bb3f066b31bce04721d5354865e75c27ae2517e8c89aa5a03259bce8f11050832114dcaeb3f52b97660b47c6d12a67cef6ff488602ec11c21bd1c5fd8b19e49f5c15b8023d9f4b6e02ff7818f45a0485a4476ebf4e4ffc12a72e27a25837a78562ca30550b7e222d48ad582ad212649ba9085b393404052816f35e90d5a3b22d6653ebe34d11f5c9cd0c3e5b567bad6d8339b366be88b6beb73b3f22c0ee900bf4e1ff1e1e651dccd5ffd342ee97641de1e7d675c6f65723a834a7ccd91951ed6214e7f93095f9c4642304e6fc76de9c6d151f";
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(
        Map<String, Object> extractClaims,
        UserDetails userDetails
    ){
        return Jwts
        .builder()
        .setClaims(extractClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
    }
    private Claims extractAllClaims(String token) {
        return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();

    }
    private Key getSignInKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
