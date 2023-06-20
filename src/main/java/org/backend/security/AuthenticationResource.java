package org.backend.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.Calendar;
import java.util.Map;

@Path("authentication")
public class AuthenticationResource {

    public static final Key key = MacProvider.generateKey();
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(LogonRequest logonRequest){
        System.out.println("POST - Authenticate user");
        try{
            String rol = MyUser.validateLogin(logonRequest.username, logonRequest.password);
            if (rol == null) throw new IllegalArgumentException("Validation failed");
            String token = createToken(logonRequest.username, rol);
            return Response.ok(Map.of("JWT", token)).build();
        } catch(IllegalArgumentException e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }
    private String createToken(String username, String role){
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration.getTime())
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
    private static class LogonRequest{
        @JsonProperty("username")
        private String username;
        @JsonProperty("password")
        private String password;

    }

}
