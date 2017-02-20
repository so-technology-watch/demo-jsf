package org.demo.formation.web.jsf.util;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class SessionManagerUtils {

	public static Object getObjectInSession (String sessionKey){
		FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extCtx = ctx.getExternalContext();
        Map<String, Object> sessionMap = extCtx.getSessionMap();
        return sessionMap.get(sessionKey);
	}
}
