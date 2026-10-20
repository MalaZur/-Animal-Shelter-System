package org.example.utils;

import org.example.model.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnimalsUtility {

    public static <T extends Animal> double averageAge(List<T> animals) {
        if (animals.isEmpty()){
            return 0;
        }

        double result = 0;
        int count = 0;

        for (T animal : animals) {
            count++;
            result += animal.getAge();
        }
        result /= count;

        return result;
    }

    public static <T extends Animal> T oldestAnimal(List<T> animals) {
        return animals.stream()
                .max((a, b) -> Integer.compare(a.getAge(), b.getAge()))
                .orElse(null);
    }

    public static <T extends Animal> Map<String, Long> countBySpecies(List<T> animals){
        return animals.stream()
                .collect(Collectors.groupingBy(
                        Animal::getSpecies,Collectors.counting()));
    }
}