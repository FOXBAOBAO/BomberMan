package test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import org.junit.Test;

import logic.Message;
import logic.*
;public class MessageControlTest {

    @Test
    public void testSetMessage() throws Exception {
    	Message message = new Message();
    	MessageControl c = new MessageControl();
    	c.setMessage(message);
        assertEquals(message, c.getMessage());

    }
    
    @Test(expected = Exception.class)
    public void testreceiveMessage() throws Exception {
    	FileInputStream fileIn = new FileInputStream("employee.txt");
    	ObjectInputStream in = new ObjectInputStream(fileIn);
        new MessageControl().receiveMessage(in);
        fail("No exception is thrown.");
    }

    

}
