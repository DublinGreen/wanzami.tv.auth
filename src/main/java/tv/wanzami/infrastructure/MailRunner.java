package tv.wanzami.infrastructure;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailRunner {

    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${app.name}")
    private String appName;
    
    private String year = String.valueOf(Year.now().getValue());
    
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Wanzami Team <mail@wanzami.tv>");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
    
    public void sendSignupEmail(String to, String subject, String userName, String link) throws MessagingException {
    	
    	String htmlMessage = String.format("""
    		    <table width='100%%' bgcolor='#f4f6f9' cellpadding='0' cellspacing='0'>
    		        <tr>
    		          <td align='center'>
    		            <table width='600' cellpadding='0' cellspacing='0' bgcolor='#ffffff' style='margin: 20px auto; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.05);'>
    		              <tr>
    		                <td align='center' bgcolor='#E67539' style='padding: 30px 0; border-radius: 8px 8px 0 0;'>
    		                  <h1 style='color: #ffffff; margin: 0;'>Welcome to %s</h1>
    		                </td>
    		              </tr>
    		              <tr>
    		                <td>
    		                    <img style='margin: 3%% 40%%;' src='https://wanzami.tv/assets/images/logo.png' title='Wanzami Logo' alt='Wanzami Logo' />
    		                </td>
    		              </tr>
    		              <tr>
    		                <td style='padding: 30px;'>
    		                  <p style='font-size: 16px; color: #333;'>Hi <strong>%s</strong>,</p>
    		                  <p style='font-size: 16px; color: #333;'>Thanks for signing up! We're excited to have you on board.</p>
    		                  <p style='font-size: 16px; color: #333;'>Click the button below to verify your email address and complete your registration.</p>
    		                  <div style='text-align: center; margin: 30px 0;'>
    		                    <a href='%s' target="blank" style='background-color: #E67539; color: #ffffff; padding: 14px 24px; text-decoration: none; border-radius: 4px; font-size: 16px;'>
    		                      Verify Email
    		                    </a>
    		                  </div>
    		                  <p style='font-size: 14px; color: #999;'>If you didn't create an account, you can safely ignore this email.</p>
    		                  <p style='font-size: 16px; color: #333;'> The %s Team</p>
    		                </td>
    		              </tr>
    		              <tr>
    		                <td align='center' bgcolor='#f4f6f9' style='padding: 20px; font-size: 12px; color: #aaa;'>
    		                  &copy; %s %s. All rights reserved.
    		                </td>
    		              </tr>
    		            </table>
    		          </td>
    		        </tr>
    		    </table>
    		""", this.appName,userName, link, this.appName, this.year, this.appName);
    	
    	MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        message.setFrom("Wanzami Team <mail@wanzami.tv>");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlMessage, true); // true = enable HTML

        mailSender.send(message);
    }
    
    public void sendPasswordRecoveryEmail(String to, String subject, String userName, String link) throws MessagingException {
    	
    	String htmlMessage = String.format("""
    		    <table width='100%%' bgcolor='#f4f6f9' cellpadding='0' cellspacing='0'>
    		        <tr>
    		          <td align='center'>
    		            <table width='600' cellpadding='0' cellspacing='0' bgcolor='#ffffff' style='margin: 20px auto; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.05);'>
    		              <tr>
    		                <td align='center' bgcolor='#E67539' style='padding: 30px 0; border-radius: 8px 8px 0 0;'>
    		                  <h1 style='color: #ffffff; margin: 0;'>%s Password Recovery</h1>
    		                </td>
    		              </tr>
    		              <tr>
    		                <td>
    		                    <img style='margin: 3%% 40%%;' src='https://wanzami.tv/assets/images/logo.png' title='Wanzami Logo' alt='Wanzami Logo' />
    		                </td>
    		              </tr>
    		              <tr>
    		                <td style='padding: 30px;'>
    		                  <p style='font-size: 16px; color: #333;'>Hello <strong>%s</strong>,</p>
    		                  <p style='font-size: 16px; color: #333;'>We received a request to reset the password for your wanzami account.</p>
    		                  <p style='font-size: 16px; color: #333;'>If you made this request, click the button below to reset your password.</p>
    		                  <div style='text-align: center; margin: 30px 0;'>
    		                    <a href='%s' target="blank" style='background-color: #E67539; color: #ffffff; padding: 14px 24px; text-decoration: none; border-radius: 4px; font-size: 16px;'>
    		                      Password Reset
    		                    </a>
    		                  </div>
    		                  <p style='font-size: 14px; color: #999;'>If you didn’t request a password reset, you can ignore this email. Your password will remain unchanged.</p>
    		                  <p style='font-size: 16px; color: #333;'> The %s Team</p>
    		                </td>
    		              </tr>
    		              <tr>
    		                <td align='center' bgcolor='#f4f6f9' style='padding: 20px; font-size: 12px; color: #aaa;'>
    		                  &copy; %s %s. All rights reserved.
    		                </td>
    		              </tr>
    		            </table>
    		          </td>
    		        </tr>
    		    </table>
    		""", this.appName,userName, link, this.appName, this.year, this.appName);
    	
    	MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        message.setFrom("Wanzami Team <mail@wanzami.tv>");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlMessage, true); // true = enable HTML

        mailSender.send(message);
    }
  
}