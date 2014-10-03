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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author José Luiz
 */
@Entity
@Table(name = "jogo")
public class Jogo implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "cod_jogo", nullable = false)
    private Integer codigo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_time")
    @Column(name = "cod_time1")
    private Time time1;
    @Column(name = "placar_time1", nullable = false)
    private Integer placarTime1;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_time")
    @Column(name = "cod_time2")
    private Time time2;
    @Column(name = "placar_time2", nullable = false)
    private Integer placarTime2;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_jogo")
    private Date dataJogo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_time")
    @Column(name = "vencedor")
    private Time vencedor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_rodada")
    private Rodada rodada;

    public Jogo() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Time getTime1() {
        return time1;
    }

    public void setTime1(Time time1) {
        this.time1 = time1;
    }

    public Integer getPlacarTime1() {
        return placarTime1;
    }

    public void setPlacarTime1(Integer placarTime1) {
        this.placarTime1 = placarTime1;
    }

    public Time getTime2() {
        return time2;
    }

    public void setTime2(Time time2) {
        this.time2 = time2;
    }

    public Integer getPlacarTime2() {
        return placarTime2;
    }

    public void setPlacarTime2(Integer placarTime2) {
        this.placarTime2 = placarTime2;
    }

    public Date getDataJogo() {
        return dataJogo;
    }

    public void setDataJogo(Date dataJogo) {
        this.dataJogo = dataJogo;
    }

    public Time getVencedor() {
        return vencedor;
    }

    public void setVencedor(Time vencedor) {
        this.vencedor = vencedor;
    }

    public Rodada getRodada() {
        return rodada;
    }

    public void setRodada(Rodada rodada) {
        this.rodada = rodada;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.codigo);
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
        final Jogo other = (Jogo) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
    
}
