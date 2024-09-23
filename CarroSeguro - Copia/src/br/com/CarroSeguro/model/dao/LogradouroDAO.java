package br.com.CarroSeguro.model.dao;

import br.com.CarroSeguro.model.dto.Logradouro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LogradouroDAO {
    private Connection con;

    public LogradouroDAO(Connection con) {
        this.con = con;
    }
    public Connection getCon() {
        return con;
    }

    public String inserir(Logradouro logradouro) {
        String sql = "insert into T_CS_LOGRADOURO(id_logradouro, nm_logradouro, nr_logradouro, nr_cep,t_cs_bairro_id_bairro,t_cs_usuario_id_usuario) values(?,?,?,?,?,?)";
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, logradouro.getIdLogradouro());
            ps.setString(2, logradouro.getNmLogradouro());
            ps.setInt(3, logradouro.getNrLogradouro());
            ps.setInt(4, logradouro.getNrCep());
            ps.setInt(5, logradouro.getIdBairro());
            ps.setInt(6, logradouro.getIdUsuario());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else{
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String alterar(Logradouro logradouro) {
        String sql = "update T_CS_LOGRADOURO set nm_logradouro=?, nr_logradouro=?, nr_cep=?,id_usuario=?,id_bairro=? where id_logradouro=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setString(1, logradouro.getNmLogradouro());
            ps.setInt(2, logradouro.getNrLogradouro());
            ps.setInt(3, logradouro.getNrCep());
            ps.setInt(4, logradouro.getIdUsuario());
            ps.setInt(5, logradouro.getIdBairro());
            ps.setInt(6, logradouro.getIdLogradouro());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch(SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String excluir(Logradouro logradouro) {
        String sql = "delete from T_CS_LOGRADOURO where id_logradouro=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);){
            ps.setInt(1, logradouro.getIdLogradouro());
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso";
            } else {
                return "Erro ao deletar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public ArrayList<Logradouro> listarTodos() {
        String sql = "select * from T_CS_LOGRADOURO order by id_logradouro";
        ArrayList<Logradouro> listaLogradouro = new ArrayList<Logradouro>();
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Logradouro logradouro = new Logradouro();
                    logradouro.setIdLogradouro(rs.getInt("id_logradouro"));
                    logradouro.setNmLogradouro(rs.getString("nm_logradouro"));
                    logradouro.setNrLogradouro(rs.getInt("nr_logradouro"));
                    logradouro.setNrCep(rs.getInt("nr_cep"));
                    listaLogradouro.add(logradouro);
                }
                return listaLogradouro;
            } else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
    public int obterNovoIdLogradouro(Connection con) throws SQLException {
        String sql = "SELECT MAX(id_logradouro) FROM T_CS_LOGRADOURO";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }
    }



}





