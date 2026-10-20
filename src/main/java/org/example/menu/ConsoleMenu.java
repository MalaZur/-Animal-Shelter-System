package org.example.menu;

import org.example.model.*;
import org.example.shelter.Shelter;
import org.example.utils.AnimalsUtility;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final Shelter<Animal> shelter;
    private final Scanner scanner =  new Scanner(System.in);
    public ConsoleMenu(Shelter<Animal> shelter) {
        this.shelter = shelter;
    }

    public void start(){

        boolean running = true;

        while (running){

            printMenu();

            System.out.println("Choose an option: ");

            if (!scanner.hasNextInt()){
                System.out.println("Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:

                    System.out.println("Enter species (Dog/Cat/Bird/Bat): ");
                    String speciesToAdd = scanner.nextLine();


                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();

                    while (name.isEmpty()){
                        System.out.println("Name cannot be empty.");
                        name = scanner.nextLine();
                    }

                    System.out.println("Enter age: ");
                    if (!scanner.hasNextInt()){
                        System.out.println("Age must be a number.");
                        scanner.nextLine();
                        continue;
                    }
                    int age = scanner.nextInt();
                    if (age < 1){
                        System.out.println("Age must be a positive number");
                        break;
                    }

                    switch (speciesToAdd.toLowerCase()){
                        case "dog":
                            shelter.addAnimal(new Dog(new AnimalId(), name, age));
                            break;
                        case "cat":
                            shelter.addAnimal(new Cat(new AnimalId(), name, age));
                            break;
                        case "bird":
                            shelter.addAnimal(new Bird(new AnimalId(), name, age));
                            break;
                        case "bat":
                            shelter.addAnimal(new Bat(new AnimalId(), name, age));
                            break;
                        default:
                            System.out.println("Unknown species");
                            break;
                    }

                    break;
                case 2:

                    for (Animal animal : shelter.getAllAnimals()){
                        System.out.println(animal);
                    }

                    break;
                case 3:

                    System.out.print("Enter species: ");
                    String species = scanner.nextLine();

                    for (Animal animal : shelter.findBySpecies(species)){
                        System.out.println(animal);
                    }

                    break;
                case 4:

                    for (Animal animal : shelter.findAvailableAnimals()){
                        System.out.println(animal);
                    }

                    break;
                case 5:

                    System.out.println("Enter animal ID: ");
                    String id = scanner.nextLine();

                    System.out.println("Enter adopter name: ");
                    String adopterName = scanner.nextLine();

                    while (adopterName.isEmpty()){
                        System.out.println("Name cannot be empty.");
                        adopterName = scanner.nextLine();
                    }

                    shelter.markAsAdopted(id, adopterName);
                    break;

                case 6:

                    for (Animal animal : shelter.sortByAge()){
                        System.out.println(animal);
                    }
                    break;

                case 7:

                    for (Animal animal : shelter.sortByName()){
                        System.out.println(animal);
                    }
                    break;

                case 8:

                    System.out.println("Average age: " + AnimalsUtility.averageAge(shelter.getAllAnimals()));
                    System.out.println("\nOldest animal: " + AnimalsUtility.oldestAnimal(shelter.getAllAnimals()));
                    System.out.println("\nCount by species: " + AnimalsUtility.countBySpecies(shelter.getAllAnimals()));

                    break;

                case 9:

                    List<AdoptionRecord> history = shelter.getAdoptionHistory();

                    if (history.isEmpty()){
                        System.out.println("No adoption history found");
                    } else{
                        for (AdoptionRecord record : history){
                            System.out.println(record);
                        }
                    }
                    break;

                case 0:

                    running = false;

                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    private void printMenu(){
        System.out.println("""
                1. Add animal
                2. List all animals
                3. Find animals by species
                4. List available animals
                5. Mark animal as adopted
                6. Sorted list of all animals by age
                7. Sorted list of all animals by name
                8. Show shelter statistics
                9. Show adoption records
                0. Exit
                """);
    }
}
