package _01_base;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static final String PATH_TO_INPUT = "src/resources/input.file";
    public static final String PATH_TO_OUTPUT = "src/resources/output.file";

    public static void main(String[] args) {
//        try (Scanner sc = new Scanner(new FileInputStream(PATH_TO_INPUT));
//             OutputStream os = new FileOutputStream(PATH_TO_OUTPUT)){
//             while (sc.hasNextLine()){
//                 byte[] bytes = sc.nextLine().getBytes();
//                 os.write(bytes);
//                 os.write("\n".getBytes());
//             }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_INPUT));
             Stream<String> stream = br.lines();
             Writer wr = new FileWriter(PATH_TO_OUTPUT)) {
            String resultString = stream.collect(Collectors.joining("\n"));
            wr.write(resultString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
