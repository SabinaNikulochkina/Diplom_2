package org.example;

public class DataOfIngredients {
    private String[] ingredients;

    public DataOfIngredients (String[] ingredients) {
        this.ingredients = ingredients;
    }

    public static DataOfIngredients getOrder() {
        return new DataOfIngredients(
                new String[]{"61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa6f"});
    }

    public static DataOfIngredients emptyOrder() {
        return new DataOfIngredients(
                new String[]{});
    }

    public static DataOfIngredients invalidOrder() {
        return new DataOfIngredients(
                new String[]{"1", "1"});
    }

}
