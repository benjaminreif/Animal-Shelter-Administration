# Animal-Shelter-Administration

This project is a Java-based animal shelter management system that allows you to manage animals in a shelter. Currently, the project is a work in progress, and more features will be added in the future.

### Features (Work in Progress)

- Add new animals to the shelter
- View all animals in the shelter
- Search for an animal by name
- Display animals by species
- Calculate the average age of animals in the shelter
- Delete animals by name
- Edit animal data
- Save animal data to a file
- Sort animals by name or species

**Note:** A graphical user interface (GUI) with Swing is planned but has not yet been implemented.

## 🛠 Tech Stack

- **Java** (Standard Edition)
- **File handling** for saving and loading animals from a text file
- No external libraries required

## 💾 Data Storage

Animal data is loaded from and saved to a file (`tiere.txt`) during the program's runtime. All data is lost once the program is closed unless explicitly saved.

## 🚀 Getting Started

### Run Locally

1. Clone or download the repository.
2. Open the project in your preferred Java IDE.
3. Run the `Main.java` file.

Alternatively, compile and run from terminal:

```bash
javac Main.java
java Main
```

### Usage

1. Upon running the program, you will be presented with a text-based menu in the console where you can choose different actions, such as adding an animal, viewing the list, or calculating the average age.
2. Follow the prompts in the console to interact with the application.



### 📂 File Structure
```
/AnimalShelterManagement
├── src/
│ ├── Main.java        # Main application source code
│ ├── Animal.java      # Base animal class
│ ├── Dog.java         # Dog subclass
│ └── Cat.java         # Cat subclass
├── animals.txt        # Generated file to store animal data
├── README.md          # Project documentation
└── LICENSE            # License file (MIT by default)
```

## 🙋‍♂️ Credits

This project was created with the help of online resources and guidance from ChatGPT, as part of my learning journey in Java.

## 📚 License

This project is open-source and available under the [MIT License](LICENSE).
