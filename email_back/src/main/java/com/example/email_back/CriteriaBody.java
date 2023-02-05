package com.example.email_back;

import java.util.ArrayList;
import java.util.List;

public class CriteriaBody implements Criteria {
    @Override
    public List<message> meetCriteria(List<message> messages, String searchKey) {
        List<message> result = new ArrayList<message>();
        for (message mess : messages) {
            int intIndex = mess.getContent().indexOf(searchKey);
            if(intIndex != - 1) {
                result.add(mess);
            }
        }
        return result;
    }
}
