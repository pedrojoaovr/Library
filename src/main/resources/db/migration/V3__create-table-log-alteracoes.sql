CREATE TABLE log_transacoes_livros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    livro_id INT NOT NULL,
    data_transacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status_anterior VARCHAR(20) NOT NULL,
    status_atual VARCHAR(20) NOT NULL,
    FOREIGN KEY (livro_id) REFERENCES livros(id)
);
