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
        return em.createNamedQuery("select n notices n", Notice.class).getResultList();   
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public void createNotice(String title, String content, String category, String userid) throws Exception
    {
        Notice n = new Notice();
        n.setTitle(title);
        n.setCategory(category);
        n.setPostedDateTime(new Date());
        n.setPoster(em.find(User.class,userid));
        em.persist(n);
    }
    
}
