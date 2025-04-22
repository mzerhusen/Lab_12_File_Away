import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileInspector
{
	public static void main(String[] args)
	{
        Scanner input = new Scanner(System.in);
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String readData = "";
        ArrayList<String> fileData = new ArrayList<>();
        int lineNumber;

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            Path selected
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                    InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                lineNumber = 0;
                while(reader.ready())
                {
                    readData = reader.readLine();
                    fileData.add(readData);
                    lineNumber++;
                    System.out.printf("\nLine %4d %s ", lineNumber, readData);
                }
                reader.close();
                System.out.println("\nData File Read.");
            }
            else
            {
                System.out.println("No file chosen, please choose a file to process. \nPlease run the program again!");
                System.exit(0);

            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
