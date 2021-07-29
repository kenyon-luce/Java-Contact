package Contacts;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Contacts {

    //PRINT ALL method
    public static void printContacts(Path filePath) throws IOException {
        System.out.println();
        System.out.printf("%-10s | %-13s\n", "NAME", "NUMBER");
        List<String> fileContents = Files.readAllLines(filePath);
        for (String fileContent : fileContents) {
            System.out.printf("%s\n", fileContent); //breaks each line to display each number in a column
        }
    }

    //SEARCH method
    public static void searchContact(Path filePath) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the name you would like to search for");
        String input = sc.nextLine();

        System.out.println();
        List<String> fileContents = Files.readAllLines(filePath);
        for (int i = 0; i < fileContents.size(); i++) {
            if (fileContents.get(i).contains(input)) {
                System.out.printf("%d %s", i + 1, fileContents.get(i));
            }
        }
    }

    //ADD contact
    public static void addContact(Path filepath) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Name: ");
        String name = sc.next();

        System.out.print("Number: ");
        //TODO: Exceptions to catch -> InputMismatch, StringIndexOutOfBounds. If caught loop function
        boolean pass = true;
//
//        try{
//            number = sc.nextInt();
//            try{
//                String numberString = Integer.toString(number);
//
//                String areaCode = numberString.substring(0, 3);
//                String firstHalf = numberString.substring(3, 6);
//                String secondHalf = numberString.substring(6, 10);
//
//                String formatNumber = String.format("(%s) %s-%s", areaCode, firstHalf, secondHalf);
//                String contact = String.format("%-20s | %-20s ", name, formatNumber);
//                List<String> newContact = Collections.singletonList(contact);
//                Files.write(filepath, newContact, StandardOpenOption.APPEND);
//                System.out.println(name + "has been added contacts");
//
//            } catch (StringIndexOutOfBoundsException e) {
//                System.out.println("Number must be 9 digits long");
//            }
//        } catch(InputMismatchException e){
//            System.out.println("Please enter a valid number");
//        }

        do {
            String number = sc.next();

            if(number.length() == 10){
                System.out.println(pass);
            } else {
                System.out.println("Please enter a valid number");
                pass = false;
            }
        } while (!pass);
    }

    //DELETE method
    public static void deleteContact(Path filePath) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the name you would like to search for");
        String input = sc.nextLine();
        List<String> fileContents = Files.readAllLines(filePath);
//        System.out.println("file contents before: \n" + fileContents);
        for (int i = 0; i < fileContents.size(); i++) {
            if (fileContents.get(i).contains(input)) {
                fileContents.remove(fileContents.get(i));
                System.out.println(input + "has been deleted from contacts");
            }
        }

        Files.write(Paths.get("./src/Contacts/Contacts-List.txt"), fileContents);
//        System.out.println("file contents after: \n" + fileContents);
    }

    public static void main(String[] args) throws IOException {
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

        String path = "./src/Contacts/Contacts-List.txt";
        Path contactPath = Paths.get(path);

        Files.createDirectories(contactPath.getParent());
        File file = new File(String.valueOf(contactPath));

        if (!file.exists()) {
            Files.createFile(contactPath);
        }

        //**********GET INPUT***********
        Scanner sc = new Scanner(System.in);
        int input;
        do {
            input = sc.nextInt();
            switch (input) {
                case 1 -> //DISPLAY
                        printContacts(contactPath);
                case 2 -> //ADD
                        addContact(contactPath);
                case 3 -> //SEARCH
                        searchContact(contactPath);
                case 4 -> //DELETE
                        deleteContact(contactPath);
                case 5 -> //EXIT
                        System.out.println("Exiting...");
            }
        } while (input != 5);
    }
}
