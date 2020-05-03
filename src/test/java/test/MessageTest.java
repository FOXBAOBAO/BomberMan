package test;

import static org.junit.Assert.*;

import org.junit.Test;
import logic.*;

public class MessageTest {

	@Test
	public void testSetBomberPower() {
		Message message = new Message();
		message.setBombPower(100);
		assertEquals(100,message.getBombPower());
	}
	@Test
	public void testSetExit() {
		Message message = new Message();
		message.setExit(10);
		assertEquals(10,message.getExit());
		
		
	}
	@Test
	public void testSetHead() {
		Message message = new Message();
		message.setHead(10);
		assertEquals(10,message.getHead());
		
	}
	@Test
	public void testSetIsDead() {
		
		Message message = new Message();
		message.setIsDead(0);
		assertEquals(0,message.getIsDead());
		
	}
	@Test
	public void testSetIsAlive() {
		
		
		Message message = new Message();
		message.setIsLive(10);
		assertEquals(10,message.getIsLive());
	}
	@Test
	public void testSetIsNext() {
		
		Message message = new Message();
		message.setIsNext(10);
		assertEquals(10,message.getIsNext());
		
	}
	@Test
	public void testSetMap() {
		
		Message message = new Message();
		message.setMap(10);
		assertEquals(10,message.getMap());
		
	}
	@Test
	public void testMoveTypeX() {
		
		Message message = new Message();
		message.setMoveTypeX(10);
		assertEquals(10,message.getMoveTypeX());
	}
	@Test
	public void testMoveTypeY() {
		

		Message message = new Message();
		message.setMoveTypeY(10);
		assertEquals(10,message.getMoveTypeY());
	}
	@Test
	public void testSetMsg() {
		
		Message message = new Message();
		message.setMsg("hello");
		assertEquals("hello",message.getMsg());

	}
	@Test
	public void testSetNumber() {

		Message message = new Message();
		message.setNumber(10);
		assertEquals(10,message.getNumber());
		
		
	}
	@Test
	public void testSetPassWord() {
		Message message = new Message();
		message.setPassWord("password");
		assertEquals("password",message.getPassWord());
		
		
	}
	@Test
	public void testSetReady() {
		Message message = new Message();
		message.setReady(10);
		assertEquals(10,message.getReady());
		
		
	}
	@Test
	public void testSetStart() {
		
		Message message = new Message();
		message.setStart(10);
		assertEquals(10,message.getStart());
	}
			
	@Test
	public void testSetTeam() {
		Message message = new Message();
		message.setTeam(10);
		assertEquals(10,message.getTeam());
		
		
	}
	@Test
	public void testSetType() {
		Message message = new Message();
		message.setType(10);
		assertEquals(10,message.getType());
		
		
	}
	@Test
	public void testSetX() {
		Message message = new Message();
		message.setX(10);
		assertEquals(10,message.getX());
		
		
	}
	@Test
	public void testSetY() {
		Message message = new Message();
		message.setY(10);
		assertEquals(10,message.getY());
		
		
	}
	@Test
	public void testSetUserName() {
		Message message = new Message();
		message.setUserName("username");
		assertEquals("username",message.getUserName());
		
		
	}	
			

}
