package pl.itcrowd.tutorials.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 2/18/13
 * Time: 2:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {

    @Id
    @SequenceGenerator(name = "USERS_ID_SEQUENCE", sequenceName = "USERS_ID_SEQUENCE", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_ID_SEQUENCE")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
