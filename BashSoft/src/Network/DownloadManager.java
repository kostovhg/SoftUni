package Network;

import IO.OutputWriter;
import StaticData.ExceptionMessages;
import StaticData.SessionData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadManager {

    public static void download(String fileUrl){

        URL url = null;
        ReadableByteChannel rbc = null;
        FileOutputStream fos = null;

        try {
            if (Thread.currentThread().getName().equals("main")){
                OutputWriter.writeMessageOnNewLine("Started downloading..");
            }
            url = new URL(fileUrl);

            rbc = Channels.newChannel(url.openStream());
            String fileName = extractNameOfFile(url.toString());
            File file = new File(SessionData.currentPath +"/Downloads/" + fileName);
            fos = new FileOutputStream(file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            if (Thread.currentThread().getName().equals("main")) {
                OutputWriter.writeMessageOnNewLine("Download complete..");
            }

        } catch (MalformedURLException e) {
            OutputWriter.writeMessageOnNewLine(ExceptionMessages.INVALID_URL);
            OutputWriter.displayException(e.getMessage());
        } catch (IOException e) {
            OutputWriter.writeMessageOnNewLine(ExceptionMessages.CANT_OPEN_STREAM);
            OutputWriter.displayException(e.getMessage());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (rbc != null ) {
                    rbc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void downloadOnNewThread(String fileUrl) {
        Thread thread = new Thread(() -> download(fileUrl));
        OutputWriter.writeMessageOnNewLine(String.format(
                "Worker thread %d started download...", thread.getId()));
        thread.setDaemon(false);
        thread.start();
    }

    private static String extractNameOfFile(String fileUrl){
        int indexOfLastSlash = fileUrl.lastIndexOf('/');
        if (indexOfLastSlash == -1 ){
            try {
                throw new MalformedURLException(ExceptionMessages.INVALID_PATH);
            } catch (MalformedURLException e) {
                OutputWriter.writeMessageOnNewLine(ExceptionMessages.INVALID_URL);
                OutputWriter.displayException(e.getMessage());
            }
        }
        return fileUrl.substring(indexOfLastSlash + 1);
    }
}
