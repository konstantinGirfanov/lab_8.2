package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "com.example")
public class App
{
    private static List<ModuleContract> modules;

    @Autowired
    public App(List<ModuleContract> modules)
    {
        App.modules = modules;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter file path (enter e to exit): ");
            String filePath = scanner.nextLine();
            if (filePath.equals("e"))
            {
                return;
            }

            File file = new File(filePath);
            if (!file.exists())
            {
                System.out.println("File does not exist");
                continue;
            }

            ArrayList<ModuleContract> availableModules = new ArrayList<ModuleContract>();
            for (ModuleContract module : modules)
            {
                if (module.CanBeExecuted(filePath))
                {
                    availableModules.add(module);
                }
            }

            System.out.println("All functions of modules:");
            for (int i = 0; i < availableModules.size(); i++)
            {
                System.out.println(i + " " + availableModules.get(i).GetDescription());
            }

            System.out.print("Enter function:");
            availableModules.get(scanner.nextInt()).Execute(filePath);
        }
    }
}