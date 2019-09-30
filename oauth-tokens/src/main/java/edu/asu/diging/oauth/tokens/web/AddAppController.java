package edu.asu.diging.oauth.tokens.web;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.asu.diging.oauth.tokens.config.ConfigurationProvider;
import edu.asu.diging.oauth.tokens.core.exceptions.MethodNotSupportedException;
import edu.asu.diging.oauth.tokens.core.service.IOAuthClientManager;
import edu.asu.diging.oauth.tokens.core.service.OAuthCredentials;
import edu.asu.diging.oauth.tokens.core.service.OAuthScope;
import edu.asu.diging.oauth.tokens.web.forms.AppForm;

@Controller
public class AddAppController extends OAuthTokenBaseController {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ConfigurationProvider configProvider;
    
    @Autowired
    private IOAuthClientManager clientManager;
    
   public String post(AppForm appForm, RedirectAttributes redirectAttrs) {
        OAuthCredentials creds = clientManager.create(appForm.getName(), appForm.getDescription(), Arrays.asList(OAuthScope.READ));
        redirectAttrs.addFlashAttribute("clientId", creds.getClientId());
        redirectAttrs.addFlashAttribute("secret", creds.getSecret());
        return configProvider.getAddAppPostSuccessView();
    }

    @Override
    protected String getMappingPath() {
        return configProvider.getAddAppPath();
    }

    @Override
    protected ModelAndView handlePost(HttpServletRequest request, HttpServletResponse response)
            throws MethodNotSupportedException, Exception {
//        OAuthCredentials creds = clientManager.create(request.getParameter("name"), request.getParameter("description"), Arrays.asList(OAuthScope.READ));
//        redirectAttrs.addFlashAttribute("clientId", creds.getClientId());
//        redirectAttrs.addFlashAttribute("secret", creds.getSecret());
//        return configProvider.getAddAppPostSuccessView();
        return null;
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