package br.com.CarroSeguro.model.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection abrirConexao() {
        Connection con = null;

        try {
            Class.forName("");
            String url = "";
            final String USER = "sai curioso";
            final String PASS = "";
            con = DriverManager.getConnection(url, USER, PASS);
            System.out.println("Conexão feita com o Banco de Dados");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro: a classe não foi encontrada\n" + e.getMessage());

        } catch (SQLException e) {
            System.out.println("Erro de SQL!\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return con;
    }
    public static void fecharConexao(Connection con) {
        try {
            con.close();
            System.out.println("Conexão fechada");
        } catch (SQLException e) {
            System.out.println("Erro de sql!" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
