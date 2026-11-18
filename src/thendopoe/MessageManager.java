package thendopoe;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MessageManager {
    private List<String> sentMessages = new ArrayList<>();
    private List<String> disregardedMessages = new ArrayList<>();
    private List<String> storedMessages = new ArrayList<>();
    private List<String> messageHashes = new ArrayList<>();
    private List<String> messageIds = new ArrayList<>();
    private List<String> recipients = new ArrayList<>();
    private List<String> allMessages = new ArrayList<>(); // Unified message list for accurate indexing

    public void populateTestData() {
        recipients.clear();
        sentMessages.clear();
        storedMessages.clear();
        disregardedMessages.clear();
        messageIds.clear();
        messageHashes.clear();
        allMessages.clear();

        // Test Message 1
        recipients.add("+27834557896");
        String m1 = "Did you get the cake?";
        sentMessages.add(m1);
        allMessages.add(m1);
        messageIds.add("ID001");
        messageHashes.add("HASH1");

        // Test Message 2 (Stored)
        recipients.add("+27838884567");
        String m2 = "Where are you? You are late! I have asked you to be on time.";
        storedMessages.add(m2);
        allMessages.add(m2);
        messageIds.add("ID002");
        messageHashes.add("HASH2");

        // Test Message 3 (Disregarded)
        recipients.add("+27834484567");
        String m3 = "Yohoooo, I am at your gate.";
        disregardedMessages.add(m3);
        allMessages.add(m3);
        messageIds.add("ID003");
        messageHashes.add("HASH3");

        // Test Message 4 (Sent)
        recipients.add("0838884567");
        String m4 = "It is dinner time !";
        sentMessages.add(m4);
        allMessages.add(m4);
        messageIds.add("ID004");
        messageHashes.add("HASH4");

        // Test Message 5 (Stored)
        recipients.add("+27838884567");
        String m5 = "Ok, I am leaving without you.";
        storedMessages.add(m5);
        allMessages.add(m5);
        messageIds.add("ID005");
        messageHashes.add("HASH5");
    }

    public void readJsonFile(String filePath) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            Object obj = parser.parse(reader);
            JSONArray jsonList = (JSONArray) obj;
            
            storedMessages.clear();
            for (Object item : jsonList) {
                JSONObject jsonMsg = (JSONObject) item;
                String msgText = (String) jsonMsg.get("message");
                storedMessages.add(msgText);
                allMessages.add(msgText);
            }
            System.out.println("JSON file read successfully.");
        } catch (Exception e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
    }

    public String getLongestStoredMessage() {
        if (storedMessages.isEmpty()) return "No stored messages found.";
        String longest = storedMessages.get(0);
        for (String msg : storedMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        return longest;
    }

    public String searchByMessageId(String targetId) {
        for (int i = 0; i < messageIds.size(); i++) {
            if (messageIds.get(i).equalsIgnoreCase(targetId)) {
                String rec = i < recipients.size() ? recipients.get(i) : "Unknown";
                String msg = i < allMessages.size() ? allMessages.get(i) : "N/A";
                return "Recipient: " + rec + ", Message: \"" + msg + "\"";
            }
        }
        return "Message ID not found.";
    }

    public String searchByRecipient(String targetRecipient) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).equals(targetRecipient)) {
                String msg = i < allMessages.size() ? allMessages.get(i) : "";
                if (!msg.isEmpty()) {
                    sb.append("\"").append(msg).append("\" ");
                }
            }
        }
        return sb.toString().trim();
    }

    public boolean deleteByHash(String hash) {
        int index = messageHashes.indexOf(hash);
        if (index != -1) {
            messageHashes.remove(index);
            messageIds.remove(index);
            if (index < recipients.size()) recipients.remove(index);
            if (index < allMessages.size()) allMessages.remove(index);
            return true;
        }
        return false;
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder("--- Message Report ---\n");
        for (int i = 0; i < messageIds.size(); i++) {
            String hash = i < messageHashes.size() ? messageHashes.get(i) : "N/A";
            String rec = i < recipients.size() ? recipients.get(i) : "N/A";
            String msg = i < allMessages.size() ? allMessages.get(i) : "N/A";
            report.append("Hash: ").append(hash)
                  .append(" | Recipient: ").append(rec)
                  .append(" | Message: \"").append(msg).append("\"\n");
        }
        return report.toString();
    }

    public List<String> getSentMessages() { return sentMessages; }
    public List<String> getStoredMessages() { return storedMessages; }
}