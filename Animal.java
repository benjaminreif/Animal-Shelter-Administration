public class Animal {

    String name;
    int age;
    String species;

    public Animal(String name, int age, String species) {
        this.name = name;
        this.age = age;
        this.species = species;
    }

    public void introduce() {
        System.out.println("\n-> I am " + name + ", " + age + " years old. " + species);
    }
}