package com.codechen.interview;

/**
 * @author：Java陈序员
 * @date：2025-2-8 9:19
 * @description：传值还是传引用
 * 基本数据类型：当传递基本数据类型时，传递的是值的副本。因此，方法内部对参数的修改不会影响外部变量。
 * 对象：当传递对象时，传递的是对象引用的副本。由于引用副本指向同一个对象，因此方法内部对对象的修改会影响外部的对象。
 */
public class TransmitValueOfRefDemo {

    public static void main(String[] args) {
        int age = 30;
        Integer value = Integer.valueOf(300);
        changeValue1(age);
        changeValue1(value);
        // age = 30
        System.out.println("age = " + age);
        // value = 300
        System.out.println("value = " + value);

        Person person = new Person("张三");
        changeValue2(person);
        // personName = 李四
        System.out.println("personName = " + person.getPersonName());

        String s = "Hello World";
        changeValue3(s);
        // s = Hello World
        // String 类型和包装类型都是对象类型，所以必然是引用传递
        // 但是由于 String 类和包装类都被设定成不可变的，没有提供 value 对应的 setter 方法，而且很多都是 final 的，无法改变其内容，所以导致看起来好像是值传递（即没有影响原来的值）。
        System.out.println("s = " + s);
    }

    private static void changeValue1(int value) {
        value = 20;
    }

    private static void changeValue2(Person person) {
        person.setPersonName("李四");
    }

    private static void changeValue3(String value) {
        value = "xxxxxxxxxxxxxxxxxxxxx";
    }

    static class Person {
        private int id;
        private String personName;

        public Person() {
        }

        public Person(String personName) {
            this.personName = personName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPersonName() {
            return personName;
        }

        public void setPersonName(String personName) {
            this.personName = personName;
        }
    }

}
