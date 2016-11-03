/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board.view;

import board.business.UserManager;
import java.io.IOException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@RequestScoped
@Named
public class UserBean {

    @EJB
    private UserManager mgr;
    private String username;
    private String password;
    private String repeatPwd;

    public String login() {
        HttpServletRequest req
                = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        try {
            req.login(username, password);
        } catch (Throwable t) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Incorrect login"));
            return (null);
        }

        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Successful", null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);

        return ("index");
    }

    public String register() {
        try {
            if (repeatPwd.equals(password)) {
                mgr.RegisterUser(username, password);
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration successful. Please login with your new credentials to post notices.", null);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                return "index";
            } else {
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage("Please check your password"));
                return (null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration unsuccessful. Try choosing an alternative username or contact admin.", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return "index";
        }
    }

    public void checkAuthentication() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        if (externalContext.getUserPrincipal() != null) {
            externalContext.redirect(externalContext.getRequestContextPath() + "/faces/index.xhtml");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPwd() {
        return repeatPwd;
    }

    public void setRepeatPwd(String repeatPwd) {
        this.repeatPwd = repeatPwd;
    }

}
