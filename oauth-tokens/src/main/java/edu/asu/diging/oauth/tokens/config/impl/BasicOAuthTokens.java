package edu.asu.diging.oauth.tokens.config.impl;

import edu.asu.diging.oauth.tokens.config.OAuthTokens;

public class BasicOAuthTokens implements OAuthTokens {

    private String addAppGet = "/admin/apps/add";
    private String addAppPostSuccessView = "redirect:/admin/apps/add";
    private String addAppView = "admin/apps/add";
    
    /* (non-Javadoc)
     * @see edu.asu.diging.oauth.tokens.config.OAuthTokens#getAddAppGet()
     */
    @Override
    public String getAddAppPath() {
        return addAppGet;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.oauth.tokens.config.OAuthTokens#setAddAppGet(java.lang.String)
     */
    @Override
    public void setAddAppPath(String addAppGet) {
        this.addAppGet = addAppGet;
    }
    @Override
    public String getAddAppPostSuccessView() {
        return addAppPostSuccessView;
    }
    @Override
    public void setAddAppPostSuccessView(String addAppPostSuccessView) {
        this.addAppPostSuccessView = addAppPostSuccessView;
    }
    @Override
    public String getAddAppView() {
        return addAppView;
    }
    @Override
    public void setAddAppView(String addAppView) {
        this.addAppView = addAppView;
    }
}
