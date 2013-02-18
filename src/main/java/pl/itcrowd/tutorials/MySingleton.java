package pl.itcrowd.tutorials;

import pl.itcrowd.tutorials.domain.Post;
import pl.itcrowd.tutorials.domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 2/18/13
 * Time: 1:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Startup
@Singleton
public class MySingleton {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void PostConstruct(){
        generateData();
    }

    public void generateData(){
        User user = new User("user1");

        Post post = new Post("name1","content1",user);
        Post post2 = new Post("name2","content2",user);

        entityManager.persist(post);
        entityManager.persist(post2);

    }
}
