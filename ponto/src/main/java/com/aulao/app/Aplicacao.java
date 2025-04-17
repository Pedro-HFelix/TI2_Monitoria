package com.aulao.app;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import com.aulao.models.PontoDeInteresse;
import com.aulao.services.PontoDeInteresseService;
import com.google.gson.Gson;

public class Aplicacao {
    public static void main(String[] args) {

        port(8081);
        staticFiles.location("/front");

        // http://localhost:8081/health
        get("/health", (req, resp) -> {
            Gson gson = new Gson();

            Map<String, String> message = new HashMap<>();

            resp.type("application/json");
            message.put("message", "rodando");
            return gson.toJson(message);
        });
        // http://localhost:8081/ErrorTest
        get("/ErrorTest", (req, resp) -> {
            throw new RuntimeException("Error boy");
        });

        // http://localhost:8081/
        get("/", (req, res) -> {
            Gson gson = new Gson();
            PontoDeInteresseService ps = new PontoDeInteresseService();

            var result = ps.pegartodosPontoDeInteresses();

            res.status(200);
            return gson.toJson(result);
        });

        // http://localhost:8081/2
        // 2 é um valor variavel
        get("/:id", (req, res) -> {
            Gson gson = new Gson();
            PontoDeInteresseService ps = new PontoDeInteresseService();

            int id = Integer.parseInt(req.params(":id"));

            var result = ps.pegarPontoDeInteressePorId(id);

            if (result == null) {
                res.status(404);
                return gson.toJson("Ponto de interesse não encontrado");
            }

            res.status(200);
            return gson.toJson(result);
        });

        // http://localhost:8081/
        post("/", (req, res) -> {
            Gson gson = new Gson();
            PontoDeInteresseService ps = new PontoDeInteresseService();

            PontoDeInteresse bodyPontoDeInteresse = gson.fromJson(req.body(), PontoDeInteresse.class);

            var result = ps.criarPontoDeInteresse(bodyPontoDeInteresse);

            if (result == null) {
                res.status(400);
                return gson.toJson("Ponto de interesse invalido");
            }

            res.status(201);
            return gson.toJson(result);
        });

        // http://localhost:8081/
        put("/:id", (req, res) -> {
            Gson gson = new Gson();
            PontoDeInteresseService ps = new PontoDeInteresseService();

            int id = Integer.parseInt(req.params(":id"));
            PontoDeInteresse bodyPontoDeInteresse = gson.fromJson(req.body(), PontoDeInteresse.class);

            var result = ps.atualizarPontoDeInteresse(bodyPontoDeInteresse, id);

            if (!result) {
                res.status(400);
                return gson.toJson("Ponto de interesse invalido");
            }

            res.status(201);
            return gson.toJson(result);
        });

        // http://localhost:8081/id
        // 11 é um valor variavel
        delete("/:id", (req, res) -> {
            Gson gson = new Gson();
            PontoDeInteresseService ps = new PontoDeInteresseService();

            int id = Integer.parseInt(req.params(":id"));

            var result = ps.deletarPontoDeInteresse(id);

            if (!result) {
                res.status(404);
                return gson.toJson("Ponto de interesse não encontrado");
            }

            res.status(204);
            return "";
        });

        exception(RuntimeException.class, (exception, request, response) -> {
            Gson gson = new Gson();

            response.status(500);

            Map<String, String> error = new HashMap<>();
            error.put("message", "Ocorreu um problema interno no servidor");
            error.put("error", exception.getMessage());

            response.body(gson.toJson(error));
        });

    }
}
