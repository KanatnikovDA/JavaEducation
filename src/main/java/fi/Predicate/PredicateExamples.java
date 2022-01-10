package fi.Predicate;

import fi.Predicate.metaPredicate.Cat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

@DisplayName("Predicate.")
public class PredicateExamples {

    /**
     * Predicate<T> - функциональный интерфейс появившийся в Јava 8. Используется для
     * проверки того или иного условия. Основная область применения это фильтрация данных
     * (подходит объект для дальнейшей обработки или нет).
     */

    //Predicate обобщенный интерфейс. Список методов:

    //boolean test(T t)
    //Проверяет удовлетворяет ли объект по ссылке t
    //заданному условию. Если да то метод должен вернуть
    //true, в противном случаe false.

    //Абстрактный метод интерфейса Predicate
    //Так как Predicate функциональный интерфейс, то обязательным к реализации должен
    //быть только один метод. В данном интерфейсе это метод boolean test (T t). Его
    //реализация и должна вернуть значение true eсли объект по ссылке t удовлетворяет
    //нужному критерию, и false eсли нет. Так, как интерфейс функциональный то в качестве его
    //реализации можно использовать как ссылки на методы, так и лямбда функции.
    @Test
    @DisplayName("boolean test(T t) + лямда")
    public void ex1() {
        Cat cat1 = new Cat("Umka", 12);
        Cat cat2 = new Cat("Luska", 5);
        Cat cat3 = new Cat("Barsic", 8);
        Cat cat4 = new Cat("Timka", 4);
        Cat cat5 = new Cat("Kuzia", 2);

        List<Cat> cats = new ArrayList<>(List.of(cat1, cat2, cat3, cat4, cat5));
        cats.removeIf(a -> a.getAge() < 6);
        System.out.println(cats);
    }

    @Test
    @DisplayName("boolean test(T t) + ссылка на метод")
    public void ex2() {
        Cat cat1 = new Cat("Umka", 12);
        Cat cat2 = new Cat("Luska", 5);
        Cat cat3 = new Cat("Barsic", 8);
        Cat cat4 = new Cat("Timka", 4);
        Cat cat5 = new Cat("Kuzia", 2);

        List<Cat> cats = new ArrayList<>(List.of(cat1, cat2, cat3, cat4, cat5));
        cats.removeIf(PredicateExamples::testCat);
        System.out.println(cats);
    }

    /**
     * < 6
     */
    public static boolean testCat(Cat cat) {
        return cat.getAge() < 6;
    }

    //default Predicate<T> and(Predicate<? super T> other)
    //Возвращает составной предикат (в виде краткого
    //логического И) на основе текущего и того что выступает
    //параметром other.

    //Этот метод принимает в качестве параметра совместимый с текущим предикатом
    //(совместимость по типу входного параметра) предикат и возвращает предикат который
    //является реализацией логического оператора AND на основе текущего предиката и
    //предиката который был параметром. Т.е. такой предикат вернет true только в том случае
    //если оба этих предиката вернут true. Внимание используется краткая форма AND т. е. ecли
    //первый предикат(текущий) вернет false то второй (параметр) даже не будет проверяться.

    @Test
    @DisplayName("default Predicate<T> and(Predicate<? super T> other)")
    public void ex3() {
        Cat cat1 = new Cat("Umka", 12);
        Cat cat2 = new Cat("Luska", 5);
        Cat cat3 = new Cat("Barsic", 8);
        Cat cat4 = new Cat("Timka", 4);
        Cat cat5 = new Cat("Kuzia", 2);

        List<Cat> cats = new ArrayList<>(List.of(cat1, cat2, cat3, cat4, cat5));
        Predicate<Cat> pr1 = a -> a.getAge() < 5;
        Predicate<Cat> pr2 = a -> a.getName().startsWith("K");
        cats.removeIf(pr1.and(pr2));
        System.out.println(cats);
    }

    //default Predicate<T> or(Predicate<? super T> other)
    //Возвращает составной предикат (в виде краткого
    //логического илИ) на основе текущего и того что выступает
    //параметром other.

    //Этот метод принимает в качестве параметра совместимый с текущим предикат
    //(совместимость по типу входного параметра) предикат и возвращает предикат которв
    //является реализацией логического оператора OR на основе текущего предиката
    //предиката который был параметром. Т.e. такой предикат вернет true в случае хотя бы од
    //из этих предикатов вернет true. Внимание используется краткая форма ОR T. е. eс.
    //первый предикат(текущий) вернет true то второй (параметр) даже не будет проверяться.

    @Test
    @DisplayName("default Predicate<T> or(Predicate<? super T> other)")
    public void ex4() {
        Cat cat1 = new Cat("Umka", 12);
        Cat cat2 = new Cat("Luska", 5);
        Cat cat3 = new Cat("Barsic", 8);
        Cat cat4 = new Cat("Timka", 4);
        Cat cat5 = new Cat("Kuzia", 2);

        List<Cat> cats = new ArrayList<>(List.of(cat1, cat2, cat3, cat4, cat5));
        Predicate<Cat> pr1 = PredicateExamples::testCat;
        Predicate<Cat> pr2 = a -> a.getName().startsWith("B");

        cats.removeIf(pr1.or(pr2));
        System.out.println(cats);
    }


    //default Predicate<T> negate()
    //Возвращает предикат в виде логического НЕ на основании
    //текущего предиката

    //Этот метод возвращает предикат который является реализацией логического
    //оператора NOT на основе текущего предиката. Т.е. такой предикат вернет true в случае
    //если текущий предикат вернет false и наоборот.

    @Test
    @DisplayName("default Predicate<T> negate()")
    public void ex5() {
        Cat cat1 = new Cat("Umka", 12);
        Cat cat2 = new Cat("Luska", 5);
        Cat cat3 = new Cat("Barsic", 8);
        Cat cat4 = new Cat("Timka", 4);
        Cat cat5 = new Cat("Kuzia", 2);

        List<Cat> cats = new ArrayList<>(List.of(cat1, cat2, cat3, cat4, cat5));
        Predicate<Cat> pr1 = PredicateExamples::testCat;

        cats.removeIf(pr1.negate());
        System.out.println(cats);
    }
    //static <T> Predicate<T> not(Predicate<? super T> other)
    //Возвращает предикат в виде логического НЕ на основе
    //предиката используемого в качестве параметра оther.

    @Test
    @DisplayName("static <T> Predicate<T> not(Predicate<? super T> other")
    public void ex6() {
        Cat cat1 = new Cat("Umka", 12);
        Cat cat2 = new Cat("Luska", 5);
        Cat cat3 = new Cat("Barsic", 8);
        Cat cat4 = new Cat("Timka", 4);
        Cat cat5 = new Cat("Kuzia", 2);

        List<Cat> cats = new ArrayList<>(List.of(cat1, cat2, cat3, cat4, cat5));

        cats.removeIf(Predicate.not(PredicateExamples::testCat));
        System.out.println(cats);
    }
    //static <T> Predicate<T> isEqual(Object targetRef)
    //Возвращает предикат, который проверяет, равны ли два
    //аргумента согласно Objects.equals (Object, Object). Объект
    //с которым будут сравниваться другие задается параметром targetRef
    //Этот метод возвращает предикат который производит сравнение объекта который
    //будет параметром метода test(T t) с объектом который был параметром этого метода. Для
    //сравнения используется метод еquals этих объектов.

    @Test
    @DisplayName("static <T> Predicate<T> isEqual(Object targetRef)")
    public void ex7() {
        List<String> names = new ArrayList<>(List.of("Anna", "Ira", "Katia", "Anna"));
        Predicate<String> pr1 = Predicate.isEqual("Anna");
        names.removeIf(pr1);
        System.out.println(names);
    }

    /**
     * Бинарные специализации.
     */

    //Бинарная специализация Predicate в виде BiPredicatе
    //Predicate бинарную специализацию (binary specializations) имеет В виде
    //функционального интерфейса BiPredicate<T, U>. Бинарная специализация это вариация
    //интерфейса описывающая методы принимающие два параметра. Т.е. теперь результат
    //который вернет реализация ВiPredicate зависит от двух объектов.

    //boolean test(T t, Uu)
    //Проверяет удовлетворяет ли объект по ссылке t и
    //объект по ссылке и заданному условию. Если да то
    //метод должен вернуть truе, в противном случае false.
    @Test
    @DisplayName("boolean test(T t, Uu)")
    public void ex8() {
        BiPredicate<String, Integer> biPred = (a, b) -> a.length() > b;
        System.out.println(biPred.test("Hlo", 3));
    }

    //default Predicate<T,U> and(Predicate<? super T, ? super U>other)
    //Возвращает составной предикат (в виде краткого
    //логического И) на основе текущего и того что
    //выступает параметром other.
    @Test
    @DisplayName("default Predicate<T,U> or(Predicate<? super T, ? super U>other)")
    public void ex9() {
        BiPredicate<String, Integer> biPred1 = (a, b) -> a.length() < b + 3;
        BiPredicate<String, Integer> biPred2 = (a, b) -> a.length() > b - 3;

        System.out.println(biPred1.and(biPred2).test("12345", 3));
    }

    //default Predicate<T,U> or(Predicate<? super T, ? super U>other)
    //Возвращает составной предикат (в виде краткого
    //логического ИЛИ) на основе текущего и того что
    //выступает параметро other.

    @Test
    @DisplayName("default Predicate<T,U> or(Predicate<? super T, ? super U>other)")
    public void ex10() {
        BiPredicate<String, Integer> biPred1 = (a, b) -> a.length() < b + 1;
        BiPredicate<String, Integer> biPred2 = (a, b) -> a.length() > b + 1;

        System.out.println(biPred1.or(biPred2).test("1234", 4));
        System.out.println(biPred1.or(biPred2).test("12345", 4));
        System.out.println(biPred1.or(biPred2).test("123456", 4));
    }

    //default Predicate<T,U> negate()
    //Возвращает предикат в виде логического НЕ на
    //Основании текущего предиката

    @Test
    @DisplayName("default Predicate<T,U> negate()")
    public void ex11() {
        BiPredicate<String, Integer> biPred1 = (a, b) -> a.length() < b + 1;

        System.out.println(biPred1.negate().test("1", 1));
        System.out.println(biPred1.negate().test("12", 1));
        System.out.println(biPred1.negate().test("123", 1));
    }

    /**Примитивные специализации функциональных интерфейсов*/

    //Существуют специализации интерфейса Predicate для работы с примитивными типами.
    //Т.е. в методе test в качестве параметра использует значение примитивных типов.
    //Эти специализации используются для работы с массивами примитивных типов и для потоков примитивных типов.

    /**
     * Тип С каким типом данных работает
     * IntPredicate  -> int
     * LongPredicate  -> long
     * DoublePredicate  -> double
     */
    // Как и у бинарных специализаций у данных специализаций есть методы по умолчанию: and, or, negate.

    @Test
    @DisplayName("default IntPredicate test()")
    public void ex12() {
        IntPredicate pr = a -> a % 2 == 0;
        System.out.println(pr.test(100));
        System.out.println(pr.test(101));
    }
}
