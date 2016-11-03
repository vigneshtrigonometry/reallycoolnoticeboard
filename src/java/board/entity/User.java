package board.entity;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {
    @Id
    private String userid;
    private String password;
    @OneToMany(mappedBy = "poster")
    private Collection<Notice> noticesPosted;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Notice> getNoticesPosted() {
        return noticesPosted;
    }

    public void setNoticesPosted(Collection<Notice> noticesPosted) {
        this.noticesPosted = noticesPosted;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    
    
}
