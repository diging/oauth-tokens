package edu.asu.diging.oauth.tokens.config;

import edu.asu.diging.oauth.tokens.config.impl.OAuthTokensConfigurationProviderImpl;

public interface OAuthTokensConfigurationProvider {
    
    /**
     * Helper method, if used without component scan or XML bean declaration.
     * @return
     */
    public static OAuthTokensConfigurationProvider build() {
        return new OAuthTokensConfigurationProviderImpl();
    }

    String getAddAppPath();

    String getAddAppView();

    String getAddAppPostSuccessView();

    

}