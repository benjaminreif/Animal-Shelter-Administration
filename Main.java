import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Animal> animals = new ArrayList<>();

        // 🟢 Load animals from file at program start
        try {
            File file = new File("animals.txt");
            if (file.exists()) {
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine(); // Read one line from file
                    String[] parts = line.split(";"); // Split line by semicolon
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    String species = parts[2];

                    // Create appropriate object based on species
                    if (species.equalsIgnoreCase("Dog")) {
                        animals.add(new Dog(name, age, species));
                    } else if (species.equalsIgnoreCase("Cat")) {
                        animals.add(new Cat(name, age, species));
                    } else {
                        animals.add(new Animal(name, age, species));
                    }
                }
                reader.close();
                System.out.println("✅ Animals loaded at program start.");
            } else {
                System.out.println("📁 No saved animals found.");
            }
        } catch (IOException e) {
            System.out.println("❌ Error while loading: " + e.getMessage());
        }

        boolean active = true;

        while (active) {
            // Display menu
            System.out.println("\n\n\n--- Animal Shelter Menu ---");
            System.out.println("[1]  Add animal");
            System.out.println("[2]  Show all animals");
            System.out.println("[3]  Search animal");
            System.out.println("[4]  Show animals by species");
            System.out.println("[5]  Calculate average age");
            System.out.println("[6]  Delete animal by name");
            System.out.println("[7]  Edit animal data");
            System.out.println("[8]  Save animals");
            System.out.println("[9]  Sort animals");
            System.out.println("[10] Show animals by specific age");
            System.out.println("[11] Count animals by species");
            System.out.println("[12] Show oldest animal");
            System.out.println("[13] Reload animals from file");
            System.out.println("[14] Show random animal");
            System.out.println("[0]  Exit program");
            System.out.print("\nChoice: ");
            int choice = sc.nextInt();

            int index = 0;
            String searchName;

            switch (choice) {
                case 1:
                    // Add animal
                    System.out.print("\nSpecies (Dog/Cat/other): ");
                    String species = sc.next();

                    System.out.print("Name: ");
                    String name = sc.next();

                    System.out.print("Age: ");
                    int age = sc.nextInt();

                    // Add correct animal type to list
                    if (species.equalsIgnoreCase("Dog")) {
                        animals.add(new Dog(name, age, species));
                    } else if (species.equalsIgnoreCase("Cat")) {
                        animals.add(new Cat(name, age, species));
                    } else {
                        animals.add(new Animal(name, age, species));
                    }
                    System.out.println("✅ Animal saved.");
                    break;

                case 2:
                    // Show all animals
                    System.out.println("\n--- Animal Overview ---");
                    for (Animal animal : animals) {
                        animal.introduce();

                        // Output specific behavior based on type
                        if (animal instanceof Dog) {
                            ((Dog) animal).bark();
                        } else if (animal instanceof Cat) {
                            ((Cat) animal).meow();
                        }
                        System.out.println();
                    }
                    break;

                case 3:
                    // Search by name
                    System.out.print("Enter animal name: ");
                    searchName = sc.next();

                    for (Animal animal : animals) {
                        if (animal.name.equalsIgnoreCase(searchName)) {
                            animal.introduce();

                            if (animal instanceof Dog) {
                                ((Dog) animal).bark();
                            } else if (animal instanceof Cat) {
                                ((Cat) animal).meow();
                            }
                            System.out.println();
                        }
                    }
                    break;

                case 4:
                    // Filter animals by species
                    System.out.print("Enter species: ");
                    String speciesSearch = sc.next();

                    for (Animal animal : animals) {
                        if (animal.species.equalsIgnoreCase(speciesSearch)) {
                            animal.introduce();

                            if (animal instanceof Dog) {
                                ((Dog) animal).bark();
                            } else if (animal instanceof Cat) {
                                ((Cat) animal).meow();
                            }
                            System.out.println();
                        }
                    }
                    break;

                case 5:
                    // Calculate average age
                    double average;
                    int sum = 0;

                    for (Animal animal : animals) {
                        index++;
                        sum += animal.age;
                    }

                    average = sum / index;
                    System.out.println("Average age: " + average);
                    break;

                case 6:
                    // Delete animal by name
                    System.out.print("Enter name of animal to delete: ");
                    searchName = sc.next();

                    boolean removed = animals.removeIf(animal -> animal.name.equalsIgnoreCase(searchName));

                    if (removed) {
                        System.out.println("✅ Animal deleted.");
                    } else {
                        System.out.println("❌ No animal found with that name.");
                    }
                    break;

                case 7:
                    // Edit animal data
                    System.out.print("Enter the name of the animal to edit: ");
                    String editName = sc.next();

                    boolean found = false;

                    for (Animal animal : animals) {
                        if (animal.name.equalsIgnoreCase(editName)) {
                            found = true;

                            System.out.println("What do you want to edit?");
                            System.out.println("[1] Name");
                            System.out.println("[2] Age");
                            System.out.println("[3] Species");
                            System.out.print("Choice: ");
                            int editChoice = sc.nextInt();

                            switch (editChoice) {
                                case 1:
                                    System.out.print("New name: ");
                                    animal.name = sc.next();
                                    System.out.println("✅ Name changed.");
                                    break;

                                case 2:
                                    System.out.print("New age: ");
                                    animal.age = sc.nextInt();
                                    System.out.println("✅ Age changed.");
                                    break;

                                case 3:
                                    System.out.print("New species: ");
                                    animal.species = sc.next();
                                    System.out.println("✅ Species changed.");
                                    break;

                                default:
                                    System.out.println("❌ Invalid choice.");
                            }

                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("❌ No animal with that name found.");
                    }
                    break;

                case 8:
                    // Save current animals to file
                    try {
                        FileWriter writer = new FileWriter("animals.txt"); // Overwrites file
                        for (Animal animal : animals) {
                            // Format: name;age;species per line
                            writer.write(animal.name + ";" + animal.age + ";" + animal.species + "\n");
                        }
                        writer.close();
                        System.out.println("✅ Animals saved successfully.");
                    } catch (IOException e) {
                        System.out.println("❌ Error while saving: " + e.getMessage());
                    }
                    break;

                case 9:
                    // Sort animals
                    System.out.println("What would you like to sort by?");
                    System.out.println("[1] By Name");
                    System.out.println("[2] By Species");
                    System.out.print("Your choice: ");
                    int sortChoice = sc.nextInt();

                    // Copy the list to keep the original intact
                    ArrayList<Animal> sortedList = new ArrayList<>(animals);

                    if (sortChoice == 1) {
                        // Sort by name
                        sortedList.sort((a, b) -> a.name.compareToIgnoreCase(b.name));
                    } else if (sortChoice == 2) {
                        // Sort by species
                        sortedList.sort((a, b) -> a.species.compareToIgnoreCase(b.species));
                    } else {
                        System.out.println("❌ Invalid input.");
                        break;
                    }

                    System.out.println("\n--- Sorted Animal List ---");
                    for (Animal animal : sortedList) {
                        animal.introduce();

                        if (animal instanceof Dog) {
                            ((Dog) animal).bark();
                        } else if (animal instanceof Cat) {
                            ((Cat) animal).meow();
                        }

                        System.out.println();
                    }

                    break;

                case 10:
                    // Show animals by specific age
                    System.out.print("🔎 Enter the age you'd like to search for: ");
                    int searchAge = sc.nextInt();

                    boolean foundAnimal = false;

                    System.out.println("\n--- Animals that are " + searchAge + " years old ---");
                    for (Animal animal : animals) {
                        if (animal.age == searchAge) {
                            animal.introduce();

                            if (animal instanceof Dog) {
                                ((Dog) animal).bark();
                            } else if (animal instanceof Cat) {
                                ((Cat) animal).meow();
                            }

                            System.out.println();
                            foundAnimal = true;
                        }
                    }

                    if (!foundAnimal) {
                        System.out.println("❌ No animals found with age " + searchAge + ".");
                    }

                    break;

                case 11:
                    // Count animals by species
                    int dogCount = 0;
                    int catCount = 0;
                    int otherCount = 0;

                    for (Animal animal : animals) {
                        if (animal instanceof Dog) {
                            dogCount ++;
                        } else if (animal instanceof Cat) {
                            catCount ++;
                        } else {
                            otherCount ++;
                        }
                    }

                    System.out.println("\n--- Animal Type Count ---");
                    System.out.println("Dogs:   " + dogCount);
                    System.out.println("Cats:   " + catCount);
                    System.out.println("Others: " + otherCount);

                    break;

                case 12:
                    // Show oldest animal
                    if (animals.isEmpty()) {
                        System.out.println("❌ No animals in the shelter.");
                        break;
                    }

                    Animal oldestAnimal = animals.get(0); // start with the first animal

                    for (Animal animal : animals) {
                        if (animal.age > oldestAnimal.age) {
                            oldestAnimal = animal;
                        }
                    }

                    System.out.println("\n--- Oldest Animal ---");
                    oldestAnimal.introduce();

                    if (oldestAnimal instanceof Dog) {
                        ((Dog) oldestAnimal).bark();
                    } else if (oldestAnimal instanceof Cat) {
                        ((Cat) oldestAnimal).meow();
                    }

                    System.out.println("This is the oldest animal in the shelter: " +oldestAnimal);

                    break;

                case 13:
                    // Reload animals from file
                    animals.clear(); // remove all current animals

                    try {
                        File file = new File("animals.txt");

                        if (file.exists()) {
                            Scanner reader = new Scanner(file);

                            while (reader.hasNextLine()) {
                                String line = reader.nextLine();
                                String[] parts = line.split(";");
                                String nameFromFile = parts[0];
                                int ageFromFile = Integer.parseInt(parts[1]);
                                String speciesFromFile = parts[2];

                                if (speciesFromFile.equalsIgnoreCase("Dog")) {
                                    animals.add(new Dog(nameFromFile, ageFromFile, speciesFromFile));
                                } else if (speciesFromFile.equalsIgnoreCase("Cat")) {
                                    animals.add(new Cat(nameFromFile, ageFromFile, speciesFromFile));
                                } else {
                                    animals.add(new Animal(nameFromFile, ageFromFile, speciesFromFile));
                                }
                            }

                            reader.close();
                            System.out.println("✅ Animals successfully reloaded from file.");
                        } else {
                            System.out.println("📁 No file found to reload.");
                        }
                    } catch (IOException e) {
                        System.out.println("❌ Error while reloading: " + e.getMessage());
                    }

                    break;

                case 14:
                    //Show random animal
                    if (animals.isEmpty()) {
                        System.out.println("❌ No animals in the shelter.");
                        break;
                    }

                    int randomIndex = (int) (Math.random() * animals.size());

                    Animal randomAnimal = animals.get(randomIndex); // get the random animal

                    System.out.println("\n🎲 Random animal selected:");
                    randomAnimal.introduce();

                    if (randomAnimal instanceof Dog) {
                        ((Dog) randomAnimal).bark();
                    } else if (randomAnimal instanceof Cat) {
                        ((Cat) randomAnimal).meow();
                    }

                    System.out.println();
                    break;

                case 15:
                    // Export all animals to a formatted file
                    try {
                        PrintWriter writer = new PrintWriter("overview.txt");

                        writer.println("--- Animal Overview ---\n");

                        for (Animal animal : animals) {
                            writer.println("Name:    " + animal.name);
                            writer.println("Age:     " + animal.age);
                            writer.println("Species: " + animal.species);
                            writer.println("------------------------\n");
                        }

                        writer.close();
                        System.out.println("✅ Animal overview exported to 'overview.txt'");
                    } catch (IOException e) {
                        System.out.println("❌ Error while exporting: " + e.getMessage());
                    }
                    break;

                case 0:
                    // Exit program
                    System.out.println("Program exiting.");
                    active = false;
                    break;

                default:
                    // Invalid input handler
                    System.out.println("❌ Invalid choice.");
                    break;
            }
        }
    }
}
