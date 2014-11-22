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
@Table(name = "jogo")
public class Jogo implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_jogo", nullable = false)
    private Integer codigo;

    @Audited
    // Conferir Mapeamento
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_time1", referencedColumnName = "cod_time_competicao")
    private TimeCompeticao time1;

    @Audited
    @Column(name = "placar_time1", nullable = false)
    private Integer placarTime1;

    @Audited
    // Conferir Mapeamento
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_time2", referencedColumnName = "cod_time_competicao")
    private TimeCompeticao time2;

    @Audited
    @Column(name = "placar_time2", nullable = false)
    private Integer placarTime2;

    @Audited
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_jogo")
    private Date dataJogo;

    @Audited
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vencedor", referencedColumnName = "cod_time")
    private Time vencedor;

    @Audited
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_rodada")
    private Rodada rodada;

    @Transient
    private boolean editando;

    @Transient
    private Integer rev;

    @Transient
    private String revType;

    public Jogo()
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

    public TimeCompeticao getTime1()
    {
        return time1;
    }

    public void setTime1(TimeCompeticao time1)
    {
        this.time1 = time1;
    }

    public Integer getPlacarTime1()
    {
        return placarTime1;
    }

    public void setPlacarTime1(Integer placarTime1)
    {
        this.placarTime1 = placarTime1;
    }

    public TimeCompeticao getTime2()
    {
        return time2;
    }

    public void setTime2(TimeCompeticao time2)
    {
        this.time2 = time2;
    }

    public Integer getPlacarTime2()
    {
        return placarTime2;
    }

    public void setPlacarTime2(Integer placarTime2)
    {
        this.placarTime2 = placarTime2;
    }

    public Date getDataJogo()
    {
        return dataJogo;
    }

    public void setDataJogo(Date dataJogo)
    {
        this.dataJogo = dataJogo;
    }

    public Time getVencedor()
    {
        return vencedor;
    }

    public void setVencedor(Time vencedor)
    {
        this.vencedor = vencedor;
    }

    public Rodada getRodada()
    {
        return rodada;
    }

    public void setRodada(Rodada rodada)
    {
        this.rodada = rodada;
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
        hash = 41 * hash + Objects.hashCode(this.codigo);
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
        final Jogo other = (Jogo) obj;
        if (!Objects.equals(this.codigo, other.codigo))
        {
            return false;
        }
        return true;
    }
}
