# 🍽️ PROJETO DE EXTRAÇÃO DE DADOS DE RECEITAS E CHEFS - 
##**Projeto em Andamento ⚠️  **

Este projeto tem como objetivo **extrair dados de receitas e chefs/confeiteiros** de páginas públicas,  
utilizando **Java + Spring Boot + Jsoup**.  

> ⚠️ **Atenção:** Usar URLS da wikipedia ou panelinha.com. 

--------------------------

## 1️⃣ EXTRAÇÃO DE RECEITAS

- **URL de exemplo:**  
  `https://panelinha.com.br/receita/salada-de-folhas-com-carambola`  

- **O que é extraído:**  
  - Nome da receita  
  - Ingredientes  
  - Modo de preparo  


**Requisição no Postman:**  
POST http://localhost:8081/receita/importar


Body (JSON):
``
{
   "url": "https://panelinha.com.br/receita/salada-de-folhas-com-carambola"
}``

**Resposta esperada (JSON):**
```json
{
  "titulo": "Salada de folhas com carambola",
  "ingredientes": [
    "1 maço de alface",
    "1 unidade de carambola",
    "Sal a gosto"
  ],
  "descricao": "Misture as folhas e a carambola em uma tigela e tempere com sal."
}
````

-----------------------------------------------------------------

## 2️⃣ EXTRAÇÃO DE CHEFS / CONFEITEIROS
URL de exemplo:
https://pt.wikipedia.org/wiki/Henrique_Foga%C3%A7a

O que é extraído:

-Nome completo

-Nacionalidade

-Data de nascimento

-Email (gerado automaticamente concatenando chefe + "gmail.com")

-Senha (Nome do chefe em minusculo + data de nascimento // exemplo:henriquefogaça1974)

Requisição no Postman:
POST http://localhost:8081/confeiteiro/importar
Body (JSON):
``
{
  "url": "https://pt.wikipedia.org/wiki/Henrique_Foga%C3%A7a"
}``
````
{
  "nome": "Henrique Fogaça",
  "nacionalidade": "Brasileiro",
  "dataNascimento": "1974-11-07",
  "email": "henriquefogaca@gmail.com",
  "senha": "henriquefogaca1974"
}
````
⚠️ Observações importantes
✅ As URLs devem ser válidas e acessíveis (wikipedia ou panelinha.com)

✅ O scraping respeita a estrutura atual das páginas; mudanças nos sites podem exigir ajustes nos selectors CSS

✅ Os e-mails e senhas dos chefs são gerados automaticamente a partir do nome e data de nascimento, removendo espaços e acentos

✅ Para testar, use Postman ou qualquer cliente HTTP enviando JSON no corpo da requisição (raw → JSON)

✅ Banco H2 rodando na porta 8081
