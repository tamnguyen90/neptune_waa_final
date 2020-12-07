package edu.miu.cs.neptune.service;

import edu.miu.cs.neptune.domain.Mail;

public interface MailService {
    public void sendEmail(Mail mail);
    public Mail createEmail(String MailFrom, String MailTo, String MailSubject, String MailContent);
}
