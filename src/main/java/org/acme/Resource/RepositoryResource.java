package org.acme.Resource;


import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.Service.GitHubService;

import java.util.Map;

@Path("/repositories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RepositoryResource {

    @Inject
    GitHubService gitHubService;

    @GET
    @Path("/{username}")
    public Uni<Response> getUserRepositories(@PathParam("username") String username) {
        return gitHubService.getRepositories(username)
                .onItem().transform(repos -> Response.ok(repos).build())
                .onFailure().recoverWithItem(throwable -> {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity(Map.of("status", 404, "message", "User not found"))
                            .build();
                });

    }
}
