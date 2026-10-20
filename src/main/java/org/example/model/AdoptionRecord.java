package org.example.model;
import java.time.LocalDate;

public record AdoptionRecord(Animal animal, LocalDate adoptionDate, String adopterName) {

    @Override
    public String toString() {
        return animal.getId() + " | "
                + animal.getName() + " | "
                + animal.getAge() + " years old | "
                + animal.getSpecies() + " | "
                + adoptionDate + " | adopted by: "
                + adopterName;

    }
}
