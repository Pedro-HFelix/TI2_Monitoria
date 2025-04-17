package com.aulao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe base de acesso a dados (DAO) responsável por gerenciar a conexão com o banco de dados PostgreSQL.
 * Pode ser estendida por outras classes DAO específicas para reutilizar os métodos de conexão e encerramento.
 */
public class DAO {

    // URL de conexão com o banco de dados PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    // Usuário do banco de dados
    private static final String USER = "admin";

    // Senha do banco de dados
    private static final String PASSWORD = "admin";

    /**
     * Construtor padrão da classe DAO.
     */
    public DAO() {
    }

    /**
     * Estabelece a conexão com o banco de dados PostgreSQL.
     *
     * @return uma instância de {@link Connection} se a conexão for bem-sucedida.
     * @throws RuntimeException se ocorrer algum erro ao conectar.
     */
    protected Connection conectar() {
        try {
            // Retorna uma conexão com os dados especificados
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // Lança exceção customizada em caso de falha na conexão
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }

    /**
     * Fecha a conexão com o banco de dados, caso ela não seja nula.
     *
     * @param conn a conexão a ser fechada.
     * @throws RuntimeException se ocorrer erro ao fechar a conexão.
     */
    protected void fecharConexao(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão fechada com sucesso!");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                throw new RuntimeException("Erro ao conectar com o banco de dados");
            }
        }
    }
}
