package xyz.marcelo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Time
{
    public static Time VAZIO = new Time(new Atleta[0]);

    private Collection<Atleta> atletas;
    private double pontos;
    private double preco;
    private double precoPorPonto;
    private Map<String, Integer> formacao;

    public Collection<Atleta> getAtletas()
    {
        return atletas;
    }

    public synchronized double getPontos()
    {
        return pontos;
    }

    public double getPreco()
    {
        return preco;
    }

    public double getPrecoPorPonto()
    {
        return precoPorPonto;
    }

    public Map<String, Integer> getFormacao()
    {
        return formacao;
    }

    public Time(Atleta... atletas)
    {
        this.atletas = new ArrayList<>();
        this.pontos = 0;
        this.preco = 0;
        this.precoPorPonto = 0;
        this.formacao = new HashMap<>();

        for (Atleta a : atletas)
        {
            this.atletas.add(a);
            this.pontos += a.getPontos();
            this.preco += a.getPreco();

            String abreviacao = a.getPosicao().getAbreviacao();
            this.formacao.putIfAbsent(abreviacao, 0);
            this.formacao.put(abreviacao, this.formacao.get(abreviacao) + 1);
        }

        this.precoPorPonto = (this.preco / this.pontos);
    }

    @Override
    public String toString()
    {
        return "Time [atletas=" + atletas + ", pontos=" + pontos + ", preco=" + preco + ", precoPorPonto=" + precoPorPonto + "]";
    }

    public String toShortString()
    {
        return "Time [pontos=" + pontos + ", preco=" + preco + ", precoPorPonto=" + precoPorPonto + ", formacao=" + formacao + "]";
    }

    public String toDetailedString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Time [pontos=" + pontos + ", preco=" + preco + ", precoPorPonto=" + precoPorPonto + ", formacao=" + formacao + ", atletas=");

        atletas.forEach(a -> sb.append("\n\t" + a));

        sb.append("]");

        return sb.toString();
    }
}
