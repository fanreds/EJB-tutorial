package pl.itcrowd.tutorials.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 2/18/13
 * Time: 2:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "POST")
public class Post implements Serializable {

    @Id
    @SequenceGenerator(name = "POST_ID_SEQUENCE", sequenceName = "POST_ID_SEQUENCE", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_ID_SEQUENCE")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USERS_ID")
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post(String name, String content, User user) {
        this.name = name;
        this.content = content;
        this.user = user;
    }

    public Post() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
