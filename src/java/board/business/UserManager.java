
package board.business;

import board.entity.Group;
import board.entity.Groupkey;
import board.entity.User;
import java.math.BigInteger;
import java.security.MessageDigest;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserManager {
    
    @PersistenceContext private EntityManager em;
    
    public void RegisterUser(String userid,String password) throws Exception
    {
        try
        {
        User u = new User();
        u.setUserid(userid);
            MessageDigest m = MessageDigest.getInstance("SHA-256");
            m.update(password.getBytes(),0,password.length());
            byte[] digest = m.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
            String hex = Integer.toHexString(0xff & digest[i]);
            if (hex.length() == 1) sb.append('0');
            sb.append(hex);
            }
            u.setPassword(sb.toString());
        Group g = new Group();
        Groupkey k = new Groupkey();
        k.setGroupid("authenticated");
        k.setUserid(userid);
        g.setKey(k);
        em.persist(u);
        em.persist(g);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
       
    }
}
