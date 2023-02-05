package com.example.email_back;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class contact{
    String username;
    String email;
    String phonenumber;
    private List<String> contacts = new ArrayList<String>();

    private contact co;

    public String addContant(String input){
        Gson gson = new Gson();
        user u = user.getInstance();
        String result;
        try {
            result = new String(Files.readAllBytes(Paths.get(u.getFolder_path() + "/contacts.json")));
            co = gson.fromJson(result,contact.class);
        }catch(final Exception e){
            e.printStackTrace();
        }
        sign n= new sign();
        if(n.isThere(input) && !co.contacts.contains(input)) {
            co.contacts.add(input);
            String json = gson.toJson(co);
            try {
                FileWriter myWriter = new FileWriter(u.getFolder_path()+"/contacts.json");
                myWriter.write(json);
                myWriter.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            return "confirmed";
        }
        else return "ERROR";
    }

    public String removecontact(String input){
        Gson gson = new Gson();
        user u = user.getInstance();
        String result;
        try {
            result = new String(Files.readAllBytes(Paths.get(u.getFolder_path() + "/contacts.json")));
            co = gson.fromJson(result,contact.class);
        }catch(final Exception e){
            e.printStackTrace();
        }
        co.contacts.remove(input);
        String json = gson.toJson(co);
        try {
            FileWriter myWriter = new FileWriter(u.getFolder_path()+"/contacts.json");
            myWriter.write(json);
            myWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "done";
    }


    public String showcontact() {
        user u = user.getInstance();
        String result = "";
        try {
            result = new String(Files.readAllBytes(Paths.get(u.getFolder_path() + "/contacts.json")));
        }catch(final Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
