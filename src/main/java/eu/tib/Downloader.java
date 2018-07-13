package eu.tib;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import java.io.*;
import java.util.ArrayList;

/*
@author  Qazi Asim Ijaz Ahmad
@E-mail asim.ahmad@tib.eu
*/

public class Downloader {
    private static UrlResponse navigateUrl(String uri, StringTemplates template){

        UrlResponse response= new UrlResponse();
        try {
            response.setResponse(Jsoup.connect(uri)
                    .ignoreContentType(true)
                    .maxBodySize(0)
                    .userAgent(template.getProperty("USER_AGENT"))
                    .timeout(70000 * 10)
                    .execute());
        } catch (Exception e) {
            template.printOnConsole("ERROR_DOWNLOADING_MESSAGE");
            response.setUrlIsAccessible(false);
        }
        return response;
    }

    private static void writeErrorFile(ArrayList<String> urlNavigErrorList, StringTemplates template){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(
                    template.getProperty("ERROR_LIST_FILE_DEST"), "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        template.printOnConsole("ERROR_LIST_FILE_GENERATION");
        writer.println(template.getProperty("ERROR_DOWNLOAD") + "\r\n");
        for (String st : urlNavigErrorList) {
            writer.println(st);
        }
        writer.close();
    }



    protected static int downloadAndSave(StringTemplates template) throws IOException {
        BufferedReader br = null;
        UrlResponse urlResponse = null;

        try {
            br = new BufferedReader(new FileReader(template.getProperty("SOURCE_PATH")));
        String uri=null;
        long fileCounter =0;
        ArrayList<String> urlNavigErrorList = new ArrayList<String>();

        while ((uri = br.readLine()) != null) {
            urlResponse = null;

            template.printOnConsole("DOWNLOADING", "\n" + uri);

            urlResponse = navigateUrl(uri, template);

            if(!urlResponse.urlIsAccessible())
            {
                urlNavigErrorList.add(uri);
            }
            else {
                fileCounter++;
                template.setFileName(fileCounter + "");
                template.printOnConsole("DOWNLOADED_FILE_MESSAGE", "\n\n");
                try {
                    FileUtils.writeByteArrayToFile(new File(template.getProperty("DOWNLOADED_FILE_PATH")),
                            urlResponse.getResponse().bodyAsBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            uri = null;
        }

        if(urlNavigErrorList.size() > 0) {
            writeErrorFile(urlNavigErrorList, template);
        }
        } catch (FileNotFoundException e) {
            return -1;
        }
        return 0;
    }

}
