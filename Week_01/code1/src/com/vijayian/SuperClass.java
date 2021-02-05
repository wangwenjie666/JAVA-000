package com.vijayian;

/**
 * 父类
 *
 * @author ViJay
 * @date 2021-02-04
 */
public class SuperClass {
    public void print() {
        System.out.println("father");
    }

    //>> TODO 子类重写了父类的方法，不管子类的实例指向谁，调用的都是子类的方法.
    public static void main(String[] args) {
        SuperClass sc = new SuperClass();
        sc.print();//father
        System.out.println(sc.getClass());//class com.vijayian.SuperClass

        SuperClass superClass = new SubClass();
        superClass.print();//sub
        System.out.println(superClass.getClass());//class com.vijayian.SubClass

        SubClass subClass = new SubClass();
        subClass.print();//sub
        System.out.println(subClass.getClass());//class com.vijayian.SubClass
    }
}


class SubClass extends SuperClass{
    @Override
    public void print() {
        System.out.println("sub");
    }
}


