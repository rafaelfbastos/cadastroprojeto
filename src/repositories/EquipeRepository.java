package repositories;

import database.ConnectionFactory;
import models.AlunoModel;
import models.EquipeModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquipeRepository {

    public static int nEquipes(){
        int contador=0;
        try(Connection conn = ConnectionFactory.getConnection()){
            String sql = "SELECT max(Id_equipe) FROM Equipe";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            contador = resultSet.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contador;
    }
    public static void add (EquipeModel equipe){

        try(Connection conn = ConnectionFactory.getConnection()){
            String sql = "insert into equipe(fk_Alunos_matricula,Id_equipe) values (?,?) ";
            PreparedStatement statement = conn.prepareStatement(sql);

            for (AlunoModel aluno :equipe.getAlunos()) {
                statement.setInt(1,aluno.getMatricula());
                statement.setInt(2,equipe.getId());
                statement.addBatch();
            }
            statement.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static EquipeModel findEquipe(int id,Connection conn) throws SQLException {
        String sql = "select * from equipe where Id_equipe = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        EquipeModel equipe = new EquipeModel();
        equipe.setId(id);
        ArrayList<AlunoModel> alunos = new ArrayList<>();
        while (resultSet.next()){
            int matricula = resultSet.getInt("fk_Alunos_matricula");
            AlunoModel aluno = AlunoRepository.findByMatricula(matricula);
            alunos.add(aluno);
        }
        equipe.setAlunos(alunos);
        return equipe;
    }
    public static void deleteEquipe(int id,Connection conn) throws SQLException {
        String sql = "delete from equipe where Id_equipe = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,id);
        statement.executeUpdate();
    }


}
