# language: pt

@APIController
Funcionalidade: Empregado-Controller

@Cadastrar
Cenário: Cadastrar Usuário
	Dado que eu tenha os dados para cadastrar o usuario
		| nome            | sexo | cpf            | departamento | admissao    | cargo                          | salario  |comissao | tipo da contratacao |
    | Teste API Lucas | m    | 706.084.240-30 | 1            | 01/01/2010 | Analista de testes de Software | 7.000,00 |500,00   | clt                 |
  Quando enviar requisicao "POST" para inmetrics-api
  Então validar 202 retorno

@ListarCadastrado  
Cenário: Listar Usuário Cadastrados
	Dado que eu tenha os dados do usuario cadastrado
  Quando enviar requisicao "GET" com "id" para inmetrics-api
  Então validar 202 retorno
  
@ListarAll
Cenário: Listar Todos Usuários
	Dado que eu envie requisicao "GET" para inmetrics-api
  Então validar 200 retorno
 
@Alterar
Cenário: Alterar Usuário
	Dado que eu tenha os dados para alterar o usuario
		| nome          | sexo | cpf            | departamento | admissao   | cargo                          | salario  |comissao | tipo da contratacao |
    | Teste Lucas B | m    | 706.084.240-30 | 1            | 01/01/2010 | Analista de testes alterado API| 7.000,00 |500,00   | clt                 |
  Quando enviar requisicao "GET" com "id" para inmetrics-api
  Então validar 202 retorno
  
  