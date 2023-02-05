package com.example.email_back;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

@RestController
@CrossOrigin(origins = {"http://localhost:61606", "http://localhost:4200"})
@RequestMapping("/get")
public class main {
    mailFactory mail = new mailFactory();
    static sign obj = new sign();
    user u = user.getInstance();
    String res = "";
    Queue<String> id = new LinkedList<>();
    String[] ids = new String[0];
    contact c = new contact();

    @PostMapping("/expression")
    public String Main(@RequestBody String expression){
        System.out.println(expression);
        String s[] = expression.split("&");
        switch (s[0]){
            case "signup" :
                obj = new sign();
                res =  obj.sign_up(s[1]);
                break;
            case "signin" :
                obj = new sign();
                res = obj.login(s[1]);
                break;
            case "show"   :
                mailBox mail0 = mail.getMail(s[1]);
                res = mail0.show_messages(s[1],s[2]);
                break;
            case "send" :
                sent sendMessage = new sent();
                sendMessage.message(s[1]);
                res = "done";
                break;
            case "draft" :
                sent std = new sent();
                res = std.sendToDraft(s[1]);
                break;
            case "move" :
                id = new LinkedList<>();
                ids = s[1].split("-");
                int size = ids.length;
                for(int i=0;i<size;i++){
                    id.add(ids[i]);
                }
                abstractMail new_mail = new abstractMail();
                res = new_mail.move(id,s[2],s[3]);
                break;
            case "delete" :
                id = new LinkedList<>();
                ids = s[1].split("-");
                int len = ids.length;
                for(int i=0;i<len;i++){
                    id.add(ids[i]);
                }
                abstractMail new_mail1 = new abstractMail();
                res = new_mail1.delete(id,s[2]);
                break;
            case "deletetrash" :
                id = new LinkedList<>();
                ids = s[1].split("-");
                int len0 = ids.length;
                for(int i=0;i<len0;i++){
                    id.add(ids[i]);
                }
                abstractMail new_mail2 = new abstractMail();
                res = new_mail2.deleteFEver(id);
                break;
            case "createfile" :
                u.addFolder(s[1]);
                res = "done";
                break;
            case "deletefile" :
                u.delete_folder(s[1]);
                res = "done2";
                break;
            case "renamefile" :
                u.rename_folder(s[1],s[2]);
                res = "done3";
                break;
            case "showfolders" :
                List<String> folders = u.getFolders();
                res = "";
                for(int i=0; i< folders.size(); i++) {
                    res += folders.get(i);
                }
                break;
            case "addcontact" :
                res = c.addContant(s[1]);
                break;
            case "showcontact" :
                res = c.showcontact();
                break;
            case "removecontact" :
                res = c.removecontact(s[1]);
                break;
        }
        System.out.println(res);
        return res;
    }




}
