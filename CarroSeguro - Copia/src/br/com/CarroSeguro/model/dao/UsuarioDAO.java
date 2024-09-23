package br.com.CarroSeguro.model.dao;

import br.com.CarroSeguro.model.dto.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {
    private Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }
    public Connection getCon() {
        return con;
    }

    public String inserir(Usuario usuario) {
        String sql = "insert into T_CS_USUARIO(id_usuario, us_cpf, nm_usuario, em_usuario, sn_usuario) values(?,?,?,?,?)";
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, usuario.getIdUsuario());
            ps.setLong(2, usuario.getUsCPF());
            ps.setString(3, usuario.getNmUsuario());
            ps.setString(4, usuario.getEmailUsuario());
            ps.setString(5, usuario.getSenhaUsuario());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else{
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String alterar(Usuario usuario) {
        String sql = "update T_CS_USUARIO set nm_usuario=?, us_cpf=?, em_usuario, sn_usuario where id_usuario=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setString(1, usuario.getNmUsuario());;
            ps.setLong(2, usuario.getUsCPF());
            ps.setString(3, usuario.getEmailUsuario());
            ps.setString(4, usuario.getSenhaUsuario());
            ps.setInt(1, usuario.getIdUsuario());;
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch(SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String excluir(Usuario usuario) {
        String sql = "delete from T_CS_USUARIO where id_usuario=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);){
            ps.setInt(1, usuario.getIdUsuario());
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso";
            } else {
                return "Erro ao deletar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public ArrayList<Usuario> listarTodos() {
        String sql = "select * from T_CS_USUARIO order by id_usuario";
        ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setUsCPF(rs.getLong("us_cpf"));
                    usuario.setNmUsuario(rs.getString("nm_usuario"));
                    usuario.setEmailUsuario(rs.getString("em_usuario"));
                    usuario.setSenhaUsuario(rs.getString("sn_usuario"));
                    listaUsuario.add(usuario);
                }
                return listaUsuario;
            } else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

    public int obterNovoIdUsuario(Connection con) throws SQLException {
        String sql = "SELECT MAX(id_usuario) FROM T_CS_USUARIO";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }
    }



}





