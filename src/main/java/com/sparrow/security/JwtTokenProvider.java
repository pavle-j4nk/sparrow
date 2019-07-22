package com.sparrow.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String TOKEN_BEARER = "Bearer";
    private static final String AUTHORITIES_CLAIMS = "aut";
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private int jwtExpirationS;

    @Value("${app.name}")
    private String appName;

    public String generateToken(UserDetails userDetails) {
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put(AUTHORITIES_CLAIMS, userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return Jwts.builder().setClaims(claims).setIssuer(appName).setIssuedAt(new Date())
                .setExpiration(generateExpirationDate()).signWith(SIGNATURE_ALGORITHM, jwtSecret).compact();
    }

    private Date generateExpirationDate() {
        return new Date(new Date().getTime() + jwtExpirationS * 1000);
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            claims.setIssuedAt(new Date());
            refreshedToken = Jwts.builder().setClaims(claims).signWith(SIGNATURE_ALGORITHM, jwtSecret).compact();
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = this.getIssuedAtDateFromToken(token);
        return (!(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset)) && (!(this.isTokenExpired(token))));
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
                | SignatureException | IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * Parse all claims from token ignoring token expiration date.
     * @param token JWT token
     * @return all token claims
     */
    private Claims getAllClaimsFromTokenUnsafe(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        } catch (Exception e) {
            claims = null;
        }
        return claims;

    }

    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (Exception e) {
            issueAt = null;
        }
        return issueAt;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String getToken(HttpServletRequest request) {
        if(request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals("token")) {
                    return c.getValue();
                }
            }
        }

        String authHeader = request.getHeader(HEADER_AUTHORIZATION);
        if (authHeader != null) {
            return authHeader.substring(TOKEN_BEARER.length() + 1);
        }

        return null;
    }

    public LoggedUser getUserFromToken(String jwt) {
        Claims claims = getAllClaimsFromToken(jwt);
        if (claims == null) {
            return null;
        }

        return new LoggedUser(claims.getSubject(), getAuthorities(claims));
    }

    public LoggedUser getUserFromTokenUnsafe(String jwt) {
        Claims claims = getAllClaimsFromTokenUnsafe(jwt);
        if (claims == null) {
            return null;
        }

        return new LoggedUser(claims.getSubject(), getAuthorities(claims));
    }

    private List<GrantedAuthority> getAuthorities(Claims claims) {
        List<String> authorityList = (List<String>) claims.get(AUTHORITIES_CLAIMS);
        String[] authorityArray = authorityList.toArray(new String[0]);
        return AuthorityUtils.createAuthorityList(authorityArray);
    }

}
