/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import javax.swing.JButton;
import javax.swing.JPanel;
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
        addButtons();
        frmMain.setVisible(true);
    }
    
    //action listeners
    
    public FrmMain getFrmMain() {
        return frmMain;
    }

    private void addButtons() {
        JPanel pnlHeaderBtns = frmMain.getPnlHeaderBtns();
        
        //napravi if loginku za logovanog usera
        //za svkao dugme dodaj actionlistenere
        
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(pnlHeaderBtns.getX(), pnlHeaderBtns.getY(), 200, 30);
        
        JButton btnSeeMyArticles = new JButton("See My Articles");
        btnSeeMyArticles.setBounds(pnlHeaderBtns.getX(), pnlHeaderBtns.getY()+40, 200, 30);
        
        JButton btnAddNewCategory = new JButton("Add New Category");
        btnAddNewCategory.setBounds(pnlHeaderBtns.getX(), pnlHeaderBtns.getY()+80, 200, 30);
        
        pnlHeaderBtns.add(btnLogin);
        pnlHeaderBtns.add(btnSeeMyArticles);
        pnlHeaderBtns.add(btnAddNewCategory);
    }
    
    //private void addArticles
    //private void fillCbCategories
    
}
