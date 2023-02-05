package com.example.email_back;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class sent extends folder{

    public sent() {
        String result;
        try {
            result = new String(Files.readAllBytes(Paths.get(u.getFolder_path() + "/sent.json")));
//            System.out.println(result);
//            if(result.length() == 0) System.out.println("here it is");
            Type mapTokenType = new TypeToken<Map<String, message>>(){}.getType();
            if(result.length() != 0) {
                this.messages = new Gson().fromJson(result, mapTokenType);
            }
        }catch(final Exception e){
            e.printStackTrace();
        }
        System.out.println(u.getFolder_path()+"/sent.json");

    }
    @Override
    public void show(){
        System.out.println("this is sent");
    }
    public void message(String input){
        System.out.println(input);
//        tempmessage m = gson.fromJson(input,tempmessage.class);
        message m = gson.fromJson(input,message.class);
        m.setFrom(u.email);
        m.make_Id();
        m.setDate();
        messages.put(m.id,m);
        String json = gson.toJson(messages);
        sign check = new sign();
        Queue<String> to = m.getTo();
        for(String i : to){
            if(check.isThere(i)) {
                inbox in = new inbox();
                in.get_message(input,i);
                try {
                    FileWriter myWriter = new FileWriter(u.getFolder_path()+"/sent.json");
                    myWriter.write(json);
                    myWriter.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            else{
                draft in = new draft();
                in.message(input);
            }
        }

    }

    public String sendToDraft(String input){
        draft in = new draft();
        in.message(input);
        return "done";
    }



}