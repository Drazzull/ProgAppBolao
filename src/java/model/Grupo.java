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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "grupo")
public class Grupo implements Serializable
{

    @Id
    @GeneratedValue
    @Column(name = "cod_grupo", nullable = false)
    private Integer codigo;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Transient
    private boolean editando;

    public Grupo()
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

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public boolean isEditando()
    {
        return editando;
    }

    public void setEditando(boolean editando)
    {
        this.editando = editando;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
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
        final Grupo other = (Grupo) obj;
        if (!Objects.equals(this.codigo, other.codigo))
        {
            return false;
        }
        return true;
    }

}
