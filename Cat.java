public class Cat extends Animal {

    public Cat(String name, int age, String species) {
        super(name, age, species);
    }

    public void meow() {
        System.out.print("-> Meow!");
    }
}