package com.ctrip.ibu.flight.internalws.common;

import com.ctrip.ibu.flight.internalws.common.utils.CommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by kyxie on 2017/10/20.
 */
public final class CommonTest {

    @Test
    public void testCommonUtil() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "xiekongye");
        map.put("gender", "male");

        boolean exist = CommonUtil.containsKey(map, null, true);

        Assert.assertEquals(exist, true);
    }

  @Test
  public void testMap() {
    Map<Long, String> map = new HashMap<>();
    map.put(1L, "a");
    map.put(2L, "b");

    long l = 1L;
    boolean exist = map.containsKey(l);

    String x = map.get(3L);
}

  @Test
  public void testList() {
    List<A> list = new ArrayList<>();
    list.add(new A(1, "s1"));
    list.add(new A(3, "s2"));
    list.add(new A(2, "s3"));

    // get first
    A a = list.stream().filter(p -> p.i == 3).findFirst().get();

    // find
    List<A> r = list.stream().filter(p -> p.i > 1).collect(Collectors.toList());

    // map
    List<String> ls = list.stream().filter(p -> p.i > 1).map(p -> p.s).collect(Collectors.toList());

    // sum
    int sum = list.stream().filter(p -> p.i > 1).mapToInt(p -> p.i).sum();


    List<String> li = new ArrayList<>();
    li.add("a");

    List<String> up = li.stream().map(p -> this.F(p)).collect(Collectors.toList());
    List<String> up1 = li.stream().map(CommonTest::F).collect(Collectors.toList());
    List<String> up2 = li.stream().map(String::toUpperCase).collect(Collectors.toList());

    Integer resultInteger = setInterge(10, val -> val + 10);
  }

  public static Integer setInterge(Integer value, Function<Integer, Integer> setMap) {
    return setMap.apply(value);
  }

  public static String F(String s) {
    return s.toUpperCase();
  }
}


class A {
  public int i;

  public String s;

  public A(int a, String s) {
    this.i = a;
    this.s = s;
  }
}


class T {
  public static <T> void F(List<T> list) {
    list.forEach(p -> System.out.println(p));
  }
}


class R implements Function<Integer, Integer> {

  /**
   * Applies this function to the given argument.
   *
   * @param integer the function argument
   * @return the function result
   */
  @Override
  public Integer apply(Integer integer) {
    return null;
  }
}
