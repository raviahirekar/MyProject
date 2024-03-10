package com.example.Employee.config;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import com.nimbusds.jwt.JWTClaimNames;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

	private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
	
	@Value("${jwt.oauth.converter.principle-attribute}")
	private String principleAttribute;
	
	@Value("${jwt.oauth.converter.resource-id}")
	private String resourceId;
	

	@Override
	public AbstractAuthenticationToken convert(Jwt jwt) {
		Collection<? extends GrantedAuthority> autherities = Stream.concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
				extractResorceRoles(jwt).stream()).collect(Collectors.toSet());
		return new JwtAuthenticationToken(jwt, autherities, getPrincipleClaimName(jwt));
	}

	private String getPrincipleClaimName(Jwt jwt) {
		String jwtClaimName = JWTClaimNames.SUBJECT;
		if(principleAttribute != null) {
			jwtClaimName = principleAttribute;
		}
		return  jwt.getClaim(jwtClaimName);
	}

	private Collection<? extends GrantedAuthority> extractResorceRoles(Jwt jwt) {
		Map<String, Object> resorceAccess;
		Map<String, Object> resourse;
		Collection<String> ressorceRoles;

		if (jwt.getClaim("resource_access") == null) {
			return Set.of();

		}

		resorceAccess = jwt.getClaim("resource_access");
		if (resorceAccess.get(resourceId) == null) {
			return Set.of();

		}

		resourse = (Map<String, Object>) resorceAccess.get("master-realm");
		ressorceRoles = (Collection<String>) resourse.get("roles");

		return ressorceRoles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
				.collect(Collectors.toSet());
	}

}
