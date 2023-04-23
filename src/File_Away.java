import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.ArrayList;
import javax.swing.JFileChooser;


public class File_Away {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser(); // sets up file chooser.
        //var declarations
        File selectedFile; // var to hold the users selected file
        String rec = ""; // stores the file text
        ArrayList<String> lines = new ArrayList<>(); // array which holds file lines
        String [] wordsNum = new String[] {}; // var used to find the number of words
        int charNum = 0; // number of characters
        int wordCount = 0;//number of words

        try
        {
           File workingDirectory = new File(System.getProperty("user.dir")); // gives current directory of user

                       chooser.setCurrentDirectory(workingDirectory); // gives user files to choose from

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) // if the user chooses an a file
            {
                selectedFile = chooser.getSelectedFile(); // sets var to the file user chooses
                Path file = selectedFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                int line = 0; // var to report line numbers
                while(reader.ready())
                {
                    rec = reader.readLine(); // the string var reads the line
                    lines.add(rec); // the text is stored in the var
                     wordsNum = rec.split(" "); // counts number of words using split
                     wordCount = wordCount + wordsNum.length; // stores number of words in int var
                     charNum = charNum + rec.length(); // finds number of characters in file
                    line++; // increments line number
                    System.out.printf("\nLine %4d %-60s ", line, rec); // displays text file with line numbers
                }

                reader.close(); // closes file
                System.out.println("\n\nThe file was successfully read!"); // outputs message to user
                System.out.println("The number of lines in the file are: " + line); // outputs the number of lines in file
                System.out.println("The number of words in the data file are: " + wordCount); // outputs number of words
                System.out.println("The number of characters in the file are: " + charNum); // outputs number of characters
                System.out.println("The name of the file read is: " + file.getFileName()); // outputs name of file
            }
            else  // the user did not choose a file
            {
                System.out.println("Failed to choose a file to process"); // outputs message to user
                System.out.println("Run the program again!"); // outputs message to user
                System.exit(0); // ends the program
            }
        }
        catch (FileNotFoundException e) // possible error
        {
            System.out.println("File not found!!!"); // outputs message if error occurs
            e.printStackTrace();
        }
        catch (IOException e) // possible error
        {
            e.printStackTrace();
        }
    }

}