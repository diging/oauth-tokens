package edu.asu.diging.oauth.tokens.config.impl;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import edu.asu.diging.oauth.tokens.config.OAuthTokensConfigurationProvider;
import edu.asu.diging.oauth.tokens.config.OAuthTokens;
import edu.asu.diging.oauth.tokens.config.OAuthTokensConfiguration;

@Service
public class OAuthTokensConfigurationProviderImpl implements OAuthTokensConfigurationProvider {

    private OAuthTokens config;
    
    @Autowired
    private ApplicationContext context;
    
    @PostConstruct
    public void init() {
        Map<String, OAuthTokensConfiguration> configs = context.getBeansOfType(OAuthTokensConfiguration.class);
        config = new BasicOAuthTokens();
        for (OAuthTokensConfiguration configuration : configs.values()) {
            configuration.configure(config);            
        } 
    }

    
    @Override
    public String getAddAppPath() {
        return config.getAddAppPath();
    }
    @Override
    public String getAddAppPostSuccessView() {
        return config.getAddAppPostSuccessView();
    }
    @Override
    public String getAddAppView() {
        return config.getAddAppView();
    }
}