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
        frmLogin.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frmLogin.dispose();
            }
            
        });
        
        if (ViewCoordinator.getInstance().getParam(Constants.CURRENT_AUTOR) != null) {
            frmLogin.getLblError().setText("Da li zelite da se izlogujete?");
            frmLogin.getBtnLogin().setText("Da");
            frmLogin.getLblUsername().setVisible(false);
            frmLogin.getLblPassword().setVisible(false);
            frmLogin.getTxtUsername().setVisible(false);
            frmLogin.getTxtPassword().setVisible(false);
            
            frmLogin.getBtnLogin().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        Communication.getInstance().izlogujAutora();
                        ViewCoordinator.getInstance().removeParam(Constants.CURRENT_AUTOR, ViewCoordinator.getInstance().getParam(Constants.CURRENT_AUTOR));
                        ViewCoordinator.getInstance().repaintMainForm();
                        frmLogin.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frmLogin, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
        else {
            frmLogin.getBtnLogin().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        String username = frmLogin.getTxtUsername().getText().trim();
                        String password = String.copyValueOf(frmLogin.getTxtPassword().getPassword());
                        if (!validate(username, password)) {
                            frmLogin.getLblError().setText("Oba polja moraju biti popunjena!");
                        }
                        else {
                            frmLogin.getLblError().setText("");
                        }

                        Autor autor = Communication.getInstance().ulogujAutora(username, password);

                        JOptionPane.showMessageDialog(frmLogin, "Uspesan pristup!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);

                        frmLogin.dispose();
                        ViewCoordinator.getInstance().addParam(Constants.CURRENT_AUTOR, autor);
                        ViewCoordinator.getInstance().repaintMainForm();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frmLogin, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }

                private boolean validate(String username, String password) {
                    return !(username.isEmpty() || password.isEmpty());
                }
            });
        }
    }
}
