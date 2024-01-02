package org.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import org.example.utils.TokenUtils;


public class Main {
    public static void main(String[] args) {
        String s = TokenUtils.genAccessToken("zhangsan");
        System.out.println(s);
        Jws<Claims> claimsJws = TokenUtils.parseClaim(s);
        JwsHeader jwsHeader = TokenUtils.parseHeader(s);
        Claims claims = TokenUtils.parsePayload(s);
        System.out.println(claimsJws);
        System.out.println(jwsHeader);
        System.out.println(claims);
    }
}