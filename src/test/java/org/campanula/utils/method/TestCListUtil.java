
package org.campanula.utils.method;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * @Author: Campanula
 * @Date 2019-12-15
 */
public class TestCListUtil {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGet() {
        List<Integer> integers = CListUtil.get(() -> Arrays.asList(1), () -> Arrays.asList(1, 2));
        Assert.assertEquals(integers.size(), 1);
        integers = CListUtil.get(Arrays::asList, () -> Arrays.asList(1, 2));
        Assert.assertEquals(integers.size(), 2);
        integers = CListUtil.get(() -> null, () -> Arrays.asList(1, 2));
        Assert.assertEquals(integers.size(), 2);
    }

    @Test
    public void testNullRuntimeThrows() {
        List<Integer> aThrows = CListUtil.getThrows(() -> Arrays.asList(1), RuntimeException::new);
        Assert.assertEquals(aThrows.size(), 1);
        thrown.expect(RuntimeException.class);
        CListUtil.getThrows(Arrays::asList, RuntimeException::new);
    }

    @Test
    public void testProcess() {
        List<Integer> process = CListUtil.process(() -> Lists.newArrayList(1), (s) -> {
            s.add(2);
            return s;
        }, () -> Lists.newArrayList(3));
        Assert.assertEquals(process.size(), 2);
        process = CListUtil.process(() -> null, (s) -> null, () -> Lists.newArrayList(3));
        Assert.assertEquals(process.size(), 1);
    }

    @Test
    public void testProcessNullRuntimeThrows() {
        List<Integer> integers = CListUtil.processThrows(() -> Lists.newArrayList(1), (s) -> {
            s.add(2);
            return s;
        }, RuntimeException::new);
        Assert.assertEquals(integers.size(), 2);
        thrown.expect(RuntimeException.class);
        CListUtil.processThrows(ArrayList::new, (s) -> null, RuntimeException::new);
    }

    @Test
    public void testConsume() {
        CListUtil.consume(() -> Lists.newArrayList(1), System.out::println, () -> System.out.println("2"));
        CListUtil.consume(ArrayList::new, System.out::println, () -> System.out.println("2"));
    }

    @Test
    public void  testConsumeNullRuntimeThrows() {
        CListUtil.consumeThrows(() -> Lists.newArrayList(1), System.out::println, RuntimeException::new);
        thrown.expect(RuntimeException.class);
        CListUtil.consumeThrows(ArrayList::new, System.out::println, RuntimeException::new);
    }

    @Test
    public void testConvert() {
        List<String> convert = CListUtil.convert(() -> Lists.newArrayList(1), (s) -> s.stream().map(String::valueOf).collect(Collectors.toList()), () -> Lists.newArrayList("2", "3"));
        Assert.assertEquals(convert.size(), 1);
        convert = CListUtil.convert(() -> null, (s) -> s.stream().map(String::valueOf).collect(Collectors.toList()), () -> Lists.newArrayList("2", "3"));
        Assert.assertEquals(convert.size(), 2);
    }

    @Test
    public void  testConvertNullRuntimeThrows() {
        List<String> strings = CListUtil.convertThrows(() -> Lists.newArrayList(1), (s) -> s.stream().map(String::valueOf).collect(Collectors.toList()), RuntimeException::new);
        Assert.assertEquals(strings.size(), 1);
        thrown.expect(RuntimeException.class);
        CListUtil.convertThrows(() -> null, (s) -> s.stream().map(String::valueOf).collect(Collectors.toList()), RuntimeException::new);
    }

}