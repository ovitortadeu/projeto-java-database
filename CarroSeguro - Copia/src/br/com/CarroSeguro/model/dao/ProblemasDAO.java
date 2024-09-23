package br.com.CarroSeguro.model.dao;

import br.com.CarroSeguro.model.dto.Problemas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProblemasDAO {
    private Connection con;

    public ProblemasDAO(Connection con) {
        this.con = con;
    }
    public Connection getCon() {
        return con;
    }

    public String inserir(Problemas problemas) {
        String sql = "insert into T_CS_PROBLEMAS(id_problema, vl_problema, nm_problema, tp_peca_problema, dc_problema, id_carro) values(?,?,?,?,?,?)";
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, problemas.getIdProblema());
            ps.setInt(2, problemas.getVl_problema());
            ps.setString(3, problemas.getNmProblema());
            ps.setString(4, problemas.getTpPecaProblema());
            ps.setString(5, problemas.getDcProblema());
            ps.setInt(6, problemas.getIdCarro());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else{
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String alterar(Problemas problemas) {
        String sql = "update T_CS_PROBLEMAS set vl_problema=?, nm_problema=?, tp_peca_problema=?, dc_problema=?, id_carro where id_problema=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, problemas.getVl_problema());
            ps.setString(2, problemas.getNmProblema());
            ps.setString(3, problemas.getTpPecaProblema());
            ps.setString(4, problemas.getDcProblema());
            ps.setInt(5, problemas.getIdCarro());
            ps.setInt(6, problemas.getIdProblema());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch(SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String excluir(Problemas problemas) {
        String sql = "delete from T_CS_PROBLEMAS where id_problema=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);){
            ps.setInt(1, problemas.getIdProblema());
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso";
            } else {
                return "Erro ao deletar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public ArrayList<Problemas> listarTodos() {
        String sql = "select * from T_CS_PROBLEMAS order by id_problema";
        ArrayList<Problemas> listaProblemas = new ArrayList<Problemas>();
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Problemas problemas = new Problemas();
                    problemas.setIdProblema(rs.getInt("id_problema"));
                    problemas.setNmProblema(rs.getString("nm_problema"));
                    problemas.setVl_problema(rs.getInt("vl_problema"));
                    problemas.setTpPecaProblema(rs.getString("tp_peca_problema"));
                    problemas.setDcProblema(rs.getString("dc_problema"));
                    listaProblemas.add(problemas);
                }
                return listaProblemas;
            } else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }


};