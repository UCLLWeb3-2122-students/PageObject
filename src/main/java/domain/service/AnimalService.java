package domain.service;

import java.util.ArrayList;

import domain.model.Animal;

public class AnimalService {

    private ArrayList<Animal> animals = new ArrayList<>();

    public AnimalService() {
        this.add(new Animal("Witje", "Kat", 3));
        this.add(new Animal("Zwartje", "Konijn", 1));
    }

    public void add(Animal animal) {
        if (animal == null)
            throw new IllegalArgumentException("Geef een bestaand dier");
        if (findAnimalWithName(animal.getName()) != null)
            throw new IllegalArgumentException("Je mag een dier maar één keer toevoegen");
        animals.add(animal);
    }

    public Animal findAnimalWithName(String naam) {
        if (naam == null || naam.isEmpty())
            throw new IllegalArgumentException("Naam mag niet leeg zijn");
        for (Animal dier : animals) {
            if (dier.getName().equals(naam))
                return dier;
        }
        return null;
    }

    public ArrayList<Animal> getAll() {
        return animals;
    }



}
