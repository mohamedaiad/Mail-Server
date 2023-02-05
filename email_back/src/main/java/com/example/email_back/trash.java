package com.example.email_back;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class trash extends folder{

    @Override
    public void show(){
        System.out.println("this is trash");
    }

    @Override
    public void message(String input) {
        return;
    }

}