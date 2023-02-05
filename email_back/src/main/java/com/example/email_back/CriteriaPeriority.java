package com.example.email_back;

import java.util.ArrayList;
import java.util.List;

public class CriteriaPeriority implements Criteria{
    @Override
    public List<message> meetCriteria(List<message> messages, String searchKey) {
        List<message> result = new ArrayList<message>();
        Integer number = Integer.valueOf(searchKey);
        for (message mess : messages) {
            if(mess.getImportance() == number){
                result.add(mess);
            }
        }
        return result;
    }
}
