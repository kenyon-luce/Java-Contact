package Contacts;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Contacts {


    //PRINT ALL method
    public static void printFile(Path filePath) throws IOException {
        System.out.println();
        List<String> fileContents = Files.readAllLines(filePath);
        for (int i = 0; i < fileContents.size(); i++) {
            System.out.printf("\n%s",  fileContents.get(i)); //each line
        }
    }
    //SEARCH method
    public static void searchFile(Path filePath) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the name you would like to search for");
        String input = sc.nextLine();

        System.out.println();
        List<String> fileContents = Files.readAllLines(filePath);
        for (int i = 0; i < fileContents.size(); i++) {
            if (fileContents.get(i).contains(input)){
                System.out.printf("%d %s", i + 1, fileContents.get(i));
            }
        }
    }

    //DELETE method
    public static void deleteContact(Path filePath) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the name you would like to search for");
        String input = sc.nextLine();
        List<String> fileContents = Files.readAllLines(filePath);
        System.out.println("file contents before: \n" + fileContents);
        for (int i = 0; i < fileContents.size(); i++) {
            if (fileContents.get(i).contains(input)){
                fileContents.remove(fileContents.get(i));
            }
        }

        Files.write(Paths.get("./src/Contacts/Contacts-List.txt"), fileContents);
        System.out.println("file contents after: \n" + fileContents);
    }

    public static void main(String[] args) throws IOException {
        //********SCANNER**********
        Scanner sc = new Scanner(System.in);

        System.out.println(
                "What would you like to do?\n" +
                        "\n" +
                        "1. View contacts.  \n" +
                        "2. Add a new contact.\n" +
                        "3. Search a contact by name.\n" +
                        "4. Delete an existing contact.\n" +
                        "5. Exit.\n" +
                        "\n" +
                        "Enter an option (1, 2, 3, 4 or 5): "
        );
        System.out.println();

        //Setting up file path

        String path = "./src/Contacts/Contacts-List.txt";

//        String filename = "Contacts-List.txt";

//        Path dataPath = Paths.get(directory);

        Path contactPath = Paths.get(path);

        //CREATE filepath if path doesn't already exist
        if (Files.notExists(contactPath)) {
            Files.createDirectories(contactPath);
        }

//        if (Files.notExists(dataFile)) {
//
//            Files.createFile(dataFile);
//        }
//
//        Path contactPath = Paths.get(path);

        //**********GET INPUT***********
        Scanner inputScanner = new Scanner(System.in);
        int input;
        do {
            input = inputScanner.nextInt();
            switch (input) { //DISPLAY
                case 1 -> printFile(contactPath);
                case 2 -> { //ADD
                    System.out.println("[Name Number]");
                    String name = sc.next();
                    String newNumber = sc.next();
                    List<String> newContact = Collections.singletonList(name + " | " + newNumber);
                    Files.write(contactPath, newContact, StandardOpenOption.APPEND);
                }
                case 3 -> //SEARCH
                        searchFile(contactPath);
                case 4 -> //DELETE
                        deleteContact(contactPath);
                case 5 -> System.out.println("Exiting...");
            }
        } while (input != 5);
    }
}
