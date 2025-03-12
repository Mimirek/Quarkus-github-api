package org.acme.DTO;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class BranchResponse {
    private String name;
    private String lastCommitSha;

    public BranchResponse(String name, String lastCommitSha) {
        this.name = name;
        this.lastCommitSha = lastCommitSha;
    }

    public String getName() {
        return name;
    }

    public String getLastCommitSha() {
        return lastCommitSha;
    }

    @Override
    public String toString() {
        return "BranchResponse{" +
                "branchName='" + name + '\'' +
                "lastCommitSha='" + lastCommitSha + '\'' +
                '}';
    }
}