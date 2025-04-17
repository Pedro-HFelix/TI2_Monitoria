package com.aulao.models;

/**
 * Representa um ponto de interesse com nome, localização (latitude e longitude)
 * e identificador único.
 */
public class PontoDeInteresse {

    // Identificador único do ponto de interesse
    private int id;

    // Nome do ponto de interesse
    private String nome;

    // Coordenada de longitude do ponto
    private int longitude;

    // Coordenada de latitude do ponto
    private int latitude;

    /**
     * Construtor completo que inicializa todos os atributos, incluindo o ID.
     *
     * @param id        identificador único do ponto de interesse
     * @param nome      nome do ponto de interesse
     * @param longitude coordenada de longitude
     * @param latitude  coordenada de latitude
     */
    public PontoDeInteresse(int id, String nome, int longitude, int latitude) {
        this.id = id;
        this.nome = nome;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Construtor utilizado para criar um novo ponto de interesse, sem o ID (gerado
     * pelo banco).
     *
     * @param nome      nome do ponto de interesse
     * @param longitude coordenada de longitude
     * @param latitude  coordenada de latitude
     */
    public PontoDeInteresse(String nome, int longitude, int latitude) {
        this.nome = nome;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Construtor padrão (vazio), necessário para algumas bibliotecas como Gson e
     * frameworks.
     */
    public PontoDeInteresse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }
}
