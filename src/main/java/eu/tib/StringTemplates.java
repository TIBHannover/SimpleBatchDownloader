package eu.tib;

import java.util.Properties;

/*
@author  Qazi Asim Ijaz Ahmad
@E-mail asim.ahmad@tib.eu
*/

public class StringTemplates {

    private Properties properties;

    public StringTemplates()
    {
        properties = new Properties();
        properties.setProperty("APP_NAME","Batch File Downloader");
        properties.setProperty("APP_VERSION","0.2");
        properties.setProperty("AUTHOR_NAME", "QAZI ASIM IJAZ AHMAD");
        properties.setProperty("AUTHOR_EMAIL", "asim.ahmad@tib.eu");
        properties.setProperty("ERROR_LIST_FILE_NAME", "log.txt");
        properties.setProperty("SOURCE_PATH", "");
        properties.setProperty("FILE_EXT", "");
        properties.setProperty("DEST_PATH", "");

        properties.setProperty("STARTUP_MESSAGE","\n\n*"+properties.getProperty("APP_NAME")+" Version "
                + properties.getProperty("APP_VERSION")+"*\n\n" +
                "Author: " + properties.getProperty("AUTHOR_NAME")
                + " [" + properties.getProperty("AUTHOR_EMAIL") + "]\n\n" +
                "Warning: \n" +
                "Input URL file [list] must be in UTF8 Encoding\n" +
                "Manually remove empty lines from the URL list\n\n" +
                "Note:\n" +
                "This application will collect the inaccessible URLs and store them in " +
                properties.getProperty("ERROR_LIST_FILE_NAME") + " in the destination folder");

        properties.setProperty("USER_AGENT", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) " +
                "Gecko/20100101 Firefox/25.0");
        properties.setProperty("DOWNLOADING", "Downloading ... ");
        properties.setProperty("ERROR_DOWNLOADING_MESSAGE", "Failed to download file... ");
        properties.setProperty("ERROR_DOWNLOAD", "Downloader could not access the following URLs: ");
        properties.setProperty("DOWNLOADED_FILE_PREFIX", "F");
        initMenu();
    }

    private void initMenu(){
        properties.setProperty("MENU","\n\n*****Menu*****\n1: Select URL List Path ["
                + properties.getProperty("SOURCE_PATH") + "]\n2: Select Output Path ["
                + properties.getProperty("DEST_PATH")
                + "]\n3: Set Download File(s) Extension [" + properties.getProperty("FILE_EXT")
                + "]\n4: Download All Files"
                + "\ne: Exit Application\n\n");
    }

    public void updateProperties(String sourcePath, String destPath, String ext) {
        properties.setProperty("SOURCE_PATH", sourcePath);
        properties.setProperty("FILE_EXT", ext);
        properties.setProperty("DEST_PATH", destPath);
        properties.setProperty("ERROR_LIST_FILE_DEST", properties.getProperty("DEST_PATH")
                + "/" + properties.getProperty("ERROR_LIST_FILE_NAME"));
        properties.setProperty("ERROR_LIST_FILE_GENERATION", "\n\nGenerating "
                + properties.getProperty("ERROR_LIST_FILE_DEST") + " ...");
        initMenu();
    }

    public void printOnConsole(String key){
        System.out.println(properties.getProperty(key));
    }

    public void printOnConsole(String key, String extendedMessage){
        System.out.println(properties.getProperty(key) + extendedMessage);
    }

    public  String getProperty(String key){
        return properties.getProperty(key);
    }

    public void setFileName(String fileName){
        properties.setProperty("DOWNLOADED_FILE_NAME", fileName);
        properties.setProperty("DOWNLOADED_FILE_PATH", properties.getProperty("DEST_PATH") + "/"
                + properties.getProperty("DOWNLOADED_FILE_NAME")+properties.get("FILE_EXT"));
        properties.setProperty("DOWNLOADED_FILE_MESSAGE", "Saving downloaded file as "
                + properties.getProperty("DOWNLOADED_FILE_PATH"));
    }
}
