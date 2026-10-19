/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;
import thendopoe.Message;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rlmam
 */
public class MessageTest {


    // Test 1 - Success case from POE doc page 14
    @Test
    public void testCheckRecipientCellSuccess() {
        Message.recipient = "+27718693002";
        String result = Message.checkRecipientCell();
        assertEquals("Cell phone number successfully captured.", result);
    }

    // Test 2 - Failure case from POE doc page 14
    @Test
    public void testCheckRecipientCellFailure() {
        Message.recipient = "08575975889";
        String result = Message.checkRecipientCell();
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code.", result);
    }

    // Test 3 - MessageID not more than 10 characters
    @Test
    public void testCheckMessageID() {
        Message.messageID = "1234567890"; 
        assertTrue(Message.checkMessageID());
        
        Message.messageID = "12345678901"; 
        assertFalse(Message.checkMessageID());
    }

    // Test 4 - Message length <= 250
    @Test
    public void testCheckMessageLengthSuccess() {
        Message.messageText = "Hi Mike, can you join us for dinner tonight";
        String result = Message.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }

    // Test 5 - Message length exceeds 250
    @Test
    public void testCheckMessageLengthFailure() {
        // Create a message with 260 characters
        Message.messageText = "a".repeat(260);
        String result = Message.checkMessageLength();
        assertTrue(result.contains("Message exceeds 250 characters by"));
    }
}   

