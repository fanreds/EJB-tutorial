package pl.itcrowd.tutorials.business;

import pl.itcrowd.tutorials.DAO.BlogDAO;
import pl.itcrowd.tutorials.domain.Post;
import pl.itcrowd.tutorials.domain.User;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 2/18/13
 * Time: 1:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BMT {
    private static final Logger LOGGER = Logger.getLogger(BMT.class.getCanonicalName());

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private BlogDAO blogDAO;

    @Resource
    private SessionContext sessionContext;

    public void execute() {
        UserTransaction ut = sessionContext.getUserTransaction();

        try {
            ut.begin();
            Post post = blogDAO.getPostById(3);
            LOGGER.info("" + post);
            if (post != null) {
                post.setContent("changed post");
                blogDAO.updatePost(post);
                LOGGER.info("commit");
                ut.commit();
            } else {
                LOGGER.info("rollback");
                ut.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
