package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Conexao {
    private String driver;
    private String url;
    private String usuario;
    private String senha;

    /*Inicializar um construtor, de modo que quando essa classe Conexao.java instanciar
    um objeto, o contrustor será executado e a classe Conexao.java inicializada
     */

    public Conexao() {
        carregarConfiguracoes();
    }

    private void carregarConfiguracoes() {
        Properties props = new Properties();
        try (InputStream inputProsConfig = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            props.load(inputProsConfig);
            driver = props.getProperty("driver");
            url = props.getProperty("url");
            usuario = props.getProperty("usuario");
            senha = props.getProperty("senha");
    }
        catch (IOException erro) {
            System.out.println("Erro ao carregar configurações: " + erro.getMessage());
        }
    }

    public Connection conectar() {
        /*Objeto da conexao inicializa nulo pois não
        se sabe se o banco vai responder*/
        Connection condb = null;

        try {
            /*Especifica a rota do driver a ser carregado (JDBC para SGBD MySQL)*/
            Class.forName(driver);

            /*Inicializar o driver já carregado por meio do metodo getConnection(IP, porta, nome do banco, usuario, senha)*/
            condb = DriverManager.getConnection(url, usuario, senha);
            return condb;

        } catch (SQLException | ClassNotFoundException erro) {
            System.out.print("Erro ao conectar ao Banco de Dados " + erro);
            return null;
        }
    }
}