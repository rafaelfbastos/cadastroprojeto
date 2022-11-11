package repositories;

import database.ConnectionFactory;
import models.EquipeModel;
import models.ProjetoModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

            JOptionPane.showMessageDialog(null,"Projeto Cadastrado com sucesso","Aviso",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static ArrayList<ProjetoModel> findAll(){
        ArrayList<ProjetoModel> projetos = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getConnection()){

            String sql = "SELECT * FROM PROJETO";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                ProjetoModel projeto = new ProjetoModel();
                projeto.setId(resultSet.getInt("id"));
                projeto.setArea(resultSet.getString("area"));
                projeto.setCidade(resultSet.getString("cidade"));
                projeto.setEstado(resultSet.getString("estado"));
                projeto.setDescricao(resultSet.getString("descricao"));
                projeto.setTitulo(resultSet.getString("titulo"));
                int idEquipe = resultSet.getInt("fk_Equipe_id_equipe");
                EquipeModel equipe = EquipeRepository.findEquipe(idEquipe,conn);
                projeto.setEquipe(equipe);
                projetos.add(projeto);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return projetos;
    }

    public static void update(ProjetoModel projeto){
        try(Connection conn = ConnectionFactory.getConnection()){

            String sql = "update projeto set area = ?, cidade =?, estado= ?, descricao=? ,titulo= ? where id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, projeto.getArea());
            statement.setString(2, projeto.getCidade());
            statement.setString(3, projeto.getEstado());
            statement.setString(4, projeto.getDescricao());
            statement.setString(5,projeto.getTitulo());
            statement.setInt(6,projeto.getId());
            statement.executeUpdate();

            EquipeRepository.deleteEquipe(projeto.getEquipe().getId(),conn);
            EquipeRepository.add(projeto.getEquipe());

            JOptionPane.showMessageDialog(null,"Projeto Atualizado com sucesso","Aviso",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void delete(ProjetoModel projeto){
        try(Connection conn = ConnectionFactory.getConnection()){

            String sql = "delete from projeto where id= ?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1,projeto.getId());
            statement.executeUpdate();

            EquipeRepository.deleteEquipe(projeto.getEquipe().getId(),conn);

            JOptionPane.showMessageDialog(null,"Projeto Deletado com sucesso","Aviso",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
