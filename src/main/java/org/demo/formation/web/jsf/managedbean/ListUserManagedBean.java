package org.demo.formation.web.jsf.managedbean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.demo.formation.librairie.bean.provider.FactoryServiceProvider;
import org.demo.formation.librairie.entity.Utilisateur;
import org.demo.formation.librairie.service.IUtilisateurService;
import org.demo.formation.librairie.service.impl.UtilisateurServiceImpl;
import org.demo.formation.web.jsf.util.DemoConstantes;
import org.demo.formation.web.jsf.util.SessionManagerUtils;

@ManagedBean(name= "listUserManagedBean")
public class ListUserManagedBean {

private IUtilisateurService userService = FactoryServiceProvider.getService(UtilisateurServiceImpl.class);

private List<Utilisateur> listeUsers;
private Utilisateur loginUser;
	
	
	public ListUserManagedBean(){
		//this.userService = new UtilisateurServiceImpl();
		//On recupere la liste en base
		//this.userService = (IUtilisateurService)FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance()).getBean("userServiceBean");
		this.listeUsers = this.userService.findAll();
        loginUser = (Utilisateur)SessionManagerUtils.getObjectInSession(DemoConstantes.USER_SESSION_KEY);
	}

	public String deleteUserById(Long idUser){
		if (idUser != null){
			this.userService.deleteById(idUser);
			this.listeUsers = this.userService.findAll();
		}
		return "";
	}

	public List<Utilisateur> getListeUsers() {
		return listeUsers;
	}


	public void setListeUsers(List<Utilisateur> listeUsers) {
		this.listeUsers = listeUsers;
	}


	public Utilisateur getLoginUser() {
		return loginUser;
	}


	public void setLoginUser(Utilisateur loginUser) {
		this.loginUser = loginUser;
	}
}
