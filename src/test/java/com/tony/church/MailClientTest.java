package com.tony.church;


import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.tony.church.service.MailClient;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailClientTest {

    private GreenMail smtpServer;

    @Autowired
    private MailClient mailClient;

    @Before
    public void setUp() throws Exception {
        smtpServer = new GreenMail(new ServerSetup(25, null, "smtp"));
        smtpServer.start();
    }

    @Test
    public void shouldSendMail() throws Exception {
        //given
        String recipient = "name@dolszewski.com";
        String message = "Test message content";
        //when
        mailClient.prepareAndSend(recipient, message);
        //then
        String content = "<span>" + message + "</span>";
        assertReceivedMessageContains(content);
    }

    private void assertReceivedMessageContains(String expected) throws IOException, MessagingException {
        MimeMessage[] receivedMessages = smtpServer.getReceivedMessages();
        Assert.assertEquals(1, receivedMessages.length);
        String content = (String) receivedMessages[0].getContent();
        Assert.assertTrue(content.contains(expected));
    }

    @After
    public void tearDown() throws Exception {
        smtpServer.stop();
    }

}

