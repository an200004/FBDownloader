package fb.queue;

import com.microsoft.azure.servicebus.ClientFactory;
import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IMessageReceiver;
import com.microsoft.azure.servicebus.IMessageSender;
import com.microsoft.azure.servicebus.IQueueClient;
import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;

import fb.operation.ApplicationInformation;

public class QueueManagement {
	
	    public static void sendMessage(String json, String queueConnectionString, String queueName) {
	    	IMessageSender messageSender = null; 
	    	
	    	try {
	    		messageSender = ClientFactory.createMessageSenderFromConnectionStringBuilder(new ConnectionStringBuilder(queueConnectionString, queueName));
		    	messageSender.send(new Message(json));
		    	
	    	} catch (ServiceBusException sbe) {
	    		sbe.printStackTrace();
	    	} catch (InterruptedException ie) {
	    		ie.printStackTrace();
	    	} finally {
	    		if (messageSender != null) {
	    			try {
	    				messageSender.close();
	    			} catch (ServiceBusException sbe1) { }
	    		}
	    	}
	    	
	    }
	    
	    @SuppressWarnings("finally")
		public static String receiveMessage(String queueConnectionString, String queueName) {
	    	IMessageReceiver messageReceiver = null;
	    	String body = null;
	    	
	    	try {
	    	messageReceiver = ClientFactory.createMessageReceiverFromConnectionStringBuilder(new ConnectionStringBuilder(queueConnectionString, queueName), ReceiveMode.RECEIVEANDDELETE);
	    	IMessage msg = messageReceiver.receive(); 
	    	
	    	if ( msg != null) {
	    		body = new String(msg.getBody());
	    	}
	    	
	    	} catch (ServiceBusException sbe) {
	    		sbe.printStackTrace();
	    	} catch (InterruptedException ie) {
	    		ie.printStackTrace();
	    	} finally {
	    		if (messageReceiver != null) {
	    			try {
	    				messageReceiver.close();
	    			} catch (ServiceBusException sbe1) { }
	    		}
	    		
	    		return body;
	    	}
	    	
	    	
	    }
	
}
