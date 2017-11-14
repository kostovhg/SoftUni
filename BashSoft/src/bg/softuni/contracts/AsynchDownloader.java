package bg.softuni.contracts;

public interface AsynchDownloader extends Downloader {
    void downloadOnNewThread(String fileUrl);
}
