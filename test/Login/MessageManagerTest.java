/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import thendopoe.MessageManager;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageManagerTest {

    @Test
    public void testSentMessagesPopulated() {
        MessageManager manager = new MessageManager();
        manager.populateTestData();
        assertFalse(manager.getSentMessages().isEmpty());
        assertTrue(manager.getSentMessages().contains("Did you get the cake?"));
        assertTrue(manager.getSentMessages().contains("It is dinner time !"));
    }

    @Test
    public void testDisplayLongestStoredMessage() {
        MessageManager manager = new MessageManager();
        manager.populateTestData();
        String expected = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expected, manager.getLongestStoredMessage());
    }

    @Test
    public void testSearchByRecipient() {
        MessageManager manager = new MessageManager();
        manager.populateTestData();
        String expected = "\"Where are you? You are late! I have asked you to be on time.\" \"Ok, I am leaving without you.\"";
        assertEquals(expected, manager.searchByRecipient("+27838884567"));
    }

    @Test
    public void testDeleteByHash() {
        MessageManager manager = new MessageManager();
        manager.populateTestData();
        boolean deleted = manager.deleteByHash("HASH2");
        assertTrue(deleted);
    }
}