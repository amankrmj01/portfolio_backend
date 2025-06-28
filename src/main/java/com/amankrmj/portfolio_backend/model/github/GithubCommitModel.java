package com.amankrmj.portfolio_backend.model.github;

import java.io.Serializable;

public class GithubCommitModel implements Serializable {
    private String message;
    private String content;
    private Committer committer;

    public GithubCommitModel() {}

    public GithubCommitModel(String message, String content, Committer committer) {
        this.message = message;
        this.content = content;
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Committer getCommitter() {
        return committer;
    }

    public void setCommitter(Committer committer) {
        this.committer = committer;
    }
}
