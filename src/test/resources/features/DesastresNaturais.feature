# language: pt
@regressivo
Funcionalidade: Cadastro de evento de desastres naturais
  Como usuário da API
  Quero cadastrar um novo evento de desastre natural
  Para que o registro seja salvo corretamente no sistema

  @padrão
  Cenário: Cadastro de evento sem sucesso ao passar o campo statusDesastre inválido
    Dado que eu tenha os seguintes dados do evento de desastre:
      | campo          | valor           |
      | numeroEvento   | 1               |
      | tipoDesastre   | Incêndio        |
      | local          | Rio de Janeiro  |
      | statusDesastre | ENCERRADO       |  # Você pode alterar isso para um valor inválido intencionalmente
      | dataEvento     | 2024-08-22      |
    Quando eu enviar a requisição para o endpoint "/desastres" de cadastro de eventos de desastres
    Então o status code da resposta deve ser 400
