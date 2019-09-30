package edu.asu.diging.oauth.tokens.config;

import edu.asu.diging.oauth.tokens.config.impl.ConfigurationProviderImpl;

public interface ConfigurationProvider {
    
    /**
     * Helper method, if used without component scan or XML bean declaration.
     * @return
     */
    public static ConfigurationProvider build() {
        return new ConfigurationProviderImpl();
    }

    String getAddAppPath();

    String getAddAppView();

    String getAddAppPostSuccessView();

    

}