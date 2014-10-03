/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

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
import javax.persistence.UniqueConstraint;

/**
 *
 * @author José Luiz
 */
@Entity
@Table(name = "apostador",uniqueConstraints = {
		@UniqueConstraint(columnNames = "cpf"),
		@UniqueConstraint(columnNames = "apelido")})
public class Apostador implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cod_apostador", nullable = false)
    private Integer codigo;
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    @Column(name = "cpf", length = 14, nullable = false)
    private String cpf;
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_nasc", nullable = false)
    private Date dataNascimento;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "apelido", length = 100)
    private String apelido;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_time")
    private Time timeDePreferencia;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_grupo")
    private Grupo grupo;

    public Apostador() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Time getTimeDePreferencia() {
        return timeDePreferencia;
    }

    public void setTimeDePreferencia(Time timeDePreferencia) {
        this.timeDePreferencia = timeDePreferencia;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Apostador other = (Apostador) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
    
    
}
