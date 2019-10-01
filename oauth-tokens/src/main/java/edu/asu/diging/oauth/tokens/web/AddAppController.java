package edu.asu.diging.oauth.tokens.web;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import edu.asu.diging.oauth.tokens.config.OAuthTokensConfigurationProvider;
import edu.asu.diging.oauth.tokens.core.exceptions.MethodNotSupportedException;
import edu.asu.diging.oauth.tokens.core.service.IOAuthClientManager;
import edu.asu.diging.oauth.tokens.core.service.OAuthCredentials;
import edu.asu.diging.oauth.tokens.core.service.OAuthScope;
import edu.asu.diging.oauth.tokens.web.forms.AppForm;

@Controller
public class AddAppController extends OAuthTokenBaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OAuthTokensConfigurationProvider configProvider;

    @Autowired
    private IOAuthClientManager clientManager;

    @Override
    protected String getMappingPath() {
        return configProvider.getAddAppPath();
    }

    @Override
    protected ModelAndView handlePost(HttpServletRequest request, HttpServletResponse response)
            throws MethodNotSupportedException, Exception {

        OAuthCredentials creds = clientManager.create(request.getParameter("name"), request.getParameter("description"),
                Arrays.asList(OAuthScope.READ));
        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);

        flashMap.put("clientId", creds.getClientId());
        flashMap.put("secret", creds.getSecret());
        ModelAndView model = new ModelAndView();
        model.setViewName(configProvider.getAddAppPostSuccessView());
        return model;
    }

    @Override
    protected ModelAndView handleGet(HttpServletRequest request, HttpServletResponse response)
            throws MethodNotSupportedException, Exception {
        ModelAndView model = new ModelAndView();
        model.getModelMap().addAttribute("appForm", new AppForm());
        model.setViewName(configProvider.getAddAppView());
        return model;
    }

    @Override
    protected String getFailureViewName() {
        return configProvider.getAddAppView();
    }

}