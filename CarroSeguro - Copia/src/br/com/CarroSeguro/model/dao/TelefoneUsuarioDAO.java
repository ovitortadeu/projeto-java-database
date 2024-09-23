package br.com.CarroSeguro.model.dao;

import br.com.CarroSeguro.model.dto.TelefoneUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TelefoneUsuarioDAO {
    private Connection con;

    public TelefoneUsuarioDAO(Connection con) {
        this.con = con;
    }
    public Connection getCon() {
        return con;
    }

    public String inserir(TelefoneUsuario tel) {
        String sql = "insert into T_CS_TELEFONE_USUARIO(id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone, t_cs_usuario_id_usuario) values(?,?,?,?,?,?,?)";
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, tel.getIdTelefone());
            ps.setInt(2, tel.getNrDDI());
            ps.setInt(3, tel.getNrDDD());
            ps.setInt(4, tel.getNrTelefone());
            ps.setString(5, tel.getTpTelefone());
            ps.setString(6, tel.getStTelefone());
            ps.setInt(7, tel.getIdUsuario());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else{
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String alterar(TelefoneUsuario tel) {
        String sql = "update T_CS_TELEFONE_USUARIO set nr_ddi=?, nr_ddd=?, nr_telefone=?, tp_telefone=?, st_telefone=?, id_usuario=?  where id_telefone=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, tel.getNrDDI());
            ps.setInt(2, tel.getNrDDD());
            ps.setInt(3, tel.getNrTelefone());
            ps.setString(4, tel.getTpTelefone());
            ps.setString(5, tel.getStTelefone());
            ps.setInt(6, tel.getIdUsuario());
            ps.setInt(7, tel.getIdTelefone());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch(SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String excluir(TelefoneUsuario tel) {
        String sql = "delete from T_CS_TELEFONE_USUARIO where id_telefone=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);){
            ps.setInt(1, tel.getIdTelefone());
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso";
            } else {
                return "Erro ao deletar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public ArrayList<TelefoneUsuario> listarTodos() {
        String sql = "select * from T_CS_TELEFONE_USUARIO order by id_telefone";
        ArrayList<TelefoneUsuario> listaTelefones = new ArrayList<TelefoneUsuario>();
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    TelefoneUsuario tel = new TelefoneUsuario();
                    tel.setIdTelefone(rs.getInt("id_telefone"));
                    tel.setNrTelefone(rs.getInt("nr_telefone"));
                    tel.setNrDDD(rs.getInt("nr_ddd"));
                    tel.setNrDDI(rs.getInt("nr_ddi"));
                    tel.setStTelefone(rs.getString("st_telefone"));
                    tel.setTpTelefone(rs.getString("tp_telefone"));
                    listaTelefones.add(tel);
                }
                return listaTelefones;
             } else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }


};