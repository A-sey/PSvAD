package com.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Files {
    public static void write(String name, String text){
        File file = new File(name);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(text.getBytes(StandardCharsets.UTF_8));
            fos.close();
            System.out.println("\nФайл сохранён под именем " + name + "\n");
        }catch (IOException e){
            System.out.println("\nНевозможно записать файл\n");
        }
    }
}
