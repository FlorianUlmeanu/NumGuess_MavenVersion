package org.fasttrackit.dev.lesson1.numgenerator;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by dj_fl on 8/22/2015.
 */
public class SendMail implements Runnable {
    public void sendEmail(){
        Thread t=new Thread(this);
        t.start();
    }

    private void run() {
        System.out.println("enter in sendmail");
        final String username = "lemonteamcode@gmail.com"; final String password = "fastrackit";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("appleteamcj@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("Florian.Ulmeanu@gmail.com"));
            message.setSubject("Num-guess");
            message.setText("Congratulation you guess! " + startingTime + guessTime + timeToGuess);

            Transport.send(message);

            System.out.println("sendmail done");


        } catch (Exception e) {
            System.out.println("Email sending problems");
            e.printStackTrace();
            System.out.println("error sendmail");

        }
    }
}
