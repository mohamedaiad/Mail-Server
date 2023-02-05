package com.example.email_back;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class user {

    String username;
    String email;
    String password;
    String phonenumber;
    private List<String> Folders = new ArrayList<>();
    private String folder_path;
    private String data_path;
    private int IDs = 0;


    private static final user instance = new user();
    private user(){}

    private user(String username, String email, String password, String phonenumber){
        this.username = username;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
    }
    public static user getInstance(){

        return instance;
    }

    public List<String> getFolders() {
        return Folders;
    }

    public void setFolders(List<String> folders) {
        Folders = folders;
    }


    public String getData_path() {
        return data_path;
    }

    public void setData_path(String data_path) {
        this.data_path = data_path;
    }
    public String getFolder_path() {
        return folder_path;
    }

    public void setFolder_path(String folder_path) {
        this.folder_path = folder_path;
    }

    public int getIDs() {
        return IDs;
    }

    public void setIDs(int IDs) {
        this.IDs = IDs;
    }
    public String addFolder(String folderName){
        String path = this.folder_path + "/" + folderName + ".json";
        try {
            File file_data = new File(path);
            file_data.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return("An error occurred while creating new folder.");
        }
        try {
            Gson gson = new Gson();
            String prof = gson.toJson(this);
            FileWriter myWriter2 = new FileWriter(this.getFolder_path()+"/profile.json");
            myWriter2.write(prof);
            myWriter2.close();
        }catch (Exception e){
            e.printStackTrace();
            return("An error occurred while creating new folder.");
        }
        this.Folders.add(folderName);
        return "foler created correctly";
    }
    public String rename_folder(String old_name, String new_name){

        File file = new File(this.folder_path+"/"+old_name+".json");
        File rename = new File(this.folder_path+"/"+new_name+".json");
        boolean flag = file.renameTo(rename);

        if (flag == true) {
            int n = this.Folders.indexOf(old_name);
            this.Folders.set(n,new_name);
            try {
                Gson gson = new Gson();
                String prof = gson.toJson(this);
                FileWriter myWriter2 = new FileWriter(this.getFolder_path()+"/profile.json");
                myWriter2.write(prof);
                myWriter2.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            return ("File Successfully Rename");
        }
        else {
            return ("Operation rename Failed");
        }

    }
    public String delete_folder(String folder_name){
        try
        {
            File f= new File(this.folder_path + "/" + folder_name + ".json");
            if(f.delete())
            {
                this.Folders.remove(folder_name);
                try {
                    Gson gson = new Gson();
                    String prof = gson.toJson(this);
                    FileWriter myWriter2 = new FileWriter(this.getFolder_path()+"/profile.json");
                    myWriter2.write(prof);
                    myWriter2.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                return (f.getName() + " deleted");
            }
            else
            {
                return ("failed");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return (folder_name + " deleted");

    }
    public String Folers_json(){
        String res = "";
        for (int i=0; i<this.Folders.size(); i++){
            if(i < this.Folders.size()-1){
                res = res + this.Folders.get(i) + ",";
            }else
                res = res + this.Folders.get(i);
        }
        return res;
    }
}