package pl.itcrowd.tutorials;

import pl.itcrowd.tutorials.DAO.BlogDAO;
import pl.itcrowd.tutorials.business.CMT;
import pl.itcrowd.tutorials.business.BMT;
import pl.itcrowd.tutorials.domain.Post;
import pl.itcrowd.tutorials.domain.User;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
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


    @Resource
    private TimerService timerService;

    @PostConstruct
    public void PostConstruct() {
        LOGGER.info("PostConstruct" + txReg.getTransactionKey());
        hello2();

    }

    public void hello2() {
        long duration = 10000;         //10s
        TimerConfig timerConfig = new TimerConfig();
        timerService.createSingleActionTimer(duration, timerConfig);
    }

    @Timeout
    public void timeout() {

        LOGGER.info("hello world!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
