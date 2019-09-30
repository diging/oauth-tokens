package edu.asu.diging.oauth.tokens.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import edu.asu.diging.oauth.tokens.config.ConfigurationProvider;
import edu.asu.diging.oauth.tokens.core.exceptions.MethodNotSupportedException;

/**
 * Abstract template class that provides often use functionality. Subclasses should implement
 * handler methods for the different request methods (GET, POST, ...).
 * 
 * @author jdamerow
 *
 */
public abstract class OAuthTokenBaseController extends AbstractController {
    
    protected final static String USERNAME_VARIABLE = "username";
    
    @Autowired
    private ConfigurationProvider configProvider;
    
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String method = request.getMethod();
        if (method.equals(RequestMethod.POST.name())) {
            try {
                return handlePost(request, response);
            } catch (MethodNotSupportedException ex) {
                return notSupported(ex.getMethod().name());
            }
        } else if (method.equals(RequestMethod.GET.name())) {
            try {
                return handleGet(request, response);
            } catch (MethodNotSupportedException ex) {
                return notSupported(ex.getMethod().name());
            }
        }
        
        return notSupported(request.getMethod());
    }
    
    private ModelAndView notSupported(String method) {
        ModelAndView model = new ModelAndView();
        model.addObject("error", String.format("Method %s not supported.", method));
        model.setViewName(getFailureViewName());
        return model;
    }
    
    protected ModelAndView generateFailureModel(String errorMsg) {
        ModelAndView model = new ModelAndView();
        model.setViewName(getFailureViewName() + "?error=" + errorMsg);
        return model;
    }
    
    /**
     * This method should return the url a controllers maps to.
     * @return
     */
    protected abstract String getMappingPath();
    
    /**
     * Handle a POST request.
     * @param request
     * @param response
     * @return
     * @throws MethodNotSupportedException thrown if a controller does not support that method.
     * @throws Exception
     */
    protected abstract ModelAndView handlePost(HttpServletRequest request, HttpServletResponse response) throws MethodNotSupportedException, Exception;
    
    /**
     * Handle a GET request.
     * @param request
     * @param response
     * @return
     * @throws MethodNotSupportedException thrown if a controller does not support that method.
     * @throws Exception
     */
    protected abstract ModelAndView handleGet(HttpServletRequest request, HttpServletResponse response) throws MethodNotSupportedException, Exception;
    
    /**
     * Return the view name of the view that should be returned when a request cannot be handled.
     * @return
     */
    protected abstract String getFailureViewName();
}