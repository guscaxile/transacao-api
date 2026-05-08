
# Transação API

Projeto desenvolvido para gerenciamento de transações e cálculo de estatísticas das transações realizadas em um certo período de tempo.

## Documentação da API

#### Receber Transações

```http
  POST /transacao
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `valor` | `BigDecimal` | **Obrigatório**. O valor da transação 
| `dataHora` | `OffsetDateTime` | **Obrigatório**. O horário que a transação ocorreu

#### Limpar Transações

```http
  DELETE /transacao
```

#### Calcular Estatísticas

```http
  GET /estatistica
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `intervaloSegundos` | `integer` | **Não Obrigatório** O padrão default é 60s  |
