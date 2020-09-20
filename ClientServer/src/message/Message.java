package message;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable { //Serializable cause Transferring of Java Objects through sockets
private String to;
private String cc;
private String subject;
private String priorty;
private String message;

public Message(String to, String cc, String subject, String priorty, String message) {
	this.to = to;
	this.cc = cc;
	this.subject = subject;
	this.priorty = priorty;
	this.message = message;
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

}
