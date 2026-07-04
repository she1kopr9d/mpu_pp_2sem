package com.shelldev.project.birds;
public class Parrot extends Bird{
    private String name;
    private static int count = 0;

    public Parrot(){
        count++;
        java.lang.System.out.println("Я попугай.");
        name = "Кesha";
    }

    public Parrot(String name){
        count++;
        java.lang.System.out.println("Я попугай.");
        this.name = name;
    }

    public void speak(){
        java.lang.System.out.println("Меня зовут " + name + ".");
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static void printCount(){
        java.lang.System.out.println("Всего попугаев: " + count + ".");
    }

    public void hello(Parrot p){
        java.lang.System.out.println("Привет, попугай " + p.name + "! Я попугай, меня зовут " + this.name + "!");
    }

    public void hello(Penguin p){
        java.lang.System.out.println("Привет, попугай! Я попугай, меня зовут " + this.name + "!");
    }

    public void hello(Sparrow s){
        java.lang.System.out.println("Привет, воробей " + s.getName() + "! Я попугай!");
    }
}
