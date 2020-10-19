package message;

import java.io.Serializable;


/**
 * 
 * It describes the message between cliend and server.
 * @author unal
 *
 */

@SuppressWarnings({ "serial" })
public class Message implements Comparable<Message>  ,Serializable { //Serializable cause Transferring of Java Objects through sockets

	private String from;
	private String to;
	private String cc;
	private String subject;
	private String priorty;
	private String message;
	private int id; // Id of messages

	private static int ID=1; // Use for generate unique Id.
	
	/**
	 * Creates a message with the specified to, cc , subject, priorty, message, from
	 * @param to Address to which the message will be sent
	 * @param cc Addresses to send a copy of the message
	 * @param subject Title of the message
	 * @param priorty Priorty of message
	 * @param message Content of the message
	 * @param from  Address to which the message was sent.
	 */

	public Message(String to, String cc, String subject, String priorty, String message, String from) {
		this.to = to;
		this.cc = cc;
		this.subject = subject;
		this.priorty = priorty;
		this.message = message;
		this.from = from;
		this.id = ID++; 
	}
	
	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public String getCc() {
		return cc;
	}
	
	public void setCc(String cc) {
		this.cc = cc;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getPriorty() {
		return priorty;
	}
	public void setPriorty(String priorty) {
		this.priorty = priorty;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "To: " + getTo() + " ; Cc: " + getCc() + " ; Subject: " + getSubject() + " ; Priorty: " + getPriorty() + " ; Message: " + getMessage(); 
	}
	
	@Override
	public int compareTo(Message mes) {
		return this.getTo().compareTo(mes.getTo());
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}

}
