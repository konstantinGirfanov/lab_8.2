package com.example.modules.image;

import com.example.ModuleContract;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


@Component
public class ImageSizeCounter implements ModuleContract
{
    @Override
    public boolean CanBeExecuted(String filePath)
    {
        return filePath.endsWith(".jpg") || filePath.endsWith(".png");
    }

    @Override
    public String GetDescription()
    {
        return "Prints image sizes.";
    }

    @Override
    public void Execute(String filePath)
    {
        File file = new File(filePath);
        try {
            BufferedImage image = ImageIO.read(file);
            System.out.println("Width: " + image.getWidth());
            System.out.println("Height: " + image.getHeight());
        } catch (IOException e) {
            System.err.println("Error with " + e.getMessage());
        }
    }
}