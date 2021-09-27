package domain.model;

public class Animal {

    private String name;
    private String type;
    private int food;

    public Animal(String name) {
        this.setName(name);
    }

    public Animal(String name, String type, int food) {
        this.setName(name);
        this.setType(type);
        this.setFood(food);
    }

    public Animal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Geen geldige naam");
        }
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.isBlank()) {
            throw new IllegalArgumentException("Geen geldige soort");
        }
        this.type = type;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        if (food < 0) {
            throw new IllegalArgumentException("Geen geldige hoeveelheid voor voedsel");
        }
        this.food = food;
    }

}
