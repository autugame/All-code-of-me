package org.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class TokenUtils {
    /**
     * Expiration time(Unit:second).
     */
    public static final int ACCESS_EXPIRE = 24 * 60 * 60;
    /**
     * Encryption algorithm.
     */
    private final static SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = Jwts.SIG.HS256;
    /**
     * Secret key.
     */
    private final static String SECRET = "zQ9`gR6#vR2^pS9&iC2]tD0#nO0$fH5{";
    /**
     * Secret key instance.
     */
    public static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    /**
     * Jwt issuer.
     */
    private final static String JWT_ISS = "autumn";
    /**
     * Jwt subject.
     */
    private final static String SUBJECT = "qfedu";

    /**
     * iss: jwt issuer
     * sub: jwt for user
     * aud: The party receiving the jwt
     * exp: jwt expiration time
     * nbf: Define how long before the jwt was unavailable
     * iat: Data of issuance of jwt
     * jti: Unique identification of jwt
     */
    public static String genAccessToken(String username) {
        // token id
        String uuid = UUID.randomUUID().toString();
        Date exprireDate = Date.from(Instant.now().plusSeconds(ACCESS_EXPIRE));

        return Jwts.builder()
                // Set the header information.
                .header()
                .add("typ", "JWT")
                .add("alg", "HS256")
                .and()
                // Set the user-defined payload
                .claim("username", username)
                // token id
                .id(uuid)
                // expiration time
                .expiration(exprireDate)
                // issuance time
                .issuedAt(exprireDate)
                // subject
                .subject(SUBJECT)
                // issuer
                .issuer(JWT_ISS)
                // signature
                .signWith(KEY, ALGORITHM)
                .compact();
    }
    /**
     * resolve token
     * @param token token
     * @return Jws<Claims>
     */
    public static Jws<Claims> parseClaim(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token);
    }

    public static JwsHeader parseHeader(String token) {
        return parseClaim(token).getHeader();
    }

    public static Claims parsePayload(String token) {
        return parseClaim(token).getPayload();
    }
}
