create table IF NOT EXISTS conta (conta_key int primary key, nome_dono varchar, saldo decimal(10,2));

insert into conta (conta_key, nome_dono, saldo) values
                                                    (1, 'João', 1000.00),
                                                    (2, 'Maria', 2000.00),
                                                    (3, 'José', 3000.00),
                                                    (4, 'Ana', 4000.00),
                                                    (5, 'Pedro', 5000.00),
                                                    (6, 'Lucas', 6000.00),
                                                    (7, 'Fernanda', 7000.00),
                                                    (8, 'Carla', 8000.00),
                                                    (9, 'Roberto', 9000.00),
                                                    (10, 'Patrícia', 10000.00);

commit ;