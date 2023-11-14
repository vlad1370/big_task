package _03_standart_compose;

import _02_standart.Child;

import java.io.*;
import java.util.List;

public class Main {
    public static final String PATH_TO_OUTPUT = "src/resources/output.file";

    public static void main(String[] args) {
        StandardCat vaska = new StandardCat("Vaska", new StandardBox(List.of("ball", "mouse")));

        try (FileOutputStream os = new FileOutputStream(PATH_TO_OUTPUT);
             ObjectOutputStream oos = new ObjectOutputStream(os);
             FileInputStream is = new FileInputStream(PATH_TO_OUTPUT);
             ObjectInputStream ois = new ObjectInputStream(is)) {

            oos.writeObject(vaska);
            StandardCat.staticField = "New Value";

            StandardCat catFromFile = (StandardCat) ois.readObject();
            System.out.println(catFromFile);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
