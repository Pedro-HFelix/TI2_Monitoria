package com.aulao.services;

import java.util.List;

import com.aulao.dao.PontoDeInteresseDAO;
import com.aulao.models.PontoDeInteresse;

/**
 * Classe de serviço que faz a ponte entre a camada de controle e a camada DAO.
 * Contém regras de negócio e validações relacionadas aos pontos de interesse.
 */
public class PontoDeInteresseService {
    private PontoDeInteresseDAO dao;

    /**
     * Construtor da classe. Inicializa a instância do DAO.
     */
    public PontoDeInteresseService() {
        dao = new PontoDeInteresseDAO();
    }

    /**
     * Cria um novo ponto de interesse no banco de dados.
     *
     * @param pt objeto contendo os dados do ponto de interesse a ser criado
     * @return o ponto de interesse criado, com ID atribuído
     */
    public PontoDeInteresse criarPontoDeInteresse(PontoDeInteresse pt) {
        return dao.criarPontoDeInteresse(pt);
    }

    /**
     * Retorna todos os pontos de interesse cadastrados no banco.
     *
     * @return uma lista com todos os pontos de interesse
     */
    public List<PontoDeInteresse> pegartodosPontoDeInteresses() {
        var pontos = dao.pegarTodosOsPontosDeInteresse();
        return pontos;
    }

    /**
     * Busca um ponto de interesse pelo seu ID.
     *
     * @param id identificador único do ponto
     * @return o ponto de interesse encontrado ou null se não existir
     * @throws Error se o ID for inválido (menor que 1)
     */
    public PontoDeInteresse pegarPontoDeInteressePorId(int id) {
        if (id < 1) {
            throw new Error("id invalido");
        }

        var pontoDeInteresse = dao.pegarPontosDeInteressePorId(id);
        return pontoDeInteresse;
    }

    /**
     * Atualiza os dados de um ponto de interesse existente.
     *
     * @param pt objeto com os novos dados do ponto
     * @param id ID do ponto de interesse a ser atualizado
     * @return true se atualizado com sucesso, false se não encontrado
     * @throws Error se o ID for inválido (menor que 1)
     */
    public boolean atualizarPontoDeInteresse(PontoDeInteresse pt, int id) {
        if (id < 1) {
            throw new Error("id invalido");
        }

        var resul = pegarPontoDeInteressePorId(id);
        if (resul == null)
            return false;

        pt.setId(resul.getId());
        return dao.atualizarPontoDeInteresse(pt);
    }

    /**
     * Deleta um ponto de interesse pelo seu ID.
     *
     * @param id ID do ponto de interesse a ser removido
     * @return true se removido com sucesso, false se não encontrado
     * @throws Error se o ID for inválido (menor que 1)
     */
    public boolean deletarPontoDeInteresse(int id) {
        if (id < 1) {
            throw new Error("id invalido");
        }

        return dao.deletarPontoDeInteresse(id);
    }
}
