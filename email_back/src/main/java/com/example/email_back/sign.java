package com.example.email_back;

import java.io.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class sign {
    Gson gson = new Gson();
    String path = "";
    Map<String,String> users = new HashMap<>();

    public sign() {
        check_files();
        String result;
        try {
            result = new String(Files.readAllBytes(Paths.get(get_dataPath() + "/data.json")));
            Type mapTokenType = new TypeToken<Map<String, String>>(){}.getType();
            Map<String,String> temp;
            temp = new Gson().fromJson(result, mapTokenType);
            if(temp!=null) {
                this.users = temp;
            }
        }catch(final Exception e){
            e.printStackTrace();
        }

    }

    public String sign_up(String input){
        System.out.println(input);
        contact co = gson.fromJson(input,contact.class);
        String cont = gson.toJson(co);
        user u2 = user.getInstance();
        user u = gson.fromJson(input,user.class);

        System.out.println(this.users);
//        for (String key : this.users.keySet()) {
//            System.out.println(key);
//            System.out.println(users.get(key));
//        }
        if(!this.users.isEmpty() && this.users.containsKey(u.email)) return "ERROR";
        else{
            this.users.put(u.email,u.password);
            String json = gson.toJson(this.users);
            u.setFolder_path(create_folder(u.email,get_dataPath()));
            String prof = gson.toJson(u);
            create_json("profile.json",u.getFolder_path());
            create_json("inbox.json",u.getFolder_path());
            create_json("trash.json",u.getFolder_path());
            create_json("sent.json",u.getFolder_path());
            create_json("draft.json",u.getFolder_path());
            create_json("contacts.json",u.getFolder_path());
            try {
                FileWriter myWriter = new FileWriter(get_dataPath()+"/data.json");
                myWriter.write(json);
                myWriter.close();
                FileWriter myWriter2 = new FileWriter(u.getFolder_path()+"/profile.json");
                myWriter2.write(prof);
                myWriter2.close();
                FileWriter myWriter3 = new FileWriter(u.getFolder_path()+"/contacts.json");
                myWriter3.write(cont);
                myWriter3.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            deep_copy(u,u2);

            return "confirmed";
        }
    }

    public String login(String input){
        System.out.println(input);
        user u2 = user.getInstance();
        user u = gson.fromJson(input,user.class);
        if(users.containsKey(u.email)){

            if (Objects.equals(users.get(u.email), u.password)){
                try {
                    String profile = new String(Files.readAllBytes(Paths.get(get_dataPath() + "/" + u.email + "/profile.json")));
                    u = gson.fromJson(profile,user.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                deep_copy(u,u2);
                return "confirmed";
            }
        }
        return "ERROR";
    }

    public void check_files(){

        create_folder("server",System.getProperty("user.dir"));

        String path = System.getProperty("user.dir")+"/server";
        user u = user.getInstance();
        u.setData_path(path);
        this.path = path;
        create_json("data.json",path);

    }
    public String get_dataPath(){
        return this.path;
    }
    public String create_folder(String fileName, String Apath){
        String path = Apath+"/"+fileName;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
        }
        return path;
    }
    public void create_json(String filename,String path){
        path = path + "/" + filename;
        try {
            File file_data = new File(path);
            file_data.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void deep_copy(user u1, user u2){
        u2.email = u1.email;
        u2.password = u1.password;
        u2.phonenumber = u1.phonenumber;
        u2.username = u1.username;
        u2.setFolders(u1.getFolders());
        u2.setIDs(u1.getIDs());
        u2.setFolder_path(u1.getFolder_path());
    }

    public boolean isThere(String to){
        if(this.users.containsKey(to)) return true;
        else return false;
    }
}