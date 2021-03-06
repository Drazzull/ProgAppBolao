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
import javax.persistence.Transient;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "time_competicao")
public class TimeCompeticao implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_time_competicao", nullable = false)
    private Integer codigo;

    @Audited
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_time")
    private Time time;

    @Audited
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_competicao")
    private Competicao competicao;

    @Transient
    private Integer rev;

    @Transient
    private String revType;

    public TimeCompeticao()
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

    public Competicao getCompeticao()
    {
        return competicao;
    }

    public void setCompeticao(Competicao competicao)
    {
        this.competicao = competicao;
    }

    public Time getTime()
    {
        return time;
    }

    public void setTime(Time time)
    {
        this.time = time;
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
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.codigo);
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
        final TimeCompeticao other = (TimeCompeticao) obj;
        if (!Objects.equals(this.codigo, other.codigo))
        {
            return false;
        }
        return true;
    }
}
