# language: pt

@regressivo
Funcionalidade: Cadastro de eventos de qualidade do ar
  Como usuário da API
  Quero cadastrar um novo evento de qualidade do ar
  Para que o registro seja salvo corretamente no sistema

  @padrão
  Cenário: Cadastro de evento de qualidade do ar
    Dado que eu tenha os seguintes dados do evento de qualidade do ar:
    | campo         | valor         |
    | numeroEvento  | 1             |
    | tipoEvento    | "Poluição"    |
    | local         | "Zona Sul"    |
    | nivelPoluicao | 3             |
    | dataEvento    | "2024-10-27"  |
  Quando eu enviar a requisição para o endpoint "/qualidadeAr" de cadastro de eventos de qualidade do ar
  Então o status code da resposta deve ser 400