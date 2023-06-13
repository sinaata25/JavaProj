package com.amirheshmati.javaproj;

import com.amirheshmati.javaproj.models.Lesson;
import com.amirheshmati.javaproj.models.Student;
import com.amirheshmati.javaproj.models.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherController {

    @FXML
    private TextField id_lesson;
    @FXML
    private TextField id_name;
    @FXML
    private TextField id_unit;
    @FXML
    private TextField id_p_time;
    @FXML
    private TextField id_p_day;
    @FXML
    private TextField edit_id;
    @FXML
    private TextField edit_name;
    @FXML
    private TextField remove_id;
    @FXML
    private TextField teacher_id;
    @FXML
    private TextField teacher_family;
    @FXML
    private TextField teacher_name;
    @FXML
    private TextField teacher_lesson;
    @FXML
    private TextField teacher_gender;
    @FXML
    private TextField teacher_edit_id;
    @FXML
    private TextField teacher_edit_name;
    @FXML
    private TextField teacher_remove_id;
    @FXML
    private TextField stu_id1;
    @FXML
    private TextField stu_family1;
    @FXML
    private TextField stu_name1;
    @FXML
    private TextField stu_lesson1;
    @FXML
    private TextField stu_gender1;
    @FXML
    private TextField stu_edit_id1;
    @FXML
    private TextField stu_edit_name1;
    @FXML
    private TextField stu_remove_id1;



    @FXML
    void addLesson(ActionEvent event) {
        if(!id_lesson.getText().equals("") && !id_name.getText().equals("") && !id_unit.getText().equals("") &&
                !id_p_time.getText().equals("") && !id_p_day.getText().equals("") ){
            Lesson lesson=new Lesson();
            lesson.setCode(Integer.parseInt(id_lesson.getText()));
            lesson.setName(id_name.getText());
            lesson.setUnit(Integer.parseInt(id_unit.getText()));
            lesson.setPresentationTime(id_p_time.getText());
            lesson.setPresentationDay(id_p_day.getText());
            append_lesson(lesson);
            System.out.println("lesson added successfuly");
        }else {
            System.out.println("fill all blanks");
        }
    }

    @FXML
    void editLessons(ActionEvent event) {
        if(!edit_id.getText().equals("") && !edit_name.getText().equals("") ){

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
                }
                for (int i = 0; i < list.size(); i++) {
                    if(Integer.parseInt(edit_id.getText())==list.get(i).getCode()){
                        list.get(i).setName(edit_name.getText());
                    }
                }
                try {
                   write_lesson(list);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            System.out.println("edited successfully!");

        }else {
            System.out.println("fill all blanks");
        }
    }

    @FXML
    void removeLesson(ActionEvent event) {
    if(!remove_id.getText().equals("")){
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
            }
            for (int i = 0; i < list.size(); i++) {
                if(Integer.parseInt(remove_id.getText())==list.get(i).getCode()){
                    list.remove(i);
                }
            }
            try {
                write_lesson(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        System.out.println("removed successfully!");
    }else {
        System.out.println("enter id!");
    }


    }

    @FXML
    void showLessons(ActionEvent event){

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

        for(int i=0;i<list.size();i++){
            Lesson lesson=list.get(i);
            System.out.println("id:"+lesson.getCode()+"     name:"+lesson.getName()+"     unit:"+lesson.getUnit()+"     p_time:"+lesson.getPresentationTime()+"     p_day:"+lesson.getPresentationDay());
        }




    }


    @FXML
    void addTeacher(ActionEvent event) {
        if(!teacher_id.getText().equals("") && !teacher_name.getText().equals("") && !teacher_family.getText().equals("") &&
                !teacher_gender.getText().equals("") && !teacher_lesson.getText().equals("") ){
                Teacher teacher=new Teacher();
                teacher.setCode(Integer.parseInt(teacher_id.getText()));
                teacher.setName(teacher_name.getText());
                teacher.setFamily(teacher_family.getText());
                teacher.setGender(teacher_gender.getText());

                String[] splited=teacher_lesson.getText().split("#");
                List<Integer>list_lessons=new ArrayList<>();
            for (int i = 0; i < splited.length; i++) {
                list_lessons.add(Integer.parseInt(splited[i]));
            }
                teacher.setListLessonsPresentation(list_lessons);

                append_teacher(teacher);
            System.out.println("teacher added successfuly");
        }else {
            System.out.println("fill all blanks");
        }
    }


    @FXML
    void removeTeacher(ActionEvent event) {
        if(!teacher_remove_id.getText().equals("")){
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
                }
                for (int i = 0; i < list.size(); i++) {
                    if(Integer.parseInt(teacher_remove_id.getText())==list.get(i).getCode()){
                        list.remove(i);
                    }
                }
                try {
                    write_teacher(list);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            System.out.println("removed successfully!");
        }else {
            System.out.println("enter id!");
        }

    }
    @FXML
    void editTeacher(ActionEvent event) {
        if(!teacher_edit_id.getText().equals("") && !teacher_edit_name.getText().equals("") ){
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
                }
                for (int i = 0; i < list.size(); i++) {
                    if(Integer.parseInt(teacher_edit_id.getText())==list.get(i).getCode()){
                        list.get(i).setName(teacher_edit_name.getText());
                    }
                }
                try {
                    write_teacher(list);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            System.out.println("edited successfully!");

        }else {
            System.out.println("fill all blanks");
        }
    }


    @FXML
    void showTeacher(ActionEvent event) {
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
        for (int i = 0; i < list.size(); i++) {
            Teacher teacher=list.get(i);
            String m=teacher.getH().replace('#',' ');
            System.out.println("id: "+teacher.getCode()+"   name:"+teacher.getName()+"   family:"+teacher.getFamily()+"   presentatin lessons id:"+m+"   gender:"+teacher.getGender());
        }

    }




    @FXML
    void showStu(ActionEvent event) {
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
        for (int i = 0; i < list.size(); i++) {
            Student student=list.get(i);
            String m=student.getH().replace('#',' ');
            System.out.println("id: "+student.getCode()+"   name:"+student.getName()+"   family:"+student.getFamily()+"   lessons id:"+m+"   gender:"+student.getGender());
        }
    }
    @FXML
    void removeStu(ActionEvent event) {
        if(!stu_remove_id1.getText().equals("")){
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
                }
                for (int i = 0; i < list.size(); i++) {
                    if(Integer.parseInt(stu_remove_id1.getText())==list.get(i).getCode()){
                        list.remove(i);
                    }
                }
                try {
                    write_student(list);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            System.out.println("removed successfully!");
        }else {
            System.out.println("enter id!");
        }

    }

    @FXML
    void editStu(ActionEvent event) {
        if(!stu_edit_id1.getText().equals("") && !stu_edit_name1.getText().equals("") ){
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
                }
                for (int i = 0; i < list.size(); i++) {
                    if(Integer.parseInt(stu_edit_id1.getText())==list.get(i).getCode()){
                        list.get(i).setName(stu_edit_name1.getText());
                    }
                }
                try {
                    write_student(list);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            System.out.println("edited successfully!");

        }else {
            System.out.println("fill all blanks");
        }
    }

    @FXML
    void addstudent(ActionEvent event) {
        if(!stu_id1.getText().equals("") && !stu_name1.getText().equals("") && !stu_family1.getText().equals("") &&
                !stu_gender1.getText().equals("") && !stu_lesson1.getText().equals("") ){
            Student student=new Student();
            student.setCode(Integer.parseInt(stu_id1.getText()));
            student.setName(stu_name1.getText());
            student.setFamily(stu_family1.getText());
            student.setGender(stu_gender1.getText());

            String[] splited=stu_lesson1.getText().split("#");
            List<Integer>list_lessons=new ArrayList<>();
            for (int i = 0; i < splited.length; i++) {
                list_lessons.add(Integer.parseInt(splited[i]));
            }
            student.setListLessons(list_lessons);
            append_student(student);
            System.out.println("student added successfuly");
        }else {
            System.out.println("fill all blanks");
        }
    }

    public void write_lesson(List<Lesson>list) throws IOException {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str+=list.get(i).getCode()+","+list.get(i).getName()+","+list.get(i).getUnit()+","+list.get(i).getPresentationTime()+","+list.get(i).getPresentationDay()+"/";
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("lesson.txt"));
        writer.write(str);
        writer.close();
    }
    public void write_student(List<Student>list) throws IOException {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str+=list.get(i).getCode()+","+list.get(i).getName()+","+list.get(i).getFamily()+","+list.get(i).getH()+","+list.get(i).getGender()+"/";
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("student.txt"));
        writer.write(str);
        writer.close();
    }
    public void write_teacher(List<Teacher>list) throws IOException {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str+=list.get(i).getCode()+","+list.get(i).getName()+","+list.get(i).getFamily()+","+list.get(i).getH()+","+list.get(i).getGender()+"/";
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("teacher.txt"));
        writer.write(str);
        writer.close();
    }

    public void append_lesson(Lesson lesson){
        try
        {
            FileWriter fw = new FileWriter("lesson.txt",true); //the true will append the new data
            fw.write(lesson.getCode()+","+lesson.getName()+","+lesson.getUnit()+","+lesson.getPresentationTime()+","+lesson.getPresentationDay()+"/");
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }


    public void append_teacher(Teacher teacher){
        String h="";
        try {
            for (int i = 0; i < teacher.getListLessonsPresentation().size(); i++) {
                if(i==teacher.getListLessonsPresentation().size()-1){
                    h+=teacher.getListLessonsPresentation().get(i);
                }else {
                    h+=teacher.getListLessonsPresentation().get(i)+"#";
                }

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try
        {
            FileWriter fw = new FileWriter("teacher.txt",true); //the true will append the new data
            fw.write(teacher.getCode()+","+teacher.getName()+","+teacher.getFamily()+","+h+","+teacher.getGender()+"/");
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }


    public void append_student(Student student){
        String h="";
        try {
            for (int i = 0; i < student.getListLessons().size(); i++) {
                if(i==student.getListLessons().size()-1){
                    h+=student.getListLessons().get(i);
                }else {
                    h+=student.getListLessons().get(i)+"#";
                }

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try
        {
            FileWriter fw = new FileWriter("student.txt",true); //the true will append the new data
            fw.write(student.getCode()+","+student.getName()+","+student.getFamily()+","+h+","+student.getGender()+"/");
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
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





}
