package com.example.modules.text;

import com.example.ModuleContract;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
public class EverySymbolEnteringCounter implements ModuleContract
{
    @Override
    public boolean CanBeExecuted(String filePath)
    {
        return filePath.endsWith(".txt");
    }

    @Override
    public String GetDescription()
    {
        return "Counts every entering of symbol in file";
    }

    @Override
    public void Execute(String filePath)
    {
        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            Map<Character, Integer> map = new HashMap<>();
            String str;
            while ((str = reader.readLine()) != null)
                for (char letter : str.toCharArray())
                    map.put(letter, map.getOrDefault(letter,0) + 1);
            for (Map.Entry<Character, Integer> entry : map.entrySet())
                System.out.println("Symbol " + entry.getKey()+"entering " + entry.getValue() +"times");
        } catch (IOException e) {
            System.out.println("Error with -  " + e.getMessage());
        }
    }
}