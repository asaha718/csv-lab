import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] people = new String[3];
        int personCount = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            while (personCount < 3) {
                Person newPerson = createPerson(scanner);
                String personCSV = newPerson.formatAsCSV();
                people[personCount] = personCSV;

                personCount++;
            }
        }

        writeToFile("people.csv", people);
    }

    public static Person createPerson(Scanner scanner) {
        Person newPerson;
        System.out.println("Lets create a person");
        System.out.println("Enter a First and Last Name: ");

        String nameInput = scanner.nextLine();
        String[] name = nameInput.split(" ");
        String firstName = name[0];
        String lastName = name[1];

        newPerson = new Person(firstName, lastName);
        return newPerson;

    }

    static void writeToFile(String fileName, String[] list) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            for (String person : list
            ) {
                fileWriter.write(person + "\n" );
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (fileWriter != null)
                fileWriter.close();
        }
    }

    static String readFromFile(String fileName, boolean addNewLine) throws IOException {
        String returnString = new String();
        Scanner fileReader = null;
        try {
            File myFile = new File(fileName);
            fileReader = new Scanner(myFile);
            while (fileReader.hasNextLine()) {
                returnString += fileReader.nextLine();
                if (addNewLine)
                    returnString += "\n";
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (fileReader != null)
                fileReader.close();
        }

        return returnString;
    }
}
