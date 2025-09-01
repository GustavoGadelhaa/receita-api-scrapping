package com.gustavo_case.business;

import com.gustavo_case.infrastructure.entitys.Receita;
import com.gustavo_case.infrastructure.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    public Receita importaReceita(Receita receita) throws IOException, SQLException {
        String url = receita.getUrl();

        // Pegando HTML da página
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(10000)
                .get();

        // Seletores genéricos
        List<String> ingredientes = new ArrayList<>();
        for (Element li : document.select("div.blockIngredientListingsctn.jsBlockIngredientListingsCtn ul li")) {
            ingredientes.add(li.text());
        }

        String titulo = document.selectFirst("h1").text();
        String descricao = document.selectFirst("p").text();

        // Preço vem do POST
        receita.setIngredientes(ingredientes);
        receita.setTitulo(titulo);
        receita.setDescricao(descricao);
        return receitaRepository.save(receita);
    }


    public Receita salvar(Receita receita) {
        return receitaRepository.save(receita);
    }

    public List<Receita> buscarPorTitulo(String titulo) {
        return receitaRepository.findReceitaByTituloIgnoreCase(titulo);
    }


    public List<Receita>listarReceitas(){
        return  receitaRepository.findAll();
    }


    public void deletarPorId(Integer id) {
        Receita receita = receitaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException());
        receitaRepository.delete(receita);
    }

    public Receita BuscarPorId(Integer id) {
        return receitaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException());
    }

    public Receita atualizarReceitaPorId(Integer id, Receita receita) {
        Receita receitaNova = receitaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException());
        receitaNova.setTitulo(receita.getTitulo());
        receitaNova.setDescricao(receita.getDescricao());
        return receitaRepository.save(receitaNova);
    }
}
