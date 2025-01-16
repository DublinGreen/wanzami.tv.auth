package tv.wanzami.service;

import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.Message;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;
import software.amazon.awssdk.services.ses.model.SesException;

public class EmailService {
	
	private String sender = null; // Replace with your verified email
    private String recipient = null; // Replace with recipient email
    private String subject = null;
    private String bodyText = null;
	
	public EmailService(String sender, String recipient, String subject, String bodyText) {
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
		this.bodyText = bodyText;
		
        try (SesClient sesClient = SesClient.create()) {
            sendEmail(sesClient, this.sender, this.recipient, this.subject, this.bodyText);
        } catch (SesException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
	}
	
    public static void sendEmail(SesClient sesClient, String sender, String recipient, String subject, String bodyText) {
        // Build the email content
        Content subjectContent = Content.builder().data(subject).build();
        Content bodyContent = Content.builder().data(bodyText).build();
        Body body = Body.builder().text(bodyContent).build();
        Message message = Message.builder().subject(subjectContent).body(body).build();

        // Create the SendEmailRequest
        SendEmailRequest emailRequest = SendEmailRequest.builder()
                .source(sender)
                .destination(Destination.builder().toAddresses(recipient).build())
                .message(message)
                .build();

        // Send the email
        sesClient.sendEmail(emailRequest);
        System.out.println("Email sent successfully to " + recipient);
    }
}
