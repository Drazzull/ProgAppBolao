/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author José Luiz
 */
public class RankingObj
{

    private Integer posicao;
    private String nome;
    private String apelido;
    private Long pontuacao;

    public RankingObj()
    {
    }

    public RankingObj(Integer posicao, String nome, String apelido, Long pontuacao)
    {
        this.posicao = posicao;
        this.nome = nome;
        this.apelido = apelido;
        this.pontuacao = pontuacao;
    }

    public RankingObj(String nome, String apelido, Long pontuacao)
    {
        this.nome = nome;
        this.apelido = apelido;
        this.pontuacao = pontuacao;
    }

    public RankingObj(String nome, Long pontuacao)
    {
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public Integer getPosicao()
    {
        return posicao;
    }

    public void setPosicao(Integer posicao)
    {
        this.posicao = posicao;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getApelido()
    {
        return apelido;
    }

    public void setApelido(String apelido)
    {
        this.apelido = apelido;
    }

    public Long getPontuacao()
    {
        return pontuacao;
    }

    public void setPontuacao(Long pontuacao)
    {
        this.pontuacao = pontuacao;
    }
}
