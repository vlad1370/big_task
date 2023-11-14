package by.itclass.utils;

import by.itclass.exceptions.CompetitionException;
import by.itclass.model.Animal;
import by.itclass.model.Cat;
import by.itclass.model.Dog;
import lombok.experimental.UtilityClass;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static by.itclass.utils.AnimalFactory.EMAIL_REGEX;

@UtilityClass
public class CompetitionUtils {
    private String PATH_TO_FILE = "src/by/itclass/resources/animals.txt";

    public static void parseFile(List<Cat> cats, List<Dog> dogs,
                                 Map<String, String> errors) {
        try (Scanner sc = new Scanner(new FileReader(PATH_TO_FILE))) {
            while (sc.hasNextLine()) {
                //System.out.println(sc.nextLine());
                fillingsCollections(sc.nextLine(), cats, dogs, errors);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File hasn't found by path \"%s\"%n", PATH_TO_FILE);
            System.exit(1);
        }
    }

    private static void fillingsCollections(String textLine, List<Cat> cats, List<Dog> dogs, Map<String, String> errors) {
        try {
            Animal animal = AnimalFactory.getInstance(textLine);
            if (animal instanceof Cat) {
                cats.add((Cat) animal);
            } else {
                dogs.add((Dog) animal);
            }
        } catch (CompetitionException e) {
            processException(errors, e);
        }
    }

    private static void processException(Map<String, String> errors, CompetitionException e) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(e.getErrorLine());
        String str = "";
        while (matcher.find()) {
            str = matcher.group();
        }
        if (!str.isEmpty()) {
            errors.put(str, String.format("Error in string %s - %s", e.getErrorLine(), e.getCause().getMessage()));
        }
    }

    public static <T> List<T> sortByBirthDay(List<T> animals) {
        return animals.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public static void printResults(List<Cat> cats, List<Dog> dogs, Map<String, String> errors) {
        System.out.println("Cats list size = " + cats.size());
        printCollection(cats);
        System.out.println("Dogs list size = " + dogs.size());
        printCollection(dogs);
        System.out.println("Errors quantity = " + errors.size());
        printMap(errors);
    }

    private static <T> void printCollection(List<T> collection) {
        collection.forEach(System.out::println);
    }

    private static void printMap(Map<String, String> map) {
        map.forEach((key, value) -> System.out.println(key + "; " + value));
    }

    private static final LocalDate AGE_DELIMITER = LocalDate.now().minusYears(2);
    private static final Predicate<Animal> YOUNG_PREDICATE = it -> it.getBirthDate().isAfter(AGE_DELIMITER);
    private static final Predicate<Animal> OLD_PREDICATE = it -> it.getBirthDate().isBefore(AGE_DELIMITER);
    public static <T extends Animal> List<T> filterAnimals(List<T> animals, boolean isYoung) {
        return animals.stream()
                .filter(isYoung ? YOUNG_PREDICATE : OLD_PREDICATE)
                .collect(Collectors.toList());
    }

    public static void printResults(List<Cat> youngCats, List<Dog> youngDogs, List<Cat> oldCats, List<Dog> oldDogs, Map<String,String> errors) {
        System.out.println("\nFirst day participants: ");
        System.out.println("Cats list size = " + youngCats.size());
        printCollection(youngCats);
        System.out.println("Dogs list size = " + youngDogs.size());
        printCollection(youngDogs);
        System.out.println("\nSecond day participants: ");
        System.out.println("Cats list size = " + oldCats.size());
        printCollection(oldCats);
        System.out.println("Dogs list size = " + oldDogs.size());
        printCollection(oldDogs);
        System.out.println("Errors quantity = " + errors.size());
        printMap(errors);

    }
}
