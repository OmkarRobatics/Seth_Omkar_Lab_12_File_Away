import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.ArrayList;
import javax.swing.JFileChooser;


public class File_Away {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser(); // sets up file chooser.
        File selectedFile;
        String rec = "";
        ArrayList<String> lines = new ArrayList<>(); // array which holds file lines
        String [] wordsNum = new String[] {};
        int charNum = 0;
        int wordCount = 0;

        try
        {
           File workingDirectory = new File(System.getProperty("user.dir"));

                       chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                // Typical java pattern of inherited classes
                // we wrap a BufferedWriter around a lower level BufferedOutputStream
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                int line = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    lines.add(rec);
                     wordsNum = rec.split(" ");
                     wordCount = wordCount + wordsNum.length;
                     charNum = charNum + rec.length();
                    line++;
                    // echo to screen
                    System.out.printf("\nLine %4d %-60s ", line, rec);

                }



                for(String l:lines)
                {
                    System.out.println(l);
                }

                String fields[] = lines.get(5).split(", ");
                for(String f:fields)
                    System.out.println(f);

                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");
                System.out.println("The number of lines in the file are: " + line); // outputs the number of lines in file
                System.out.println("The number of words in the data file are: " + wordCount);
                System.out.println("The number of characters in the file are: " + charNum);
                System.out.println("The name of the file read is: " + file.getFileName());
            }
            else  // user closed the file dialog without choosing
            {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        }  // end of TRY
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}