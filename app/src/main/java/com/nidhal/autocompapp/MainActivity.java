package com.nidhal.autocompapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lst;
    String[] allStudents = {"Sara", "Samira", "Sami"};
    String[] allTopics = {"Android", "Angular", "UX", "Databases", "C++", "Big Data"};

    String selectedStudent;

    //1st Version
    String[] NotesSara = {"12", "7", "3.5", "15", "8", "10.5"};
    String[] NotesSamira = {"2", "4", "18.5", "16", "18", "5.75"};
    String[] NotesSami = {"11", "17", "5.5", "10", "2", "10.25"};

    //2nd Version
    String[][] NotesAll = {
            {"12", "7", "3.5", "15", "8", "10.5"},
            {"2", "4", "18.5", "16", "18", "5.75"},
            {"11", "17", "5.5", "10", "2", "10.25"}
    };

    AutoCompleteTextView autoSaisie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lst = findViewById(R.id.notesLst);

        this.autoSaisie = findViewById(R.id.saisieAuto);
        ArrayAdapter ar = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, this.allStudents);
        autoSaisie.setAdapter(ar);

        autoSaisie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedStudent = ((TextView) view).getText().toString();

                //ArrayAdapter<String> adapter;
                MyAdapter adapter;
                if (selectedStudent.equals("Sara")) {
                    //adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                      //      NotesSara);
                    adapter = new MyAdapter(MainActivity.this, NotesSara);

                    //2nd Version
                    //adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                    //NotesAll[0]);
                } else if (selectedStudent.equals("Samira")) {
                    //adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                        //    NotesSamira);
                    adapter = new MyAdapter(MainActivity.this, NotesSamira);

                    //2nd Version
                    //adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                    //NotesAll[1]);
                } else {
                    //adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                           // NotesSami);

                    adapter = new MyAdapter(MainActivity.this, NotesSami);

                    //2nd Version
                    //adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                    //NotesAll[2]);
                }

                lst.setAdapter(adapter);

                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedTopic = allTopics[position];
                        TextView txt = (TextView) view.findViewById(R.id.note);

                        Double note = Double.parseDouble(txt.getText().toString());
                        if (note > 10)
                            Toast.makeText(getApplicationContext(), selectedTopic + ": Pass", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), selectedTopic + ": Fail", Toast.LENGTH_LONG).show();
                    }
                });



            }
        });
    }
}
