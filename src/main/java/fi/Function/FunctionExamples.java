package fi.Function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.*;

public class FunctionExamples {
    /**
     * Function B Java
     * Function<T,R> -функциональный интерфейс появившийся в Јava 8. Используется для
     * описания функции генерирующей на основании объекта одного типа объект другого (или
     * такого же) типа. Используется в методах создания одних объектов на основании других
     * или для преобразования одних значений в другие. Для использования необходимо
     * импортировать пакет јаva.util.function.
     */

    //R apply(T t)
    //объекта который параметра новый объект Создает на ОСНовании Ссылка на
    //используется В качестве возвращает ссылку на него
    @Test
    @DisplayName("R apply(T t)")
    public void ex1() {
        Function<String, Integer> function0 = (a) -> a.length();
        Function<String, Integer> function1 = String::length;
        System.out.println(function0.apply("Hell"));
        System.out.println(function1.apply("Hello"));
    }


    //default <V> Function<T,V> andThen(Function<? super R,? extends V> after)
    //Возвращает композицию функций. Сначала будет применяться текущая функция и к ее результату
    //будет применяться функцйия выступаюая в качестве параметра.

    @Test
    @DisplayName("default <V> Function<T,V> andThen(Function<? super R,? extends V> after)")
    public void ex2() {
        Function<Integer, Double> function0 = (a) -> a + 3.0;
        Function<String, Integer> function1 = (a) -> a.length();

        Function<String, Double> function2 = function1.andThen(function0);

        System.out.println(function2.apply("Hello"));
    }


    //default <V> Function<V,R> compose(Function<? super V,? extends T> before)
    //Возвращает композицию функций. Сначала будет применяться выступаюая в качестве параметра ]
    //затем текущая

    @Test
    @DisplayName("default <V> Function<T,V> andThen(Function<? super R,? extends V> after)")
    public void ex3() {
        Function<Integer, Double> function0 = (a) -> a + 3.0;
        Function<String, Integer> function1 = (a) -> a.length();

        Function<String, Double> function2 = function0.compose(function1);

        System.out.println(function2.apply("Hello"));
    }

    //static <T> Function<T,T> identity()
    //Возвращает функцию которая возвращает свой входной параметр.

    @Test
    @DisplayName("default <V> Function<T,V> andThen(Function<? super R,? extends V> after)")
    public void ex4() {
        Function<Integer, Integer> fun = Function.identity();
        System.out.println(fun.apply(100));
        //TODO: И нахрен это нужно? Что получили то вернули!
    }

    /**
     * Бинарная специализация Function в виде BiFunction
     * Function бинарную (binary specializations) виде
     * функционального интерфейса Bifunction<T, U, R>. Бинарная специализация это вариация
     * интерфейса описывающая методы принимающие два параметра. Т.е. теперь результат
     * который вернет реализация BiFunction вычисляется на основе двух параметров T, U
     */

    @Test
    @DisplayName("default <V> Function<T,V> andThen(Function<? super R,? extends V> after)")
    public void ex5() {
        BiFunction<String, String, Integer> biFunction = (a, b) -> a.length() + b.length();
        System.out.println(biFunction.apply("hell0", "World"));

        //Пример для BiFunction andThen
        Function<Integer, Double> function1 = a -> a * 1.0;
        BiFunction<String, String, Double> resultFun = biFunction.andThen(function1);
        System.out.println(resultFun.apply("test", "test"));
    }
    //примитивные специализации Function

    //Принимающие примитивные специализации Function
    @Test
    @DisplayName("Принимающие примитивные специализации Function")
    public void ex6() {
        IntFunction<String> function = Integer::toString;
        LongFunction<String> function1 = Long::toString;
        DoubleFunction<String> function2 = Double::toString;

        System.out.println(function.apply(1));
        System.out.println(function1.apply(1L));
        System.out.println(function2.apply(1.0));
    }


    //Возвращающие примитивные специализации
    @Test
    @DisplayName("Возвращающие примитивные специализации ")
    public void ex7() {
        ToIntFunction<String> function0 = a -> a.hashCode();
        ToDoubleFunction<String> function1 = a -> a.length() * 3.0;
        ToLongFunction<String> function2 = a -> a.length() + 3L;

        System.out.println(function0.applyAsInt("Hello"));
        System.out.println(function1.applyAsDouble("Hello"));
        System.out.println(function2.applyAsLong("Hello"));
    }

    //Возвращающие и приминяет примитивные специализации Function используются В случае

    //Названия абстрактных методов формируется по принципу:
    //applyAsTип примитивного значения (тип результата) -> applyAsInt()
    @Test
    @DisplayName("Возвращающие и приминяет примитивные специализации Function")
    public void ex8() {
        DoubleToIntFunction fun = a -> (int) a;
        System.out.println(fun.applyAsInt(1.99));

        //По аналогии:
        //IntToLongFunction Создает значение типа long на основании значения типа int
        //IntToDoubleFunction Создает значение типа double на основании значения типа int
        //LongToDoubleFunction Создает+значение типа double на основании значения типа long
        //Long TolntFunction Создает значение типа int на основании значения типа long
        //DoubleTolntFunction Создает значение типа int на основании значения типа double
        //DoubleToLongFunction Создает значение типа Iong на основании значения типа double

    }

    //Примитивные BiFunction
    //Существуют примитивные специализации BiFunction. Они используются для
    //генерации на основании объектов значений примитивных типов.
    // Название Описание
    //TolntBiFunction<T, U> На основания объектов типа Т и U создается значение типа int
    //TOLongBiFunction<T, U> На основания объектов типа Т и U создается значение типа long
    //ToDoubleBiFunction<T, U> На основания объектов типа Т и U создается значение типа double
    //Внимание названия абстрактных методов формируется по принципу:
    //applyAsTип примитивного значения (тип результата)
    //Так например в ТolntBiFunction aбстрактный метод называется арplyAsint
    @Test
    @DisplayName("Примитивные BiFunction")
    public void ex9() {
        ToIntBiFunction<String,String> fun = (a,b) -> a.length() + b.length();
        System.out.println(fun.applyAsInt("test", "test"));
    }
}
