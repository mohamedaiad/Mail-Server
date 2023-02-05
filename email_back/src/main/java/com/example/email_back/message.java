package com.example.email_back;
import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.FileWriter;
import java.sql.Struct;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


// Adding Replies
public class message {
    String id;
    private Queue<String> to;
    private String from;
    private String subject;
    private String content;
    private String date;
    private int importance;
    private List<String> attachement = new ArrayList<>();

    public List<String> getAttachement() {
        return attachement;
    }

    public void setAttachement(List<String> attachement) {
        this.attachement = attachement;
    }

    public void setDate() {
        LocalDate date = LocalDate.now(); // Gets the current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.date = date.format(formatter);
//        System.out.println(this.date);
    }

    public String getDate() {
        return date;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Queue<String> getTo() {
        return to;
    }

    public void setTo(Queue<String> to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String add_attachment(){
        String attach = get_path();
        this.attachement.add(attach);
        return attach;
    }

    public String get_path() {
        String x ="";
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Select an attachment");

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            x = jfc.getSelectedFile().getPath();
            System.out.println(x);
        }
        return x;
    }
    public void make_Id(){
        user u = user.getInstance();
        int ids = u.getIDs();
        this.id = u.email + Integer.toString(ids);
        u.setIDs(ids+1);
        try {
            Gson gson = new Gson();
            String prof = gson.toJson(u);
            FileWriter myWriter2 = new FileWriter(u.getFolder_path()+"/profile.json");
            myWriter2.write(prof);
            myWriter2.close();
        }catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println(this.id);
    }
}