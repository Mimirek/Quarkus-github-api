package org.acme.Service;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.Client.GitHubClient;
import org.acme.DTO.BranchResponse;
import org.acme.DTO.RepositoryResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GitHubService {

    @Inject
    @RestClient
    GitHubClient gitHubClient;

    public Uni<List<RepositoryResponse>> getRepositories(String username) {
        return gitHubClient.getUserRepos(username)
                .onItem().transformToMulti(repos -> Multi.createFrom().iterable(
                        repos.stream().filter(repo -> !repo.fork).collect(Collectors.toList())
                ))
                .onItem().transformToUniAndMerge(repo ->
                        gitHubClient.getRepoBranches(repo.owner.login, repo.name)
                                .onItem().transform(branches -> {
                                    List<BranchResponse> branchResponses = branches.stream()
                                            .map(branch ->
                                                    new BranchResponse(branch.name, branch.commit.sha))
                                            .collect(Collectors.toList());
                                    return new RepositoryResponse(repo.name,
                                            repo.owner.login, branchResponses);
                                })
                )
                .collect().asList();
    }
}

