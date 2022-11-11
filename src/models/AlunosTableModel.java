package models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AlunosTableModel extends AbstractTableModel {

    private String[] labels = {"Matricula","Nome","E-mail","Telefone","Curso"};
    private ArrayList<AlunoModel> alunos;

    public AlunosTableModel(ArrayList<AlunoModel> alunos) {
        this.alunos = alunos;
    }

    @Override
    public int getRowCount() {
        return alunos.size();
    }

    @Override
    public int getColumnCount() {
        return labels.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex){
            case 0 -> alunos.get(rowIndex).getMatricula();
            case 1 -> alunos.get(rowIndex).getNome();
            case 2 -> alunos.get(rowIndex).getEmail();
            case 3 -> alunos.get(rowIndex).getTelefone();
            case 4 -> alunos.get(rowIndex).getCurso();
            default -> null;
        };
    }
    @Override
    public String getColumnName(int column) {
        return labels[column];
    }

    public void setAlunos(ArrayList<AlunoModel> alunos) {
        this.alunos = alunos;
    }
}
