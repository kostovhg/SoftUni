package bg.softuni.Network;

import bg.softuni.contracts.AsynchDownloader;
import bg.softuni.contracts.Downloader;
import bg.softuni.exceptions.InvalidPathException;
import bg.softuni.io.OutputWriter;
import bg.softuni.StaticData.ExceptionMessages;
import bg.softuni.StaticData.SessionData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadManager implements AsynchDownloader {

    // this function downloads not in main directory but with subdirectory /downloads
    public void download(String fileUrl) {

        URL url;
        ReadableByteChannel rbc = null;
        FileOutputStream fos = null;

        try {
            if (Thread.currentThread().getName().equals("main")) {
                OutputWriter.writeMessageOnNewLine("Started downloading..");
            }
            url = new URL(fileUrl);
            rbc = Channels.newChannel(url.openStream());
            String fileName = extractNameOfFile(url.toString());
            File file = new File(SessionData.currentPath + "/Downloads/" + fileName);
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
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (rbc != null) {
                    try {
                        rbc.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void downloadOnNewThread(String fileUrl) {
        Thread thread = new Thread(() -> download(fileUrl));
        OutputWriter.writeMessageOnNewLine(String.format(
                "Worker thread %d started download...", thread.getId()));
        // thread.setDaemon(false);
        SessionData.threadPool.add(thread);
        thread.start();
    }

    private String extractNameOfFile(String fileUrl) throws InvalidPathException {
        int indexOfLastSlash = fileUrl.lastIndexOf('/');
        if (indexOfLastSlash == -1) {
            throw new InvalidPathException();
        }
        return fileUrl.substring(indexOfLastSlash + 1);
    }
}
