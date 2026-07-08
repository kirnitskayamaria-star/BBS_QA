package org.example;

public class MainContainer {

public static class VariablePosition {
    public int replacement() {

        int a = 1;
        int b = a;

        return b;
    }
}
}
/*Статический класс может существовать, только если он является внутренним. Внешний класс не может быть статическим.*/
