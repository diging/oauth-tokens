package edu.asu.diging.oauth.tokens.core.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import edu.asu.diging.oauth.tokens.core.data.OAuthClientRepository;
import edu.asu.diging.oauth.tokens.core.model.impl.OAuthClient;

@Service
@Transactional
public class OAuthClientDetailsService implements ClientDetailsService {

    @Autowired
    private OAuthClientRepository clientRepo;
    
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
}
