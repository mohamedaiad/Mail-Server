package com.example.email_back;

public interface mailBox {
    public void show();
    public void message(String input);
    public String show_messages(String folder,String sortType);
}