package org.acme.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubRepo {
    public String name;
    public boolean fork;
    public Owner owner;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Owner {
        public String login;
    }
}
