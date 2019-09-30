package edu.asu.diging.oauth.tokens.core.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import edu.asu.diging.oauth.tokens.core.data.OAuthClientRepository;
import edu.asu.diging.oauth.tokens.core.model.impl.OAuthClient;
import edu.asu.diging.oauth.tokens.core.service.GrantTypes;
import edu.asu.diging.oauth.tokens.core.service.IOAuthClientManager;
import edu.asu.diging.oauth.tokens.core.service.OAuthCredentials;
import edu.asu.diging.oauth.tokens.core.service.OAuthScope;

@Transactional
public class OAuthClientManager implements ClientDetailsService, IOAuthClientManager {

    private OAuthClientRepository clientRepo;
    
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    private int accessTokenValidity;
    
    public OAuthClientManager(OAuthClientRepository repo, BCryptPasswordEncoder bCryptPasswordEncoder, int accessTokenValidity) {
        this.clientRepo = repo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.accessTokenValidity = accessTokenValidity;
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.citesphere.core.service.oauth.impl.IOAuthClientManager#loadClientByClientId(java.lang.String)
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Optional<OAuthClient> clientOptional = clientRepo.findById(clientId);
        if (clientOptional.isPresent()) {
            ClientDetails details = clientOptional.get();
            details.getAuthorities().size();
            return details;
        }
        return null;
    }
    
    /* (non-Javadoc)
     * @see edu.asu.diging.citesphere.core.service.oauth.impl.IOAuthClientManager#store(org.springframework.security.oauth2.provider.ClientDetails)
     */
    @Override
    public OAuthCredentials create(String name, String description, List<OAuthScope> scopes) {
        final OAuthClient client = new OAuthClient();
        client.setName(name);
        client.setDescription(description);
        String clientSecret = UUID.randomUUID().toString();
        client.setClientSecret(bCryptPasswordEncoder.encode(clientSecret));
        client.setAuthorizedGrantTypes(new HashSet<>());
        client.getAuthorizedGrantTypes().add(GrantTypes.CLIENT_CREDENTIALS);
        client.setAccessTokenValiditySeconds(accessTokenValidity);
        client.setScope(new HashSet<>());
        scopes.forEach(s -> client.getScope().add(s.getScope()));
        OAuthClient storeClient = clientRepo.save(client);
        return new OAuthCredentials(storeClient.getClientId(), clientSecret);
    }
    
}