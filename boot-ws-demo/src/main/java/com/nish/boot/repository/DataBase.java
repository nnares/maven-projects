package com.nish.boot.repository;

import com.nish.boot.model.Message;

import java.util.HashMap;
import java.util.Map;

public class DataBase {

    private static Map<Integer, Message> messageTable;

    static{
        messageTable = new HashMap<>();
        messageTable.put(1, new Message(1, "IPL is kicking off tomorrow", "M S Dhoni", "18/09/2020"));
        messageTable.put(2, new Message(2, "Todays poll - Mountain / Beach ?", "Aprajita Lilly", "18/09/2020"));
        messageTable.put(3, new Message(3, "RCB goes 2nd", "Virat Kohli", "04/10/2020"));
        messageTable.put(4, new Message(4, "Delhi goes top in points table", "Ricky Pointing", "Sun Oct 04 10:44:31 IST 2020"));
    }

    public static Map<Integer, Message> getMessageTable() {
        return messageTable;
    }

}
