# 📌 Tabela: ponto_de_interesse

```sql
CREATE TABLE ponto_de_interesse (
    id serial primary key, -- NUNCA PODE MUDAR
    nome varchar(60) not null,
    latitude int not null,
    longitude int not null
);
```

---

# 🌱 Inserts iniciais

```sql
INSERT INTO ponto_de_interesse (nome, latitude, longitude) VALUES
    ('Praça Central', -19923456, -44567890),
    ('Museu de História', -19923890, -44567543),
    ('Parque das Flores', -19924000, -44568000),
    ('Biblioteca Municipal', -19923500, -44567000),
    ('Estação Ferroviária', -19925000, -44569000),
    ('Shopping Cidade', -19922000, -44566000),
    ('Teatro Municipal', -19924500, -44568500),
    ('Estádio Central', -19925500, -44569500),
    ('Universidade Federal', -19921000, -44565000),
    ('Hospital Regional', -19921500, -44565500);
```

---

# 🔎 SELECTs (consultas)

### Buscar todos os pontos (forma correta):

```sql
SELECT id, nome, latitude, longitude FROM ponto_de_interesse;
```

### Buscar ponto por ID:

```sql
SELECT id, nome, latitude, longitude FROM ponto_de_interesse WHERE id = ?;
```

🚫 Evite usar:

```sql
SELECT * FROM ponto_de_interesse;
```

---

# 🗑 DELETE

### Deletar por ID:

```sql
DELETE FROM ponto_de_interesse WHERE id = ?;
```

---

# ✏️ UPDATE

### Atualizar um ponto específico:

```sql
UPDATE ponto_de_interesse
SET nome = ?,
    latitude = ?,
    longitude = ?
WHERE id = ?;
```

🚫 Nunca faça:

```sql
UPDATE ponto_de_interesse
SET nome = ?, latitude = ?, longitude = ?;
```

> Isso atualiza **todos os registros da tabela**, o que provavelmente não é o que você deseja!

---

# 📖 Referência rápida SQL

### SELECT

```sql
SELECT colunas FROM tabela [WHERE condição];
```

### FROM

```sql
FROM nome_da_tabela
```

### WHERE

```sql
WHERE coluna = valor
```

### INSERT

```sql
INSERT INTO tabela (coluna1, coluna2) VALUES (valor1, valor2);
```

### UPDATE

```sql
UPDATE tabela SET coluna1 = valor1 WHERE condição;
```

### DELETE

```sql
DELETE FROM tabela WHERE condição;
```

---

# 📌 Explicação dos métodos SQL usados:

- **CREATE TABLE**: Cria a estrutura da tabela no banco de dados.
- **INSERT INTO**: Insere registros (linhas) na tabela.
- **SELECT**: Recupera dados da tabela. Pode ser com ou sem filtro (WHERE).
- **UPDATE**: Atualiza valores de colunas em linhas específicas (com WHERE).
- **DELETE**: Remove linhas da tabela com base em um critério (WHERE).

