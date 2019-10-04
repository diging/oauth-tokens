package edu.asu.diging.oauth.tokens.web;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
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
        
        FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
        if (flashMapManager == null) {
            throw new IllegalStateException("FlashMapManager not found despite output FlashMap having been set");
        }
        flashMapManager.saveOutputFlashMap(flashMap, request, response);
        
        ModelAndView model = new ModelAndView();
        model.setViewName(configProvider.getAddAppSuccessView());
        return model;
    }

    @Override
    protected ModelAndView handleGet(HttpServletRequest request, HttpServletResponse response)
            throws MethodNotSupportedException, Exception {
        ModelAndView model = new ModelAndView();
        model.getModelMap().addAttribute("appForm", new AppForm());
        model.setViewName(configProvider.getAddAppView());
        
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
          inputFlashMap.entrySet().forEach(e -> model.getModelMap().addAttribute(e.getKey(), e.getValue()));
        }
        
        return model;
    }

    @Override
    protected String getFailureViewName() {
        return configProvider.getAddAppView();
    }

}