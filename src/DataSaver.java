import javax.swing.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;


public class DataSaver
{
    public static void main(String[] args)
    {
        final int CURRENT_YEAR = 2025;
        final int FIELD_COUNT = 5;
        Scanner input = new Scanner(System.in);
        JFileChooser chooser = new JFileChooser();
        String fileName = "";
        File selectedFile;
        ArrayList<String> records = new ArrayList<>();
        String firstName = "";
        String lastName = "";
        int idNumber;
        String idNumberStr = "";
        String userEmail = "";
        int birthYear;
        String birthYearStr = "";
        boolean moreEntries = true;

        fileName = SafeInput.getRegExString(input,"Please name your file", "^[a-zA-Z0-9]+$");
        System.out.println("\nPlease enter data below\n");

        do
        {
            firstName = SafeInput.getNonZeroLenString(input, "Please enter your first name");
            lastName = SafeInput.getNonZeroLenString(input, "Please enter your last name");
            idNumber = SafeInput.getInt(input,"Please enter your id number");
            idNumberStr = String.format("%06d", idNumber);
            userEmail = SafeInput.getRegExString(input, "Please enter your email", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
            birthYear = SafeInput.getRangedInt(input, "Please enter the year of your birth", 1900, CURRENT_YEAR);
            birthYearStr = String.format("%04d", birthYear);
            records.add(firstName + ", " + lastName + ", " + idNumberStr + ", " + userEmail + ", " + birthYear);
            moreEntries = SafeInput.getYesNoConfirm(input, "Do you have more entries?");
        }
        while(moreEntries);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName + ".txt");

        try
        {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            for(String rec : records)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data File Written Successfully.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}