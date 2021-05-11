package Contacts;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Contacts {

    //print file method
    public static void printFile(Path filePath) throws IOException {

        System.out.println();
        List<String> fileContents = Files.readAllLines(filePath);
        for (int i = 0; i < fileContents.size(); i++) {
            System.out.printf("%d %s", i + 1, fileContents.get(i));
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException{
        System.out.println();

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
        //Print file

        Path filepathtoList = Paths.get("./src/Contacts/Contacts-List.txt");


        //System.out.println("filepathtoList = " + filepathtoList);
        //System.out.println("Files.exists() = " + Files.exists(filepathtoList));
        printFile(filepathtoList);

        //Setting up file path

        String directory = "./src/Contacts";

        String filename = "Contacts-List.txt";

        Path dataDirectory = Paths.get(directory);

        Path dataFile = Paths.get(directory);

        //System.out.println("dataFile = " + dataFile);

        if(Files.notExists(dataDirectory)) {

            Files.createDirectories(dataDirectory);
        }

        if (Files.notExists(dataFile)){

            Files.createFile(dataFile);
        }

        //APPEND to contact list
        Path contactPath = Paths.get(directory, filename);

        //System.out.println("contactPath = " + contactPath);

        List<String> newContact = Arrays.asList("###-###-####");
        //System.out.println("newContact = " + newContact);

        Files.write(contactPath, newContact, StandardOpenOption.APPEND);

    }
}
