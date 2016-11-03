
package board.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="groups")
public class Group {
    @EmbeddedId
    private Groupkey key;

    public Groupkey getKey() {
        return key;
    }

    public void setKey(Groupkey key) {
        this.key = key;
    }
    
}
