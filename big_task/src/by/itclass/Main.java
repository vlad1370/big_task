package by.itclass;

import by.itclass.model.Cat;
import by.itclass.model.Dog;
import by.itclass.utils.CompetitionUtils;

import static by.itclass.utils.CompetitionUtils.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Cat> cats = new ArrayList<>();
        List<Dog> dogs = new ArrayList<>();
        Map<String, String> errors = new HashMap<>();

        parseFile(cats, dogs, errors);
        //CompetitionUtils.printResults(cats, dogs, errors);zz

        List<Cat> youngSortedCats = filterAnimals(sortByBirthDay(cats), true);
        List<Cat> oldSortedCats = filterAnimals(sortByBirthDay(cats), false);

        List<Dog> youngSortedDogs = filterAnimals(sortByBirthDay(dogs), true);
        List<Dog> oldSortedDogs = filterAnimals(sortByBirthDay(dogs), false);

        printResults(youngSortedCats, youngSortedDogs, oldSortedCats, oldSortedDogs, errors);



    }
}
