package org.demo.formation.web.jsf.managedbean;

import java.io.Serializable;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.demo.formation.librairie.entity.Utilisateur;
import org.demo.formation.librairie.service.IUtilisateurService;
import org.demo.formation.web.jsf.util.DemoConstantes;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2298573988062240389L;
	private String email;
	private String password;
	private IUtilisateurService userService;
	
	
	public LoginManagedBean(){
		this.userService = (IUtilisateurService)FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance()).getBean("userServiceBean");
	}
	
	public String loginAction(){
		Utilisateur loginUser = this.userService.getUserByEmailAndPassword(email, password);
		if (loginUser != null){
			FacesContext ctx = FacesContext.getCurrentInstance();
	        ExternalContext extCtx = ctx.getExternalContext();
	        Map<String, Object> sessionMap = extCtx.getSessionMap();
	        sessionMap.put(DemoConstantes.USER_SESSION_KEY, loginUser);
	        return DemoConstantes.MSG_OK;
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERREUR DE CONNEXION...", null));
			return DemoConstantes.MSG_KO;
		}
	}
	
	public String logoutAction() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return DemoConstantes.MSG_OK;
    }
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
