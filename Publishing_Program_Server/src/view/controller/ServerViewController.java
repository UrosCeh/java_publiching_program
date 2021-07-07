/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.Controller;
import domain.classes.Autor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import server.Server;
import view.form.FrmServer;
import view.model.AuthorTableModel;

/**
 *
 * @author hatch
 */
public class ServerViewController {
    
    private static ServerViewController instance;
    private final FrmServer frmServer;
    private AuthorTableModel model;
    private Server server;

    public ServerViewController() {
        this.frmServer = new FrmServer();
    }

    public static ServerViewController getInstance() {
        if (instance == null) {
            instance = new ServerViewController();
        }
        return instance;
    }
    
    public void startForm() {
        addActionListeners();
        frmServer.setVisible(true);
        try {
            fillTable();
        } catch (Exception ex) {
            Logger.getLogger(ServerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addActionListeners() {
        frmServer.getBtnStart().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                server = new Server();
            }
        });
        
        frmServer.getBtnChangeDbConfig().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String dbName = frmServer.getTxtDbName().getText().trim();
                    String dbUrl = "jdbc:mysql://localhost:3306/"+dbName;
                    String dbUser = frmServer.getTxtUsername().getText();
                    String dbPass = String.copyValueOf(frmServer.getTxtPassword().getPassword());
                    if (dbUser.equals("") || dbName.equals("")) {
                        JOptionPane.showMessageDialog(frmServer, "Naziv baze i korisnicko ime ne smeju biti prazni!", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }   
                    
                    FileReader reader = new FileReader("resources/database.properties");
                    Properties prop = new Properties();
                    prop.load(reader);
                    
                    prop.setProperty("url", dbUrl);
                    prop.setProperty("user", dbUser);
                    prop.setProperty("password", dbPass);
                    
                    prop.store(new FileOutputStream("resources/database.properties"), null);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(frmServer, "Nije pronadjen fajl!", "Greska", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frmServer, "Naziv baze i korisnicko ime ne smeju biti prazni!", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    private void fillTable() throws Exception {
        ArrayList<Autor> autori = Controller.getInstance().ucitajSveAutore();
        Map<String, String> map = fillMap(autori);
        model = new AuthorTableModel(autori, map);
        frmServer.getTblAuthor().setModel(model);
    }

    private HashMap<String, String> fillMap(ArrayList<Autor> autori) {
        Map<String, String> map = new HashMap<>();
        for (Autor autor: autori) {
            map.put(autor.getKorisnickoIme(), "offline");
        }
        return (HashMap<String, String>) map;
    }
    
    public void loginAuthor(Autor autor) {
        model.loggedInAuthor(autor);

    }
    
    public void logoutAuthor(Autor autor) {
        model.loggedOutAuthor(autor);
    }
    
}
