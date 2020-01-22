
package io.github.campanula.utils.method;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author: Campanula
 * @Date 2019-12-15
 */
public class TestCMapUtil {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private <T> Map<T, T> getMap(T ...t) {
        Map<T, T> map = new HashMap<>();
        int l = t.length;
        for (int i = 0; i < l; i += 2) {
            map.put(t[i], t[i + 1]);
        }
        return map;
    }

    @Test
    public void testGet() {
        Map<String, String> map = CMapUtil.get(() -> getMap("key", "1"), () -> getMap("key", "2"));
        Assert.assertEquals(map.get("key"), "1");
        map = CMapUtil.get(HashMap::new, () -> getMap("key", "2"));
        Assert.assertEquals(map.get("key"), "2");
        map = CMapUtil.get(() -> null, () -> getMap("key", "3"));
        Assert.assertEquals(map.get("key"), "3");
    }

    @Test
    public void testNullRuntimeThrows() {
        Map<String, String> map = CMapUtil.getThrows(() -> getMap("key", "1"), RuntimeException::new);
        Assert.assertEquals(map.get("key"), "1");
        thrown.expect(RuntimeException.class);
        CMapUtil.getThrows(HashMap::new, RuntimeException::new);
    }

    @Test
    public void testProcess() {
        Map<String, String> process = CMapUtil.process(() -> getMap("key", "1"), (s) -> {
            s.put("value", "2");
            return s;
        }, () -> getMap("key", "3"));
        Assert.assertEquals(process.size(), 2);
        process = CMapUtil.process(() -> null, (s) -> null, () -> getMap("key", "3"));
        Assert.assertEquals(process.size(), 1);
    }

    @Test
    public void testProcessNullRuntimeThrows() {
        Map<String, String> map = CMapUtil.processThrows(() -> getMap("key", "1"), (s) -> {
            s.put("value", "2");
            return s;
        }, RuntimeException::new);
        Assert.assertEquals(map.size(), 2);
        thrown.expect(RuntimeException.class);
        CMapUtil.processThrows(HashMap::new, (s) -> null, RuntimeException::new);
    }

    @Test
    public void testConsume() {
        CMapUtil.consume(() -> getMap("key", "1"), System.out::println, () -> System.out.println("2"));
        CMapUtil.consume(HashMap::new, System.out::println, () -> System.out.println("2"));
    }

    @Test
    public void  testConsumeNullRuntimeThrows() {
        CMapUtil.consumeThrows(() -> getMap("key", "1"), System.out::println, RuntimeException::new);
        thrown.expect(RuntimeException.class);
        CMapUtil.consumeThrows(HashMap::new, System.out::println, RuntimeException::new);
    }

    @Test
    public void testConvert() {
        Map<String, Integer> convert = CMapUtil.convert(() -> getMap("key", "1"), (s) -> {
            Map<String, Integer> map = new HashMap<>();
            map.put("key", Integer.valueOf(1));
            return map;
        }, () -> {
            Map<String, Integer> map = new HashMap<>();
            map.put("key", Integer.valueOf(2));
            return map;
        });
        Assert.assertEquals(convert.size(), 1);
        convert = CMapUtil.convert(() -> null, (s) -> null, () -> {
            Map<String, Integer> map = new HashMap<>();
            map.put("key", Integer.valueOf(2));
            map.put("value", Integer.valueOf(3));
            return map;
        });
        Assert.assertEquals(convert.size(), 2);
    }

    @Test
    public void  testConvertNullRuntimeThrows() {
        Map<String, Integer> convertThrows = CMapUtil.convertThrows(() -> getMap("key", "1"), (s) -> {
            Map<String, Integer> map = new HashMap<>();
            map.put("key", Integer.valueOf(1));
            return map;
        }, RuntimeException::new);
        Assert.assertEquals(convertThrows.size(), 1);
        thrown.expect(RuntimeException.class);
        CMapUtil.convertThrows(() -> null, (s) -> null, RuntimeException::new);
    }

}