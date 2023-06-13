package com.amirheshmati.javaproj;

import com.amirheshmati.javaproj.models.Lesson;
import com.amirheshmati.javaproj.models.Student;
import com.amirheshmati.javaproj.models.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentController {


    @FXML
    private TextField getlesson_id;
    @FXML
    private TextField find_name;

    @FXML
    void getLesson(ActionEvent event) {
        if(!getlesson_id.getText().equals("")){
            int num=Integer.parseInt(getlesson_id.getText());

            //check this student get this lesson before
           String data= readFile("student.txt");
            List<Student> list=new ArrayList<>();
            if (!data.equals("")){
                String[] splited=data.split("/");
                for (int i = 0; i < splited.length; i++) {
                    String[] splitedSec=splited[i].split(",");
                    Student student=new Student();
                    student.setCode(Integer.valueOf(splitedSec[0]));
                    student.setName(splitedSec[1]);
                    student.setFamily(splitedSec[2]);
                    student.setH(splitedSec[3]);
                    student.setGender(splitedSec[4]);
                    list.add(student);
                }}
            for (int i = 0; i < list.size(); i++) {
                boolean can_get=true;
                if(list.get(i).getCode()==HelloController.logined_id){
                    Student student=list.get(i);
                    String s=student.getH();
                    String[] splited2=s.split("#");
                    for (int j = 0; j < splited2.length; j++) {
                        if(num==Integer.parseInt(splited2[j])){
                            can_get=false;
                            break;
                        }
                    }

                    if(can_get==false){
                        System.out.println("you cant get this lesson again!");
                    }

                }
            }



        }else {
            System.out.println("enter id!");
        }

    }


    @FXML
    void findLesson(ActionEvent event) {
    if(!find_name.getText().equals("")){
        String txt=find_name.getText();
        List<Lesson>list=getLessons();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getName().contains(txt)){
                System.out.println("lesson id:"+list.get(i).getCode()+"   name: "+list.get(i).getName());
            }
        }

    }
    }

    @FXML
    void findTeacher(ActionEvent event) {
        if(!find_name.getText().equals("")){
            String txt=find_name.getText();
            List<Teacher>list=getTeachers();
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getName().contains(txt)){
                    System.out.println("teacher id:"+list.get(i).getCode()+"   name: "+list.get(i).getName());
                }
            }

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

    List<Lesson> getLessons(){
        List<Lesson>list=new ArrayList<>();
        String data=readFile("lesson.txt");
        if (!data.equals("")){
            String[] splited=data.split("/");
            for (int i = 0; i < splited.length; i++) {
                String[] splitedSec=splited[i].split(",");
                Lesson lesson=new Lesson();
                lesson.setCode(Integer.valueOf(splitedSec[0]));
                lesson.setName(splitedSec[1]);
                lesson.setUnit(Integer.parseInt(splitedSec[2]));
                lesson.setPresentationTime(splitedSec[3]);
                lesson.setPresentationDay(splitedSec[4]);
                list.add(lesson);
            }}
        return list;
    }

    @FXML
    void showNumberOfUnits(ActionEvent event) {
        int id=HelloController.logined_id;
        List<Student>list=getStudents();
        Student student=new Student();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getCode()==id){
                student=list.get(i);
                break;
            }
        }

        String[] splited=student.getH().split("#");
        List<Integer>list_id_lessons=new ArrayList<>();
        for (int i = 0; i < splited.length; i++) {
            list_id_lessons.add(Integer.parseInt(splited[i]));
        }
        int sum=0;
        List<Lesson>lessonList=getLessons();
        for (int i = 0; i < lessonList.size(); i++) {
            for (int j = 0; j < list_id_lessons.size(); j++) {
                if(list_id_lessons.get(j)==lessonList.get(i).getCode()){
                    sum+=lessonList.get(i).getUnit();
                }
            }
        }
        System.out.println("number of units presented: "+sum);


    }


    List<Teacher> getTeachers(){
        List<Teacher>list=new ArrayList<>();
        String data=readFile("teacher.txt");
        if (!data.equals("")){
            String[] splited=data.split("/");
            for (int i = 0; i < splited.length; i++) {
                String[] splitedSec=splited[i].split(",");
                Teacher teacher=new Teacher();
                teacher.setCode(Integer.valueOf(splitedSec[0]));
                teacher.setName(splitedSec[1]);
                teacher.setFamily(splitedSec[2]);
                teacher.setH(splitedSec[3]);
                teacher.setGender(splitedSec[4]);
                list.add(teacher);
            }}

        return list;
    }

    List<Student> getStudents(){
        List<Student>list=new ArrayList<>();
        String data=readFile("student.txt");
        if (!data.equals("")){
            String[] splited=data.split("/");
            for (int i = 0; i < splited.length; i++) {
                String[] splitedSec=splited[i].split(",");
                Student student=new Student();
                student.setCode(Integer.valueOf(splitedSec[0]));
                student.setName(splitedSec[1]);
                student.setFamily(splitedSec[2]);
                student.setH(splitedSec[3]);
                student.setGender(splitedSec[4]);
                list.add(student);
            }}
        return list;
    }

    @FXML
    void calGPA(ActionEvent event) {
        int id=HelloController.logined_id;
        String data=readFile("grade.txt");
        String[]splited= data.split("/");
        int sum=0;
        for (int i = 0; i < splited.length; i++) {
            String[] splited_1=splited[i].split(",");
            if(Integer.parseInt(splited_1[0])==id){
                  sum+=Integer.parseInt(splited_1[2]);
            }
        }
        float avg=(float)sum/(splited.length-1);
        System.out.println("GPA: "+ avg);
    }



}
