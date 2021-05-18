/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import server.Server;
import view.form.FrmServer;

/**
 *
 * @author hatch
 */
public class ServerViewController {
    
    private static ServerViewController instance;
    private FrmServer frmServer;
    private Server server;

    private ServerViewController() {
        frmServer = new FrmServer();
    }
    
    public static ServerViewController getInstance() {
        if (instance == null) {
            instance = new ServerViewController();
        }
        return instance;
    }
    
    public void startForm() {
        frmServer.setVisible(true);
        addActionListeners();
    }

    private void addActionListeners() {
        frmServer.getBtnStart().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                server = new Server();
                server.startServer();
            }
        });
    }
    
    
}
