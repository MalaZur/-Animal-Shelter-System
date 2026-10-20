package org.example.model;

public final class Bat extends Animal{
    public Bat(AnimalId id, String name, int age){
        super(id,name,age);
    }

    @Override
    public String getSpecies(){
        return "Bat";
    }
}
