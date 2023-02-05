package com.example.email_back;

import java.util.List;

public interface Criteria {
    public List<message> meetCriteria(List<message> messages,String searchKey);
}