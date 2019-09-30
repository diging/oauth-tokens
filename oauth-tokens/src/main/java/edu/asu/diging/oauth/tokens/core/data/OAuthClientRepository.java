package edu.asu.diging.oauth.tokens.core.data;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.asu.diging.oauth.tokens.core.model.impl.OAuthClient;

public interface OAuthClientRepository extends JpaRepository<OAuthClient, String> {

}