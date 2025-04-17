package com.aulao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aulao.models.PontoDeInteresse;

/**
 * Classe responsável por interagir com a tabela `ponto_de_interesse` no banco
 * de dados.
 * Herda a capacidade de conectar e fechar conexões da classe {@link DAO}.
 */
public class PontoDeInteresseDAO extends DAO {

    private void explicacao() {
        /**
         * Explicação dos principais objetos utilizados para acessar o banco de dados:
         *
         * - Connection:
         * Representa uma conexão ativa com o banco de dados. Através dela conseguimos
         * enviar comandos SQL.
         * Ela é obtida através do DriverManager usando a URL, usuário e senha do banco.
         *
         * - PreparedStatement:
         * É uma versão otimizada do Statement que permite enviar comandos SQL com
         * parâmetros (placeholders `?`).
         * Ele previne SQL Injection e melhora a performance em comandos repetidos.
         * Exemplo: `SELECT * FROM tabela WHERE nome = ?`
         * Depois você define os valores com `pstmt.setString(1, "João");`
         *
         * - Statement:
         * Usado para executar comandos SQL simples sem parâmetros, mas é menos seguro
         * que o PreparedStatement.
         * Aqui no projeto, usamos `Statement.RETURN_GENERATED_KEYS` como argumento ao
         * criar um `PreparedStatement`,
         * para que o banco retorne o ID gerado automaticamente.
         *
         * - ResultSet:
         * Representa o resultado de uma consulta SQL (normalmente SELECT).
         * Permite iterar pelas linhas retornadas e acessar os valores de cada coluna.
         */

    }

    /**
     * Insere um novo ponto de interesse no banco de dados.
     *
     * @param pontoDeInteresse o objeto a ser inserido.
     * @return o ponto de interesse com o ID gerado pelo banco.
     */
    public PontoDeInteresse criarPontoDeInteresse(PontoDeInteresse pontoDeInteresse) {
        String sql = "INSERT INTO ponto_de_interesse (nome, latitude, longitude) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conectar();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Define os valores dos parâmetros
            pstmt.setString(1, pontoDeInteresse.getNome());
            pstmt.setInt(2, pontoDeInteresse.getLatitude());
            pstmt.setInt(3, pontoDeInteresse.getLongitude());

            pstmt.executeUpdate();
            System.out.println("Ponto de interesse inserido com sucesso!");

            // Recupera o ID gerado pelo banco
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                pontoDeInteresse.setId(rs.getInt(1));
            }

            return pontoDeInteresse;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir ponto de interesse: " + e.getMessage());
            throw new RuntimeException("Erro ao criar o ponto de interesse", e);
        } finally {
            fecharConexao(conn);
        }
    }

    /**
     * Recupera um ponto de interesse pelo seu ID.
     *
     * @param id o ID do ponto.
     * @return o ponto correspondente, ou null se não encontrado.
     */
    public PontoDeInteresse pegarPontosDeInteressePorId(int id) {
        String sql = "SELECT id, nome, latitude, longitude FROM ponto_de_interesse WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PontoDeInteresse ponto = null;

        try {
            conn = conectar();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Mapeia os dados do banco para o objeto
                ponto = new PontoDeInteresse(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("longitude"),
                        rs.getInt("latitude"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar pontos: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar pontos", e);
        } finally {
            fecharConexao(conn);
        }

        return ponto;
    }

    /**
     * Retorna todos os pontos de interesse cadastrados no banco de dados.
     *
     * @return lista de todos os pontos de interesse.
     */
    public List<PontoDeInteresse> pegarTodosOsPontosDeInteresse() {
        String sql = "SELECT id, nome, latitude, longitude FROM ponto_de_interesse";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<PontoDeInteresse> pontosDeInteresse = new ArrayList<>();

        try {
            conn = conectar();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PontoDeInteresse ponto = new PontoDeInteresse(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("longitude"),
                        rs.getInt("latitude"));

                pontosDeInteresse.add(ponto);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar pontos: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar pontos", e);
        } finally {
            fecharConexao(conn);
        }

        return pontosDeInteresse;
    }

    /**
     * Atualiza um ponto de interesse existente no banco de dados.
     *
     * @param pontoDeInteresse o ponto de interesse com os novos dados.
     * @return true se a atualização foi bem-sucedida, false caso contrário.
     */
    public boolean atualizarPontoDeInteresse(PontoDeInteresse pontoDeInteresse) {
        String sql = "UPDATE ponto_de_interesse SET nome = ?, latitude = ?, longitude = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conectar();
            pstmt = conn.prepareStatement(sql);

            // Define os valores atualizados
            pstmt.setString(1, pontoDeInteresse.getNome());
            pstmt.setInt(2, pontoDeInteresse.getLatitude());
            pstmt.setInt(3, pontoDeInteresse.getLongitude());
            pstmt.setInt(4, pontoDeInteresse.getId());

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas == 0) {
                return false;
            }

            System.out.println("Ponto de interesse atualizado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar ponto de interesse: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar o ponto de interesse", e);
        } finally {
            fecharConexao(conn);
        }
    }

    /**
     * Remove um ponto de interesse do banco de dados.
     *
     * @param id o ID do ponto a ser deletado.
     * @return true se o ponto foi deletado, false caso não exista.
     */
    public boolean deletarPontoDeInteresse(int id) {
        String sql = "DELETE FROM ponto_de_interesse WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        Boolean foiDeletado = false;

        try {
            conn = conectar();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            foiDeletado = pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error ao deletar ponto");
        } finally {
            fecharConexao(conn);
        }

        return foiDeletado;
    }
}
