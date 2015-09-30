package TestJson;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by weiqiang.yuan on 15/9/22 14:45.
 */
public class TestCollections {

    @Test
    public void can_not_remove_map_item() {
        Map<Integer, String> map = Maps.newHashMap();
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        System.out.println(map);

        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            if (entry.getKey().equals(1)) {
                map.remove(entry.getKey());
            }
        }
        System.out.println(map);
    }

    @Test
    public void can_remove_map_item() {
        Map<Integer, String> map = Maps.newHashMap();
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        System.out.println(map);

        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet ().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();

            if (entry.getKey().equals(1)) {
                iterator.remove();
            }
        }
        System.out.println(map);
    }
}
