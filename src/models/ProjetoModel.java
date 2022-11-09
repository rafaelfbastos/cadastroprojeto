package models;

import java.util.Objects;

public class ProjetoModel {

   private int id;
   private String titulo;
   private String area;
   private String cidade;
   private String estado;
   private String descricao;
   private EquipeModel equipe;

    public ProjetoModel(int id, String titulo, String area, String cidade, String estado, String descricao, EquipeModel equipe) {
        this.id = id;
        this.titulo = titulo;
        this.area = area;
        this.cidade = cidade;
        this.estado = estado;
        this.descricao = descricao;
        this.equipe = equipe;
    }
    public ProjetoModel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EquipeModel getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeModel equipe) {
        this.equipe = equipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjetoModel that = (ProjetoModel) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
