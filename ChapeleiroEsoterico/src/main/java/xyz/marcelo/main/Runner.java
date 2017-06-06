package xyz.marcelo.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.pmw.tinylog.Logger;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import xyz.marcelo.model.Atleta;
import xyz.marcelo.model.Formacao;
import xyz.marcelo.model.Time;

// https://github.com/wgenial/cartrolandofc/blob/master/nova-api.md
public class Runner
{
    // Lista de todos os jogadores (retorna todas as informações)
    private static final String URL_API_TODOS_JOGADORES = "https://api.cartolafc.globo.com/atletas/mercado";
    private static final AtomicReference<Time> MELHOR_TIME_GLOBAL = new AtomicReference<>(Time.VAZIO);

    private static List<Atleta> ATLETAS = new ArrayList<>();
    private static Random RANDOM = new Random();
    private static int THREADS, ORCAMENTO;

    public static void main(String[] args) throws Exception
    {
        THREADS = args.length == 2 ? Integer.parseInt(args[0]) : 1;
        ORCAMENTO = args.length == 2 ? Integer.parseInt(args[1]) : 10000;
        Logger.debug("Threads: {} Orcamento: {}\n", THREADS, ORCAMENTO);

        // faz um GET na API do Cartola para pegar os dados dos jogadores
        HttpResponse<JsonNode> response = Unirest.get(URL_API_TODOS_JOGADORES).asJson();

        // inicializa os dados sobre cada atleta
        JSONObject jsonObject = response.getBody().getArray().getJSONObject(0);
        JSONArray jsonArray = (JSONArray) jsonObject.get("atletas");
        for (int i = 0; i < jsonArray.length(); i++)
            ATLETAS.add(new Atleta(jsonArray.getJSONObject(i)));

        // remove atletas inelegíveis, isto é, com status diferente de Provável
        ATLETAS = ATLETAS.stream().filter(a -> a.getStatus().getNome().equals("Provável")).collect(Collectors.toList());

        for (int i = 0; i < THREADS; i++)
        {
            new Thread(() ->
            {
                // cria uma cópia da lista de atletas que será usada por cada thread
                List<Atleta> copia = new ArrayList<>(ATLETAS);
                Time melhorTime = Time.VAZIO;

                // cada thread gerará times aleatórios indefinidamente
                Logger.debug("Iniciando Thread [{}]\n", Thread.currentThread().getId());
                while (true)
                {
                    // caso o time gerado seja o melhor até o momento nesta thread, atualiza
                    Time timeAleatorio = geraTimeAleatorio(copia);
                    if (validaTime(timeAleatorio) && timeAleatorio.getFitness() > melhorTime.getFitness())
                    {
                        melhorTime = timeAleatorio;

                        // caso o time gerado seja o melhor até o momento em todas threads, atualiza
                        if (melhorTime.getFitness() > MELHOR_TIME_GLOBAL.get().getFitness())
                        {
                            MELHOR_TIME_GLOBAL.set(melhorTime);
                            Logger.debug("[Thread #{}]\n\t[{}] : {}\n", Thread.currentThread().getId(), MELHOR_TIME_GLOBAL.get().getFitness(), MELHOR_TIME_GLOBAL.get().toDetailedString());
                        }
                    }
                }
            }).start();
        }
    }

    private static Time geraTimeAleatorio(List<Atleta> atletas)
    {
        Atleta[] escalacao = new Atleta[12];
        for (int i = 0; i < 12; i++)
            escalacao[i] = atletas.get(RANDOM.nextInt(ATLETAS.size()));
        return new Time(escalacao);
    }

    private static boolean validaTime(Time t)
    {
        // valida orcamento
        if (t.getPreco() > ORCAMENTO)
            return false;

        // valida jogadores
        else if (t.getAtletas().stream().distinct().count() != 12)
            return false;

        // valida formacao
        else
        {
            int tec = t.getFormacao().getOrDefault("tec", 0);
            int gol = t.getFormacao().getOrDefault("gol", 0);
            int lat = t.getFormacao().getOrDefault("lat", 0);
            int zag = t.getFormacao().getOrDefault("zag", 0);
            int mei = t.getFormacao().getOrDefault("mei", 0);
            int ata = t.getFormacao().getOrDefault("ata", 0);

            for (Formacao f : Formacao.values())
                if (tec == f.getTec() && gol == f.getGol() && lat == f.getLat() && zag == f.getZag() && mei == f.getMei() && ata == f.getAta())
                    return true;

            return false;
        }
    }
}
