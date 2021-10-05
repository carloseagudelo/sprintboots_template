package co.com.api.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;

import co.com.common.utils.Utils;
import co.com.entity.response.JWTResponseDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Collections.emptyList;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtUtil {

	@Value("${security.signing-key}")
	private static String signingKey;
	
	static void addAuthentication(HttpServletResponse res, JWTResponseDTO userJWTResponseDTO) {
        String token = Utils.encodeJWTToken(userJWTResponseDTO);
        res.addHeader("Authorization", "Bearer " + token);
    }
    
    static Authentication getAuthentication(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        if (token != null) {
            String user = Utils.decodeJWTToken(token).getSubject();
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }
}
