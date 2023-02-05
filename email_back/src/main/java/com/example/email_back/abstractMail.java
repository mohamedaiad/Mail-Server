package com.example.email_back;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class abstractMail {
    Gson gson = new Gson();
    Map<String ,message> toMap = new HashMap<>();
    Map<String ,message> fromMap = new HashMap<>();
    user u = user.getInstance();

    public String move(Queue<String> id, String from, String to) {
        String result = "";
        try {
            result = new String(Files.readAllBytes(Paths.get(u.getFolder_path() + "/" + from + ".json")));
            Type mapTokenType = new TypeToken<Map<String, message>>() {
            }.getType();
            if (result.length() != 0) {
                this.fromMap = new Gson().fromJson(result, mapTokenType);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        try {
            result = new String(Files.readAllBytes(Paths.get(u.getFolder_path() + "/" + to + ".json")));
//            System.out.println(result);
            Type mapTokenType = new TypeToken<Map<String, message>>() {
            }.getType();
            if (result.length() != 0) {
                this.toMap = new Gson().fromJson(result, mapTokenType);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        for (String i : id) {
            message m = this.fromMap.get(i);
            this.fromMap.remove(i);
            String json = gson.toJson(this.fromMap);
            result = json;
            try {
                FileWriter myWriter = new FileWriter(u.getFolder_path() + "/" + from + ".json");
                myWriter.write(json);
                myWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            this.toMap.put(i, m);
            json = gson.toJson(this.toMap);
            try {
                FileWriter myWriter = new FileWriter(u.getFolder_path() + "/" + to + ".json");
                myWriter.write(json);
                myWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }



    public String delete(Queue<String> id,String from){
        return move(id,from,"trash");
    }
    public String deleteFEver(Queue<String> id){
        String result = "";
        try {
            result = new String(Files.readAllBytes(Paths.get(u.getFolder_path() + "/trash.json")));
            Type mapTokenType = new TypeToken<Map<String, message>>() {
            }.getType();
            if (result.length() != 0) {
                this.fromMap = new Gson().fromJson(result, mapTokenType);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        for (String i : id) {
            this.fromMap.remove(i);
            String json = gson.toJson(this.fromMap);
            result = json;
            try {
                FileWriter myWriter = new FileWriter(u.getFolder_path() + "/trash.json");
                myWriter.write(json);
                myWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
