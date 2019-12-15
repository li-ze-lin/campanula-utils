package org.campanula.utils.method;

import org.campanula.utils.exception.ObjectEmptyException;
import org.campanula.utils.exception.ObjectEmptyRuntimeException;
import org.campanula.utils.exception.MethodNullException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: Campanula
 * @Date 2019-12-15
 */
public class TestCObjectUtil {

    private final String getString = "1";
    private final String orString = "2";
    private final String OBJECT_IS_NULL = "object is null";
    private final String EXECUTE_METHOD_IS_NULL = "execute method is null";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getO() {
        String get = CObjectUtil.getO(() -> getString, () -> orString);
        Assert.assertEquals(get, getString);

        get = CObjectUtil.getO(() -> null, () -> orString);
        Assert.assertEquals(get, orString);

        get = CObjectUtil.getO(() -> null, () -> null);
        Assert.assertNull(get);
    }

    @Test
    public void testONullRuntimeThrows() {
        String get = CObjectUtil.oNullRuntimeThrows(() -> getString);
        Assert.assertEquals(get, getString);

        thrown.expect(ObjectEmptyRuntimeException.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oNullRuntimeThrows(() -> null);
    }

    @Test
    public void oNullRuntimeThrows1() {
        String get = CObjectUtil.oNullRuntimeThrows(() -> getString, OBJECT_IS_NULL);
        Assert.assertEquals(get, getString);

        thrown.expect(ObjectEmptyRuntimeException.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oNullRuntimeThrows(() -> null, OBJECT_IS_NULL);
    }

    @Test
    public void oNullRuntimeThrows2() {
        String get = CObjectUtil.oNullRuntimeThrows(() -> getString, new RuntimeException());
        Assert.assertEquals(get, getString);

        thrown.expect(RuntimeException.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oNullRuntimeThrows(() -> null, new RuntimeException(OBJECT_IS_NULL));
    }

    @Test
    public void oNullThrows() throws Exception {
        String get = CObjectUtil.oNullThrows(() -> getString);
        Assert.assertEquals(get, getString);

        thrown.expect(ObjectEmptyException.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oNullThrows(() -> null);
    }

    @Test
    public void oNullThrows1() throws Exception {
        String get = CObjectUtil.oNullThrows(() -> getString, OBJECT_IS_NULL);
        Assert.assertEquals(get, getString);

        thrown.expect(ObjectEmptyException.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oNullThrows(() -> null, OBJECT_IS_NULL);
    }

    @Test
    public void oNullThrows2() throws Exception {
        String get = CObjectUtil.oNullThrows(() -> getString, new Exception(OBJECT_IS_NULL));
        Assert.assertEquals(get, getString);

        thrown.expect(Exception.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oNullThrows(() -> null, new Exception(OBJECT_IS_NULL));
    }

    @Test
    public void oProcess() {
        String get = CObjectUtil.oProcess(() -> getString, (s) -> s.concat(orString), () -> orString);
        Assert.assertEquals(get, getString.concat(orString));

        get = CObjectUtil.oProcess(() -> getString, null, () -> orString);
        Assert.assertEquals(get, getString);

        get = CObjectUtil.oProcess(null, null, () -> orString);
        Assert.assertEquals(get, orString);

        get = CObjectUtil.oProcess(null, null, null);
        Assert.assertNull(get);
    }

    @Test
    public void oProcessNullRuntimeThrows() {
        String get = CObjectUtil.oProcessNullRuntimeThrows(() -> getString, (s) -> s.concat(orString));
        Assert.assertEquals(get, getString.concat(orString));

        get = CObjectUtil.oProcessNullRuntimeThrows(() -> getString, null);
        Assert.assertEquals(get, getString);

        thrown.expect(ObjectEmptyRuntimeException.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oProcessNullRuntimeThrows(null, null);
    }

    @Test
    public void oProcessNullRuntimeThrows1() {
        String get = CObjectUtil.oProcessNullRuntimeThrows(() -> getString, (s) -> s.concat(orString), OBJECT_IS_NULL);
        Assert.assertEquals(get, getString.concat(orString));

        get = CObjectUtil.oProcessNullRuntimeThrows(() -> getString, null, OBJECT_IS_NULL);
        Assert.assertEquals(get, getString);

        thrown.expect(ObjectEmptyRuntimeException.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oProcessNullRuntimeThrows(null, null, OBJECT_IS_NULL);
    }

    @Test
    public void oProcessNullRuntimeThrows2() {
        String get = CObjectUtil.oProcessNullRuntimeThrows(() -> getString, (s) -> s.concat(orString), new RuntimeException(OBJECT_IS_NULL));
        Assert.assertEquals(get, getString.concat(orString));

        get = CObjectUtil.oProcessNullRuntimeThrows(() -> getString, null, new RuntimeException(OBJECT_IS_NULL));
        Assert.assertEquals(get, getString);

        thrown.expect(RuntimeException.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oProcessNullRuntimeThrows(null, null, new RuntimeException(OBJECT_IS_NULL));
    }

    @Test
    public void oProcessNullThrows() throws Exception {
        String get = CObjectUtil.oProcessNullThrows(() -> getString, (s) -> s.concat(orString));
        Assert.assertEquals(get, getString.concat(orString));

        get = CObjectUtil.oProcessNullThrows(() -> getString, null);
        Assert.assertEquals(get, getString);

        thrown.expect(ObjectEmptyException.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oProcessNullThrows(null, null);
    }

    @Test
    public void oProcessNullThrows1() throws Exception {
        String get = CObjectUtil.oProcessNullThrows(() -> getString, (s) -> s.concat(orString), OBJECT_IS_NULL);
        Assert.assertEquals(get, getString.concat(orString));

        get = CObjectUtil.oProcessNullThrows(() -> getString, null, OBJECT_IS_NULL);
        Assert.assertEquals(get, getString);

        thrown.expect(ObjectEmptyException.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oProcessNullThrows(null, null, OBJECT_IS_NULL);
    }

    @Test
    public void oProcessNullThrows2() throws Exception {
        String get = CObjectUtil.oProcessNullThrows(() -> getString, (s) -> s.concat(orString), new Exception(OBJECT_IS_NULL));
        Assert.assertEquals(get, getString.concat(orString));

        get = CObjectUtil.oProcessNullThrows(() -> getString, null, new Exception(OBJECT_IS_NULL));
        Assert.assertEquals(get, getString);

        thrown.expect(Exception.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oProcessNullThrows(null, null, new Exception(OBJECT_IS_NULL));
    }

    @Test
    public void oConsume() {
        List<String> get = new ArrayList<>();
        CObjectUtil.oConsume(() -> get, (l) -> l.add(getString), () -> get.add(orString));
        Assert.assertEquals(get.size(), 1);
        Assert.assertEquals(get.get(0), getString);
        get.clear();

        CObjectUtil.oConsume(() -> get, null, () -> get.add(orString));
        Assert.assertEquals(get.size(), 0);

        CObjectUtil.oConsume(null, null, () -> get.add(orString));
        Assert.assertEquals(get.size(), 1);
        Assert.assertEquals(get.get(0), orString);
        get.clear();

        CObjectUtil.oConsume(null, null, null);
        Assert.assertEquals(get.size(), 0);
    }

    @Test
    public void oConsumeNullRuntimeThrows() {
        final List<String> get = new ArrayList<>();
        CObjectUtil.oConsumeNullRuntimeThrows(() -> get, (l) -> l.add(getString));
        Assert.assertEquals(get.size(), 1);
        Assert.assertEquals(get.get(0), getString);
        get.clear();

        CObjectUtil.oConsumeNullRuntimeThrows(() -> get, null);
        Assert.assertEquals(get.size(), 0);

        thrown.expect(ObjectEmptyRuntimeException.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oConsumeNullRuntimeThrows(null, null);
    }

    @Test
    public void oConsumeNullRuntimeThrows1() {
        final List<String> get = new ArrayList<>();
        CObjectUtil.oConsumeNullRuntimeThrows(() -> get, (l) -> l.add(getString), OBJECT_IS_NULL);
        Assert.assertEquals(get.size(), 1);
        Assert.assertEquals(get.get(0), getString);
        get.clear();

        CObjectUtil.oConsumeNullRuntimeThrows(() -> get, null, OBJECT_IS_NULL);
        Assert.assertEquals(get.size(), 0);

        thrown.expect(ObjectEmptyRuntimeException.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oConsumeNullRuntimeThrows(null, null, OBJECT_IS_NULL);
    }

    @Test
    public void oConsumeNullRuntimeThrows2() {
        final List<String> get = new ArrayList<>();
        CObjectUtil.oConsumeNullRuntimeThrows(() -> get, (l) -> l.add(getString), new RuntimeException(OBJECT_IS_NULL));
        Assert.assertEquals(get.size(), 1);
        Assert.assertEquals(get.get(0), getString);
        get.clear();

        CObjectUtil.oConsumeNullRuntimeThrows(() -> get, null, new RuntimeException(OBJECT_IS_NULL));
        Assert.assertEquals(get.size(), 0);

        thrown.expect(RuntimeException.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oConsumeNullRuntimeThrows(null, null, new RuntimeException(OBJECT_IS_NULL));
    }

    @Test
    public void oConsumeNullThrows() throws Exception {
        final List<String> get = new ArrayList<>();
        CObjectUtil.oConsumeNullThrows(() -> get, (l) -> l.add(getString));
        Assert.assertEquals(get.size(), 1);
        Assert.assertEquals(get.get(0), getString);
        get.clear();

        CObjectUtil.oConsumeNullThrows(() -> get, null);
        Assert.assertEquals(get.size(), 0);

        thrown.expect(ObjectEmptyException.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oConsumeNullThrows(null, null);
    }

    @Test
    public void oConsumeNullThrows1() throws Exception {
        final List<String> get = new ArrayList<>();
        CObjectUtil.oConsumeNullThrows(() -> get, (l) -> l.add(getString), OBJECT_IS_NULL);
        Assert.assertEquals(get.size(), 1);
        Assert.assertEquals(get.get(0), getString);
        get.clear();

        CObjectUtil.oConsumeNullThrows(() -> get, null, OBJECT_IS_NULL);
        Assert.assertEquals(get.size(), 0);

        thrown.expect(ObjectEmptyException.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oConsumeNullThrows(null, null, OBJECT_IS_NULL);
    }

    @Test
    public void oConsumeNullThrows2() throws Exception {
        final List<String> get = new ArrayList<>();
        CObjectUtil.oConsumeNullThrows(() -> get, (l) -> l.add(getString), new Exception(OBJECT_IS_NULL));
        Assert.assertEquals(get.size(), 1);
        Assert.assertEquals(get.get(0), getString);
        get.clear();

        CObjectUtil.oConsumeNullThrows(() -> get, null, new Exception(OBJECT_IS_NULL));
        Assert.assertEquals(get.size(), 0);

        thrown.expect(Exception.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oConsumeNullThrows(null, null, new Exception(OBJECT_IS_NULL));
    }

    @Test
    public void oConvert() {
        Integer get = CObjectUtil.oConvert(() -> getString, Integer::valueOf, () -> Integer.valueOf(orString));
        Assert.assertEquals(get, Integer.valueOf(getString));

        get = CObjectUtil.oConvert(null, null, () -> Integer.valueOf(orString));
        Assert.assertEquals(get, Integer.valueOf(orString));

        thrown.expect(MethodNullException.class);
        thrown.expectMessage(EXECUTE_METHOD_IS_NULL);
        CObjectUtil.oConvert(() -> getString, null, () -> Integer.valueOf(orString));
    }

    @Test
    public void oConvert1() {
        thrown.expect(MethodNullException.class);
        thrown.expectMessage(EXECUTE_METHOD_IS_NULL);
        CObjectUtil.oConvert(null, null, null);
    }

    @Test
    public void oConvertNullRuntimeThrows() {
        Integer get = CObjectUtil.oConvertNullRuntimeThrows(() -> getString, Integer::valueOf);
        Assert.assertEquals(get, Integer.valueOf(getString));

        CObjectUtil.oConvertNullRuntimeThrows(() -> get, null);
        Assert.assertEquals(get.size(), 0);

        thrown.expect(ObjectEmptyRuntimeException.class);
        thrown.expectMessage(OBJECT_IS_NULL);
        CObjectUtil.oConsumeNullRuntimeThrows(null, null);
    }

    @Test
    public void oConvertNullRuntimeThrows1() {
    }

    @Test
    public void oConvertNullRuntimeThrows2() {
    }

    @Test
    public void oConvertNullThrows() {
    }

    @Test
    public void oConvertNullThrows1() {
    }

    @Test
    public void oConvertNullThrows2() {
    }
}