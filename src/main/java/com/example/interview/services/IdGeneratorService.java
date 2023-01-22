package com.example.interview.services;

import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;


public abstract class IdGeneratorService {

    protected final RestTemplate restTemplate;

    protected final URI URI = new URI("https://www.uuidtools.com/api/generate/v1");

    protected IdGeneratorService(RestTemplate restTemplate) throws URISyntaxException {
        this.restTemplate = restTemplate;
    }
}
