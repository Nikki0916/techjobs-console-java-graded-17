import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by LaunchCode
 */
import java.util.*;

public class TechJobs {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");
        while (true) {

            String actionChoice = getUserSelection("View jobs by (type 'x' to quit):", actionChoices);

            if (actionChoice == null) {
                break;
            } else if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice == null) {
                    break; // If the user quits while selecting column choice, exit the loop
                }

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {
                    printJobs(JobData.findAll(columnChoice));
                }

            } else { // choice is "search"

               
                String searchField = getUserSelection("Search by:", columnChoices);

                if (searchField == null) {
                    break; // If the user quits while selecting search field, exit the loop
                }

                
                System.out.println("\nSearch term:");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                    printJobs(JobData.findByValue(searchTerm));
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }


    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        String[] choiceKeys = choices.keySet().toArray(new String[0]);

        do {
            System.out.println("\n" + menuHeader);

            // Print available choices
            for (int i = 0; i < choiceKeys.length; i++) {
                System.out.println("" + i + " - " + choices.get(choiceKeys[i]));
            }

            String userInput = in.nextLine();

            if (userInput.equals("x")) {
                return null; // If the user types 'x', exit the loop and return null
            }

            try {
                int choiceIdx = Integer.parseInt(userInput);
                if (choiceIdx >= 0 && choiceIdx < choiceKeys.length) {
                    return choiceKeys[choiceIdx];
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Try again.");
            }

        } while (true);
    }


    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {

        if (someJobs.isEmpty()) {
            System.out.println("No Results");
            return;
        }

        for (HashMap<String, String> job : someJobs) {
            System.out.println("*****");
            for (Map.Entry<String, String> entry : job.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("*****\n");
        }
    }
}

