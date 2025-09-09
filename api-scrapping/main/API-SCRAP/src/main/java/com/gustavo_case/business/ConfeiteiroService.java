package com.gustavo_case.business;

import com.gustavo_case.infrastructure.entitys.Confeiteiro;
import com.gustavo_case.infrastructure.repository.ConfeiteiroRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfeiteiroService {

    private final ConfeiteiroRepository confeiteiroRepository;

    public Confeiteiro salvar(Confeiteiro confeiteiro) {
        return confeiteiroRepository.save(confeiteiro);
    }

    public List<Confeiteiro> listarTodos() {
        return confeiteiroRepository.findAll();
    }

    public Confeiteiro buscarPorId(Integer id) {
        return confeiteiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Confeiteiro não encontrado"));
    }


    public Confeiteiro importarConfeiteiro(Confeiteiro confeiteiro) throws IOException {
        String url = confeiteiro.getUrl();

        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("A URL não pode ser nula ou vazia!");
        }

        Document pagina = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
                .referrer("https://www.google.com")
                .timeout(10_000)
                .get();

        String nomeweb = pagina.selectFirst("h1.firstHeading").text().replace(" ","");
        String nacionalidade = pagina
                .selectFirst("table.infobox tr:has(th:contains(Nacionalidade)) td")
                .text();
        String nascimento = pagina
                .selectFirst("table.infobox tr:has(th:contains(Nascimento)) td")
                .text().split("\\(")[0];

        confeiteiro.setNome(nomeweb);
        confeiteiro.setNacionalidade(nacionalidade);
        confeiteiro.setDataNascimento(nascimento);
        confeiteiro.setEmail(nomeweb.trim().toLowerCase() + "@gmail.com");
        confeiteiro.setSenha(nomeweb.trim().toLowerCase() + nascimento.split("e")[2]
                .replace(" ",""));

        return confeiteiroRepository.save(confeiteiro);
    }

}
