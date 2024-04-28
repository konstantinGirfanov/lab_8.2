package com.example.modules.image;

import com.example.ModuleContract;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Component
public class FilterMaker implements ModuleContract {
    @Override
    public boolean CanBeExecuted(String filePath)
    {
        return filePath.endsWith(".png");
    }

    @Override
    public String GetDescription()
    {
        return "Applies a filter to an image.";
    }

    @Override
    public void Execute(String filePath)
    {
        File file = new File(filePath);
        try {
            BufferedImage image = ImageIO.read(file);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter R:");
            int r = scanner.nextInt();

            System.out.println("Enter G:");
            int g = scanner.nextInt();

            System.out.println("Enter B:");
            int b = scanner.nextInt();

            BufferedImage filter = ImageIO.read(file);
            for(int x = 0; x < filter.getWidth(); x++){
                for(int y = 0; y < filter.getHeight(); y++){
                    Color resColor = new Color(r, g, b, 50);
                    filter.setRGB(x, y, resColor.getRGB());
                }
            }

            image.getGraphics().drawImage(filter,0, 0, null);
            ImageIO.write(image, "png", new File("result.png"));
        } catch (IOException e) {
            System.err.println("Error with " + e.getMessage());
        }
    }
}
