package edu.asu.diging.oauth.tokens.core.exceptions;
import org.springframework.web.bind.annotation.RequestMethod;

public class MethodNotSupportedException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private RequestMethod method;
    
    public MethodNotSupportedException(RequestMethod method) {
        this.method = method;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }
    
    
}