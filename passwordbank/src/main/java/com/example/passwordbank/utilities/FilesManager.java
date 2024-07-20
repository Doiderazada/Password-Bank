package com.example.passwordbank.utilities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import com.example.passwordbank.model.AppUser;

public final class FilesManager {

    private final String filesDirectory = System.getProperty("user.home")+"\\AppData\\Local\\Password-Bank\\";
    private final String userFileName = "User.pbu";
    private final String passFileName = "PassReg.pbp";

    private static AppUser user = null;
    private static LoginList logs = null;
    private File userFile;
    private File passFile;
    public static boolean haveUser;
    public static boolean havePass;



    
    public FilesManager() {
        userFile = new File(filesDirectory, userFileName);
        haveUser = userFile.exists();

        passFile = new File(filesDirectory, passFileName);
        havePass = passFile.exists();
    }





    public AppUser openUserFile() {
        try {
            user = null;
            FileInputStream fileInput = new FileInputStream(userFile);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            AppUser newUser = (AppUser) objectInput.readObject();
 
            objectInput.close();
            fileInput.close();

            user = newUser;
            
        } catch (FileNotFoundException e) {
            System.out.println("The specified file was not found in your system. \n");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("It was not possible to read the Object from the input. \n");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Could not convert the extracted object to the desired Object Type. \n");
            e.printStackTrace();
        }

        return user;
    }

    private void saveUserFile() {
        try {
            FileOutputStream fileOutput = new FileOutputStream(userFile);
            BufferedOutputStream buffOut = new BufferedOutputStream(fileOutput);
            ObjectOutputStream objectOutput = new ObjectOutputStream(buffOut);
            objectOutput.writeObject(user);

            objectOutput.close();
            fileOutput.close();

        } catch (IOException e) {
            System.out.println("It was not possible to write the Object on the output. \n");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }




    public LoginList openLoginsFile() {
        try {
            FileInputStream fileInput = new FileInputStream(passFile);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            logs = (LoginList) objectInput.readObject();

            fileInput.close();
            objectInput.close();

        } catch (FileNotFoundException e) {
            System.out.println("The specified file was not found in your system. \n");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("It was not possible to read the the Object from the input. \n");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Could not convert the extracted object to the desired Object Type. \n");
            e.printStackTrace();
        }

        return logs;
    }

    private void savePassFile() {
        try {
            FileOutputStream fileOutput = new FileOutputStream(passFile);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(logs);

            fileOutput.close();
            objectOutput.close();
            
        } catch (IOException e) {
            System.out.println("It was not possible to write the Object on the output. \n");
        }
    }



    public void deleteFiles() {
        try {
            Files.deleteIfExists(userFile.toPath());
            Files.deleteIfExists(passFile.toPath());
            Files.deleteIfExists(Path.of(filesDirectory));
        } catch (IOException e) {
            System.out.println("Error: An error happened trying to delete the files");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }



    public void closeFiles(AppUser aUser, LoginList logins) {
        user = aUser;
        logs = logins;
        if (!haveUser) {
            File newPath = new File(filesDirectory);
            try   {Files.createDirectory(newPath.toPath());} 
            catch (IOException e) {e.printStackTrace();}
        }
        if (user != null) saveUserFile();
        if (logs != null) savePassFile();
    }
}
