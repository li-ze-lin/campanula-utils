# 基于JDK8封装的一些工具类(不依赖于任何三方jar)

> 项目内每个方法都写了UT 具体可参照[test](https://github.com/li-ze-lin/campanula-utils/tree/test)分支 

## 引入
```xml
<!-- https://mvnrepository.com/artifact/io.github.campanula-medium/utils -->
<dependency>
    <groupId>io.github.campanula-medium</groupId>
    <artifactId>utils</artifactId>
    <version>1.0.3</version>
</dependency>

```

## 职责链模式的封装
- [io.github.campanula.utils.cor.Chain<IN, OUT>](https://github.com/li-ze-lin/campanula-utils/blob/dev/src/main/java/io/github/campanula/utils/cor/Chain.java)
- [io.github.campanula.utils.cor.AbstractChain<IN, OUT> implements Chain<IN, OUT>](https://github.com/li-ze-lin/campanula-utils/blob/dev/src/main/java/io/github/campanula/utils/cor/AbstractChain.java)

```java
/**
 * 每一节链只需继承AbstractChain 去实现handler()方法 最后执行execute()方法即可
 * @param <IN> 入参泛型
 * @param <OUT> 出参泛型
 */
 public interface Chain<IN, OUT>{
 	default Object execute()
 }
 
public abstract AbstractChain<IN, OUT> implements Chain<IN, OUT> {
	protected abstract OUT handler(IN inParam);
}
```
*[例子可以参考UT](https://github.com/li-ze-lin/campanula-utils/blob/test/src/test/java/io/github/campanula/utils/cor/ChainTest.java)*

## jdk8新的日期类之间的转换以及与java.util.Date
- [新日期类转换成Date io.github.campanula.utils.date.CDateUtil](https://github.com/li-ze-lin/campanula-utils/blob/master/src/main/java/io/github/campanula/utils/date/CDateUtil.java)
- [java.time.Instant相关的一些操作 io.github.campanula.utils.date.CInstantUtil](https://github.com/li-ze-lin/campanula-utils/blob/master/src/main/java/io/github/campanula/utils/date/CInstantUtil.java)
- [java.time.LocalDateTime相关的一些操作 io.github.campanula.utils.date.CLocalDateTimeUtil](https://github.com/li-ze-lin/campanula-utils/blob/master/src/main/java/io/github/campanula/utils/date/CLocalDateTimeUtil.java)
- [java.time.LocalDate相关的一些操作 io.github.campanula.utils.date.CLocalDateUtil](https://github.com/li-ze-lin/campanula-utils/blob/master/src/main/java/io/github/campanula/utils/date/CLocalDateUtil.java)

## 一些对象/集合/条件处理的工具方法
- [处理List的方法 io.github.campanula.utils.method.CListUtil](https://github.com/li-ze-lin/campanula-utils/blob/master/src/main/java/io/github/campanula/utils/method/CListUtil.java)
- [处理Map的方法 io.github.campanula.utils.method.CMapUtil](https://github.com/li-ze-lin/campanula-utils/blob/master/src/main/java/io/github/campanula/utils/method/CMapUtil.java)
- [处理List的方法 io.github.campanula.utils.method.CListUtil](https://github.com/li-ze-lin/campanula-utils/blob/master/src/main/java/io/github/campanula/utils/method/CListUtil.java)
- [处理对象的方法 io.github.campanula.utils.method.CObjectUtil](https://github.com/li-ze-lin/campanula-utils/blob/master/src/main/java/io/github/campanula/utils/method/CObjectUtil.java)
- [处理条件处理的方法 io.github.campanula.utils.method.CWhereUtil](https://github.com/li-ze-lin/campanula-utils/blob/master/src/main/java/io/github/campanula/utils/method/CWhereUtil.java)
*[例子可以参考UT](https://github.com/li-ze-lin/campanula-utils/tree/test/src/test/java/io/github/campanula/utils/method)*

## 基于JDK的代理模式进行封装






























