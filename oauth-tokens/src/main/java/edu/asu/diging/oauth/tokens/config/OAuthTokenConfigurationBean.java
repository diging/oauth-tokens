package edu.asu.diging.oauth.tokens.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

@Configuration
public class OAuthTokenConfigurationBean {

    @Autowired
    private OAuthTokensConfigurationProvider configProvider;
    
    @Bean
    public SimpleUrlHandlerMapping oauthTokenHandlerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        
        Map<String, Object> urlMappings = new HashMap<>();
        urlMappings.put(configProvider.getAddAppPath(), "addAppController");
        mapping.setUrlMap(urlMappings);
        return mapping;
    }
    
}