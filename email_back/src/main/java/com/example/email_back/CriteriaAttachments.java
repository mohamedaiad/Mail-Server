package com.example.email_back;

import java.util.ArrayList;
import java.util.List;

public class CriteriaAttachments implements Criteria{
    @Override
    public List<message> meetCriteria(List<message> messages, String searchKey) {
        List<message> result = new ArrayList<message>();

        for (message mess : messages) {
            if (mess.getAttachement().indexOf(searchKey) != -1) {
                result.add(mess);
            }
        }
        return result;
    }
}
