package com.api.business;

import com.api.business.DTO.ReceitaRequestDTO;
import com.api.business.DTO.ReceitaResponseDTO;
import com.api.infrastructure.entitys.ReceitaEntity;
import com.api.infrastructure.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    public ReceitaResponseDTO importaOuCriarReceita(ReceitaRequestDTO receita) throws IOException, SQLException {
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


    public ReceitaEntity salvar(ReceitaEntity receita) {
        return receitaRepository.save(receita);
    }

    public List<ReceitaEntity> buscarPorTitulo(String titulo) {
        return receitaRepository.findReceitaByTituloIgnoreCase(titulo);
    }


    public List<ReceitaEntity>listarReceitas(){
        return  receitaRepository.findAll();
    }


    public void deletarPorId(Integer id) {
        ReceitaEntity receita = receitaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException());
        receitaRepository.delete(receita);
    }

    public ReceitaEntity BuscarPorId(Integer id) {
        return receitaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException());
    }

    public ReceitaEntity atualizarReceitaPorId(Integer id, ReceitaEntity receita) {
        ReceitaEntity receitaNova = receitaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException());
        receitaNova.setTitulo(receita.getTitulo());
        receitaNova.setDescricao(receita.getDescricao());
        return receitaRepository.save(receitaNova);
    }
}
