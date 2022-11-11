package models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProjetoTableModel extends AbstractTableModel {

    private String[] labels = {"ID","Título","Area","Cidade","Estado"};
    private ArrayList<ProjetoModel> projetos;

    public ProjetoTableModel(ArrayList<ProjetoModel> projetos) {
        this.projetos = projetos;
    }

    @Override
    public int getRowCount() {
        return projetos.size();
    }

    @Override
    public int getColumnCount() {
        return labels.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex){
            case 0 -> projetos.get(rowIndex).getId();
            case 1 -> projetos.get(rowIndex).getTitulo();
            case 2 -> projetos.get(rowIndex).getArea();
            case 3 -> projetos.get(rowIndex).getCidade();
            case 4 -> projetos.get(rowIndex).getEstado();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return labels[column];
    }
    public void setProjetos(ArrayList<ProjetoModel> projetos){
        this.projetos =projetos;
    }


}
