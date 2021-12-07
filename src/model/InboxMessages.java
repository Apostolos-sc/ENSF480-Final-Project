package model;


public class InboxMessages {
	private int messageID;
	private String message;
	private String senderEmail;
	private String recieverEmail;
	
	public InboxMessages(int messageId,String message,String sender,String reciever) {
		this.messageID=messageID;
		this.message=message;
		this.senderEmail=sender;
		this.recieverEmail=reciever;
	}
	public String getMessage() {
		return this.message;
	}
	public String getSenderEmail() {
		return this.senderEmail;
	}
	public String getRecieverEmail() {
		return this.recieverEmail;
	}
	public int getMessageID() {
		return this.messageID;
	}
}
