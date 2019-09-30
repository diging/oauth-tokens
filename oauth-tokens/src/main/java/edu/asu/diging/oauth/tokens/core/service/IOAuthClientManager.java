package edu.asu.diging.oauth.tokens.core.service;

import java.util.List;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

public interface IOAuthClientManager {

    ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException;

    OAuthCredentials create(String name, String description, List<OAuthScope> scopes);

}