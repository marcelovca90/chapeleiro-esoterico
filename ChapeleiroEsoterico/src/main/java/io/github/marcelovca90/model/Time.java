package io.github.marcelovca90.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Time
{
    public static Time VAZIO = new Time(Estrategia.NENHUMA, new Atleta[0]);

    private Collection<Atleta> atletas;
    private double preco;
    private double pontos;
    private double tendencia;
    private double fitness;
    private Map<String, Integer> formacao;

    public Collection<Atleta> getAtletas()
    {
        return atletas;
    }

    public double getPreco()
    {
        return preco;
    }

    public double getPontos()
    {
        return pontos;
    }

    public double getTendencia()
    {
        return tendencia;
    }

    public double getFitness()
    {
        return fitness;
    }

    public Map<String, Integer> getFormacao()
    {
        return formacao;
    }

    public Time(Estrategia estrategia, Atleta... atletas)
    {
        this.atletas = new ArrayList<>();
        this.preco = 0;
        this.pontos = 0;
        this.tendencia = 0;
        this.formacao = new HashMap<>();

        for (Atleta a : atletas)
        {
            this.atletas.add(a);
            this.preco += a.getPreco();
            this.pontos += a.getPontos();
            this.tendencia += a.getTendencia();

            String abreviacao = a.getPosicao().getAbreviacao();
            this.formacao.putIfAbsent(abreviacao, 0);
            this.formacao.put(abreviacao, this.formacao.get(abreviacao) + 1);
        }

        switch (estrategia)
        {
            case NENHUMA:
                this.fitness = Double.MIN_VALUE;
                break;

            case PONTOS:
                this.fitness = this.pontos;
                break;

            case TENDENCIA:
                this.fitness = this.tendencia;
                break;
        }
    }

    @Override
    public String toString()
    {
        return "Time [formacao=" + formacao + ", preco=" + preco + ", pontos=" + pontos + ", tendencia=" + tendencia + ", fitness=" + fitness + " + atletas=" + atletas + "]";
    }

    public String toShortString()
    {
        return "Time [formacao=" + formacao.values() + ", preco=" + preco + ", pontos=" + pontos + ", tendencia=" + tendencia + ", fitness=" + fitness + "]";
    }

    public String toDetailedString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Time [formacao=" + formacao + ", preco=" + preco + ", pontos=" + pontos + ", tendencia=" + tendencia + ", fitness=" + fitness + ", atletas=");

        atletas.forEach(a -> sb.append("\n\t\t" + a.toShortString()));

        sb.append("]");

        return sb.toString();
    }
}
