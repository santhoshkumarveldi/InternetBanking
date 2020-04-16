package com.altimetrick.bank.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.altimetrick.bank.exceptions.ApplicationException;

/**
 * We should use OncePerRequestFilter since we are doing a database call, there
 * is no point in doing this more than once
 * 
 * @author VSanthosh
 *
 */
public class JwtTokenFilter extends OncePerRequestFilter
{

	private JwtTokenProvider jwtTokenProvider;

	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider)
	{
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException
	{
		String token = jwtTokenProvider.resolveToken(httpServletRequest);
		try {
			if (token != null && jwtTokenProvider.validateToken(token)) {
				Authentication auth = jwtTokenProvider.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}

			filterChain.doFilter(httpServletRequest, httpServletResponse);

		} catch (ApplicationException ex) {
			// this is very important, since it guarantees the user is not authenticated at
			// all
			SecurityContextHolder.clearContext();
			httpServletResponse.sendError(ex.getHttpStatus().value(), ex.getMessage());
			return;
		}

	}

}
