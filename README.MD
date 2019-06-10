Requisitos

Aplicação para controle pessoal de gastos. O usu�rio poder� se cadastrar. O usu�rio quem informar� seus gastos atrav�s de um formul�rio e a qual �rea este gasto est� vinculado (Entretenimento, Sa�de, Educa��o, Alimenta��o, ...). 

Terá um dashboard com uma tabela na qual vemos os cadastros de gastos identificados por �rea (Entretenimento, Sa�de, Educa��o, Alimenta��o, ...) e ordenados por data.

Ter� um gr�fico para visualizar o total de gastos de uma �rea ao longo de um per�odo em meses.

Requisitos (n�o funcionais)

Banco de dados MySQL ou SQL Server (de acordo com a tecnologia)
Bootstrap no front-end
Gr�fico sem jQuery (me procurar para validar)
MVC
Separa��o em camadas (MVC, reposit�rio, servi�o)
Usar tecnologias padr�o para valida��o, acesso a dados e seguran�a (Bean Validation, JPA, etc)

SET @@global.time_zone = '+3:00';
SET GLOBAL lc_time_names=pt_BR;

--
insert into produto values(null,'Alimentacao', '2019-01-09 14:19:14','Pastel', 5, 1 );
insert into produto values(null,'Alimentacao', '2019-01-10 14:19:14','Costela com Batata', 32, 1 );
insert into produto values(null,'Alimentacao', '2019-01-11 14:19:14','Peixe frito', 35, 1 );
insert into produto values(null,'Alimentacao', '2019-01-12 14:19:14','Camar�o frito', 60, 1 );
insert into produto values(null,'Alimentacao', '2019-01-13 14:19:14','Pizza', 45, 1 );

insert into produto values(null,'Alimentacao', '2019-02-07 14:19:14','Batata frita', 15, 1 );
insert into produto values(null,'Alimentacao', '2019-02-08 14:19:14','X-tudo', 8, 1 );
insert into produto values(null,'Alimentacao', '2019-02-09 14:19:14','Pastel', 5, 1 );
insert into produto values(null,'Alimentacao', '2019-02-10 14:19:14','Costela com Batata', 32, 1 );
insert into produto values(null,'Alimentacao', '2019-02-11 14:19:14','Peixe frito', 35, 1 );
insert into produto values(null,'Alimentacao', '2019-02-12 14:19:14','Camar�o frito', 60, 1 );
insert into produto values(null,'Alimentacao', '2019-02-13 14:19:14','Pizza', 45, 1 );

insert into produto values(null,'Alimentacao', '2019-03-07 14:19:14','Batata frita', 15, 1 );
insert into produto values(null,'Alimentacao', '2019-03-08 14:19:14','X-tudo', 8, 1 );
insert into produto values(null,'Alimentacao', '2019-03-09 14:19:14','Pastel', 5, 1 );
insert into produto values(null,'Alimentacao', '2019-03-12 14:19:14','Camar�o frito', 60, 1 );
insert into produto values(null,'Alimentacao', '2019-03-13 14:19:14','Pizza', 45, 1 );

insert into produto values(null,'Alimentacao', '2019-04-13 14:19:14','Pizza', 45, 1 );