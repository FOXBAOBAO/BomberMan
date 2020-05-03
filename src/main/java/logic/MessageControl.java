package logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import logic.Message;
import util.Util;

public class MessageControl {
	public Message message;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
	public static void sendMessage(Message message,ObjectOutputStream out){
		try{

			Util.printMessage("------All Send Messages ",message);
			if(message == null)System.out.println("Message is null ");
			out.writeObject(message);
		}
    catch(IOException e){
      System.out.println("Send Messages Exception.");
    }
	}
	
	public static Message receiveMessage(ObjectInputStream in){
		Message msg = null;
		try{
			msg = new Message();
			msg=(Message)in.readObject();
		}
    catch(Exception e){
      System.out.println("Rreceive Meassge Failure.");
      return null;
    }
		Util.printMessage("------All Receive Messages ",msg);
		return msg;
	}
	public static void doMessage(Message message){
		
	}
	
}
