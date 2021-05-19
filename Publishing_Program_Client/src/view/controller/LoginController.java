/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communication.Communication;
import domain.classes.Autor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.constants.Constants;
import view.coordinator.ViewCoordinator;
import view.form.FrmLogin;

/**
 *
 * @author hatch
 */
public class LoginController {
    private final FrmLogin frmLogin;

    public LoginController(FrmLogin frmLogin) {
        this.frmLogin = frmLogin;
    }
    
    public void openForm() {
        addActionListeners();
        frmLogin.setVisible(true);
    }

    private void addActionListeners() {
        frmLogin.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String username = frmLogin.getTxtUsername().getText().trim();
                    String password = String.copyValueOf(frmLogin.getTxtPassword().getPassword());
                    if (!validate(username, password)) {
                        frmLogin.getLblError().setText("Oba polja moraju biti popunjena!");
                    }
                    
                    Autor autor = Communication.getInstance().login(username, password);
                    
                    JOptionPane.showMessageDialog(frmLogin, "Uspesno ste se ulogovali!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    
                    frmLogin.dispose();
                    ViewCoordinator.getInstance().addParam(Constants.CURRENT_AUTOR, autor);
                    
                    ViewCoordinator.getInstance().repaintMainForm();
//                    System.out.println(frmLogin.getParent());
//                    frmLogin.getParent().dispose();
//                    frmLogin.getParent().repaint();
//                    frmLogin.getParent().
                    //repaint mainform
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmLogin, ex.getMessage(), "Login error", JOptionPane.ERROR_MESSAGE);
                }
            }

            private boolean validate(String username, String password) {
                if (username.isEmpty() || password.isEmpty()){
                    return false;
                }
                return true;
            }
        });
    }
}
