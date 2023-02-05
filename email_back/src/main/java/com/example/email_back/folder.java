package com.example.email_back;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class folder implements mailBox {
    Gson gson = new Gson();
    Map<String ,message> messages = new HashMap<>();
    user u = user.getInstance();

    @Override
    public String show_messages(String folder, String sortType){
        String result;
        try {
            result = new String(Files.readAllBytes(Paths.get(u.getFolder_path() + "/" + folder + ".json")));
            Type mapTokenType = new TypeToken<Map<String, message>>(){}.getType();
            if(result.length() != 0) {
                this.messages = new Gson().fromJson(result, mapTokenType);
            }
        }catch(final Exception e){
            e.printStackTrace();
        }
        int len = this.messages.size(),i=0;
        List<message> obj = new ArrayList<message>();
        for (Map.Entry<String, message> entry : this.messages.entrySet()){
            obj.add(entry.getValue());
        }
        sort ss = new sort(sortType,obj);
        if(sortType.equals("default")){
            System.out.println("default");
        }
        else if(sortType.equals("priority")) {
            obj = ss.switchSort();
        }else if(sortType.equals("subject")){
            obj = ss.switchSort();
        }else if(sortType.equals("sender")){
            obj = ss.switchSort();
        }else {
            String[] sear = sortType.split("-");
            System.out.println(sear[1]+sear[2]);
            if(sear[1].equals("subject")){
                Criteria subject = new CriteriaSubject();
                obj = subject.meetCriteria(obj,sear[2]);
            }else if(sear[1].equals("sender")){
                Criteria sender = new CriteriaSender();
                obj = sender.meetCriteria(obj,sear[2]);
            }else if(sear[1].equals("date")){
                Criteria date = new CrireriaDate();
                obj = date.meetCriteria(obj,sear[2]);
            }else if(sear[1].equals("body")){
                Criteria body = new CriteriaBody();
                obj = body.meetCriteria(obj,sear[2]);
            }else if(sear[1].equals("reciever")){
                Criteria reciever = new CriteriaReciever();
                obj = reciever.meetCriteria(obj,sear[2]);
            }else if(sear[1].equals("periority")){
                Criteria periority = new CriteriaPeriority();
                obj = periority.meetCriteria(obj,sear[2]);
            }else if(sear[1].equals("attachment")){
                Criteria attachment = new CriteriaAttachments();
                obj = attachment.meetCriteria(obj,sear[2]);
            }
        }
        String json = gson.toJson(obj);
        return json;

    }

    public abstract void show();
    public abstract void message(String input);
}