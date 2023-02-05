package com.example.email_back;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class inbox extends folder{


    public inbox(){
        String result;
        try {
            result = new String(Files.readAllBytes(Paths.get(u.getFolder_path() + "/inbox.json")));
//            System.out.println(result);
            Type mapTokenType = new TypeToken<Map<String, message>>(){}.getType();
            if(result.length() != 0) {
                this.messages = new Gson().fromJson(result, mapTokenType);
            }
//            System.out.println(this.messages);
        }catch(final Exception e){
            e.printStackTrace();
        }
        System.out.println(u.getFolder_path()+"/inbox.json");
    }

    @Override
    public void show(){
        System.out.println("this is inbox");
    }
    @Override
    public void message(String input){return;}


    public void get_message(String input, String to){
        String result;
        try {
            result = new String(Files.readAllBytes(Paths.get(u.getData_path()+"/"+to+"/inbox.json")));
//            System.out.println(result);
            Type mapTokenType = new TypeToken<Map<String, message>>(){}.getType();
            if(result.length() != 0) {
                this.messages = new Gson().fromJson(result, mapTokenType);
            }
//            System.out.println(this.messages);
        }catch(final Exception e){
            e.printStackTrace();
        }
        System.out.println(u.getFolder_path()+"/inbox.json");
        message m = gson.fromJson(input,message.class);
        m.setDate();
        m.make_Id();
        m.setFrom(u.email);
        messages.put(m.id,m);
        String json = gson.toJson(messages);
        try {
            FileWriter myWriter = new FileWriter(u.getData_path()+"/"+to+"/inbox.json");
            myWriter.write(json);
            myWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}