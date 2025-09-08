package com.api.business;

import com.api.business.Mapper.ConfeiteiroMapper;
import com.api.infrastructure.entitys.ConfeiteiroEntity;
import com.api.infrastructure.repository.ConfeiteiroRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfeiteiroService {

    private final ConfeiteiroRepository confeiteiroRepository;
    private final ConfeiteiroMapper mapper;

    public ConfeiteiroEntity salvar(ConfeiteiroEntity confeiteiro) {
        return confeiteiroRepository.save(confeiteiro);
    }

    public List<ConfeiteiroEntity> listarTodos() {
        return confeiteiroRepository.findAll();
    }

    public ConfeiteiroEntity buscarPorId(Integer id) {
        return confeiteiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Confeiteiro não encontrado"));
    }


    public ConfeiteiroEntity importarOuCriarConfeiteiro(ConfeiteiroEntity confeiteiro) throws IOException {
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
        confeiteiro.setSenha(nomeweb.trim().toUpperCase() + nascimento.split("e")[2]
                .replace(" ",""));

        return confeiteiroRepository.save(confeiteiro);
    }

}
