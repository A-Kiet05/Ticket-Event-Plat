package com.example.ticketEvent.config;


@Component
public class JwtAuthenticationConverter implements Converter<Jwt, JwtAuthenticationToken> {
    // Implement JWT authentication conversion logic here

    @Override
    public JwtAuthenticationToken convert(Jwt jwt) {
        // Extract user information from the JWT and create an authentication token
        Collection<GrantedAuthority> authorities = extractAuthorities(jwt);
        return new JwtAuthenticationToken(jwt, authorities);
    }
    
    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {

        Map<String, Object> realmAccess = jwt.getClaims("realm_access");
        
        if(null == realmAccess || !realmAccess.containsKey("roles")) {
            return Collections.emptyList();
        }
       
       @SuppressWarnings ("unchecked")
       List<String> roles = (List<String>) realmAccess.get("roles");

       roles.stream().filter(role -> role.startsWith("ROLE_"))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }
} 