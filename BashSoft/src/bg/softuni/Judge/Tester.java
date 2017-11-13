package bg.softuni.Judge;

import bg.softuni.io.OutputWriter;
import bg.softuni.StaticData.ExceptionMessages;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static bg.softuni.StaticData.ExceptionMessages.*;

public class Tester {

    /* Comparing two text files line by line */
    public static void compareContent(String actualOutput, String expectedOutput) {
        try {
            OutputWriter.writeMessageOnNewLine("Reading files...");
            String mismatchPath = getMismatchPath(expectedOutput);

            List<String> actualOutputString = readTextFile(actualOutput);
            List<String> expectedOutputString = readTextFile(expectedOutput);

            boolean mismatch =
                    compareString(actualOutputString, expectedOutputString, mismatchPath);
            if (mismatch) {
                List<String> mismatchString = readTextFile(mismatchPath);
                mismatchString.forEach(OutputWriter::writeMessageOnNewLine);
            } else {
                OutputWriter.writeMessageOnNewLine("Files are identical. There are no mismatches.");
            }
        } catch (IOException ioe) {
            OutputWriter.displayException(ExceptionMessages.INVALID_PATH);
        }
    }

    /* Return the path to the file mismatch.txt in the same directory as of 'expectedOutput' */
    private static String getMismatchPath(String expectedOutput) {
        int index = expectedOutput.lastIndexOf('\\');
        String directoryPath = expectedOutput.substring(0, index);
        return directoryPath + "\\mismatch.txt";
    }

    /* read text file if exist and return a list of lines */
    private static List<String> readTextFile(String filePath) throws IOException {
        List<String> text = new ArrayList<>();

        File file = new File(filePath);

        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String line = reader.readLine();

        while (line != null) {
            text.add(line);
            line = reader.readLine();
        }

        reader.close();

        return text;
    }

    /* Check each line from both files, generate mismatch.txt and return boolean */
    private static boolean compareString(
            List<String> actualOutputString,
            List<String> expectedOutputString,
            String missmatchPath) {
        OutputWriter.writeMessageOnNewLine("Comparing files...");
        String output = "";
        boolean isMismatch = false;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(missmatchPath));

            int maxLength = Math.max(actualOutputString.size(), expectedOutputString.size());

            for (int i = 0; i < maxLength; i++) {
                String actualLine = actualOutputString.get(i);
                String expectedLine = expectedOutputString.get(i);

                if (!actualLine.equals(expectedLine)) {
                    output = String.format("mismatch -> expected{%s}, actual{%s}%n", expectedLine, actualLine);
                    isMismatch = true;
                    writer.write(output);
                } else {
                    output = String.format("line match -> %s%n", actualLine);
                }

                writer.write(output);
            }
            writer.close();
        } catch (IOException ioe) {
            isMismatch = true;
            OutputWriter.displayException(CANNOT_ACCESS_FILE);
        } catch (IndexOutOfBoundsException ioobe) {
            isMismatch = true;
            OutputWriter.displayException(INVALID_OUTPUT_LENGTH);
        }
        return isMismatch;
    }

    private static void printOutput(String mismatchPath, boolean isMismatch) throws IOException {
        if(isMismatch){
            List<String> mismatchStrings = Files.readAllLines(Paths.get(mismatchPath));
            mismatchStrings.forEach(OutputWriter::writeMessageOnNewLine);
            return;
        }

        OutputWriter.writeMessageOnNewLine("Files are identical. There are no mismatches.");
    }
}
