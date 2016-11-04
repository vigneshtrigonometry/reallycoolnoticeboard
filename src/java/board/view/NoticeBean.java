/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board.view;

import board.business.NoticeManager;
import board.entity.Notice;
import java.io.IOException;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author vigne
 */
@Named
@RequestScoped
public class NoticeBean implements Serializable {

    @EJB private NoticeManager mgr;
    private List<Notice> notices;
    private String title;
    private String content;
    private String category;
    
    private void sortNoticesLatestFirst()
    {
        notices.sort(new Comparator<Notice>(){
            @Override
            public int compare(Notice n1, Notice n2) {
                Date d1 = n1.getPostedDateTime();
                Date d2 = n2.getPostedDateTime();
                if(d1.before(d2)) return 1;
                else if(d1.after(d2)) return -1;
                else return 0;
            }
        });
    }

    public void getAllNotices() {
        notices = mgr.getAllNotices();
        sortNoticesLatestFirst();
    }

    @RolesAllowed("authenticated")
    public String addNotice() {
        try {
            System.out.println(">>>>ffffffffffff>>>>>>>"+title + content+ category);
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
    @RolesAllowed("authenticated")
    public void getMyNoticeList() throws IOException
    {
        notices=mgr.getNoticesByCategoryUser(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        sortNoticesLatestFirst();
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
