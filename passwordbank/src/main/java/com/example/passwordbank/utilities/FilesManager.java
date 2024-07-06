package com.example.passwordbank.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.example.passwordbank.model.Login;
import com.example.passwordbank.model.AppUser;

public final class FilesManager {

    private final String filesDirectory = "C:\\Users\\%username%\\AppData\\Local\\Password-Bank\\";
    private final String userFileName = "User.pbu";
    private final String passFileName = "PassReg.pbl";

    private static AppUser user;
    private static Login[] logs;
    private File userFile;
    private File passFile;
    public static boolean haveUser;
    public static boolean havePass;



    
    public FilesManager() {
        userFile = new File(filesDirectory + userFileName);
        haveUser = userFile.exists();

        passFile = new File(filesDirectory + passFileName);
        havePass = passFile.exists();
    }





    public AppUser openUserFile() {
        try {
            FileInputStream fileInput = new FileInputStream(userFile);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            user = (AppUser) objectInput.readObject();

            fileInput.close();
            objectInput.close();
            
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
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(user);

            objectOutput.close();
            fileOutput.close();

        } catch (IOException e) {
            System.out.println("It was not possible to write the Object on the output. \n");
        }
    }




    public Login[] openLoginsFile() {
        try {
            FileInputStream fileInput = new FileInputStream(passFile);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            logs = (Login[]) objectInput.readObject();

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
            FileOutputStream fileOutput = new FileOutputStream(userFile);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(logs);

            objectOutput.close();
            fileOutput.close();
            
        } catch (IOException e) {
            System.out.println("It was not possible to write the Object on the output. \n");
        }
    }



    public void closeFiles(AppUser aUser, Login[] logins) {
        user = aUser;
        logs = logins;
        saveUserFile();
        savePassFile();
    }
}
