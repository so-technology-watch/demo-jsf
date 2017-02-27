package org.demo.formation.web.jsf.managedbean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.demo.formation.librairie.bean.provider.FactoryServiceProvider;
import org.demo.formation.librairie.service.IEleveService;
import org.demo.formation.librairie.service.impl.EleveServiceImpl;
import org.demo.formation.librairie.service.view.EleveView;
import org.demo.formation.web.jsf.util.DemoConstantes;
import org.demo.formation.web.jsf.util.SessionManagerUtils;

@ManagedBean(name= "listUserManagedBean")
public class ListUserManagedBean {

private IEleveService userService = FactoryServiceProvider.getService(EleveServiceImpl.class);

private List<EleveView> listeUsers;
private EleveView loginUser;
	
	
	public ListUserManagedBean(){
		//this.userService = new UtilisateurServiceImpl();
		//On recupere la liste en base
		//this.userService = (IUtilisateurService)FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance()).getBean("userServiceBean");
		this.listeUsers = this.userService.findAll();
        loginUser = (EleveView)SessionManagerUtils.getObjectInSession(DemoConstantes.USER_SESSION_KEY);
	}

	public String deleteUserById(Long idUser){
		if (idUser != null){
			this.userService.deleteById(idUser);
			this.listeUsers = this.userService.findAll();
		}
		return "";
	}

	public List<EleveView> getListeUsers() {
		return listeUsers;
	}


	public void setListeUsers(List<EleveView> listeUsers) {
		this.listeUsers = listeUsers;
	}


	public EleveView getLoginUser() {
		return loginUser;
	}


	public void setLoginUser(EleveView loginUser) {
		this.loginUser = loginUser;
	}
}
