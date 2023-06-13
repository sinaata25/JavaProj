package com.amirheshmati.javaproj;

import com.amirheshmati.javaproj.models.Grade;
import com.amirheshmati.javaproj.models.Lesson;
import com.amirheshmati.javaproj.models.Student;
import com.amirheshmati.javaproj.models.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.*;

public class Teacher_NonAdmin {

    @FXML
    private TextField present_lesson_id;
    @FXML
    private TextField grading_student_id;
    @FXML
    private TextField grading_lesson_id;
    @FXML
    private TextField grading_student_score;
    @FXML
    private TextField maximum_lesson_id;
    @FXML
    private TextField not_passed_id;
    @FXML
    private TextField name_find;
    @FXML
    private TextField gpa_lesson_id;


    @FXML
    void Present(ActionEvent event) throws IOException {
            if(!present_lesson_id.getText().equals("")){
                Teacher logined_teacher=new Teacher();
                int logined_num = 0;
                List<Teacher> list=new ArrayList<>();
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
                        if(teacher.getCode()==HelloController.logined_id){
                            logined_teacher=teacher;
                            logined_num=i;
                        }
                        list.add(teacher);
                    }}

                    boolean can=true;
                for (int i = 0; i < list.size(); i++) {
                    Teacher teacher=list.get(i);
                    String[] splited=teacher.getH().split("#");
                    for (int j = 0; j < splited.length; j++) {
                        if(present_lesson_id.getText().equals(splited[j])){
                            can=false;
                            break;
                        }
                    }

                    if(can==false){
                        System.out.println("you cant present this lesson!because this lesson was presented before");
                        break;
                    }

                }

                List<Integer>list_lessons=new ArrayList<>();
                if(can==true){
                    String h=list.get(logined_num).getH();
                    String[] splited=h.split("#");
                    for (int j = 0; j < splited.length; j++) {
                        list_lessons.add(Integer.valueOf(splited[j]));
                    }
                    list_lessons.add(Integer.parseInt(present_lesson_id.getText()));
                    String m="";
                    for (int j = 0; j < list_lessons.size(); j++) {
                        m+=list_lessons.get(j)+"#";
                    }
                    list.get(logined_num).setH(m);
                    write_teacher(list);
                    System.out.println("presented successfully");
                }





            }
    }




    @FXML
    void Grade(ActionEvent event) {
            if(!grading_lesson_id.getText().equals("") && !grading_student_id.getText().equals("") && !grading_student_score.getText().equals("") ){
                Grade grade=new Grade();
                grade.setGrade(Integer.parseInt(grading_student_score.getText()));
                grade.setId_student(Integer.parseInt(grading_student_id.getText()));
                grade.setId_lesson(Integer.parseInt(grading_lesson_id.getText()));
                append_grade(grade);
                System.out.println("graded successfully");
            }else {
                System.out.println("fill all blanks!");
            }
    }



    @FXML
    void sortingAsGrade(ActionEvent event) {
        List<Grade>list=new ArrayList<>();
        String data=readFile("grade.txt");
        String[] splited=data.split("/");
        for (int i = 0; i < splited.length; i++) {
            Grade grade=new Grade();
            String[] splited_1= splited[i].split(",");
            grade.setId_student(Integer.parseInt(splited_1[0]));
            grade.setId_lesson(Integer.parseInt(splited_1[1]));
            grade.setGrade(Integer.parseInt(splited_1[2]));
            list.add(grade);
        }


        for (int i = 0; i < list.size() - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < list.size(); j++){
                if (list.get(j).getGrade() < list.get(index).getGrade()){
                    index = j;//searching for lowest index
                }
            }
            int smallerNumber = list.get(index).getGrade();
           list.get(index).setGrade(list.get(i).getGrade());
            list.get(i).setGrade(smallerNumber);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println("student id: "+list.get(i).getId_student()+"    Grade: "+list.get(i).getGrade());
        }




    }


    @FXML
    void showNumberOfUnits(ActionEvent event) {
        int id=HelloController.logined_id;
        List<Teacher>list=getTeachers();
        Teacher teacher=new Teacher();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getCode()==id){
                teacher=list.get(i);
                break;
            }
        }

        String[] splited=teacher.getH().split("#");
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



    @FXML
    void maximumStudent(ActionEvent event) {
        if(!maximum_lesson_id.getText().equals("")){
            int txt=Integer.parseInt(maximum_lesson_id.getText());
            List<Grade>list=new ArrayList<>();
            String data=readFile("grade.txt");
            String[] splited=data.split("/");
            for (int i = 0; i < splited.length; i++) {
                Grade grade=new Grade();
                String[] splited_1= splited[i].split(",");
                grade.setId_student(Integer.parseInt(splited_1[0]));
                grade.setId_lesson(Integer.parseInt(splited_1[1]));
                grade.setGrade(Integer.parseInt(splited_1[2]));
                list.add(grade);
            }


            List<Grade>list_selected=new ArrayList<>();

            for (int i = 0; i < list.size(); i++) {
                Grade grade=list.get(i);
                if(grade.getId_lesson()==txt){
                    list_selected.add(grade);
                }
            }

            List<Grade>mlist=sort(list_selected);
            Grade max_choosed_grade=mlist.get(mlist.size()-1);
            List<Student>studentList=getStudents();
            for (int i = 0; i < studentList.size(); i++) {
                if(max_choosed_grade.getId_student()==studentList.get(i).getCode()){
                    System.out.println("name: "+studentList.get(i).getName()+" "+studentList.get(i).getFamily()
                    +"   gender: "+studentList.get(i).getGender());
                }
            }

        }else {
            System.out.println("fill all blanks");
        }
    }


    @FXML
    void find(ActionEvent event) {

        if(!name_find.getText().equals("")){
            List<Student>list=getStudents();
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getName().contains(name_find.getText())){
                    System.out.println("userid: "+list.get(i).getCode()+"   name: "+list.get(i).getName());
                }
            }

        }else {
            System.out.println("fill all blanks");
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

    public void write_teacher(List<Teacher>list) throws IOException {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str+=list.get(i).getCode()+","+list.get(i).getName()+","+list.get(i).getFamily()+","+list.get(i).getH()+","+list.get(i).getGender()+"/";
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("teacher.txt"));
        writer.write(str);
        writer.close();
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




    public void append_grade(Grade grade){
        try
        {
            FileWriter fw = new FileWriter("grade.txt",true); //the true will append the new data
            fw.write(grade.getId_student()+","+grade.getId_lesson()+","+grade.getGrade()+"/");
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }




List<Grade> sort(List<Grade>list){

    for (int i = 0; i < list.size() - 1; i++)
    {
        int index = i;
        for (int j = i + 1; j < list.size(); j++){
            if (list.get(j).getGrade() < list.get(index).getGrade()){
                index = j;//searching for lowest index
            }
        }
        int smallerNumber = list.get(index).getGrade();
        list.get(index).setGrade(list.get(i).getGrade());
        list.get(i).setGrade(smallerNumber);
    }
    return list;
}




    @FXML
    void notPassedStudents(ActionEvent event) {

    if(!not_passed_id.getText().equals("")){
        int txt=Integer.parseInt(not_passed_id.getText());
        List<Grade>list=new ArrayList<>();
        String data=readFile("grade.txt");
        String[] splited=data.split("/");
        for (int i = 0; i < splited.length; i++) {
            Grade grade=new Grade();
            String[] splited_1= splited[i].split(",");
            grade.setId_student(Integer.parseInt(splited_1[0]));
            grade.setId_lesson(Integer.parseInt(splited_1[1]));
            grade.setGrade(Integer.parseInt(splited_1[2]));
            list.add(grade);
        }

        List<Grade>list_selected=new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Grade grade=list.get(i);
            if(grade.getId_lesson()==txt){
                list_selected.add(grade);
            }
        }

        for (int i = 0; i < list_selected.size(); i++) {
            if(list_selected.get(i).getGrade()>10){
                list_selected.remove(i);
            }
        }

        List<Student>studentList=getStudents();
        List<Student>finalStulist=new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            for (int j = 0; j < list_selected.size(); j++) {
                if(studentList.get(i).getCode()==list_selected.get(j).getId_student()){
                    finalStulist.add(studentList.get(i));
                    System.out.println("name: "+finalStulist.get(i).getName()+" "+finalStulist.get(i).getFamily()+"   " +
                            "score:   "+list_selected.get(j).getGrade());
                }
            }
        }





    }else {
        System.out.println("fill all blanks");
    }

    }


    @FXML
    void calculateGPA(ActionEvent event) {
        if(!gpa_lesson_id.getText().equals("")){
       String txt= gpa_lesson_id.getText();
            String data=readFile("grade.txt");
            String[]splited= data.split("/");
            int sum=0;
            for (int i = 0; i < splited.length; i++) {
                String[] splited_1=splited[i].split(",");
                if(Integer.parseInt(splited_1[1])==Integer.parseInt(txt)){
                    sum+=Integer.parseInt(splited_1[2]);
                }
            }
            float avg=(float)sum/(splited.length-1);
            System.out.println("class avg is: "+avg);

        }else {
            System.out.println("fill all blanks");
        }
    }



}
