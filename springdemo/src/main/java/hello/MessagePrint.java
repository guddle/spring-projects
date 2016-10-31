package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagePrint {

	final MessageService service;
	
	@Autowired
	public MessagePrint(MessageService service) {
		this.service = service;
	}
	
	public void printMessage(){
		System.out.println(this.service.getMessage());
	}
}
