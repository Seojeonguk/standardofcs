package com.js.freeproject.global.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.js.freeproject.domain.user.application.UserService;
import com.js.freeproject.domain.user.domain.User;
import com.js.freeproject.global.util.RedisUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

	private final UserService userService;
	private final RedisUtil redisUtil;
	
	@Bean
	public FilterRegistrationBean JwtFilterRegistration(JwtFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean(filter);
		registration.setEnabled(false);
		return registration;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(TokenProvider.HEADER_STRING);

		String email = null;
		String token = null;

		if (header != null && header.startsWith(TokenProvider.TOKEN_PREFIX)) {
			token = header.substring(TokenProvider.TOKEN_PREFIX.length());

			try {
				email = TokenProvider.getSubject(token);
			} catch (TokenExpiredException e) {
				String refresh_token = redisUtil.getData(token);
				email = TokenProvider.getSubject(refresh_token);
				log.info("{} 사용자가 token을 재발급 받았습니다.",email);
			} catch (Exception e) {
				log.info("JWT Filter Error : {}",e.toString());
			}
		}

		if (email != null) {
			Authentication authentication = getAuthentication(email);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			log.info("Security Context에 {} 인증정보를 저장했습니다.", authentication.getName());
		} else {
			//response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}

		chain.doFilter(request, response);
	}

	@Transactional(readOnly = true)
	public Authentication getAuthentication(String email) {
		User user = userService.findByUserEmail(email);
		
		CustomUserDetails accountDetails = new CustomUserDetails(user);
		UsernamePasswordAuthenticationToken jwtAuthentication = new UsernamePasswordAuthenticationToken(email, null,
				accountDetails.getAuthorities());
		jwtAuthentication.setDetails(accountDetails);
		return jwtAuthentication;
	}
}