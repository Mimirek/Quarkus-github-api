package org.acme.Client;
import org.acme.DTO.GitHubBranch;
import org.acme.DTO.GitHubRepo;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/")
@RegisterRestClient(configKey = "github-client")
public interface GitHubClient { @GET
@Path("/users/{username}/repos")
@Produces(MediaType.APPLICATION_JSON)
Uni<List<GitHubRepo>> getUserRepos(@PathParam("username") String username);

    @GET
    @Path("/repos/{owner}/{repo}/branches")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<GitHubBranch>> getRepoBranches(@PathParam("owner") String owner,
                                            @PathParam("repo") String repo);
}