package views;

import app.Controller;
import app.Main;
import models.*;
import repositories.AlunoRepository;
import repositories.EquipeRepository;
import repositories.ProjetoRepository;
import repositories.Validator;

import javax.swing.*;
import java.util.ArrayList;

public class AtualizarProjeto extends JFrame {
    private Controller controller;
    private JPanel CadastarProjeto;
    private JTextField tituloField;
    private JTextField areaField;
    private JTextField cidadeField;
    private JTextField estadoField;
    private JTextArea descricaoArea;
    private JTextField matriculaField;
    private JButton addButton;
    private JList list1;
    private JButton removerButton;
    private JButton atualizarDadosDoAlunoButton;
    private JButton atualizarButton;
    private JButton apagarButton;
    private JPanel panel1;
    private ArrayList<Integer> listaSenhas;
    private ProjetoModel projeto;

    public AtualizarProjeto(Controller controller,ProjetoModel projeto){
        super("AtualizarProjeto");
        this.projeto = projeto;
        configurarButtons();
        setarForm();
        listaSenhas = new ArrayList<>(
            projeto.getEquipe().getAlunos().stream().map(AlunoModel::getSenha).toList()
        );

        this.controller = controller;
        add(panel1);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void configurarButtons(){
        addButton.addActionListener(e -> {
            if(Validator.validarNumero(matriculaField.getText())){
                int matricula = Integer.parseInt(matriculaField.getText());
                AlunoModel alunoModel = AlunoRepository.findByMatricula(matricula);
                if(alunoModel==null){
                    new AlunoCadastro(this,matricula);
                }else {
                    projeto.getEquipe().getAlunos().add(alunoModel);
                    matriculaField.setText("");
                    render();
                }
            }
        });
        removerButton.addActionListener(e -> {
            if(list1.getSelectedIndex()>=0){
                projeto.getEquipe().getAlunos().remove(list1.getSelectedIndex());
                render();
            }

        });

        atualizarButton.addActionListener(e->{
            if(validarForm() && validarSenha()){

                projeto.setArea(areaField.getText());
                projeto.setCidade(cidadeField.getText());
                projeto.setEstado(estadoField.getText());
                projeto.setDescricao(descricaoArea.getText());
                projeto.setTitulo(tituloField.getText());
                ProjetoRepository.update(projeto);
                controller.setProjetos(ProjetoRepository.findAll());
                Main.janelaPrincipal.render();
                dispose();
            }
        });

        apagarButton.addActionListener(e -> {
            Object[] objects = { "Sim", "Não" };
            int confirm = JOptionPane.showOptionDialog(this, "Tem certeza que deseja apagar o projeto:", "Atenção", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, objects, objects);
            if(confirm==0){
                if(validarSenha()){
                    ProjetoRepository.delete(projeto);
                    controller.setProjetos(ProjetoRepository.findAll());
                    Main.janelaPrincipal.render();
                    dispose();
                }
            }

        });

        atualizarDadosDoAlunoButton.addActionListener(e -> {
            if(list1.getSelectedIndex()>=0){
                AlunoModel aluno = projeto.getEquipe().getAlunos().get(list1.getSelectedIndex());
                new AlunoCadastro(controller,aluno);

            }
        });


    }
    private void configurarJList(ArrayList<AlunoModel> alunos){
        EquipeListModel listModel = new EquipeListModel(alunos);
        list1.setModel(listModel);

    }

    private boolean validarForm(){

        if(!Validator.validarSimples(tituloField.getText())){
            JOptionPane.showMessageDialog(this,"Digite um título válido:","Erro",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!Validator.validarTexto(areaField.getText())){
            JOptionPane.showMessageDialog(this,"Digite um area válido:","Erro",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!Validator.validarTexto(cidadeField.getText())){
            JOptionPane.showMessageDialog(this,"Digite um Cidade válido:","Erro",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!Validator.validarTexto(estadoField.getText())){
            JOptionPane.showMessageDialog(this,"Digite um Estado válido:","Erro",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!Validator.validarSimples(descricaoArea.getText())){
            JOptionPane.showMessageDialog(this,"Digite uma descrição válido:","Erro",JOptionPane.WARNING_MESSAGE);
            return  false;
        }
        if(projeto.getEquipe().getAlunos().size() == 0){
            JOptionPane.showMessageDialog(this,"Equipe deve ter ao menos 1 aluno","Erro",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void render(){
        SwingUtilities.updateComponentTreeUI(list1);

    }

    private boolean validarSenha(){
        JLabel label = new JLabel("Senha: ");
        JPasswordField senhaField = new JPasswordField();
        Object[] objects = { label, senhaField };
        JOptionPane.showConfirmDialog(this, objects, "Digite usa Senha:", JOptionPane.OK_CANCEL_OPTION);
        int senha = String.valueOf(senhaField.getPassword()).hashCode();
        if(listaSenhas.contains(senha)){
            return true;
        }else {
            JOptionPane.showMessageDialog(this,"Senha não confere","Senha Inválida",JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    public void setarForm(){
        Controller.getInstance().getEquipe().clear();
        tituloField.setText(projeto.getTitulo());
        areaField.setText(projeto.getArea());
        estadoField.setText(projeto.getEstado());
        estadoField.setText(projeto.getEstado());
        cidadeField.setText(projeto.getCidade());
        descricaoArea.setText(projeto.getDescricao());
        configurarJList(projeto.getEquipe().getAlunos());
        render();

    }
    public void addAluno(AlunoModel aluno){
        projeto.getEquipe().getAlunos().add(aluno);
    }

    private void createUIComponents() {



    }
}
