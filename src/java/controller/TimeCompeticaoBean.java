/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CompeticaoDao;
import dao.TimeDao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Competicao;
import model.Time;

/**
 *
 * @author Drazzull
 */
@ManagedBean
@SessionScoped
public class TimeCompeticaoBean
{

    TimeDao timeDao = new TimeDao();
    private Time time = new Time();

    CompeticaoDao competicaoDao = new CompeticaoDao();
    private Competicao competicao = new Competicao();

}
