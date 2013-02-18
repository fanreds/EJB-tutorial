package pl.itcrowd.tutorials.business;

import pl.itcrowd.tutorials.DAO.BlogDAO;
import pl.itcrowd.tutorials.domain.Post;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 2/18/13
 * Time: 1:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class CMT {

    private static final Logger LOGGER = Logger.getLogger(CMT.class.getCanonicalName());

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private BlogDAO blogDAO;

    public void execute() {
        Post post;
        if ((post = blogDAO.getPostById(1)) != null) {
            post.setContent("changed post");
            blogDAO.updatePost(post);
        }
    }
}
