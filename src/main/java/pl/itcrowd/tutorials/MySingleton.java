package pl.itcrowd.tutorials;
import pl.itcrowd.tutorials.DAO.BlogDAO;
import pl.itcrowd.tutorials.business.CMT;
import pl.itcrowd.tutorials.business.BMT;
import pl.itcrowd.tutorials.domain.Post;
import pl.itcrowd.tutorials.domain.User;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionSynchronizationRegistry;
import java.util.logging.Logger;

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
    private static final Logger LOGGER = Logger.getLogger(MySingleton.class.getCanonicalName());

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private CMT cmt;

    @EJB
    private BMT bmt;

    @EJB
    private BlogDAO blogDAO;

    @Resource
    private TransactionSynchronizationRegistry txReg;

    @PostConstruct
    public void PostConstruct() {
        LOGGER.info("PostConstruct"+txReg.getTransactionKey());
        generateData();
        cmt.execute();
        cmt.getSizeOfPost();
        LOGGER.info("PostConstruct"+txReg.getTransactionKey());
    }

    public void generateData() {
        User user = new User("user1");

        Post post = new Post("name1", "content1", user);
        Post post2 = new Post("name2", "content2", user);
        Post post3 = new Post("name3", "content3", user);

        blogDAO.createPost(post);
        blogDAO.createPost(post2);
        blogDAO.createPost(post3);
    }
}
