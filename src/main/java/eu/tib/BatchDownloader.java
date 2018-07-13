package eu.tib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
@author  Qazi Asim Ijaz Ahmad
@E-mail asim.ahmad@tib.eu
*/

public class BatchDownloader {

    public static void main(String [ ] args) throws IOException {

        StringTemplates templates = new StringTemplates();
        templates.printOnConsole("STARTUP_MESSAGE");
        BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
        char opt = 'n';
        String inputFile = "";
        String destPath ="";
        String ext = "";
        templates.printOnConsole("MENU");
        System.out.print("Enter option: ");

        while(opt != 'e')
        {
            opt=brInput.readLine().charAt(0);

            switch (opt){
                case '1':
                    System.out.print("Enter the path to the file list (e.g., /urls2download.txt) : ");
                    inputFile = brInput.readLine();
                    break;
                case '2':
                    System.out.print("Enter the destination path (e.g., /DownloadedData) : ");
                    destPath = brInput.readLine();
                    break;
                case '3':
                    System.out.print("Enter the file extension/format of the files to be downloaded " +
                            "(e.g., .pdf,.txt, etc.) : ");
                    ext= brInput.readLine();
                    break;
                case '4':
                    if(inputFile.compareTo("") != 0 && destPath.compareTo("")!= 0 && ext.compareTo("") != 0)
                    {
                        System.out.print("\n\n");
                       templates.updateProperties(inputFile, destPath, ext);
                        if(Downloader.downloadAndSave(templates) == 0)
                            System.out.println("\n\nDownload complete...");
                        else
                            System.out.println("\n\n URL list file is unreachable... ");
                    }
                    else
                        System.out.println("Please fill all the necessary fields to start downloading... ");
                    break;
                case 'e':
                    break;
                 default:
                     break;

            }
            if(opt!='e')
            {
                templates.updateProperties(inputFile,destPath,ext);
                templates.printOnConsole("MENU");
                System.out.print("Enter option: ");
            }
            else
                System.out.println("Exiting Application...");
        }


    }
}
