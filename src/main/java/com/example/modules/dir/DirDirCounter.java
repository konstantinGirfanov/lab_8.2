package com.example.modules.dir;

import com.example.ModuleContract;
import org.springframework.stereotype.Component;

import java.io.File;
@Component
public class DirDirCounter implements ModuleContract {

    @Override
    public boolean CanBeExecuted(String filePath) {
        File fileSystemElement = new File(filePath);
        return fileSystemElement.isDirectory();
    }

    @Override
    public String GetDescription() {
        return "Prints list of all directories in directory";
    }

    @Override
    public void Execute(String filePath) {
        File directory = new File(filePath);
        File[] files = directory.listFiles();
        if (files != null)
            for (File file : files)
                if(file.isDirectory())
                    System.out.println(file.getName());
    }
}
