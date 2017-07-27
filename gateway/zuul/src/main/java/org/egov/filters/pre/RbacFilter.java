package org.egov.filters.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.egov.contract.Action;
import org.egov.contract.User;
import org.springframework.http.HttpStatus;

import static org.egov.constants.RequestContextConstants.*;

/**
 *  5th pre filter to get executed.
 *  Filter gets executed if the RBAC flag is enabled. Returns an error if the URI is not present in the authorized action list.
 */
public class RbacFilter extends ZuulFilter{

    @Override
    public String filterType() {return "pre";}

    @Override
    public int filterOrder() {return 4;}

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getBoolean("shoulDoRbac");
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String requestUri = ctx.getRequest().getRequestURI();
        User user = (User) ctx.get(USER_INFO_KEY);
        if(user.getActions().stream().anyMatch(action -> isActionMatchingIncomingURI(requestUri, action)))
            return null;

        abortWithStatus(ctx,HttpStatus.NOT_FOUND,"The resource you are trying to find is not available");
        return null;
    }

    private boolean isActionMatchingIncomingURI(String requestUri, Action action) {
        if(action.hasDynamicFields()) {
            return requestUri.matches(action.getRegexUrl());
        }
        return requestUri.equals(action.getUrl());
    }


    private void abortWithStatus(RequestContext ctx, HttpStatus status, String message) {
        ctx.set(ERROR_CODE_KEY, status.value());
        ctx.set(ERROR_MESSAGE_KEY, message);
        ctx.setSendZuulResponse(false);
    }
}
