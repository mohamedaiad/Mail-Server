package com.example.email_back;

import java.util.ArrayList;
import java.util.List;

public class CriteriaSubject implements Criteria {

    @Override
    public List<message> meetCriteria(List<message> messages,String searchKey) {
        List<message> filterSubjects = new ArrayList<message>();

        for (message mess : messages) {
            if(mess.getSubject().equalsIgnoreCase(searchKey)){
                System.out.println("222222222");
                filterSubjects.add(mess);
            }
        }
        return filterSubjects;
    }
}
