package org.example;

public class DeclareVariables {
    public String customerName = "Sasha";
    public int cupsOfCoffee = 1;
    public double costOfCoffee = 99.9;
    public boolean tip = true;
    public double totalCost = cupsOfCoffee * costOfCoffee;
    public String declareVariables() {
        String response = "";
        response = "Dear " + customerName + ", thank you for supporting our business!\n"
                + "Cups of coffee bought: " + cupsOfCoffee + ".\nCost of 1 cup of coffee: $"
                + costOfCoffee + ".\nYour total is: $" + totalCost + ".\nTip is included? " + tip + ".";

        return response;
    }
}
