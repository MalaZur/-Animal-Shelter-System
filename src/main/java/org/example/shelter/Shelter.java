package org.example.shelter;

import org.example.model.AdoptionRecord;
import org.example.model.AdoptionStatus;
import org.example.model.Animal;
import org.example.model.AdoptionRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Shelter <T extends Animal>{
    private final List<T> animals = new ArrayList<>();
    private final List<AdoptionRecord> adoptionHistory = new ArrayList<>();


    public void addAnimal(T animal){
        animals.add(animal);
    }

    public List<T> getAllAnimals(){

        return new ArrayList<>(animals);

    }

    public List<T> findBySpecies(String species){

        List<T> result = new ArrayList<>();

        for (T animal : animals){
            if (animal.getSpecies().equals(species)){
                result.add(animal);
            }
        }

        return result;
    }

    public List<T> findAvailableAnimals(){

        List<T> result = new ArrayList<>();

        for (T animal : animals){
            if (animal.getAdoptionStatus() == AdoptionStatus.AVAILABLE){
                result.add(animal);
            }
        }

        return result;
    }

    public void markAsAdopted(String id, String adopterName){
        for (T animal : animals){
            if (animal.getId().toString().equals(id)){
                if (animal.getAdoptionStatus() == AdoptionStatus.ADOPTED){
                    System.out.println("This animal has already been adopted.");
                    return;
                }

                animal.markAsAdopted();

                adoptionHistory.add(new AdoptionRecord(animal, LocalDate.now(), adopterName));

                System.out.println("Animal successfully adopted");
                return;
            }
        }
        System.out.println("Animal not found");
    }

    public List<T> sortByAge(){
        List<T> result = getAllAnimals();
        result.sort(Comparator.comparingInt(Animal::getAge));
        return result;
    }

    public List<T> sortByName(){
        List<T> result = getAllAnimals();
        result.sort(Comparator.comparing(Animal::getName));
        return result;
    }

    public List<AdoptionRecord> getAdoptionHistory(){
        return new ArrayList<>(adoptionHistory);
    }


}
