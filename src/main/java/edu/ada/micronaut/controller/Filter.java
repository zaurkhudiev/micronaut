

package edu.ada.micronaut.controller;

import org.glassfish.jersey.internal.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
@Provider
public class Filter implements ContainerRequestFilter {
    private static final String AUTH_HEAD = "Authorization";
    private static final String AUTH_HEAD_PRE = "Basic";
    private static final String SECURED_URL = "secured";

    @Override
    public void filter(ContainerRequestContext reqContext) throws IOException {
        if (reqContext.getUriInfo().getPath().contains(SECURED_URL)) {
            List<String> auth = reqContext.getHeaders().get(AUTH_HEAD);

            if (auth != null && auth.size() > 0) {
                String authorizationToken = auth.get(0);
                authorizationToken = authorizationToken.replaceFirst(AUTH_HEAD_PRE, "");
                String decodedString = Base64.decodeAsString(authorizationToken);
                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();
                if ("user".equals(username) && "password".equals(password)) {
                    return;
                }

            }

            Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access the resource").build();
            reqContext.abortWith(unauthorizedStatus);
        }
    }
}