package co.com.common.utils;

import java.util.UUID;

import co.com.entity.response.JWTResponseDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

public class Utils {
	
	private static final String JWT_SIGN_KEY = "pRoj33ct_aPp*";
	
	public static String encodeJWTToken(JWTResponseDTO userJWTResponseDTO){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date expDate = null;
    	Claims claims = new DefaultClaims();
    	//claims.put("userId", userJWTResponseDTO.getUser().getId());
    	//claims.put("name", userJWTResponseDTO.getUser().getName());
    	claims.put("Authority", JWT_SIGN_KEY);
    	try {
        	expDate = sdf.parse("20251231");
		} catch (ParseException e) {
			e.printStackTrace();
			expDate = new Date( new Date().getTime()+1000000000 );
		}
        return Jwts.builder()
        	.setId(generateUUID())
        	.setClaims(claims)
            .setSubject(userJWTResponseDTO.getUsername())           
            .setExpiration(expDate)
            .signWith(SignatureAlgorithm.HS512, JWT_SIGN_KEY)
            .compact();		
	}
	
	public static Claims decodeJWTToken(String token){
		return token == null ? null:Jwts.parser()
		        .setSigningKey(JWT_SIGN_KEY)
		        .parseClaimsJws(token.replace("Bearer", "")) //este metodo es el que valida
		        .getBody();
	}
	
	public static String encodeAdditionalJWTToken(String email, String name, String UserId, String role){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	Date expDate = null;
    	Claims claims = new DefaultClaims();
    	claims.put("userId", UserId);
    	claims.put("name", name);
    	claims.put("rol", "ADMIN");
        try {
        	expDate = sdf.parse("20251231");
		} catch (ParseException e) {
			e.printStackTrace();
			expDate = new Date( new Date().getTime()+1000000000 );
		}
		return Jwts.builder()
				.setClaims(claims)
			    .setSubject(email)
			    .setExpiration( expDate )
			    .signWith(SignatureAlgorithm.HS512, JWT_SIGN_KEY)
			    .compact();
	}
	
	public static String generateUUID(){
		return UUID.randomUUID().toString();
	}

}
