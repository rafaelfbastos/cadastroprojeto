package views;

import app.Controller;
import models.AlunoModel;
import models.EquipeListModel;
import repositories.AlunoRepository;
import repositories.Validator;

import javax.swing.*;

public class JanelaPrincipal extends JFrame {

    private Controller controller;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel CadastarProjeto;
    private JPanel ListarProjeto;
    private JTextField tituloField;
    private JTextField areaField;
    private JTextField cidadeField;
    private JTextField estadoField;
    private JTextArea descricaoArea;
    private JTextField matriculaField;
    private JButton addButton;
    private JList list1;
    private JButton removerButton;
    private JButton cadastrarProjetoButton;
    private JButton limparFormulárioButton;
    private JButton atualizarDadosDoAlunoButton;

    public JanelaPrincipal(Controller controller){
        super("Cadastro de Projetos");
        configurarButtons();
        configurarJList();
        this.controller = controller;
        add(panel1);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void configurarButtons(){
        addButton.addActionListener(e -> {
            if(Validator.validarNumero(matriculaField.getText())){
                int matricula = Integer.parseInt(matriculaField.getText());
                AlunoModel alunoModel = AlunoRepository.findByMatricula(matricula);
                if(alunoModel==null){
                    new AlunoCadastro(controller,matricula);
                }else {
                    Controller.getInstance().addEquipe(alunoModel);
                    matriculaField.setText("");
                    render();
                }
            }
        });
        removerButton.addActionListener(e -> {
           if(list1.getSelectedIndex()>=0){
               Controller.getInstance().getEquipe().remove(list1.getSelectedIndex());
               render();
           }

        });
        limparFormulárioButton.addActionListener(e -> {
            Controller.getInstance().getEquipe().clear();
            tituloField.setText("");
            areaField.setText("");
            estadoField.setText("");
            estadoField.setText("");
            cidadeField.setText("");
            cidadeField.setText("");
            descricaoArea.setText("");
            matriculaField.setText("");
            render();

        });

        atualizarDadosDoAlunoButton.addActionListener(e -> {
            if(list1.getSelectedIndex()>=0){
                AlunoModel aluno = Controller.getInstance().getEquipe().get(list1.getSelectedIndex());
                new AlunoCadastro(controller,aluno);

            }
        });
    }
    private void configurarJList(){
        EquipeListModel listModel = new EquipeListModel(Controller.getInstance().getEquipe());
        list1.setModel(listModel);

    }


    public void render(){
        SwingUtilities.updateComponentTreeUI(list1);
    }
}
