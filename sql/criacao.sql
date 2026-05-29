CREATE TABLE Agendamento ( 
	id BIGINT AUTO_INCREMENT PRIMARY KEY, 
	data DATETIME NOT NULL, 
	nomeUsuario VARCHAR(100) NOT NULL, 
	cpfUsuario VARCHAR(14) NOT NULL, 
	emailUsuario VARCHAR(50) NOT NULL, 
	nomeAdvogado VARCHAR(100) NOT NULL, 
	tipoAtividade VARCHAR(50) NOT NULL,
	primeiraReuniao BOOLEAN NOT NULL DEFAULT TRUE,
	observacao TEXT
)

-- 1. Criar a tabela de usuários
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    perfil VARCHAR(20) NOT NULL -- Valores: 'ADMIN' ou 'USER'
) ENGINE=InnoDB;

-- 2. Adicionar a coluna 'usuario_id' na tabela de agendamentos existente
ALTER TABLE agendamentos ADD COLUMN usuario_id BIGINT;

-- 3. Criar a relação (Foreign Key) entre Agendamentos e Usuários
ALTER TABLE agendamentos 
ADD CONSTRAINT fk_agendamento_usuario 
FOREIGN KEY (usuario_id) REFERENCES usuarios(id);