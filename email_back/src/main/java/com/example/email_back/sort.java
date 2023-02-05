package com.example.email_back;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


public class sort {
    String sortType = "";
    List<message> messages;
    public sort(String SortType, List<message> messages){
        System.out.println(SortType);
        this.sortType = SortType;
        this.messages = messages;
    }
    public List<message> switchSort() {
        System.out.println(this.sortType + "111111111111111");
        if (sortType.equals("priority")) {
            System.out.println("11111111");
            return periorityOrder(messages);
        }else if (sortType.equals("subject")) {

            return subjectOrder(messages);
        }else if (sortType.equals("sender")) {
            return senderOrder(messages);
        }
        return null;
    }
    public List<message> periorityOrder(List<message> messages)
    {
        int n = messages.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (messages.get(j).getImportance() < messages.get(j + 1).getImportance())
                {
                    // swap message[j+1] and message[j]
                    message temp = messages.get(j);
                    messages.set(j,messages.get(j+1));
                    messages.set(j+1,temp);
                }
        return messages;
    }
    public List<message> senderOrder(List<message> messages)
    {
        int n = messages.size();
        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++) {
                if (messages.get(i).getFrom().compareTo(messages.get(j).getFrom())>0)
                {
                    message temp = messages.get(i);
                    messages.set(i,messages.get(j));
                    messages.set(j,temp);
                }
            }
        }
        return messages;
    }
    public List<message> subjectOrder(List<message> messages)
    {
        int n = messages.size();
        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++) {
                if (messages.get(i).getSubject().compareTo(messages.get(j).getSubject())>0)
                {
                    message temp = messages.get(i);
                    messages.set(i,messages.get(j));
                    messages.set(j,temp);
                }
            }
        }
        return messages;
    }

}