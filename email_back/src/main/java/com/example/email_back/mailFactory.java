package com.example.email_back;

public class mailFactory {

    //use getShape method to get object of type shape
    public mailBox getMail(String mailType){
        if(mailType == null){
            return null;
        }
        if(mailType.equalsIgnoreCase("INBOX")){
            return new inbox();

        } else if(mailType.equalsIgnoreCase("TRASH")){
            return new trash();

        } else if(mailType.equalsIgnoreCase("DRAFT")){
            return new draft();

        } else if(mailType.equalsIgnoreCase("SENT")){
            return new sent();
        } else {
            return new generalfolder();
        }
    }
}