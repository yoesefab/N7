package Java;

public class main {
    public static void main(String[] args) {

        Ingénieur ing = new Ingénieur("Ali", "Ahmed", "ali@mail.com", "0600000000", 10000, "Java");

        Manager man = new Manager("Sara", "Ben", "sara@mail.com", "0700000000", 15000, "IT");

        System.out.println(ing);
        System.out.println(man);
    }
}