package logic;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import entities.Question;
import entities.Response;


public class EmailLogic {
	
	Session newSession = null;
	MimeMessage mimeMessage = null;
	
	public void sendQuizResults(String recipientEmail, List<Response> responses) {
		
		try {
		    setupServerProperties();
		    mimeMessage = draftEmail(recipientEmail, responses);
		    sendEmail(); 
		} catch (IOException e) {
		    e.printStackTrace();  
		} catch (MessagingException e) {
		    e.printStackTrace();  
		}
	}
	
    private void sendEmail() throws MessagingException {
        String fromUser = "-email@gmail.com";  // Crear un mail del proyecto para poner aca 
        String fromUserPassword = "-password"; // 
        String emailHost = "smtp.gmail.com";
        
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        

    }
    
    private MimeMessage draftEmail(String recipientEmail, List<Response> responses)
            throws AddressException, MessagingException, IOException {
        mimeMessage = new MimeMessage(newSession);
        

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        mimeMessage.setSubject("Resultados del cuestionario");

        StringBuilder emailBody = new StringBuilder(); // Cuerpo del mail
        emailBody.append("Respuestas del cuestionario:\n\n");
     
        for (Response response : responses) {
            Question question = response.getQuestion();
            int userAnswer = response.getUserResponse();
            
            emailBody.append("Pregunta: ").append(question.getQuestionText()).append("\n");
            emailBody.append("Tu respuesta: ").append(userAnswer).append("\n\n");
        }
        
        emailBody.append("Gracias por completar el cuestionario.");
        
        // Crear MimeBodyPart
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody.toString(), "text/plain");
        
        // Crear MimeMultipart y agregar la parte de cuerpo
        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);
        
        // Agregar el contenido al mensaje
        mimeMessage.setContent(multiPart);
        
        return mimeMessage;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties, null);
    }

}
