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
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "aposta")
public class Aposta implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_aposta")
    private Integer codigo;

    @Audited
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_apostador")
    private Apostador apostador;

    @Audited
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_aposta", nullable = false)
    private Date dataAposta;

    @Audited
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_jogo")
    private Jogo jogo;

    @Audited
    @Column(name = "placar_time1", nullable = false)
    private Integer placarTime1;

    @Audited
    @Column(name = "placar_time2", nullable = false)
    private Integer placarTime2;

    @Audited
    @Column(name = "vencedor", length = 10, nullable = false)
    private String vencedor;

    @Transient
    private boolean editando;

    @Transient
    private Integer rev;

    @Transient
    private String revType;

    public Aposta()
    {
    }

    public Integer getCodigo()
    {
        return codigo;
    }

    public void setCodigo(Integer codigo)
    {
        this.codigo = codigo;
    }

    public Apostador getApostador()
    {
        return apostador;
    }

    public void setApostador(Apostador apostador)
    {
        this.apostador = apostador;
    }

    public Date getDataAposta()
    {
        return dataAposta;
    }

    public void setDataAposta(Date dataAposta)
    {
        this.dataAposta = dataAposta;
    }

    public Jogo getJogo()
    {
        return jogo;
    }

    public void setJogo(Jogo jogo)
    {
        this.jogo = jogo;
    }

    public Integer getPlacarTime1()
    {
        return placarTime1;
    }

    public void setPlacarTime1(Integer placarTime1)
    {
        this.placarTime1 = placarTime1;
    }

    public Integer getPlacarTime2()
    {
        return placarTime2;
    }

    public void setPlacarTime2(Integer placarTime2)
    {
        this.placarTime2 = placarTime2;
    }

    public String getVencedor()
    {
        return vencedor;
    }

    public void setVencedor(String vencedor)
    {
        this.vencedor = vencedor;
    }

    public boolean isEditando()
    {
        return editando;
    }

    public void setEditando(boolean editando)
    {
        this.editando = editando;
    }

    public Integer getRev()
    {
        return rev;
    }

    public void setRev(Integer rev)
    {
        this.rev = rev;
    }

    public String getRevType()
    {
        return revType;
    }

    public void setRevType(String revType)
    {
        this.revType = revType;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.codigo);
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
        final Aposta other = (Aposta) obj;
        if (!Objects.equals(this.codigo, other.codigo))
        {
            return false;
        }
        return true;
    }

}
