package br.com.CarroSeguro.model.dao;

import br.com.CarroSeguro.model.dto.Carro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarroDAO {
    private Connection con;

    public CarroDAO(Connection con) {
        this.con = con;
    }
    public Connection getCon() {
        return con;
    }

    public String inserir(Carro carro) {
        String sql = "insert into T_CS_CARRO(id_carro, mc_carro, md_carro, t_cs_usuario_id_usuario) values(?,?,?,?)";
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, carro.getIdCarro());
            ps.setString(2, carro.getMarcaCarro());
            ps.setString(3, carro.getModeloCarro());
            ps.setInt(4, carro.getIdUsuario());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else{
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String alterar(Carro carro) {
        String sql = "update T_CS_CARRO set md_carro=?, mc_carro=? where id_carro=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setString(1, carro.getModeloCarro());
            ps.setString(2, carro.getModeloCarro());
            ps.setInt(3, carro.getIdCarro());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch(SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String excluir(Carro carro) {
        String sql = "delete from T_CS_CARRO where id_carro=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);){
            ps.setInt(1, carro.getIdCarro());
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso";
            } else {
                return "Erro ao deletar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public ArrayList<Carro> listarTodos() {
        String sql = "select * from T_CS_CARRO order by id_carro";
        ArrayList<Carro> listaCarro = new ArrayList<Carro>();
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Carro carro = new Carro();
                    carro.setIdCarro(rs.getInt("id_carro"));
                    carro.setModeloCarro(rs.getString("md_carro"));
                    carro.setMarcaCarro(rs.getString("mc_carro"));
                    listaCarro.add(carro);
                }
                return listaCarro;
            } else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }


}




