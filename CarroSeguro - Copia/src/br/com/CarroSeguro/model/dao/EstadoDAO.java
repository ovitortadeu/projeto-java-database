package br.com.CarroSeguro.model.dao;

import br.com.CarroSeguro.model.dto.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstadoDAO {
    private Connection con;

    public EstadoDAO(Connection con) {
        this.con = con;
    }
    public Connection getCon() {
        return con;
    }

    public String inserir(Estado estado) {
        String sql = "insert into T_CS_ESTADO(id_estado, sg_estado, nm_estado) values(?,?,?)";
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, estado.getIdEstado());
            ps.setString(2, estado.getSgEstado());
            ps.setString(3, estado.getNmEstado());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else{
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String alterar(Estado estado) {
        String sql = "update T_CS_ESTADO set nm_estado=?, sg_estado=? where id_estado=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setString(1, estado.getNmEstado());
            ps.setString(2, estado.getSgEstado());
            ps.setInt(3, estado.getIdEstado());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch(SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String excluir(Estado estado) {
        String sql = "delete from T_CS_ESTADO where id_estado=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);){
            ps.setInt(1, estado.getIdEstado());
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso";
            } else {
                return "Erro ao deletar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public ArrayList<Estado> listarTodos() {
        String sql = "select * from T_CS_ESTADO order by id_estado";
        ArrayList<Estado> listaEstado = new ArrayList<Estado>();
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Estado estado = new Estado();
                    estado.setIdEstado(rs.getInt("id_estado"));
                    estado.setNmEstado(rs.getString("nm_estado"));
                    estado.setSgEstado(rs.getString("sg_estado"));
                    listaEstado.add(estado);
                }
                return listaEstado;
            } else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
    public int obterNovoIdEstado(Connection con) throws SQLException {
        String sql = "SELECT MAX(id_estado) FROM T_CS_ESTADO";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }
    }
}
