package views;

import app.Controller;
import app.Main;
import models.AlunoModel;
import repositories.AlunoRepository;
import repositories.Validator;

import javax.swing.*;
import java.awt.*;

public class AlunoCadastro extends JFrame{
    Controller controller;
    private JTextField matriculaField;
    private JTextField nomeField;
    private JTextField telefoneField;
    private JTextField cursoField;
    private JButton cadastrarButton;
    private JPanel panel;
    private JTextField EmailField;

    public AlunoCadastro(Controller controller){
        super("Cadastro Aluno");
        this.controller = controller;

        add(panel);
        setLocation(200,100);
        setPreferredSize(new Dimension(400,400));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cadastrarButton.addActionListener(e -> {
            if(validarInput()){
                AlunoModel aluno = new AlunoModel();
                aluno.setMatricula(Integer.parseInt(matriculaField.getText()));
                aluno.setCurso(cursoField.getText());
                aluno.setEmail(EmailField.getText());
                aluno.setTelefone(telefoneField.getText());
                aluno.setNome(nomeField.getText());
                AlunoRepository.gravar(aluno);
                controller.addEquipe(aluno);
                Main.janelaPrincipal.render();
                dispose();

            }
        });

        pack();
        setVisible(true);

    }
    public AlunoCadastro(Controller controller, int matricula){
        super("Cadastro Aluno");
        this.controller = controller;
        add(panel);
        setLocation(200,100);
        setPreferredSize(new Dimension(400,400));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        matriculaField.setText(""+matricula);
        matriculaField.setEditable(false);
        cadastrarButton.addActionListener(e -> {
            if(validarInput()){
                AlunoModel aluno = new AlunoModel();
                aluno.setMatricula(Integer.parseInt(matriculaField.getText()));
                aluno.setCurso(cursoField.getText());
                aluno.setEmail(EmailField.getText());
                aluno.setTelefone(telefoneField.getText());
                aluno.setNome(nomeField.getText());
                AlunoRepository.gravar(aluno);
                controller.addEquipe(aluno);
                Main.janelaPrincipal.render();
                dispose();

            }
        });

        pack();
        setVisible(true);

    }
    public AlunoCadastro(Controller controller,AlunoModel alunoModel){
        super("Cadastro Aluno");
        this.controller = controller;
        cadastrarButton.setText("Atualizar");
        matriculaField.setText(""+alunoModel.getMatricula());
        cursoField.setText(alunoModel.getCurso());
        EmailField.setText(alunoModel.getEmail());
        telefoneField.setText(alunoModel.getTelefone());
        nomeField.setText(alunoModel.getNome());
        matriculaField.setEditable(false);



        add(panel);
        setLocation(200,100);
        setPreferredSize(new Dimension(400,400));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cadastrarButton.addActionListener(e -> {
            if(validarInput()){
                AlunoModel aluno = new AlunoModel();
                aluno.setMatricula(Integer.parseInt(matriculaField.getText()));
                aluno.setCurso(cursoField.getText());
                aluno.setEmail(EmailField.getText());
                aluno.setTelefone(telefoneField.getText());
                aluno.setNome(nomeField.getText());
                AlunoRepository.update(aluno);
                controller.getEquipe().remove(alunoModel);
                controller.addEquipe(aluno);
                Main.janelaPrincipal.render();
                dispose();

            }
        });

        pack();
        setVisible(true);

    }

    private boolean validarInput(){

        if(!Validator.validarNumero(matriculaField.getText())){
            JOptionPane.showMessageDialog(this,"Digite uma matrícula válida");
            return false;
        }
        if(!Validator.validarTexto(nomeField.getText())){
            JOptionPane.showMessageDialog(this,"Digite um nome válido");
            return false;
        }
        if(!Validator.validarTelefone(telefoneField.getText())){
            JOptionPane.showMessageDialog(this,"Digite um telefone válido");
            return false;
        }
        if(!Validator.validarEmail(EmailField.getText())){
            JOptionPane.showMessageDialog(this,"Digite um e-mail válido");
            return false;
        }
        if(cursoField.getText()==null || cursoField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Digite um curso válido");
            return false;
        }

        return true;
    }


}
