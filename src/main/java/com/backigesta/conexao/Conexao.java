package com.backigesta.conexao;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private Dotenv dotenv = Dotenv.load();
    // Carregando as variáveis de ambiente para o projeto
    private String url = dotenv.get("DB_URL");
    private String user = dotenv.get("DB_USER");
    private String senha = dotenv.get("DB_SENHA");

    // Método para fazer conexão com o banco de dados
    public Connection conexao() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url,user,senha);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Class não encontrada");
        } catch (SQLException sqle) {
            System.out.println("Erro ao se conectar com o banco de dados");
        }
        return conn;
    }

    // Método para se desconectar com o banco de dados
    public void desconectar(Connection conn) {
        try {
            conn.close();
        } catch (SQLException sqle) {
            System.out.println("Erro ao se desconectar com o banco de dados");
        }
    }
}
