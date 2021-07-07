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
        }

        if (autor.isAdmin() && autor.isPisac()) {
            frmMain.getMenu().setVisible(true);
            frmMain.getMenuPisac().setVisible(true);
            frmMain.getMenuAdmin().setVisible(true);
        }
        else if (autor.isPisac() && !autor.isAdmin()) {
            frmMain.getMenu().setVisible(true);
            frmMain.getMenuPisac().setVisible(true);
            frmMain.getMenuAdmin().setVisible(false);
        }
        else if (autor.isAdmin() && !autor.isPisac()) {
            frmMain.getMenu().setVisible(true);
            frmMain.getMenuPisac().setVisible(false);
            frmMain.getMenuAdmin().setVisible(true);
        }
        else {
            frmMain.getMenu().setVisible(false);
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

        frmMain.getBtnHome().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frmMain.getTxtSearch().setText("");
                addArticles();
            }
        });
        
        frmMain.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ViewCoordinator.getInstance().openLoginForm();
            }
        });
        
        frmMain.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                addArticles();
            }
        });
        
        frmMain.getMiDodajClanak().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ViewCoordinator.getInstance().openAddArticleForm();
            }
        });
        
        frmMain.getMiAzurirajClanak().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ViewCoordinator.getInstance().openEditArticleForm();
            }
        });
        
        frmMain.getMiObrisiClanak().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ViewCoordinator.getInstance().openDeleteArticleForm();
            }
        });
        
        frmMain.getMiObjaviClanak().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ViewCoordinator.getInstance().openPublishArticleForm();
            }
        });
        
        frmMain.getMiUkloniObjavljenClanak().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ViewCoordinator.getInstance().openUnpublishArticleForm();
            }
        });
        
        frmMain.getMiDodajKategoriju().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ViewCoordinator.getInstance().openAddNewCategoryForm();
            }
        });
        
        frmMain.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    Communication.getInstance().izlogujAutora();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        
        
    }   
    
    private void addArticles() {
        JPanel pnlAllArticles = frmMain.getPnlAllArticles();
        pnlAllArticles.removeAll();
        pnlAllArticles.revalidate();
        pnlAllArticles.repaint();
        ArrayList<ObjavljenClanak> articles;
        try {
            String term = (String) frmMain.getTxtSearch().getText().trim();
            articles = Communication.getInstance().ucitajListuObjavljenihClanaka(term);
            frmMain.getPnlAllArticles().setPreferredSize(new Dimension(1000, articles.size()*220));
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
            
        JLabel lblDate = new JLabel(article.getStringDatum(), SwingConstants.LEADING);

        JLabel lblCategory = new JLabel(article.getKategorija().getNaziv());
//        JLabel lblTitle = new JLabel("Ovo je neki naslov koji treba da bude dugacak da bih video kako radi jebani swing", SwingConstants.CENTER);
        JLabel lblTitle = new JLabel(article.getNaslov());
        JButton btnRead = new JButton("Read More");
            btnRead.setPreferredSize(new Dimension(200,35));   
        btnRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ViewCoordinator.getInstance().openReadArticleForm(article);
            }
        });
        
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
