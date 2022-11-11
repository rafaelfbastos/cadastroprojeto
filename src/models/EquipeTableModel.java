package models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class EquipeTableModel extends AbstractTableModel {

    private String[] labels = {"Projeto", "Matrícula", "Nome", "Curso", "E-mail", "Telefone"};
    private ArrayList<EquipeQueryModel> equipe;

    public EquipeTableModel(ArrayList<EquipeQueryModel> equipe) {
        this.equipe = equipe;
    }

    @Override
    public int getRowCount() {
        return equipe.size();
    }

    @Override
    public int getColumnCount() {
        return labels.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> equipe.get(rowIndex).getProjeto();
            case 1 -> equipe.get(rowIndex).getMatricula();
            case 2 -> equipe.get(rowIndex).getNome();
            case 3 -> equipe.get(rowIndex).getCurso();
            case 4 -> equipe.get(rowIndex).getEmail();
            case 5 -> equipe.get(rowIndex).getTelefone();
            default -> null;
        };
    }

    public String getColumnName(int column) {

        return labels[column];
    }
}
