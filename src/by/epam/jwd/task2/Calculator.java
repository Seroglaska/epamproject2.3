package by.epam.jwd.task2;


public class Calculator {
    public void calc(String s) {
        Logic logic = new Logic();
        View view = new View();
        s = s.replaceAll("\\s", "");
        double a = logic.calculator(s);
        view.outPut(a);
    }
}
