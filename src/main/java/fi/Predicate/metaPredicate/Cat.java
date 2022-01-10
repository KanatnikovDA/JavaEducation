package fi.Predicate.metaPredicate;

import lombok.Data;

@Data
public class Cat {

    private String name;
    private int age;

    public Cat(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public Cat() {
        super();
    }

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return " | name = " + name + ", age = " + age + " |";
    }
}
