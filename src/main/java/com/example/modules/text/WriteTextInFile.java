package com.example.modules.text;

import com.example.ModuleContract;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class WriteTextInFile implements ModuleContract {
    public boolean CanBeExecuted(String filePath)
    {
        return filePath.endsWith(".txt");
    }

    public String GetDescription()
    {
        return "Prints all lines in file";
    }

    public void Execute(String filePath)
    {
        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null){
                System.out.println(line);
                line = reader.readLine();
            };
        } catch (IOException e) {
            System.out.println("Error with - " + e.getMessage());
        }
    }
}
