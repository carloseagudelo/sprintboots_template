package co.com.api.filter;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.common.encrypt.Encryption;
import co.com.common.encrypt.EncryptionException;
import co.com.common.messages.MessageUtils;
import co.com.common.response.FinalResponse;
import co.com.entity.request.LoginUserRequestDTO;
import co.com.entity.response.JWTResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;


public class LoginFilter extends AbstractAuthenticationProcessingFilter {
	
	@Autowired FinalResponse finalResponse; 
	@Autowired MessageUtils messages;
	
    public LoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication( HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
    	
    	String passwordEncrypted = null;
    	InputStream body = req.getInputStream();
        LoginUserRequestDTO user = new ObjectMapper().readValue(body, LoginUserRequestDTO.class);
		
		try {
			passwordEncrypted = Encryption.encrypt(user.getPassword());
		} catch (EncryptionException ex) {
			ex.printStackTrace();
		}
		UsernamePasswordAuthenticationToken login =  new UsernamePasswordAuthenticationToken( user.getEmail(),  passwordEncrypted,  Collections.emptyList());
        return getAuthenticationManager().authenticate(login);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
    	JwtUtil.addAuthentication(res, (JWTResponseDTO)auth.getPrincipal());
    }
    
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) throws IOException, ServletException {
    	res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}