package org.ucsccaa.mms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ucsccaa.mms.domains.Authorization;
import org.ucsccaa.mms.models.ServiceResponse;
import org.ucsccaa.mms.models.Status;
import org.ucsccaa.mms.services.AuthorService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("authorization")
public class AuthorizationController {
    @Autowired
    private AuthorService authorService;
    
    @PostMapping
    public ServiceResponse<URI> addAuthority(@RequestBody String level, @RequestBody String authority, HttpServletRequest req) throws URISyntaxException {
        try {
            authorService.addAuthority(Authorization.LEVEL.valueOf(level), authority);
            return new ServiceResponse<>(new URI(req.getRequestURI() + "/" + level + "/" + authority));
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
    }
}