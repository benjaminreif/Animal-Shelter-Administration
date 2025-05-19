public class Dog extends Animal {

    public Dog(String name, int age, String species) {
        super(name, age, species);
    }

    public void bark() {
        System.out.print("-> Woof!");
    }
}