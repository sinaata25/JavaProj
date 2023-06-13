package com.amirheshmati.javaproj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class HelloController {

    public static int logined_id;
    @FXML
    private TextField textview;

    @FXML
    void signStudent(ActionEvent event) throws IOException {
        String txt= textview.getText();
        if(!txt.equals("")){
           if( checkLogin("student.txt",txt)){
               logined_id=Integer.parseInt(txt);
               HelloApplication helloApplication=new HelloApplication();
               helloApplication.stg.start("student.fxml");
           }else {
               System.out.println("wrong code...try again!");
           }
        }else {
            System.out.println("enter some code...");
        }

    }

    @FXML
    void signTeacher(ActionEvent event) throws IOException {
        String txt= textview.getText();
        if(!txt.equals("")){
            if(checkLogin("admin.txt",txt)){
                logined_id=Integer.parseInt(txt);
                HelloApplication helloApplication=new HelloApplication();
                helloApplication.stg.start("teacher.fxml");
            }else {
                System.out.println("wrong code...try again!");
            }
        }else {
            System.out.println("enter some code...");
        }

    }

    @FXML
    void signNonAdminTeacher(ActionEvent event) throws IOException {
        String txt= textview.getText();
        if(!txt.equals("")){
            if(checkLogin("teacher.txt",txt)){
                logined_id=Integer.parseInt(txt);
                HelloApplication helloApplication=new HelloApplication();
                helloApplication.stg.start("teacher_non_admin.fxml");
            }else {
                System.out.println("wrong code...try again!");
            }
        }else {
            System.out.println("enter some code...");
        }
    }

    public String readFile(String filename){
        String data = null;
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }


    boolean checkLogin(String fileName,String code){
        boolean flag=false;
            String data=readFile(fileName);
            if(!data.equals("") || data==null){
                String[] splited=data.split("/");
                for(int i=0;i<splited.length;i++){
                    String[] splitedSec=splited[i].split(",");
                    if(splitedSec[0].equals(code)){
                        flag=true;
                    }
                }
            }else {
                System.out.println("wrong input");
            }
            return flag;
    }

}