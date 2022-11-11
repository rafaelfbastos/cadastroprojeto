package models;

public class EquipeQueryModel {
    private String projeto;
    private int matricula;
    private String nome;
    private String email;
    private String curso;
    private String telefone;

    public EquipeQueryModel(String projeto, int matricula, String nome, String email, String curso, String telefone) {
        this.projeto = projeto;
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.curso = curso;
        this.telefone = telefone;
    }

    public String getProjeto() {
        return projeto;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
