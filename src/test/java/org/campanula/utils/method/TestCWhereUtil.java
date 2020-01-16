package org.campanula.utils.method;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestCWhereUtil {

    @Test
    public void testThen() {
        List<Integer> data = new ArrayList<>();
        data.add(1);

        CWhereUtil.then(() -> data.add(2), () -> data.clear(), () ->  1 == 1);
        assertEquals(data.size(), 2);

        CWhereUtil.then(() -> data.add(2), () -> data.clear(), () ->  1 != 1);
        assertEquals(data.size(), 0);

        CWhereUtil.then(() -> data.add(2), () -> data.clear(), () ->  1 == 1, () -> 2 == 2);
        assertEquals(data.size(), 1);

        CWhereUtil.then(() -> data.add(2), () -> data.clear(), () ->  1 != 1, () -> 2 == 2);
        assertEquals(data.size(), 0);

        CWhereUtil.then(() -> data.add(2), () -> data.clear(), () ->  1 != 1, () -> 2 != 2);
        assertEquals(data.size(), 0);
    }

    @Test
    public void testThen1() {
        String data = CWhereUtil.then(() -> "success", () -> "fail", () -> 1 == 1);
        assertEquals(data, "success");

        data = CWhereUtil.then(() -> "success", () -> "fail", () ->  1 != 1);
        assertEquals(data, "fail");

        data = CWhereUtil.then(() -> "success", () -> "fail", () ->  1 == 1, () -> 2 == 2);
        assertEquals(data, "success");

        data = CWhereUtil.then(() -> "success", () -> "fail", () ->  1 != 1, () -> 2 == 2);
        assertEquals(data, "fail");

        data = CWhereUtil.then(() -> "success", () -> "fail", () ->  1 != 1, () -> 2 != 2);
        assertEquals(data, "fail");
    }

    @Test
    public void testThenThrow() {
        CWhereUtil.thenThrow(() -> System.out.println("success"), new RuntimeException(), () ->  1 == 1);

        assertThrows(RuntimeException.class, () -> CWhereUtil.thenThrow(() -> System.out.println("success"), new RuntimeException(), () ->  1 != 1));

        CWhereUtil.thenThrow(() -> System.out.println("success"), new RuntimeException(), () ->  1 == 1, () -> 2 == 2);

        assertThrows(RuntimeException.class, () -> CWhereUtil.thenThrow(() -> System.out.println("success"), new RuntimeException(), () ->  1 != 1, () -> 2 == 2));

        assertThrows(RuntimeException.class, () -> CWhereUtil.thenThrow(() -> System.out.println("success"), new RuntimeException(), () ->  1 != 1, () -> 2 != 2));
    }

    @Test
    public void testThenThrow1() {
        String s = CWhereUtil.thenThrow(() -> "success", new RuntimeException(), () -> 1 == 1);
        assertEquals(s, "success");

        assertThrows(RuntimeException.class, () -> CWhereUtil.thenThrow(() -> "success", new RuntimeException(), () ->  1 != 1));

        s = CWhereUtil.thenThrow(() -> "success", new RuntimeException(), () ->  1 == 1, () -> 2 == 2);
        assertEquals(s, "success");

        assertThrows(RuntimeException.class, () -> CWhereUtil.thenThrow(() -> "success", new RuntimeException(), () ->  1 != 1, () -> 2 == 2));

        assertThrows(RuntimeException.class, () -> CWhereUtil.thenThrow(() -> "success", new RuntimeException(), () ->  1 != 1, () -> 2 != 2));

    }
}