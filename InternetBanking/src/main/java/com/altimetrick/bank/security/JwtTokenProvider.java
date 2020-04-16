package com.altimetrick.bank.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.altimetrick.bank.exceptions.ApplicationException;
import com.altimetrick.bank.model.Permissions;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JwtTokenProvider class
 * 
 * @author VSanthosh
 *
 */
@Component
public class JwtTokenProvider
{

	@Value("${security.jwt.token.secret-key:ALTIMTGPSUI@123#$}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length:3600000}")
	private long validityInMilliseconds;

	@Autowired
	private CustomerDetailsService customerDetails;

	@PostConstruct
	protected void init()
	{
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(String loginId, List<Permissions> permissions)
	{

		Claims claims = Jwts.claims().setSubject(loginId);
		claims.put("auth", permissions.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority()))
				.filter(Objects::nonNull).collect(Collectors.toList()));

		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder()//
				.setClaims(claims)//
				.setIssuedAt(now)//
				.setExpiration(validity)//
				.signWith(SignatureAlgorithm.HS512, secretKey)//
				.compact();
	}

	public Authentication getAuthentication(String token)
	{
		UserDetails userDetails = customerDetails.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUsername(String token)
	{
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req)
	{
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null) {
			return bearerToken;
		}
		return null;
	}

	public boolean isTokenExpired(String token)
	{
		try {
			return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration()
					.after(new Date());
		} catch (JwtException | IllegalArgumentException e) {
			throw new ApplicationException("JWT token expired", HttpStatus.UNAUTHORIZED);
		}

	}

	public boolean validateToken(String token)
	{
		try {
			if (Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token) != null && isTokenExpired(token)) {
				return true;
			} else {
				return false;
			}
		} catch (ExpiredJwtException | ApplicationException e) {
			throw new ApplicationException("JWT token expired", HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			throw new ApplicationException("Invalid JWT token", HttpStatus.FORBIDDEN);
		}
	}

}
