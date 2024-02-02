# API REST Java com Spring Boot para Gerenciamento de Livros (Library)
<p>Este é um projeto de API REST construído com Spring Boot para gerenciar livros em uma biblioteca. A API fornece endpoints para realizar operações CRUD (Create, Read, Update, Delete) em livros, bem como filtrar livros por diferentes critérios.</p>

## ⚙️ Principais funcionalidades 

- [x] Cadastro e atualização de um livro
- [x] Listagem totais de livros, por Status, ou nome 
- [x] Exclusão de livros
- [x] Alterar Status do livro
- [x] Visualização do histórico de alteração do livro  
</br></br>

## :green_circle: Endpoints

| HTTP Method | Endpoint                          |
|-------------|-----------------------------------|
| `GET`       | /livros                           |
| `GET`       | /livros/porStatus?status=         |
| `GET`       | /livros/porTitulo?palavraChave=   |
| `DELETE`    | /livros/{id}                      |
| `POST`      | /livros                           |
| `PUT`       | /livros/{id}/alterar-status       |

## :mechanical_arm: Resultados
`GET` http://localhost:8080/livros?page1
```json
{
	"content": [
		{
			"id": 1,
			"titulo": "A Herança Sombria",
			"autor": "Silvia Moreno-Garcia",
			"ano_publicacao": 0,
			"genero": "Horror Gótico",
			"isbn": "9788532531519",
			"status": "DISPONIVEL"
		},
		{
			"id": 3,
			"titulo": "O Segredo do Abismo",
			"autor": "Stephen King",
			"ano_publicacao": 0,
			"genero": "Suspense",
			"isbn": "9788532531625",
			"status": "EMPRESTADO"
		},
```
`GET` http://localhost:8080/livros/porStatus?status=EMPRESTADO&page=0
```json
{
	"content": [
		{
			"id": 3,
			"titulo": "O Segredo do Abismo",
			"autor": "Stephen King",
			"ano_publicacao": 0,
			"genero": "Suspense",
			"isbn": "9788532531625",
			"status": "EMPRESTADO"
		},
		{
			"id": 7,
			"titulo": "O Hospício Abandonado",
			"autor": "H.P. Lovecraft",
			"ano_publicacao": 1920,
			"genero": "Horror Cósmico",
			"isbn": "9788532531953",
			"status": "EMPRESTADO"
		},
```
`GET` http://localhost:8080/livros/porTitulo?palavraChave=percy&page=0
```json
{
	"content": [
		{
			"id": 31,
			"titulo": "Percy Jackson: O Ladrão de Raios",
			"autor": "Rick Riordan",
			"ano_publicacao": 2005,
			"genero": "Fantasia",
			"isbn": "9788501031217",
			"status": "DISPONIVEL"
		},
		{
			"id": 32,
			"titulo": "Percy Jackson: O Mar de Monstros",
			"autor": "Rick Riordan",
			"ano_publicacao": 2006,
			"genero": "Fantasia",
			"isbn": "9788501031224",
			"status": "EMPRESTADO"
		},
		{
			"id": 33,
			"titulo": "Percy Jackson: A Maldição do Titã",
			"autor": "Rick Riordan",
			"ano_publicacao": 2007,
			"genero": "Fantasia",
			"isbn": "9788501031231",
			"status": "DISPONIVEL"
		},
```
`POST` http://localhost:8080/livros

Request Body:
```json
{
  "titulo": "Objetos Cortantes",
  "autor": "Gillian Flynn",
  "ano_publicacao": 2006,
  "genero": "Suspense Psicológico",
  "isbn": "9788580574427",
  "status": "EMPRESTADO"
}
```
Response Body
```json
{
	"id": 37,
	"titulo": "Objetos Cortantes",
	"autor": "Gillian Flynn",
	"ano_publicacao": 2006,
	"genero": "Suspense Psicológico",
	"isbn": "9788580574427",
	"status": "EMPRESTADO"
}
```
`PUT` http://localhost:8080/livros/{37}/alterar-status

Request Body

```json
{
  "status": "DISPONIVEL"
}
```
Response Body

```json
{
	"id": 37,
	"titulo": "Objetos Cortantes",
	"autor": "Gillian Flynn",
	"ano_publicacao": 2006,
	"genero": "Suspense Psicológico",
	"isbn": "9788580574427",
	"status": "DISPONIVEL"
}
```




