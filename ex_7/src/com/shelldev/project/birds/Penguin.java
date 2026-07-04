package com.shelldev.project.birds;

public class Penguin extends Bird{
    private static int count = 0;

    public Penguin(){
        count++;
        java.lang.System.out.println("Я пингвин.");
        //java.lang.System.out.println("Я пингвин." + " Всего пингвинов: " + count + ".");
    }

    @Override
    public void fly(){
        //;
        java.lang.System.out.println("Пингвины не летают!");
    }

    public static void printCount(){
        java.lang.System.out.println("Всего пингвинов: " + count + ".");
    }

    public void hello(Parrot p){
        java.lang.System.out.println("Привет, попугай " + p.getName() + "! Я пингвин!");
    }

    public void hello(Penguin p){
        java.lang.System.out.println("Привет, пингвин! Я пингвин!");
    }

    public void hello(Sparrow s){
        java.lang.System.out.println("Привет, воробей " + s.getName() + "! Я пингвин!");
    }
}
