PROJETO DE EXTRAÇÃO DE DADOS DE RECEITAS E CHEFS

Este projeto tem como objetivo extrair dados de receitas e chefs/confeiteiros a partir de páginas públicas,
utilizando Java + Spring Boot + Jsoup.

Ele possui dois tipos principais de extração:

1. EXTRAÇÃO DE RECEITAS

- URL de exemplo: https://panelinha.com.br/receita/salada-de-folhas-com-carambola
- O sistema faz scraping da página e captura dados como:
  - Nome da receita
  - Ingredientes
  - Modo de preparo

Requisição no Postman:

POST http://localhost:8081/receita/importar

Body (JSON):
{
    "url": "https://panelinha.com.br/receita/salada-de-folhas-com-carambola"
}

Resposta esperada:
{
    "nome": "Salada de folhas com carambola",
    "ingredientes": [...],
    "modoPreparo": [...]
}

2. EXTRAÇÃO DE CHEFS / CONFEITEIROS

- URL de exemplo: https://pt.wikipedia.org/wiki/Henrique_Foga%C3%A7a
- O sistema faz scraping do infobox da Wikipédia e captura dados como:
  - Nome completo
  - Nacionalidade
  - Data de nascimento
  - Email (gerado automaticamente)
  - Senha (gerada automaticamente)

Requisição no Postman:

POST http://localhost:8081/confeiteiro/importar

Body (JSON):
{
    "url": "https://pt.wikipedia.org/wiki/Henrique_Foga%C3%A7a"
}

Resposta esperada:
{
    "nome": "Henrique Fogaça",
    "nacionalidade": "Brasileiro",
    "nascimento": "1974-11-27",
    "email": "henriquefogaca@gmail.com",
    "senha": "henriquefogaca123"
}

OBSERVAÇÕES IMPORTANTES

- As URLs devem ser válidas e acessíveis.
- O scraping respeita a estrutura atual das páginas; mudanças na página podem exigir ajustes nos selectors CSS.
- O e-mail e senha dos chefs são gerados automaticamente a partir do nome, removendo espaços e acentos.
- Para testar, use Postman ou qualquer cliente HTTP enviando JSON no corpo da requisição (raw → JSON).
