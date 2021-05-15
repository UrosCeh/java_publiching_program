/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import domainClient.Article;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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
        addArticles();
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
    
    private void addArticles() {
        JPanel pnlAllArticles = frmMain.getPnlAllArticles();
        pnlAllArticles.setBackground(Color.BLACK);
        List<Article> articles = new ArrayList<>();
        
        Article a1 = new Article();
        a1.setArticleID(1);
        a1.setTitle("Article no 1");
        a1.setBody("Sport / Celebrity");
        a1.setWriter("Dominic Thiem");
        
        Article a2 = new Article();
        a2.setArticleID(2);
        a2.setTitle("Article no 2");
        a2.setBody("Sport / Film");
        a2.setWriter("Roger Federer");

        Article a3 = new Article();
        a3.setArticleID(3);
        a3.setTitle("Article no 3");
        a3.setBody("TV / Reality");
        a3.setWriter("Roger Federer");
        
        Article a4 = new Article();
        a4.setArticleID(4);
        a4.setTitle("Article no 4");
        a4.setBody("Animals");
        a4.setWriter("Rafel Nadal");
        
        articles.add(a1);
        articles.add(a2);
        articles.add(a3);
        articles.add(a4);
        
        int i = 0;
        
        for (Article article : articles) {
            JPanel pnlArticle = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            pnlArticle.setBounds(pnlAllArticles.getX(), pnlAllArticles.getY()+(i*220), pnlAllArticles.getWidth(), 200);
            
            JLabel lblTitle = new JLabel(article.getTitle(), SwingConstants.CENTER);
            JLabel lblCategory = new JLabel(article.getBody());
            JLabel lblWriter = new JLabel(article.getWriter());
            JButton btnRead = new JButton("Read More");
            btnRead.setPreferredSize(new Dimension(200,35));
            
            JButton btnEdit = new JButton("Edit");
            btnEdit.setPreferredSize(new Dimension(100,25));
            JButton btnDelete = new JButton("Delete");
            btnDelete.setPreferredSize(new Dimension(100,25));
            
            JButton btnPublish = new JButton("Publish");
            btnPublish.setPreferredSize(new Dimension(100,25));
            JButton btnUnpublish = new JButton("Unpublish");
            btnUnpublish.setPreferredSize(new Dimension(100,25));
            
            
            
            JPanel pnlLeft = new JPanel(new GridBagLayout());
            pnlLeft.setBackground(Color.gray);
            JPanel pnlRight = new JPanel(new GridBagLayout());
            pnlRight.setBackground(Color.gray);
            
            JPanel pnlCenter = new JPanel(new GridBagLayout());
            pnlCenter.setBackground(Color.white);
            
            GridBagConstraints gbcs = new GridBagConstraints();
            
            
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.gridx = 0;
            gbc.gridy = 0;
            pnlArticle.add(pnlLeft, gbc);
            
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 2.5;
            gbc.weighty = 1;
            gbc.gridx = 1;
            gbc.gridy = 0;
            pnlArticle.add(pnlCenter, gbc);
            
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.gridx = 2;
            gbc.gridy = 0;
            pnlArticle.add(pnlRight, gbc);
            
            
            
            gbcs.insets = new Insets(15, 0, 0, 0);
            gbcs.gridx = 0;
            gbcs.gridy = 0;
            pnlLeft.add(btnEdit, gbcs);
            gbcs.gridx = 0;
            gbcs.gridy = 1;
            pnlLeft.add(btnDelete, gbcs);
            
            gbcs.insets = new Insets(15, 0, 0, 0);
            gbcs.gridx = 0;
            gbcs.gridy = 0;
            pnlRight.add(btnPublish, gbcs);
            gbcs.gridx = 0;
            gbcs.gridy = 1;
            pnlRight.add(btnUnpublish, gbcs);
            
            gbcs.insets = new Insets(25, 0, 0, 0);
//            gbcs.fill = GridBagConstraints.HORIZONTAL;
            
            gbcs.anchor = GridBagConstraints.PAGE_START;
            gbcs.weightx = 1;
            gbcs.gridx = 0;
            gbcs.gridy = 0;
            pnlCenter.add(lblCategory, gbcs);
            gbcs.anchor = GridBagConstraints.CENTER;
            gbcs.weightx = 4;
            gbcs.gridx = 1;
            gbcs.gridy = 1;
            pnlCenter.add(lblTitle, gbcs);
            gbcs.anchor = GridBagConstraints.CENTER;
            gbcs.weightx = 4;
            gbcs.gridx = 1;
            gbcs.gridy = 2;
            pnlCenter.add(btnRead, gbcs);
            gbcs.anchor = GridBagConstraints.PAGE_END;
            gbcs.weightx = 1;
            gbcs.gridx = 2;
            gbcs.gridy = 3;
            pnlCenter.add(lblWriter, gbcs);
            
            
            
            
            pnlAllArticles.add(pnlArticle);
            i+=1;
//            System.out.println(articles.get(i));
        }
        
    }
}
