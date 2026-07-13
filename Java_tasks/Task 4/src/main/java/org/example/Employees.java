package org.example;

public class Employees {
    public String name;
    public String role;

    public Employees(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public static String getWelcomeMessage(Employees male, Employees female) {
        return "Вчера наша компания пополнилась новыми сотрудниками. " +
                male.getName() + " нанят на должность " + male.getRole() + ", а " +
                female.getName() + " нанята в должности: " + female.getRole() + ".";
    }
}
