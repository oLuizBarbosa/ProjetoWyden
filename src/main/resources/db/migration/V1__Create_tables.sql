-- Tabela de usuários (CADASTRO)
CREATE TABLE usuarios (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE,
                          senha VARCHAR(100) NOT NULL,
                          grupo ENUM('ADMIN','USER','SUPPORT') NOT NULL,
                          telefone VARCHAR(11),
                          ativo BOOLEAN DEFAULT TRUE,
                          bloqueado BOOLEAN DEFAULT FALSE,
                          data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- Tabela de ocorrências
CREATE TABLE ocorrencias (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             titulo VARCHAR(200) NOT NULL,
                             descricao TEXT NOT NULL,
                             data_abertura DATETIME NOT NULL,
                             data_fechamento DATETIME,
                             status ENUM('ABERTO','EM_ANDAMENTO','FECHADO') NOT NULL,
                             prioridade ENUM('BAIXA','MEDIA','ALTA','URGENTE') NOT NULL,
                             usuario_id BIGINT NOT NULL,
                             FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
) ENGINE=InnoDB;

-- Tabela de comentários
CREATE TABLE comentarios (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             texto TEXT NOT NULL,
                             data_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             ocorrencia_id BIGINT NOT NULL,
                             usuario_id BIGINT NOT NULL,
                             FOREIGN KEY (ocorrencia_id) REFERENCES ocorrencias(id) ON DELETE CASCADE,
                             FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
) ENGINE=InnoDB;