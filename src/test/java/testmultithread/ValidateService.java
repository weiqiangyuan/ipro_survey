package testmultithread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by weiqiang.yuan on 15/10/9 17:38.
 */
public class ValidateService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ExecutorService executorService = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(100));

    class ValidateTTSSelectWorker implements Runnable {

        @Override
        public void run() {
            logger.info(Thread.currentThread().getName() + "begin");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info(Thread.currentThread().getName() + "end");
        }
    }

    @Test
    public void should_get_select() throws InterruptedException {
        logger.info("=======start{}",Integer.MAX_VALUE);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new ValidateTTSSelectWorker());
        }
        logger.info("=======over");
        Thread.sleep(100000);
        logger.info("=======over.....");


    }


}
