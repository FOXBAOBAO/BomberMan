package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import logic.Message;
import logic.MessageControl;

import server.ServerThread;
import util.Key;
import util.Util;

public class Server {
	int number = 0;

	Message allMessage = new Message();

	private static ServerSocket serversocket;

	private static int port;

	private Socket clientsocket;

	protected ObjectInputStream inf;

	protected ObjectOutputStream out;

	private static Hashtable ht_out = new Hashtable();

	public static void main(String args[]) throws Exception {
		port = Key.PORT;
		new Server();
	}

  public Server() throws IOException {
		// Socket clientsocket;
    allMessage.type = 3;
		try {
			serversocket = new ServerSocket(port);
			System.out.println("Game Server started at:"
					+ serversocket.getInetAddress().getLocalHost() + ":"
					+ serversocket.getLocalPort());

			while (true) {
				if (number < 2) {
					number++;
				} else {
					number = 0;
					continue;
        }
				
				clientsocket = serversocket.accept();
				System.out.println("Connection from:" + clientsocket);
				
				out = new ObjectOutputStream(clientsocket.getOutputStream());
				
				ht_out.put(clientsocket, out);

				ServerThread serverThread = new ServerThread(clientsocket, ht_out,
						number, out, allMessage);

				Thread thread = new Thread(serverThread);
				thread.start();

			}

		} catch (IOException ex) {
    }

  }

}

class ServerThread extends Thread implements Runnable {
	private Socket clientsocket;

	private Hashtable ht_out;

	private int number; //

	private static Hashtable htPlayer = new Hashtable();

	protected ObjectOutputStream out;

	protected Message allMessage = new Message();

	protected static Message returnMessage = new Message();
	
  public ServerThread(Socket clientsocket, Hashtable ht_out, int num,
			ObjectOutputStream out, Message allMessage) {
    this.clientsocket = clientsocket;
		// this.htPlayer=htPlayer;
    this.ht_out = ht_out;
    number = num;
    this.out = out;
    this.allMessage = allMessage;
  }

	public void run() {
		ObjectInputStream inData;
		try {
			inData = new ObjectInputStream(clientsocket.getInputStream());
			while (true) {
				Message message= new Message();
				message = (Message) inData.readObject();
				if(message == null){
					System.out.println("Connect is not open ");
					inData.close();
					return;
				}
				Util.printMessage("Message From ",message);
				// message.number=number;
				// sendMessage(message,out);
				doMessage(message);
				//if(message.type==88)continue;
				// returnMessage=message;
				if (!htPlayer.isEmpty()) {
					if(number != 0)message.number = number;
					if(this.clientsocket.isClosed()|| ! this.clientsocket.isConnected()){

						System.out.println("-----= Socket closed =-----");
						synchronized (ht_out) {
							ht_out.remove(clientsocket);
						}
						synchronized (htPlayer) {
							System.out.println("-----= player removed1 =-----");
							htPlayer.remove(number);
						}
						if(this.isAlive()){
							System.out.println("-----= Destroy =-----");
							this.destroy();
						}
					}
					
					synchronized (ht_out) {
						
						for (Iterator it = ht_out.keySet().iterator(); it
								.hasNext();) {
							Socket key = (Socket) it.next();
							ObjectOutputStream outData =(ObjectOutputStream) ht_out.get(key);
							if(key.isClosed()||!key.isConnected()){
								ht_out.remove(key);
							}else{
								Util.printMessage("Send Message",message);
								MessageControl.sendMessage(message,outData);
							}
						}
						
//						for (Enumeration e = ht_out.elements(); e.hasMoreElements();) {
//							ObjectOutputStream outData = (ObjectOutputStream) e
//									.nextElement();
//							Util.printMessage("Send Message",message);
//							MessageControl.sendMessage(message ,outData);
//							
//						}
					}

					for (Enumeration ee = htPlayer.elements(); ee
							.hasMoreElements();) {
						Message test = (Message) ee.nextElement();
						Util.printMessage("Play List ",test);
					}

				}
			}
		} catch (IOException e) {
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
	}

	public Message getMessage() {
		return returnMessage;
	}

	public void doMessage(Message msg) {
		int type = msg.type;
		Util.printMessage("Do Message",msg);
		switch (type) {
		
      case 1: {
			if (number <= 8) {
				htPlayer.remove(msg.number);
				allMessage = msg;
				if(msg.number!=0){
					allMessage.number = msg.number;
				}else{
					allMessage.number = number;
          }
				allMessage.type = 3;
				htPlayer.put(allMessage.number, allMessage);

				for (Enumeration e = htPlayer.elements(); e.hasMoreElements();) {
					Message test = (Message) e.nextElement();
					MessageControl.sendMessage(test,out);

					Util.printMessage("Send Message(doMessage)",test);
				}

			}

      }
			break;
		//exit
      case 11: {
			for (Enumeration ee = htPlayer.elements(); ee.hasMoreElements();) {
				Message test = (Message) ee.nextElement();
				if (test.number == msg.number) {
					synchronized(htPlayer){
						htPlayer.remove(msg.number);
					}
				}
			}
        System.out.println("^^^^^^^^^^^^^^^^^^^^remove!");
      }
			break;

      case 10: {
        htPlayer.remove(msg.number);
        htPlayer.put(msg.number, msg);
      }
			break;

      case 44: {
        number = 0;
			if (htPlayer.isEmpty()) {
			}
      }
			break;

      case 101 :{
        MessageControl.sendMessage(msg,out);
        break;
      }
		}
	}

}
