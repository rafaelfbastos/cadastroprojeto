package models;

import java.util.ArrayList;

public class EquipeModel {
    private int id;
    private ArrayList<AlunoModel> alunos;

    public EquipeModel(int id, ArrayList<AlunoModel> alunos) {
        this.id = id;
        this.alunos = alunos;
    }

    public  EquipeModel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<AlunoModel> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<AlunoModel> alunos) {
        this.alunos = alunos;
    }
}
