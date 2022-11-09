package repositories;

import database.ConnectionFactory;
import models.ProjetoModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjetoRepository {

    public static void add(ProjetoModel projeto){
        try(Connection conn = ConnectionFactory.getConnection()){

            String sql = "insert into projeto(area,cidade,estado,descricao,fk_Equipe_id_equipe,titulo) values (?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, projeto.getArea());
            statement.setString(2, projeto.getCidade());
            statement.setString(3, projeto.getEstado());
            statement.setString(4, projeto.getDescricao());
            statement.setInt(5, projeto.getEquipe().getId());
            statement.setString(6,projeto.getTitulo());
            statement.executeUpdate();

            JOptionPane.showConfirmDialog(null,"Projeto Cadastrado com sucesso","Aviso",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
