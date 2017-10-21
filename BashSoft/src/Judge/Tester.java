package Judge;

import IO.OutputWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Tester {
    /* Comparing two text files line by line */
    public static void compareContent(String actualOutput, String expectedOutput){
        OutputWriter.writeMessageOnNewLine("Reading files...");
        String mismatchPath = getMismatchPath(expectedOutput);

        List<String> acutalOutputString = readTextFile(actualOutput);
        List<String> expectedOutputString = readTextFile(expectedOutput);

        boolean mismatch =
                compareString(acutalOutputString, expectedOutputString, getMismatchPath(actualOutput));
        if(mismatch){
            List<String> mismatchString = readTextFile(mismatchPath);
            mismatchString.forEach(OutputWriter::writeMessageOnNewLine);
        } else {
            OutputWriter.writeMessageOnNewLine("Files are identical. There are no mismatches.");
        }
    }

    /* Return the path to the file mismatch.txt in the same directory as of 'expectedOutput' */
    private static String getMismatchPath(String expectedOutput){
        int index = expectedOutput.lastIndexOf('\\');
        String directoryPath = expectedOutput.substring(0, index);
        return directoryPath + "\\mismatch.txt";
    }

    /* read text file if exist and return a list of lines */
    private static List<String> readTextFile(String filePath) {
        List<String> text = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line = reader.readLine();
            while(line != null){
                text.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            OutputWriter.writeMessageOnNewLine("File not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    /* Check each line from both files, generate mismatch.txt and return boolean */
    private static boolean compareString(
            List<String> actualOutputString,
            List<String> expectedOutputString,
            String missmatchPath){
        OutputWriter.writeMessageOnNewLine("Comparing files...");
        String output = "";
        boolean isMismatch = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(missmatchPath))){
            for (int i = 0; i < expectedOutputString.size(); i++) {
                String actualLine = actualOutputString.get(i);
                String expectedLine = expectedOutputString.get(i);
                if(!actualLine.equals(expectedLine)){
                    output = String.format("mismatch -> expected{%s}, actual{%s}%n", expectedLine, actualLine);
                    isMismatch = true;
                    writer.write(output);
                } else {
                    output = String.format("line match -> %s%n", actualLine);
                }

            }
            writer.close();
        } catch (IOException ioe) {
            OutputWriter.displayException("Error.");
        }
        return isMismatch;
    }
}
