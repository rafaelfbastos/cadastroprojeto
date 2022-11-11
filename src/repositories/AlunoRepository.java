package repositories;

import database.ConnectionFactory;
import models.AlunoModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoRepository {

    private AlunoRepository(){}

    public static void gravar(AlunoModel aluno){
        try(Connection conn = ConnectionFactory.getConnection()){

           String sql = "insert into aluno(matricula,nome,email,telefone,curso,senha) values(?,?,?,?,?,?)";
           PreparedStatement statement = conn.prepareStatement(sql);

           statement.setInt(1,aluno.getMatricula());
           statement.setString(2,aluno.getNome());
           statement.setString(3,aluno.getEmail());
           statement.setString(4,aluno.getTelefone());
           statement.setString(5, aluno.getCurso());
           statement.setInt(6,aluno.getSenha());

           statement.executeUpdate();



        } catch (SQLException e) {
            if(e.getMessage().contains("SQLITE_CONSTRAINT_PRIMARYKEY")){
                JOptionPane.showMessageDialog(null,"Aluno já cadastrado.","Erro ao cadastrar Aluno",JOptionPane.ERROR_MESSAGE);
            }
            e.printStackTrace();
        }

    }

    public static AlunoModel findByMatricula(int matricula){
        AlunoModel aluno = null;

        try(Connection conn = ConnectionFactory.getConnection()){

            String sql = "select * from aluno where matricula = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,matricula);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                aluno = new AlunoModel(
                        resultSet.getInt("matricula"),
                        resultSet.getString("nome"),
                        resultSet.getString("email"),
                        resultSet.getString("telefone"),
                        resultSet.getString("curso"),
                        resultSet.getInt("senha")

                );
            }

            return aluno;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aluno;
    }

    public static void update(AlunoModel aluno){
        try(Connection conn = ConnectionFactory.getConnection()){

            String sql = "update aluno set nome = ? , email = ? ,telefone = ? ,curso =? where matricula = ?";
            PreparedStatement statement = conn.prepareStatement(sql);


            statement.setString(1,aluno.getNome());
            statement.setString(2,aluno.getEmail());
            statement.setString(3,aluno.getTelefone());
            statement.setString(4, aluno.getCurso());
            statement.setInt(5,aluno.getMatricula());
            statement.executeUpdate();



        } catch (SQLException e) {
            if(e.getMessage().contains("SQLITE_CONSTRAINT_PRIMARYKEY")){
                JOptionPane.showMessageDialog(null,"Aluno já cadastrado.","Erro ao cadastrar Aluno",JOptionPane.ERROR_MESSAGE);
            }
            e.printStackTrace();
        }
    }
    public static AlunoModel findByMatricula(int matricula,Connection conn) throws SQLException {
        AlunoModel aluno = null;

        String sql = "select * from aluno where matricula = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,matricula);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            aluno = new AlunoModel(
                    resultSet.getInt("matricula"),
                    resultSet.getString("nome"),
                    resultSet.getString("email"),
                    resultSet.getString("telefone"),
                    resultSet.getString("curso"),
                    resultSet.getInt("senha")
            );
        }

        return aluno;
    }


}
