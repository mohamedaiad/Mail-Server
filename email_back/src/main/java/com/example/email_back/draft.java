package com.example.email_back;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class draft extends folder{


    public draft(){
        String result;
        try {
            result = new String(Files.readAllBytes(Paths.get(u.getFolder_path() + "/draft.json")));
//            System.out.println(result);
            Type mapTokenType = new TypeToken<Map<String, message>>(){}.getType();
            if(result.length() != 0) {
                this.messages = new Gson().fromJson(result, mapTokenType);
            }
//            System.out.println(this.messages);
        }catch(final Exception e){
            e.printStackTrace();
        }
        System.out.println(u.getFolder_path()+"/draft.json");
    }
    @Override
    public void show(){
        System.out.println("this is draft");
    }

    @Override
    public void message(String input){
        message m = gson.fromJson(input,message.class);
        m.setFrom(u.email);
        m.setDate();
        m.make_Id();
        messages.put(m.id,m);
        String json = gson.toJson(messages);
        try {
            FileWriter myWriter = new FileWriter(u.getFolder_path()+"/draft.json");
            myWriter.write(json);
            myWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
