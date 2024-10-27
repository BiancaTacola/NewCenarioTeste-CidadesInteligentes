# language: pt

@regressivo
Funcionalidade: Cadastro de eventos de qualidade da água
  Como usuário da API
  Quero cadastrar um novo evento de qualidade da água
  Para que o registro seja salvo corretamente no sistema

  @padrão
  Cenário: Cadastro de evento de qualidade da água sem sucesso ao passar nível de contaminação inválido
    Dado que eu tenha os seguintes dados do evento de qualidade da água:
      | campo             | valor             |
      | numeroEvento      | 2                 |
      | tipoEvento        | Qualidade da Água |
      | local             | Lagoa Azul        |
      | nivelContaminacao | 15.0              |
      | dataEvento        | 2024-09-11        |
    Quando eu enviar a requisição para o endpoint "/qualidadeAgua" de cadastro de eventos de qualidade da água
    Então o status code da resposta deve ser 400
