/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package thendopoe;

import java.util.Scanner;

/**
 *
 * @author rlmam
 */
public class ThendoPoe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String name;
        String surname;
        String userName;
        String emailAddress;
        String password;
        String numberPhone;

        // prompt the user for their name
        System.out.print("Enter your Name: ");
        name = input.nextLine();

        // prompt the user for their surname
        System.out.print("Enter your Surname: ");
        surname = input.nextLine();

        // a while loop to prompt the user for their username
        while (true) {
            System.out.print("Please enter your username: ");
            userName = input.nextLine();

            if (Login.checkUserName(userName)) {
                System.out.println("Username succesfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formated, Username must contain an underscore, Username must atleast contain atleast 5 characters.");
            }
        }

        // a while loop to prompt the user for their email
        while (true) {
            System.out.print("Please enter your email address: ");
            emailAddress = input.nextLine();

            if (Login.checkEmailAddress(emailAddress)) {
                System.out.println("Email address succesfully captured");
                break;
            } else {
                System.out.println("Email address is not correctly formated: Email address must contain @gmail.com");
            }
        }

        while (true) {
            System.out.print("Please enter your password: ");
            password = input.nextLine();

            if (Login.checkPassword(password)) {
                System.out.println("password succesfully captured");
                break;
            } else {
                System.out.println("Password is not correctly formatted, Password must contain atleast 8 characters, Password must contain a capital letter, Password must contain a number, Password must contain a special character.");
            }
        }

        while (true) {
            System.out.print("Please enter your number phone: ");
            numberPhone = input.nextLine();

            if (Login.checkCellPhoneNumber(numberPhone)) {
                System.out.println("number phone succesfully captured");
                break;
            } else {
                System.out.println("number phone is not correctly formatted, Number phone must contain international code, Number phone must atleast contain 10 digits");
            }
        }

        System.out.println("you have registerd succesfully");
        System.out.println("Welcome " + name + " " + surname);

        // --- PART 3: MAIN MENU & MESSAGE MANAGER INTEGRATION ---
        MessageManager manager = new MessageManager();
        manager.populateTestData(); // Preloads the required test data arrays

        boolean running = true;
        while (running) {
            System.out.println("\n=================================");
            System.out.println("          MAIN MENU              ");
            System.out.println("=================================");
            System.out.println("1. Send a new message");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Check message details");
            System.out.println("4. Stored Messages");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            String choiceStr = input.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceStr.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n--- Send a New Message ---");
                    System.out.print("Enter recipient number: ");
                    String recipient = input.nextLine();
                    System.out.print("Enter message text: ");
                    String msgText = input.nextLine();
                    manager.addNewMessage(recipient, msgText);
                    System.out.println("Message captured successfully.");
                    break;

                case 2:
                    System.out.println("\n--- Recently Sent Messages ---");
                    for (String m : manager.getSentMessages()) {
                        System.out.println("- " + m);
                    }
                    break;

                case 3:
                    System.out.println("\n--- Check Message Details ---");
                    System.out.print("Enter Message ID or Hash to inspect: ");
                    String queryId = input.nextLine();
                    System.out.println(manager.searchByMessageId(queryId));
                    break;

                case 4:
                    // Part 3: Stored Messages Sub-Menu
                    boolean subRunning = true;
                    while (subRunning) {
                        System.out.println("\n---------------------------------");
                        System.out.println("     STORED MESSAGES MENU        ");
                        System.out.println("---------------------------------");
                        System.out.println("a. Display sender and recipient of all stored messages");
                        System.out.println("b. Display the longest stored message");
                        System.out.println("c. Search for a message ID");
                        System.out.println("d. Search messages for a particular recipient");
                        System.out.println("e. Delete a message using a message hash");
                        System.out.println("f. Display full message report");
                        System.out.println("g. Return to Main Menu");
                        System.out.print("Enter your option (a-g): ");

                        String subChoiceInput = input.nextLine().trim().toLowerCase();
                        String subChoice = "";
                        if (!subChoiceInput.isEmpty()) {
                            subChoice = String.valueOf(subChoiceInput.charAt(0));
                        }

                        switch (subChoice) {
                            case "a":
                                System.out.println("\n--- Stored Messages Senders & Recipients ---");
                                for (String msg : manager.getStoredMessages()) {
                                    System.out.println("Message: \"" + msg + "\"");
                                }
                                break;
                            case "b":
                                System.out.println("\n--- Longest Stored Message Details ---");
                                System.out.println(manager.getLongestStoredMessage());
                                break;
                            case "c":
                                System.out.print("Enter Message ID to search (e.g., ID002): ");
                                String searchId = input.nextLine();
                                System.out.println("\n--- Search Result ---");
                                System.out.println(manager.searchByMessageId(searchId));
                                break;
                            case "d":
                                System.out.print("Enter recipient number (e.g., +27838884567): ");
                                String rec = input.nextLine();
                                System.out.println("\n--- Messages for " + rec + " ---");
                                String res = manager.searchByRecipient(rec);
                                System.out.println(res.isEmpty() ? "No messages found for this recipient." : res);
                                break;
                            case "e":
                                System.out.print("Enter Message Hash to delete (e.g., HASH2): ");
                                String hash = input.nextLine();
                                if (manager.deleteByHash(hash)) {
                                    System.out.println("Message successfully deleted.");
                                } else {
                                    System.out.println("Message hash not found.");
                                }
                                break;
                            case "f":
                                System.out.println("\n" + manager.generateReport());
                                break;
                            case "g":
                                subRunning = false;
                                break;
                            default:
                                System.out.println("Invalid option. Please choose between a and g.");
                        }
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }

        input.close();
    }
}