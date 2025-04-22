import javax.swing.*;
import java.io.*;
import java.io.File;
import java.nio.file.Paths;
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
        Path selectedPath = Paths.get("C:\\Users\\Michael\\IdeaProjects\\Lab_12_File_Away\\src");
        File selectedFile;
        String readData = "";
        ArrayList<String> fileData = new ArrayList<>();
        String fileName = "";
        int lineNumber;
        int wordCount;
        int charCount;

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            File subFolder = new File(workingDirectory, "src");
            chooser.setCurrentDirectory(subFolder);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                    InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                fileName = selectedFile.getName();
                lineNumber = 0;
                wordCount = 0;
                charCount = 0;
                while(reader.ready())
                {
                    readData = reader.readLine();
                    fileData.add(readData);
                    lineNumber++;
                    System.out.printf("\nLine %-2d %-20s ", lineNumber, readData);
                    String [] dataElements;
                    dataElements = readData.split(" ");
                    wordCount += dataElements.length;
                    for(int i=0; i<dataElements.length; i++)
                    {
                        charCount += dataElements[i].length();
                    }

                }
                reader.close();
                System.out.println("\n\n\nData File Read. \nFile: " + fileName + ". \nTotal Lines " + lineNumber + ". \nTotal Words: " + wordCount + ". \nTotal Chars: " + charCount + ".");
                System.exit(0);
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
