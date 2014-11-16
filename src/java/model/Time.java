/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Drazzull
 */
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "time")
public class Time implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_time", nullable = false)
    private Integer codigo;
    
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_cadastro", nullable = false)
    private Date dataCadastro;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_fundacao", nullable = false)
    private Date dataFundacao;
    
    @Column(name = "cidade", nullable = false)
    private String cidade;
    
    @Column(name = "site", nullable = true)
    private String site;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "fone", nullable = false)
    private String fone;
    
    @Column(name = "endereco", nullable = true)
    private String endereco;
    
    @Column(name = "descricao", nullable = false)
    private String descricao;
    
    @Transient
    private boolean editando;
    
    @Transient
    private Integer rev;

    @Transient
    private String revType;

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
        return this.codigo;
    }

    /**
     * Define o identificador do time
     *
     * @param codigo Código identificador do time
     */
    public void setCodigo(Integer codigo)
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
        return this.nome;
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
        return this.dataCadastro;
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

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public String getRevType() {
        return revType;
    }

    public void setRevType(String revType) {
        this.revType = revType;
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
        return Objects.equals(this.codigo, other.codigo);
    }
}
