package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Gmail extends Email {

    private int inboxCapacity;
    private List<Mail> inbox = new ArrayList<>();
    private List<Mail> trash = new ArrayList<>();

    private class Mail {
        Date date;
        String sender;
        String message;

        Mail(Date date, String sender, String message) {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }
    }

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message) {
        if (inbox.size() == inboxCapacity) {
            trash.add(inbox.remove(0)); // Move oldest email to trash
        }
        inbox.add(new Mail(date, sender, message));
    }

    public void deleteMail(String message) {
        Mail mailToDelete = inbox.stream()
                                 .filter(mail -> mail.message.equals(message))
                                 .findFirst()
                                 .orElse(null);
        if (mailToDelete != null) {
            inbox.remove(mailToDelete);
            trash.add(mailToDelete);
        }
    }

    public String findLatestMessage() {
        return inbox.isEmpty() ? null : inbox.get(inbox.size() - 1).message;
    }

    public String findOldestMessage() {
        return inbox.isEmpty() ? null : inbox.get(0).message;
    }

    public int findMailsBetweenDates(Date start, Date end) {
        return (int) inbox.stream()
                          .filter(mail -> !mail.date.before(start) && !mail.date.after(end))
                          .count();
    }

    public int getInboxSize() {
        return inbox.size();
    }

    public int getTrashSize() {
        return trash.size();
    }

    public void emptyTrash() {
        trash.clear();
    }

    public int getInboxCapacity() {
        return inboxCapacity;
    }
}
