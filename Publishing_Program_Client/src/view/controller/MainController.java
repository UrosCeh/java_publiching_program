/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communication.Communication;
import domain.classes.Autor;
import domain.classes.Clanak;
import domain.classes.ObjavljenClanak;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import view.constants.Constants;
import view.coordinator.ViewCoordinator;
import view.form.FrmLogin;
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
        setupComponents();
        frmMain.setVisible(true);
    }
    
    public FrmMain getFrmMain() {
        return frmMain;
    }
    
    private void setupComponents() {
        Autor autor = (Autor) ViewCoordinator.getInstance().getParam(Constants.CURRENT_AUTOR);
        addActionListeners(autor);
        if (autor == null) {
            autor = new Autor();
            autor.setIme("Gost");
            autor.setPrezime("");
            autor.setAdmin(false);
            autor.setPisac(false);
            frmMain.getMenu().setVisible(false);
        }
        
        if (autor.isPisac()) {
            addHeaderBtns();
//            frmMain.getMePisac().setVisible(false);
        }
        if (autor.isAdmin()) {
            addHeaderAdmin();
//            frmMain.getMeAdmin().setVisible(false);
        }
        
        
        setUser(autor);
        addArticles();
    }

    private void addActionListeners(Autor autor) {
        if (autor != null) { 
            frmMain.getBtnLogin().setText("Logout");
        }
        else{
            frmMain.getBtnLogin().setText("Login");
        }

        frmMain.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ViewCoordinator.getInstance().openLoginForm();
            }
        });
    }   
    
    private void addHeaderBtns() {
        JPanel pnlHeaderWriter = frmMain.getPnlHeaderWriter();
        int width = 200, height = 50, margin = 10, order = 0;
        
        JButton btnSeeMyArticles = makeButton("See My Articles", order++, width, height, margin);
        btnSeeMyArticles.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
//                list all writers articles
            }
        });
        
        JButton btnAddNewArticle = makeButton("Add New Article", order++, width, height, margin);
        btnAddNewArticle.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCoordinator.getInstance().openAddArticleForm();
            }
        });
        
//        pnlHeaderWriter.add(btnLogin);
        pnlHeaderWriter.add(btnSeeMyArticles);
        pnlHeaderWriter.add(btnAddNewArticle);
                
        
    }
    
    private void addHeaderAdmin() {
        
        JPanel pnlHeaderAdmin = frmMain.getPnlHeaderAdmin();
        int width = 200, height = 20, margin = 4, order = 0;
        
        JButton btnSeeAll = makeButton("See All", order++, width, height, margin);
        btnSeeAll.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
//                ViewCoordinator.getInstance().openAddArticleForm();
            }
        });
        
        JButton btnSeePublished = makeButton("See Published", order++, width, height, margin);
        btnSeePublished.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
//                ViewCoordinator.getInstance().openAddArticleForm();
            }
        });
        
        JButton btnSeeDueUnpublished = makeButton("See Due Unpublished", order++, width, height, margin);
        btnSeeDueUnpublished.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
//                ViewCoordinator.getInstance().openAddArticleForm();
            }
        });
        
        JButton btnSeeUnpublished = makeButton("See Unpublished", order++, width, height, margin);
        btnSeeUnpublished.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
//                ViewCoordinator.getInstance().openAddArticleForm();
            }
        });
        
        JButton btnAddNewCategory = makeButton("Add New Category", order++, width, height, margin);
        btnAddNewCategory.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
//                ViewCoordinator.getInstance().openAddArticleForm();
            }
        });
        
        
        pnlHeaderAdmin.add(btnSeeAll);
        pnlHeaderAdmin.add(btnSeePublished);
        pnlHeaderAdmin.add(btnSeeDueUnpublished);
        pnlHeaderAdmin.add(btnSeeUnpublished);
        pnlHeaderAdmin.add(btnAddNewCategory);
    }
    
    private JButton makeButton(String title, int order, int width, int height, int margin) {
        JButton btn = new JButton(title);
        btn.setBounds(0, order*(height+margin), width, height);
        
        return btn;
    }
    
    private void addArticles() {
        JPanel pnlAllArticles = frmMain.getPnlAllArticles();
//        pnlAllArticles.setBackground(Color.BLACK);
        List<ObjavljenClanak> articles;
        try {
            articles = Communication.getInstance().ucitajListuObjavljenihClanaka();
            String term = (String) ViewCoordinator.getInstance().getParam(Constants.SEARCH_TERM);
            if (term != null) {
                List<ObjavljenClanak> filtered = null;
                for (ObjavljenClanak article : articles) {
                    if (article.getNaslov().toLowerCase().contains(term.toLowerCase())) {
                        filtered.add(article);
                    }
                }
                articles = filtered;
            }
            
            for (int i = 0; i < articles.size(); i++) {
                JPanel pnlArticle = makeArticle(articles.get(i));
                pnlArticle.setBounds(pnlAllArticles.getX(), pnlAllArticles.getY()+(i*220), pnlAllArticles.getWidth(), 200);
                pnlAllArticles.add(pnlArticle);
            }
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
 
    }

    private JPanel makeArticle(ObjavljenClanak article) {
        JPanel pnlArticle = new JPanel(new BorderLayout());
            
        JLabel lblDate = new JLabel(article.getStringDatumIVreme(), SwingConstants.LEADING);

        JLabel lblCategory = new JLabel(article.getKategorija().getNaziv());
//        JLabel lblTitle = new JLabel("Ovo je neki naslov koji treba da bude dugacak da bih video kako radi jebani swing", SwingConstants.CENTER);
        JLabel lblTitle = new JLabel(article.getNaslov());
        JButton btnRead = new JButton("Read More");
            btnRead.setPreferredSize(new Dimension(200,35));    
        JPanel pnlMiddle = new JPanel(new GridBagLayout());
        pnlMiddle.setBackground(Color.white);
        
        JLabel lblWriter = new JLabel(article.getAutor().toString(), SwingConstants.TRAILING);



        JPanel pnlLeft = new JPanel();
        pnlLeft.setBackground(Color.gray);
        pnlLeft.setPreferredSize(new Dimension(200,200));
        
        JPanel pnlRight = new JPanel();
        pnlRight.setBackground(Color.gray);
        pnlRight.setPreferredSize(new Dimension(200,200));
        
        JPanel pnlCenter = new JPanel(new BorderLayout());
        pnlCenter.setBackground(Color.white);
        
//        pnlArticle.add(pnlLeft, BorderLayout.WEST);
//        pnlArticle.add(pnlCenter, BorderLayout.CENTER);
//        pnlArticle.add(pnlRight, BorderLayout.EAST);
        
        JButton btnEdit = new JButton("Edit/Delete");
        btnEdit.setPreferredSize(new Dimension(150,25));
        pnlLeft.add(btnEdit);
            
        JButton btnPublish = new JButton("Publish/Unpublish");
        btnPublish.setPreferredSize(new Dimension(150,25));
        pnlRight.add(btnPublish);

        Autor autor = (Autor) ViewCoordinator.getInstance().getParam(Constants.CURRENT_AUTOR);
        if (autor != null) {
        System.out.println(autor.toString());
            if (!autor.isPisac() || !article.getAutor().equals(autor)) {
//                System.out.println("pisac i njegov artikal " + article.toString());
//                JButton btnEdit = new JButton("Edit/Delete");
//                btnEdit.setPreferredSize(new Dimension(150,25));
//                pnlLeft.remove(btnEdit);
                pnlLeft.remove(btnEdit);
//                addToGBC(pnlLeft, btnEdit, 0, 0, 0, 30);
            }

            if (!autor.isAdmin()) {
//                System.out.println("admin je " + article.toString());
//                JButton btnPublish = new JButton("Publish/Unpublish");
//                btnPublish.setPreferredSize(new Dimension(150,25));
//                pnlRight.remove(btnPublish);
                pnlRight.remove(btnPublish);
            }
        }
        else {
            pnlLeft.removeAll();
            pnlRight.removeAll();
        }
//        if (autor == null) {
//            pnlLeft.removeAll();
//            pnlRight.removeAll();
//        }
        addToGBC(pnlMiddle, lblCategory, 0, 0, -1, 5);
        addToGBC(pnlMiddle, lblTitle, 0, 1, 0, 20);
        addToGBC(pnlMiddle, btnRead, 0, 2, 0, 25);
        
        pnlCenter.add(lblDate, BorderLayout.NORTH);
        pnlCenter.add(pnlMiddle, BorderLayout.CENTER);
        pnlCenter.add(lblWriter, BorderLayout.SOUTH);


        pnlArticle.add(pnlLeft, BorderLayout.WEST);
        pnlArticle.add(pnlCenter, BorderLayout.CENTER);
        pnlArticle.add(pnlRight, BorderLayout.EAST);
        
        return pnlArticle;
    }
    
    public void addToGBC(Container parent, Component child, int gridx, int gridy, int anchor, int topMargin) {
        Insets insets = new Insets(topMargin, 5, 0, 5);
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.gridx = gridx;
        c.gridy = gridy;
        switch (anchor) {
            case -1:
                c.anchor = GridBagConstraints.LINE_START;
                break;
            case 1:
                c.anchor = GridBagConstraints.LINE_END;
                break;
            default:
                c.anchor = GridBagConstraints.CENTER;
                break;
        }
        c.insets = insets;
        
        parent.add(child, c);
    }

    private void setUser(Autor autor) {
        frmMain.getLblUser().setText(autor.toString());
    }

    public void repaint() {
        frmMain.dispose();
        this.openForm();
    }
    
}
