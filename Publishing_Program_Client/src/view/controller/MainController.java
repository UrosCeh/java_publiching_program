/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import view.form.FrmMain;

/**
 *
 * @author hatch
 */
public class MainController {
    private final FrmMain frmMain;
    
    public MainController(FrmMain frmMain) {
        this.frmMain = frmMain;
    }

    public void openForm() {
        frmMain.setVisible(true);
    }
    
    //action listeners
    
    public FrmMain getFrmMain() {
        return frmMain;
    }
}
