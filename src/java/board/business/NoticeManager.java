/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board.business;

import board.entity.Notice;
import board.entity.User;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vigne
 */
@Stateless
public class NoticeManager {
    
    @PersistenceContext private EntityManager em;
    
    public Notice getNoticeById(int noticeId)
    {
        try
        {
            return em.find(Notice.class,noticeId);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            return null;
    }
    
    public List<Notice> getAllNotices()
    {
        try
        {
        return em.createNamedQuery(Notice.FINDALL, Notice.class).getResultList();   
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public void createNotice(String title, String content, String category, String userid) throws Exception
    {
        try
        {
                    Notice n = new Notice();
        n.setTitle(title);
        n.setCategory(category);
        n.setContent(content);
        n.setPostedDateTime(new Date());
        n.setPoster(em.find(User.class,userid));
        em.persist(n);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("error in ejb");
        }

    }
    
    public List<Notice> getAllNoticesForUser(String userId)
    {
        try
        {
            return em.createNamedQuery(Notice.FINDBYUSER,Notice.class).setParameter(":userid", userId).getResultList();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Notice> getNoticesForCategory(String category)
    {
        try
        {
            return em.createNamedQuery(Notice.FINDBYCATEGORY,Notice.class).setParameter(":category", category).getResultList();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Notice> getNoticesByCategoryUser(String userid)
    {
        try
        {
                return em.createNamedQuery(Notice.FINDBYUSER, Notice.class)
                    .setParameter("userid", userid)
                    .getResultList();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
}
