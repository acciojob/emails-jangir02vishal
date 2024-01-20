package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail {

    private ArrayList<Meeting> calendar = new ArrayList<>();

    public Workspace(String emailId) {
        super(emailId, Integer.MAX_VALUE);
    }

    public void addMeeting(Meeting meeting) {
        calendar.add(meeting);
    }

    public int findMaxMeetings() {
        Collections.sort(calendar, Comparator.comparing(Meeting::getEndTime));

        int count = 0;
        LocalTime lastEndTime = null;

        for (Meeting meeting : calendar) {
            if (lastEndTime == null || meeting.getStartTime().isAfter(lastEndTime)) {
                lastEndTime = meeting.getEndTime();
                count++;
            }
        }

        return count;
    }
}
