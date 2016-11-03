/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board.view;

import board.business.NoticeManager;
import board.entity.Notice;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author vigne
 */
@Named
@RequestScoped
public class NoticeBean {
    @EJB private NoticeManager mgr;
    private List<Notice> notices;
    
    public void getAllNotices()
    {
        notices = mgr.getAllNotices();
    }
    
}
