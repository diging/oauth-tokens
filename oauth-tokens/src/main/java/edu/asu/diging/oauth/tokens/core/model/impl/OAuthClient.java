package edu.asu.diging.oauth.tokens.core.model.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import edu.asu.diging.oauth.tokens.core.model.IOAuthClient;

@Entity
public class OAuthClient implements IOAuthClient, ClientDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "oauth_client_id_generator")
    @GenericGenerator(name = "oauth_client_id_generator", parameters = @Parameter(name = "prefix", value = "OAUTHCLIENT"), strategy = "edu.asu.diging.oauth.tokens.core.data.IdGenerator")
    private String clientId;
    private String name;
    private String description;
    @ElementCollection(fetch=FetchType.EAGER)
    private Set<String> resourceIds;
    private boolean secretRequired;
    private String clientSecret;
    private boolean scoped;
    @ElementCollection(fetch=FetchType.EAGER)
    private Set<String> scope;
    @ElementCollection(fetch=FetchType.EAGER)
    private Set<String> authorizedGrantTypes;
    @ElementCollection(fetch=FetchType.EAGER)
    private Set<String> registeredRedirectUri;
    @ElementCollection(fetch=FetchType.EAGER)
    private Collection<GrantedAuthority> authorities;
    private int accessTokenValiditySeconds;
    private int refereshTokenValiditySeconds;
    private boolean autoApprove;

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return secretRequired;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return scoped;
    }

    @Override
    public Set<String> getScope() {
        return scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refereshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return autoApprove;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
        // return additionalInformation;
    }

    @Override
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    @Override
    public void setSecretRequired(boolean secretRequired) {
        this.secretRequired = secretRequired;
    }

    @Override
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public void setScoped(boolean scoped) {
        this.scoped = scoped;
    }

    @Override
    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    @Override
    public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    @Override
    public void setRegisteredRedirectUri(Set<String> registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }

    @Override
    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    @Override
    public void setRefereshTokenValiditySeconds(int refereshTokenValiditySeconds) {
        this.refereshTokenValiditySeconds = refereshTokenValiditySeconds;
    }

    @Override
    public void setAutoApprove(boolean autoApprove) {
        this.autoApprove = autoApprove;
    }
}