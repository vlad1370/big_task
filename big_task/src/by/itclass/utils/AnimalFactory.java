package by.itclass.utils;

import by.itclass.exceptions.CompetitionException;
import by.itclass.model.Animal;
import by.itclass.model.Cat;
import by.itclass.model.Dog;
import by.itclass.model.Genus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AnimalFactory {
    public static final String DELIMITER = "[;,]";
    public static final String CHIP_REGEX = "(?=\\d{15}\\b)\\d{3}09(?:81|56)\\d{8}";
    protected static final String EMAIL_REGEX = "[\\w!#$%&*+/=?^'`{|}~\\-]+(?:\\.[\\w!#$%&*+/=?^'`{|}~\\-]+)*@(?:[a-zA-Z\\d](?:[a-zA-Z\\d\\-]*[a-zA-Z\\d])?\\.)+[a-zA-Z\\d][a-zA-Z\\d\\-]*[a-zA-Z\\d]";
    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");


    private static String validateStringMatches(String value, String regex) {
        if (value.matches(regex)) {
            return value;
        }
        throw new IllegalStateException("Chip number or email has invalid format");
    }

    private static String validateStringByEmpty(String value) {
        if (!value.isEmpty()) {
            return value;
        }
        throw new IllegalStateException("Name or breed is empty");
    }

    public static Animal getInstance(String textString)
            throws CompetitionException {
        String[] stringArray = textString.split(DELIMITER);
        try {
            long chipNumber = Long.parseLong(
                    validateStringMatches(stringArray[0], CHIP_REGEX));
            String name = validateStringByEmpty(stringArray[2]);
            LocalDate birth = LocalDate.parse(stringArray[3], FORMATTER);
            String breed = validateStringByEmpty(stringArray[4]);
            String email = validateStringMatches(stringArray[5], EMAIL_REGEX);
            return stringArray[1].equalsIgnoreCase("cat")
                    ? new Cat(chipNumber, Genus.of(stringArray[1]), name, birth, breed, email)
                    : new Dog(chipNumber, Genus.of(stringArray[1]), name, birth, breed, email);
        } catch (IllegalStateException e) {
            throw new CompetitionException(e, textString);
        }
    }
}
