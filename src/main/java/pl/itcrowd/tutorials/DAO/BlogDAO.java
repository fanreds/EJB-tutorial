package pl.itcrowd.tutorials.DAO;

import pl.itcrowd.tutorials.domain.Post;
import pl.itcrowd.tutorials.domain.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 2/18/13
 * Time: 2:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class BlogDAO {

    @PersistenceContext
    private EntityManager entityManager;


    public Post getPostById(int id) {
        return entityManager.find(Post.class, id);
    }

    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    public List<Post> getAllPosts() {
        return entityManager.createQuery("select p from Post p").getResultList();
    }

    public Integer getAllPostsSize() {
        return (Integer) entityManager.createQuery("select count(*) from Post").getSingleResult();
    }

    public void updatePost(Post post) {
        entityManager.merge(post);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void createPost(Post post) {
        if (post.getUser().getId() != null && !entityManager.contains(post.getUser())) {
            User u1 = getUserById(post.getUser().getId());
            post.setUser(u1);
        }
        entityManager.persist(post);


    }
}
