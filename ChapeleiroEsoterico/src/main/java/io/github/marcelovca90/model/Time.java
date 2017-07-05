package io.github.marcelovca90.model;

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
    private double fitness;
    private Map<String, Integer> formacao;

    public Collection<Atleta> getAtletas()
    {
        return atletas;
    }

    public double getPontos()
    {
        return pontos;
    }

    public double getPreco()
    {
        return preco;
    }

    public double getFitness()
    {
        return fitness;
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
        this.fitness = 0;
        this.formacao = new HashMap<>();

        for (Atleta a : atletas)
        {
            this.atletas.add(a);
            this.pontos += a.getPontos();
            this.preco += a.getPreco();
            this.fitness += a.getFitness();

            String abreviacao = a.getPosicao().getAbreviacao();
            this.formacao.putIfAbsent(abreviacao, 0);
            this.formacao.put(abreviacao, this.formacao.get(abreviacao) + 1);
        }
    }

    @Override
    public String toString()
    {
        return "Time [formacao=" + formacao + ", pontos=" + pontos + ", preco=" + preco + ", fitness=" + fitness + " + atletas=" + atletas + "]";
    }

    public String toShortString()
    {
        return "Time [formacao=" + formacao.values() + ",pontos=" + pontos + ", preco=" + preco + ", fitness=" + fitness + "]";
    }

    public String toDetailedString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Time [formacao=" + formacao + ", pontos=" + pontos + ", preco=" + preco + ", fitness=" + fitness + ", atletas=");

        atletas.forEach(a -> sb.append("\n\t\t" + a.toShortString()));

        sb.append("]");

        return sb.toString();
    }
}
