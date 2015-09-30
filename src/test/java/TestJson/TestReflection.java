package TestJson;

import com.qunar.fuwu.service.CallQueryService;
import com.qunar.fuwu.service.SynonymConvertor;
import com.qunar.fuwu.service.impl.TestService;
import org.junit.Test;

/**
 * Created by weiqiang.yuan on 15/9/24 14:58.
 */
public class TestReflection {

    @Test
    public void should_get_instance() throws IllegalAccessException, InstantiationException {
        CallQueryService callQueryService = new CallQueryServiceByES();
        CallQueryService testService = new TestService();


        Class a = callQueryService.getClass();
//        Object instanceA = a.newInstance();
        Class[] interfaces = a.getInterfaces();

        Class b = testService.getClass();
//        Object instanceB = b.newInstance();
        Class[] interfaces1 = b.getInterfaces();


//        Class<?>[] interfaces = clazz.getInterfaces();

        System.out.println(testService.getClass().getName());

        if(callQueryService instanceof SynonymConvertor) {
            System.out.println(" A yes~~~~");
            System.out.println(((SynonymConvertor) callQueryService).convert(""));
        }

        if(testService instanceof SynonymConvertor) {
            System.out.println(" instanceB yes~~~~");
            System.out.println(((SynonymConvertor) testService).convert(""));
        }

    }
}
