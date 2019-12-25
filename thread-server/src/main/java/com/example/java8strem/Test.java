package com.example.java8strem;

import java.util.Optional;
import java.util.function.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author：张鸿建
 * @time：2019/12/24 11:33
 * @desc：
 **/
public class Test {

    public static void main(String[] args) {
        //consumerMethod();
       // functionMethod();
        //predicateMethod();
        //streamMethod1();
        //System.out.println("--------------------------");
        //streamMethod2();
        //filterStream();
        //mapStream();
        flatMapStream();
    }

    static void consumerMethod() {
        Consumer<String> a = (o) -> {
            System.out.println("a:" + o);
        };
        Consumer b = (o) -> System.out.println("b:" + o);
        Consumer c = System.out::println;
        a.andThen(b).accept("bbbbbbbb");
        a.accept("aaaaaa");
        b.accept("bbbbbb");
        c.accept("cccccc");
    }

    static void functionMethod() {
        Function<Integer, Integer> f = s -> ++s;
        Function<Integer, Integer> g = s -> s * 3;

        //  先执行g  用g的输出作为f输入
        System.out.println(f.compose(g).apply(1));
        //  与上面相反
        System.out.println(f.andThen(g).apply(1));
        System.out.println(g.apply(1));

        // 返回一个不进行任何处理的function
        System.out.println(Function.identity().apply("a"));
    }

    static void predicateMethod(){
        Predicate<String> p=o->o.equals("test");
        Predicate<String> g=o->o.startsWith("t");
        //  取反
        System.out.println(p.negate().test("test"));
        // and并列 都为ture时返回true
        System.out.println(p.and(g).test("t"));
        // or
        System.out.println(p.or(g).test("ta"));

    }
    static void streamMethod1(){
        Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        }).limit(10).forEach(System.out::println);
    }

    static void streamMethod2(){
        Stream.generate(()->Math.random()).limit(10).forEach(System.out::println);
    }

    static void filterStream(){
        Stream<String> s = Stream.of("test", "1", "2", "good", "ta", "aaa");
        s.filter(n->n.contains("t")).forEach(System.out::println);
    }

    static void mapStream(){
        Stream<String> s = Stream.of("test", "t1", "t2", "teeeee", "aaaa");
        s.map(n -> n.concat(".txt")).forEach(System.out::println);
    }

    static void flatMapStream(){
        Stream<String> s = Stream.of("test", "good", "python", "java", "test", "java");
        s.flatMap(n->Stream.of(n.split(""))).forEach(System.out::print);

    }

    static void optionalStream(){
        Optional<String> test = Optional.of("test");
        boolean present = test.isPresent();
    }
}
