package com.amankrmj.portfolio_backend.controller;

import com.amankrmj.portfolio_backend.model.contact.details.ContactModel;
import com.amankrmj.portfolio_backend.services.GithubFilesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/files")
public class GithubFilesController {

    private final GithubFilesService githubFilesService;

    public GithubFilesController(GithubFilesService githubFilesService) {
        this.githubFilesService = githubFilesService;
    }

    @GetMapping("/contacts")
    public List<ContactModel> getFilesInContacts() {
        return githubFilesService.getFilesInContacts();
    }
}
