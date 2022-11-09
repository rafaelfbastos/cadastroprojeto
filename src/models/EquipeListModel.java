package models;

import javax.swing.*;
import java.util.ArrayList;

public class EquipeListModel extends AbstractListModel {

    private ArrayList<AlunoModel> alunos;

    public EquipeListModel(ArrayList<AlunoModel> alunos) {
        this.alunos = alunos;
    }

    @Override
    public int getSize() {
        return alunos.size();
    }

    @Override
    public Object getElementAt(int index) {
        return alunos.get(index);
    }
}
