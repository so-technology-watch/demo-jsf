package org.demo.formation.web.jsf.managedbean;

import java.io.Serializable;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.demo.formation.librairie.bean.provider.FactoryServiceProvider;
import org.demo.formation.librairie.entity.Eleve;
import org.demo.formation.librairie.exception.DemoBusinessException;
import org.demo.formation.librairie.service.IEleveService;
import org.demo.formation.librairie.service.impl.EleveServiceImpl;
import org.demo.formation.librairie.service.view.EleveView;
import org.demo.formation.librairie.util.DepartementEnum;
import org.demo.formation.web.jsf.util.DemoConstantes;
import org.demo.formation.web.jsf.util.SessionManagerUtils;

@ManagedBean(name = "userManagedBean")
@ViewScoped
public class UserManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8437951921380040419L;
	private EleveView userCourant;
	private IEleveService userService = FactoryServiceProvider.getService(EleveServiceImpl.class);
	private EleveView loginUser;

	public UserManagedBean() {
		//this.userService = (IUtilisateurService)FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance()).getBean("userServiceBean");
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String idUserParam = params.get("idUserParam");
		if (StringUtils.isNotBlank(idUserParam)){//On affiche en mode modification
			Long idUser = Long.parseLong(idUserParam);
			this.userCourant = this.userService.getViewById(idUser);
		}else {//On affiche en mode creation
			this.userCourant = new EleveView();
		}
		loginUser = (EleveView)SessionManagerUtils.getObjectInSession(DemoConstantes.USER_SESSION_KEY);
	}


	public String addUserAction(){
		try {
			//On cree l'utilisateur 
			this.userService.creerOuMetAJourUtilisateur(this.userCourant);
			//On recupere la liste en base
		} catch (DemoBusinessException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), null));
			return DemoConstantes.MSG_KO;
		}
		return DemoConstantes.MSG_OK;
	}

	public String loadUserAction(Long idUser){
		if (idUser != null){
			this.userCourant = this.userService.getViewById(idUser);
		}
		return DemoConstantes.MSG_OK;
	}

	public DepartementEnum[] getDepartements() {
		return DepartementEnum.values();
	}

	public EleveView getUserCourant() {
		return userCourant;
	}

	public void setUserCourant(EleveView userCourant) {
		this.userCourant = userCourant;
	}



	public EleveView getLoginUser() {
		return loginUser;
	}



	public void setLoginUser(EleveView loginUser) {
		this.loginUser = loginUser;
	}
}
