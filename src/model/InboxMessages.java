/** 
@author Dave Sharma <a href="mailto:dave.sharma@ucalgary.ca">agampreet.aulakh@ucalgary.ca </a>
Nuha Shaikh <a href="mailto:nuha.shaikh1@ucalgary.ca">nuha.shaikh1@ucalgary.ca</a>
Apostolos Scondrianis <a href="mailto:apostolos.scondriani@ucalgary.ca">huda.abbas@ucalgary.ca</a>
Harsh Sharma <a href= "mailto:harshit.sharma@ucalgary.ca">melanie.nguyen@ucalgary.ca</a>
@version 2.2
@since  2.0
  * Data.java (insert explanation of class)
*/
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
