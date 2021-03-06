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
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "apostador", uniqueConstraints =
{
    @UniqueConstraint(columnNames = "cpf"),
    @UniqueConstraint(columnNames = "apelido")
})
public class Apostador implements Serializable
{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cod_apostador")
    private Integer codigo;

    @Audited
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Audited
    @Column(name = "cpf", length = 14, nullable = false)
    private String cpf;

    @Audited
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_nasc", nullable = false)
    private Date dataNascimento;

    @Audited
    @Column(name = "email", length = 100)
    private String email;

    @Audited
    @Column(name = "apelido", length = 100)
    private String apelido;

    @Audited
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_time")
    private Time timeDePreferencia;

    @Audited
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_grupo")
    private Grupo grupo;

    @Transient
    private boolean editando;

    @Transient
    private Integer rev;

    @Transient
    private String revType;

    public Apostador()
    {
    }

    public Apostador(String nome, String cpf, Date dataNascimento, String email, String apelido, Time timeDePreferencia, Grupo grupo) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.apelido = apelido;
        this.timeDePreferencia = timeDePreferencia;
        this.grupo = grupo;
    }

    public Integer getCodigo()
    {
        return codigo;
    }

    public void setCodigo(Integer codigo)
    {
        this.codigo = codigo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public Date getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getApelido()
    {
        return apelido;
    }

    public void setApelido(String apelido)
    {
        this.apelido = apelido;
    }

    public Time getTimeDePreferencia()
    {
        return timeDePreferencia;
    }

    public void setTimeDePreferencia(Time timeDePreferencia)
    {
        this.timeDePreferencia = timeDePreferencia;
    }

    public Grupo getGrupo()
    {
        return grupo;
    }

    public void setGrupo(Grupo grupo)
    {
        this.grupo = grupo;
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
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.codigo);
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
        final Apostador other = (Apostador) obj;
        if (!Objects.equals(this.codigo, other.codigo))
        {
            return false;
        }
        return true;
    }

}
