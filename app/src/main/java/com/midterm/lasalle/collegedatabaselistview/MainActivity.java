package com.midterm.lasalle.collegedatabaselistview;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import model.Employee;
import model.Person;
import model.Student;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener,
DialogInterface.OnClickListener{

    EditText editTextCaption, editTextId,editTextAge, editTextJob, editTextSalary, editTextProgram;
    ListView listViewES;
    Button btnStudents, btnEmployees, btnAll;
    TextView textViewId;

    ArrayList<Student> lisOfStudents;
    ArrayList<Employee> listOfEmployees;
    ArrayList<Person> listOfPersons;
    ArrayAdapter<Student> adapterStudent;
    ArrayAdapter<Employee> adapterEmployee;
    ArrayAdapter<Person> adapterPerson;

    String flag = "a";

    AlertDialog.Builder alertDialog;

    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        editTextCaption = findViewById(R.id.editTextCaption);
        editTextId = findViewById(R.id.editTextId);
        editTextAge = findViewById(R.id.editTextAge);
        editTextJob = findViewById(R.id.editTextJob);
        editTextSalary = findViewById(R.id.editTextSalary);
        editTextProgram = findViewById(R.id.editTextProgram);
        textViewId = findViewById(R.id.textViewId);
        listViewES = findViewById(R.id.listViewES);
        btnStudents = findViewById(R.id.btnStudents);
        btnEmployees = findViewById(R.id.btnEmployees);
        btnAll = findViewById(R.id.btnAll);
        btnStudents.setOnClickListener(this);
        btnEmployees.setOnClickListener(this);
        listViewES.setOnItemClickListener(this);
        listViewES.setOnItemLongClickListener(this);
        btnAll.setOnClickListener(this);

        lisOfStudents = new ArrayList<Student>();
        listOfEmployees = new ArrayList<Employee>();
        listOfPersons = new ArrayList<Person>();

        Student s1 = new Student("Sara", 37, 143, "Mobile Application"),
                s2 = new Student("Morteza", 33, 714, "3DMax"),
                s3 = new Student("Larry", 29, 890, "MBA");
        lisOfStudents.add(s1); lisOfStudents.add(s2); lisOfStudents.add(s3);
        adapterStudent = new ArrayAdapter<Student>(this, R.layout.one_item, lisOfStudents);


        Employee e1 = new Employee("Fido", 30, 765, "Manager", 3500),
                e2 = new Employee("Magie", 50, 657, "Secretary", 2500),
                e3 = new Employee("Philip", 35, 200, "Office Manager", 2000);
        listOfEmployees.add(e1); listOfEmployees.add(e2); listOfEmployees.add(e3);
        adapterEmployee = new ArrayAdapter<Employee>(this, R.layout.one_item, listOfEmployees);

        listOfPersons.add(s1); listOfPersons.add(s2); listOfPersons.add(s3); listOfPersons.add(e1); listOfPersons.add(e2); listOfPersons.add(e3);
        adapterPerson = new ArrayAdapter<>(this, R.layout.one_item, listOfPersons);


        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Delete?");
        alertDialog.setMessage("Do you want to delete? (Y/N)");
        alertDialog.setPositiveButton("Yes", this);
        alertDialog.setNegativeButton("No", this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnStudents:
                editTextId.setText(null);
                editTextAge.setText(null);
                editTextJob.setVisibility(EditText.INVISIBLE);
                editTextSalary.setVisibility(EditText.INVISIBLE);
                editTextProgram.setVisibility(EditText.VISIBLE);
                editTextProgram.setText(null);
                editTextCaption.setText("Student");
                textViewId.setText("Student ID: ");
                listViewES.setAdapter(adapterStudent);
                adapterStudent.notifyDataSetChanged();
                flag = "s";
                break;

            case R.id.btnEmployees:
                editTextId.setText(null);
                editTextAge.setText(null);
                editTextJob.setVisibility(EditText.VISIBLE);
                editTextJob.setText(null);
                editTextSalary.setVisibility(EditText.VISIBLE);
                editTextSalary.setText(null);
                editTextProgram.setVisibility(EditText.INVISIBLE);
                editTextCaption.setText("Employee");
                textViewId.setText("Emp. ID: ");
                listViewES.setAdapter(adapterEmployee);
                adapterEmployee.notifyDataSetChanged();
                flag = "e";
                break;

            case R.id.btnAll:
                editTextId.setText(null);
                editTextAge.setText(null);
                editTextJob.setVisibility(EditText.VISIBLE);
                editTextJob.setText(null);
                editTextSalary.setVisibility(EditText.VISIBLE);
                editTextSalary.setText(null);
                editTextProgram.setVisibility(EditText.VISIBLE);
                editTextProgram.setText(null);
                editTextCaption.setText("All Information");
                textViewId.setText("ID : ");
                listViewES.setAdapter(adapterPerson);
                adapterPerson.notifyDataSetChanged();
                flag = "a";
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        int index = position;

          if (flag.equals("a") && listOfPersons.get(position).getClass().equals(Student.class)) {
            for (int i = 0; i < lisOfStudents.size(); i++) {
                if (lisOfStudents.get(i).getName().equals(listOfPersons.get(position).getName()))
                    index = i;
            }
            getStudent(index);

        } else if(flag.equals("a") && listOfPersons.get(position).getClass().equals(Employee.class)){
              for(int i = 0; i < listOfEmployees.size(); i ++){
                  if(listOfEmployees.get(i).getName().equals(listOfPersons.get(position).getName()))
                      index = i;
              }
              getEmployee(index);
          }else if (flag.equals("s")){
              getStudent(position);
          }else if (flag.equals("e")){
              getEmployee(position);
          }
    }

        public void getStudent(int index){
            String sName = lisOfStudents.get(index).getName();
            int sAge = lisOfStudents.get(index).getAge();
            int sId = lisOfStudents.get(index).getStudentId();
            String sProgram = lisOfStudents.get(index).getProgram();

            editTextId.setText(String.valueOf(sId));
            editTextAge.setText(String.valueOf(sAge));
            editTextJob.setVisibility(EditText.INVISIBLE);
            editTextSalary.setVisibility(EditText.INVISIBLE);
            editTextProgram.setVisibility(EditText.VISIBLE);
            editTextProgram.setText(sProgram);
        }

        public void getEmployee(int index){
            String eName = listOfEmployees.get(index).getName();
            int eAge = listOfEmployees.get(index).getAge();
            int eId = listOfEmployees.get(index).getEmployeeId();
            String eJob = listOfEmployees.get(index).getJob();
            double eSalary = listOfEmployees.get(index).getSalary();

            editTextId.setText(String.valueOf(eId));
            editTextAge.setText(String.valueOf(eAge));
            editTextJob.setVisibility(EditText.VISIBLE);
            editTextJob.setText(eJob);
            editTextSalary.setVisibility(EditText.VISIBLE);
            editTextSalary.setText(String.valueOf(eSalary));
            editTextProgram.setVisibility(EditText.INVISIBLE);
        }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        currentPosition = position;
        AlertDialog alertBox = alertDialog.create();
        alertBox.show();
        return false;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        switch (which){

            case Dialog.BUTTON_POSITIVE:

               String caption = editTextCaption.getText().toString();

               switch (caption){

                   case "Student":
                       lisOfStudents.remove(currentPosition);
                       adapterStudent.notifyDataSetChanged();
                       listOfPersons.remove(currentPosition);
                       adapterPerson.notifyDataSetChanged();
                       break;

                   case "Employee":
                       listOfEmployees.remove(currentPosition);
                       adapterEmployee.notifyDataSetChanged();
                       listOfPersons.remove(currentPosition);
                       adapterPerson.notifyDataSetChanged();
                       break;

                   case "All Information":
                       for (int i = 0; i < lisOfStudents.size(); i++) {
                           if (lisOfStudents.get(i).getName().equals(listOfPersons.get(currentPosition).getName())) {
                               lisOfStudents.remove(i);
                               adapterStudent.notifyDataSetChanged();
                               listOfPersons.remove(currentPosition);
                               adapterPerson.notifyDataSetChanged();
                           }else if (listOfEmployees.get(i).getName().equals(listOfPersons.get(currentPosition).getName())){
                               listOfEmployees.remove(i);
                               adapterEmployee.notifyDataSetChanged();
                               listOfPersons.remove(currentPosition);
                               adapterPerson.notifyDataSetChanged();
                           }
                       }
                       break;
               }
                break;

            case Dialog.BUTTON_NEGATIVE:
                break;

        }

    }
}
