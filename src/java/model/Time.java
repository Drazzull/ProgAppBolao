/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Drazzull
 */
public class Time
{

    private long codigo;
    private String nome;
    private Date dataCadastro;
    private Date dataFundacao;
    private String cidade;
    private String site;
    private String email;
    private String fone;
    private String endereco;
    private String descricao;
    private boolean editando;

    public Time()
    {
        this.dataCadastro = new Date(System.currentTimeMillis());
    }

    /**
     * Obtém o código do time
     *
     * @return Código identificador do time
     */
    public long getCodigo()
    {
        return codigo;
    }

    /**
     * Define o identificador do time
     *
     * @param codigo Código identificador do time
     */
    public void setCodigo(long codigo)
    {
        this.codigo = codigo;
    }

    /**
     * Obtém o nome do time
     *
     * @return Nome do time
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * Define o nome do time
     *
     * @param nome Nome do time
     */
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    /**
     * Obtém a data de cadastro do time
     *
     * @return Data de cadastro do time
     */
    public Date getDataCadastro()
    {
        return dataCadastro;
    }

    /**
     * Define a data de cadastro do time
     *
     * @param dataCadastro Data de cadastro do time
     */
    public void setDataCadastro(Date dataCadastro)
    {
        this.dataCadastro = dataCadastro;
    }

    /**
     * Obtém a data de fundação do time
     *
     * @return Data de fundação do time
     */
    public Date getDataFundacao()
    {
        return dataFundacao;
    }

    /**
     * Define a data de fundação do time
     *
     * @param dataFundacao Data de fundação do time
     */
    public void setDataFundacao(Date dataFundacao)
    {
        this.dataFundacao = dataFundacao;
    }

    /**
     * Obtém a cidade do time
     *
     * @return Cidade do time
     */
    public String getCidade()
    {
        return cidade;
    }

    /**
     * Define a cidade do time
     *
     * @param cidade Cidade do time
     */
    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }

    /**
     * Obtém o site do time
     *
     * @return Site do time
     */
    public String getSite()
    {
        return site;
    }

    /**
     * Define o site do time
     *
     * @param site Site do time
     */
    public void setSite(String site)
    {
        this.site = site;
    }

    /**
     * Obtém o email do time
     *
     * @return Email do time
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Define o email do time
     *
     * @param email Email do time
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Obtém o telefone do time
     *
     * @return Telefone do time
     */
    public String getFone()
    {
        return fone;
    }

    /**
     * Define o telefone do time
     *
     * @param fone Telefone do time
     */
    public void setFone(String fone)
    {
        this.fone = fone;
    }

    /**
     * Obtém o endereço do time
     *
     * @return Endereço do time
     */
    public String getEndereco()
    {
        return endereco;
    }

    /**
     * Define o endereço do time
     *
     * @param endereco Endereço do time
     */
    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    /**
     * Obtém a descrição do time
     *
     * @return Descrição do time
     */
    public String getDescricao()
    {
        return descricao;
    }

    /**
     * Define a descrição do time
     *
     * @param descricao Descrição do time
     */
    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    /**
     * Verifica se o objeto está sendo editado
     *
     * @return true para edição e false para inclusão
     */
    public boolean isEditando()
    {
        return editando;
    }

    /**
     * Define se o objeto está sendo editado
     *
     * @param editando true para edição, false para inclusão
     */
    public void setEditando(boolean editando)
    {
        this.editando = editando;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 59 * hash + (int) (this.codigo ^ (this.codigo >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;

        }
        final Time other = (Time) obj;
        return this.codigo == other.codigo;
    }
}
