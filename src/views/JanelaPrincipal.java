package views;

import app.Controller;
import models.*;
import repositories.AlunoRepository;
import repositories.EquipeRepository;
import repositories.ProjetoRepository;
import repositories.Validator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

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
    private JTable table;

    private JTable table1;
    private JButton mostrarEquipeButton;
    private JButton atualizarProjetoButton;
    private JButton descricaoButton;
    private JPanel pesquisarAluno;
    private JTextField matriculaPesquisarField;
    private JRadioButton informacoesRButton;
    private JRadioButton projetosRButton;
    private JRadioButton equipesRButton;
    private JTable alunoTable;
    private JButton atualizarCadastroButton;
    private JLabel iconLabel;
    private ProjetoTableModel tableModel;
    private AlunosTableModel tableModelAluno;
    private ButtonGroup buttonGroup;


    public JanelaPrincipal(Controller controller){
        super("Cadastro de Projetos");
        configurarButtons();
        configurarJList();
        controller.setProjetos(ProjetoRepository.findAll());

        this.controller = controller;
        add(panel1);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
    }

    private void configurarButtons(){

        buttonGroup = new ButtonGroup();
        buttonGroup.add(informacoesRButton);
        buttonGroup.add(projetosRButton);
        buttonGroup.add(equipesRButton);


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
            limparForm();
        });

        cadastrarProjetoButton.addActionListener(e->{
            if(validarForm()){

                int id_equipe = EquipeRepository.nEquipes() +1;
                EquipeModel equipe = new EquipeModel(id_equipe,controller.getEquipe());
                EquipeRepository.add(equipe);

                ProjetoModel projeto = new ProjetoModel();
                projeto.setArea(areaField.getText());
                projeto.setCidade(cidadeField.getText());
                projeto.setEstado(estadoField.getText());
                projeto.setDescricao(descricaoArea.getText());
                projeto.setEquipe(equipe);
                projeto.setTitulo(tituloField.getText());
                ProjetoRepository.add(projeto);
                limparForm();
                controller.setProjetos(ProjetoRepository.findAll());
                tableModel.setProjetos(controller.getProjetos());
            }
        });

        atualizarDadosDoAlunoButton.addActionListener(e -> {
            if(list1.getSelectedIndex()>=0){
                AlunoModel aluno = Controller.getInstance().getEquipe().get(list1.getSelectedIndex());
                new AlunoCadastro(controller,aluno);
            }
        });

        mostrarEquipeButton.addActionListener(e -> {
            int index = table.getSelectedRow();
            if(index>=0){
                tableModelAluno.setAlunos(controller.getProjetos().get(index).getEquipe().getAlunos());
                table1.setModel(tableModelAluno);
                SwingUtilities.updateComponentTreeUI(table1);
            }
        });
        atualizarProjetoButton.addActionListener(e -> {
            int index = table.getSelectedRow();
            if(index>=0){
               ProjetoModel projeto = controller.getProjetos().get(index);
               new AtualizarProjeto(controller,projeto);
            }
        });

        descricaoButton.addActionListener(e -> {
            int index = table.getSelectedRow();
            if(index>=0){
                ProjetoModel projeto = controller.getProjetos().get(index);

                JOptionPane.showMessageDialog(this,projeto.getDescricao(),"Descrição",JOptionPane.PLAIN_MESSAGE);
            }

        });
        projetosRButton.addActionListener(e -> {
            if(Validator.validarNumero(matriculaPesquisarField.getText())){
                AlunoModel aluno = AlunoRepository.findByMatricula(Integer.parseInt(matriculaPesquisarField.getText()));
                if(aluno!=null){
                    ArrayList<ProjetoModel> projetos = ProjetoRepository.findByAluno(aluno);
                    ProjetoTableModel projetoTableModel = new ProjetoTableModel(projetos);
                    alunoTable.setModel(projetoTableModel);
                    SwingUtilities.updateComponentTreeUI(alunoTable);
                }
                else JOptionPane.showMessageDialog(this,"Aluno não cadastrado:","Erro",JOptionPane.WARNING_MESSAGE);
            }
        });
        informacoesRButton.addActionListener(e -> {
            if(Validator.validarNumero(matriculaPesquisarField.getText())){
                AlunoModel aluno = AlunoRepository.findByMatricula(Integer.parseInt(matriculaPesquisarField.getText()));
                if(aluno!=null){
                    ArrayList<AlunoModel> alunos = new ArrayList<>();
                    alunos.add(aluno);
                    AlunosTableModel alunosTableModel = new AlunosTableModel(alunos);
                    alunoTable.setModel(alunosTableModel);
                    SwingUtilities.updateComponentTreeUI(alunoTable);
                }
                else JOptionPane.showMessageDialog(this,"Aluno não cadastrado:","Erro",JOptionPane.WARNING_MESSAGE);
            }
        });
        equipesRButton.addActionListener(e -> {
            if(Validator.validarNumero(matriculaPesquisarField.getText())){
                AlunoModel aluno = AlunoRepository.findByMatricula(Integer.parseInt(matriculaPesquisarField.getText()));
                if(aluno!=null){
                    ArrayList<EquipeQueryModel> equipe = new ArrayList<>();
                    equipe = EquipeRepository.equipeQuery(aluno);
                    EquipeTableModel equipeTableModel = new EquipeTableModel(equipe);
                    alunoTable.setModel(equipeTableModel);
                    SwingUtilities.updateComponentTreeUI(alunoTable);
                }
                else JOptionPane.showMessageDialog(this,"Aluno não cadastrado:","Erro",JOptionPane.WARNING_MESSAGE);
            }
        });
        atualizarCadastroButton.addActionListener(e -> {
            if(Validator.validarNumero(matriculaPesquisarField.getText())){
                AlunoModel aluno = AlunoRepository.findByMatricula(Integer.parseInt(matriculaPesquisarField.getText()));
                if(aluno!=null){
                    new AlunoCadastro(aluno);
                }
                else JOptionPane.showMessageDialog(this,"Aluno não cadastrado:","Erro",JOptionPane.WARNING_MESSAGE);
            }
        });


    }
    private void configurarJList(){
        EquipeListModel listModel = new EquipeListModel(Controller.getInstance().getEquipe());
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
        if(controller.getEquipe().size() == 0){
            JOptionPane.showMessageDialog(this,"Equipe deve ter ao menos 1 aluno","Erro",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void updateTable(){

        tableModel.setProjetos(controller.getProjetos());
        table.setModel(tableModel);
    }
    public void render(){
        updateTable();
        SwingUtilities.updateComponentTreeUI(list1);
        SwingUtilities.updateComponentTreeUI(table);
        SwingUtilities.updateComponentTreeUI(table1);
    }
    public void limparForm(){
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

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        tableModel = new ProjetoTableModel(ProjetoRepository.findAll());
        tableModelAluno = new AlunosTableModel(new ArrayList<>());
        table = new JTable(tableModel);
        table1 = new JTable(tableModelAluno);
        try {
            BufferedImage imageBuffered = ImageIO.read(getClass().getResource("/Logo_Unijorge.png"));
            iconLabel = new JLabel(new ImageIcon(imageBuffered));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
