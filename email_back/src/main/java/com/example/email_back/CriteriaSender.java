package com.example.email_back;

import java.util.ArrayList;
import java.util.List;

public class CriteriaSender implements Criteria {

    @Override
    public List<message> meetCriteria(List<message> messages,String searchKey) {
        List<message> result = new ArrayList<message>();

        for (message mess : messages) {
            if(mess.getFrom().equalsIgnoreCase(searchKey)){
                result.add(mess);
            }
        }
        return result;
    }
}