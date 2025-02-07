package com.codechen.interview;

import java.util.HashSet;

/**
 * @author：Java陈序员
 * @date：2025-2-7 10:41
 * @description：== 和 equals 的区别比较
 *  1 比较范围
 *      1.1 == 既可以比较基本类型也可以比较引用类型，
 *      1.2 equals 只能比较引用类型，equals(Object obj)
 *  2 比较规则
 *      == 对于基本类型值是否相等，对于引用类型内存地址是否相等
 *      equals 比较规则，看是否被重写过。没有被重写，出厂默认就是 == 如果被重写，具体看实现方法
 */
public class EqualsDemo {

    public static void main(String[] args) {

        String s1 = new String("aa");
        String s2 = new String("aa");

        // false
        System.out.println(s1 == s2);
        // true
        System.out.println(s1.equals(s2));

        HashSet<String> set01 = new HashSet<>();
        set01.add(s1);
        set01.add(s2);
        System.out.println(s1.hashCode() + "\t" + s2.hashCode());
        // 1
        System.out.println(set01.size());

        System.out.println("====================================");

        Person person1 = new Person("张三");
        Person person2 = new Person("张三");


        System.out.println(person1 == person2);
        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode() + "\t" + person2.hashCode());

        HashSet<Person> set02 = new HashSet<>();
        set02.add(person1);
        set02.add(person2);
        System.out.println(set02.size());

    }

    static class Person {
        private String personName;

        public Person() {
        }

        public Person(String personName) {
            this.personName = personName;
        }

//        @Override
//        public boolean equals(Object obj) {
//
//            Person newPerson = (Person) obj;
//            return this.personName.equals(newPerson.personName);
//        }
    }
}
