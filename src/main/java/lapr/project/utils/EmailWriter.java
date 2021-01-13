/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author joaol
 */
public class EmailWriter {

    /**
     *
     */
    public EmailWriter() {
        
    }

    /**
     *
     * @param to
     * @param name
     * @param parkedWell
     * @param time
     * @return
     */
    public String sendEmail(String to, String name, boolean parkedWell, String time) {
        String s;
        s = writeEmail(name, parkedWell, time);
        
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        String from = "g22ridesharing@gmail.com";
        String fromPwd = "8auK97VcQqXVeW27";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, fromPwd);
            }
        });

        try {
           MimeMessage message = new MimeMessage(session);
           message.setFrom(new InternetAddress(from));
           message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
           message.setSubject("Parking status on your latest trip");
           message.setText(s);
           Transport.send(message);
           System.out.println("Sent message successfully");
        } catch (MessagingException mex) {
            mex.printStackTrace();
           return "";
        }
        return s;
    }
    
    /**
     *
     * @param name
     * @param parkedWell
     * @param time
     * @return
     */
    public String writeEmail(String name, boolean parkedWell, String time) {
        StringBuilder sb = new StringBuilder();
        sb.append("Greetings, ").append(name).append("\nYour vehicle");
        if (parkedWell) {
            sb.append(" has been parked correctly, ");
        } else {
            sb.append(" has NOT been parked correctly, ");
        }
        sb.append("and your trip lasted ").append(time);
        if (parkedWell) {
            sb.append(". We hope you had a great ride!");
        } else {
            sb.append(". Please be more mindful of where you park next time.");
        }
        return sb.toString();
    }

}
