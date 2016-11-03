package board.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "notices")
@NamedQueries({
    @NamedQuery(name = Notice.FINDALL, query = "SELECT n FROM Notice n"),
    @NamedQuery(name = Notice.FINDBYCATEGORY, query = "SELECT n FROM Notice n WHERE n.category = :category"),
    @NamedQuery(name = Notice.FINDBYUSER, query = "SELECT n FROM Notice n WHERE n.poster.userid = :userid"),
    @NamedQuery(name = Notice.FINDBYUSERCATEGORY, query = "SELECT n FROM Notice n WHERE n.category = :category AND n.poster.userid = :userid")
})
public class Notice implements Serializable {

    public static final String FINDALL = "Notice.FINDALL";
    public static final String FINDBYCATEGORY = "Notice.FINDBYCATEGORY";
    public static final String FINDBYUSER = "Notice.FINDBYUSER";
    public static final String FINDBYUSERCATEGORY = "Notice.FINDBYUSERCATEGORY";
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private int noticeId;

    private String title;
    private Date postedDateTime;
    private String category;
    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User poster;

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPostedDateTime() {
        return postedDateTime;
    }

    public void setPostedDateTime(Date postedDateTime) {
        this.postedDateTime = postedDateTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

}
