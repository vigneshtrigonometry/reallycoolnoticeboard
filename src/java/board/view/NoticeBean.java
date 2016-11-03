/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board.view;

import board.business.NoticeManager;
import board.entity.Notice;
import java.net.URI;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author vigne
 */
@Named
@RequestScoped
public class NoticeBean {

    @EJB
    private NoticeManager mgr;
    private List<Notice> notices;
    private String title;
    private String content;
    private String category;

    public void getAllNotices() {
        notices = mgr.getAllNotices();
    }

    @RolesAllowed("authenticated")
    public String addNotice() {
        try {
            mgr.createNotice(title, content, category, FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notice posted successfully", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return "/index";
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error occured while posting Notice.", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return null;
        }
    }

    public NoticeManager getMgr() {
        return mgr;
    }

    public void setMgr(NoticeManager mgr) {
        this.mgr = mgr;
    }

    public List<Notice> getNotices() {
        return notices;
    }

    public void setNotices(List<Notice> notices) {
        this.notices = notices;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
