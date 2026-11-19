# ThendoPoe - Java Messaging Application (POE Part 3)

## Project Overview
`ThendoPoe` is a Java console application developed in NetBeans for managing user registration, authentication, and message handling. Part 3 introduces advanced stored message management with search, deletion, reporting, and JSON-based persistence.

## Key Features
* **User Authentication:** Validated user registration, login credentials, and password formatting checks (`Login.java`)[cite: 4].
* **Message Creation:** Generates structured messages with unique IDs, recipient formatting, character limit enforcement, and hashing (`Message.java`)[cite: 4, 5].
* **Message Management:** JSON file-based storage, retrieval, search, and reporting system (`MessageManager.java`)[cite: 4, 5].
* **Interactive Menus:** Main navigation menu (`1`–`5`) and sub-menu (`a`–`g`) for stored message operations (`ThendoPoe.java`).

## Stored Messages Sub-Menu (Options `a`–`g`)
* **a:** Display all stored messages[cite: 3].
* **b:** Display the longest stored message details (ID, Recipient, Hash, and Content)[cite: 2, 3].
* **c:** Search stored messages by Message ID[cite: 3].
* **d:** Search stored messages by Recipient[cite: 3].
* **e:** Delete a message by Message Hash[cite: 3].
* **f:** Display full message report[cite: 3].
* **g:** Return to Main Menu[cite: 3].

## Technology Stack & Libraries
* **Language:** Java 8+[cite: 4, 5]
* **IDE:** NetBeans IDE[cite: 4, 5]
* **Testing:** JUnit 4 / 5[cite: 4]
* **JSON Library:** `json-simple-1.1.jar`[cite: 4, 5]
* **Version Control:** Git & GitHub[cite: 4, 5]

## How to Run the Project
1. Clone this repository:
   ```bash
   git clone [https://github.com/ST10482700/Poe-part2.git](https://github.com/ST10482700/Poe-part2.git)
