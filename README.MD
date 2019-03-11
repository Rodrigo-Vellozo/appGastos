Requisitos

Aplicação para controle pessoal de gastos. O usuário poderá se cadastrar. O usuário quem informará seus gastos através de um formulário e a qual área este gasto está vinculado (Entretenimento, Saúde, Educação, Alimentação, ...). 

Terá um dashboard com uma tabela na qual vemos os cadastros de gastos identificados por área (Entretenimento, Saúde, Educação, Alimentação, ...) e ordenados por data.

Terá um gráfico para visualizar o total de gastos de uma área ao longo de um período em meses.

Requisitos (não funcionais)

Banco de dados MySQL ou SQL Server (de acordo com a tecnologia)
Bootstrap no front-end
Gráfico sem jQuery (me procurar para validar)
MVC
Separação em camadas (MVC, repositório, serviço)
Usar tecnologias padrão para validação, acesso a dados e segurança (Bean Validation, JPA, etc)

SET @@global.time_zone = '+3:00';
SET GLOBAL lc_time_names=pt_BR;

--
insert into produto values(null,'Alimentacao', '2019-01-09 14:19:14','Pastel', 5, 1 );
insert into produto values(null,'Alimentacao', '2019-01-10 14:19:14','Costela com Batata', 32, 1 );
insert into produto values(null,'Alimentacao', '2019-01-11 14:19:14','Peixe frito', 35, 1 );
insert into produto values(null,'Alimentacao', '2019-01-12 14:19:14','Camarão frito', 60, 1 );
insert into produto values(null,'Alimentacao', '2019-01-13 14:19:14','Pizza', 45, 1 );

insert into produto values(null,'Alimentacao', '2019-02-07 14:19:14','Batata frita', 15, 1 );
insert into produto values(null,'Alimentacao', '2019-02-08 14:19:14','X-tudo', 8, 1 );
insert into produto values(null,'Alimentacao', '2019-02-09 14:19:14','Pastel', 5, 1 );
insert into produto values(null,'Alimentacao', '2019-02-10 14:19:14','Costela com Batata', 32, 1 );
insert into produto values(null,'Alimentacao', '2019-02-11 14:19:14','Peixe frito', 35, 1 );
insert into produto values(null,'Alimentacao', '2019-02-12 14:19:14','Camarão frito', 60, 1 );
insert into produto values(null,'Alimentacao', '2019-02-13 14:19:14','Pizza', 45, 1 );

insert into produto values(null,'Alimentacao', '2019-03-07 14:19:14','Batata frita', 15, 1 );
insert into produto values(null,'Alimentacao', '2019-03-08 14:19:14','X-tudo', 8, 1 );
insert into produto values(null,'Alimentacao', '2019-03-09 14:19:14','Pastel', 5, 1 );
insert into produto values(null,'Alimentacao', '2019-03-12 14:19:14','Camarão frito', 60, 1 );
insert into produto values(null,'Alimentacao', '2019-03-13 14:19:14','Pizza', 45, 1 );

insert into produto values(null,'Alimentacao', '2019-04-13 14:19:14','Pizza', 45, 1 );