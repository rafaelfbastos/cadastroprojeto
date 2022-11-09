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
            String sql = "SELECT COUNT ( DISTINCT id_equipe) AS \" Números de Equipes\"FROM Equipe;";
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

}
