/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communication.Communication;
import domain.classes.Autor;
import domain.classes.Clanak;
import domain.classes.Kategorija;
import domain.classes.NeobjavljenClanak;
import domain.classes.ObjavljenClanak;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.constants.Constants;
import view.coordinator.ViewCoordinator;
import view.form.FrmArticle;
import view.form.util.FrmMode;

/**
 *
 * @author hatch
 */
public class ArticleController {
    private final FrmArticle frmArticle;

    public ArticleController(FrmArticle frmArticle) {
        this.frmArticle = frmArticle;
    }
    
    public void openForm(FrmMode frmMode, Clanak c) {
        try {
            setupComponents(frmMode);
            prepareView(frmMode);
            addActionListeners(frmMode);
            if (c != null) {
                setArticle(c, frmMode);
            }
            frmArticle.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupComponents(FrmMode frmMode) {
        switch (frmMode) {
            case ARTICLE_READ:
                frmArticle.getBtnAction().setVisible(false);
                frmArticle.getCbArticles().setVisible(false);
                frmArticle.getLblChooseArticle().setVisible(false);
                frmArticle.getTxtBody().setEditable(false);
                frmArticle.getTxtBody().setBackground(new Color(245, 245, 245));
                frmArticle.getTxtTitle().setEditable(false);
                frmArticle.getCbCategory().setEnabled(false);
                frmArticle.getPnlSetDateTime().setVisible(false);
                break;
            case ARTICLE_ADD:
                frmArticle.getCbArticles().setVisible(false);
                frmArticle.getLblChooseArticle().setVisible(false);
                frmArticle.getBtnAction().setText("Kreiraj clanak");
                break;
            case ARTICLE_EDIT:
                frmArticle.getBtnAction().setText("Izmeni clanak");
                break;
            case ARTICLE_DELETE:
                frmArticle.getBtnAction().setText("Obrisi clanak");
                frmArticle.getTxtBody().setEditable(false);
                frmArticle.getTxtBody().setBackground(new Color(245, 245, 245));
                frmArticle.getTxtTitle().setEditable(false);
                frmArticle.getCbCategory().setEnabled(false);
                frmArticle.getPnlSetDateTime().setVisible(false);
                break;
            case ARTICLE_PUBLISH:
                frmArticle.getBtnAction().setText("Objavi clanak");
                frmArticle.getTxtBody().setEditable(false);
                frmArticle.getTxtBody().setBackground(new Color(245, 245, 245));
                frmArticle.getTxtTitle().setEditable(false);
                frmArticle.getCbCategory().setEnabled(false);
                frmArticle.getPnlSetDateTime().setVisible(false);
                break;
            case ARTICLE_UNPUBLISH:
                frmArticle.getBtnAction().setText("Ukloni objvljen clanak");
                frmArticle.getTxtBody().setEditable(false);
                frmArticle.getTxtBody().setBackground(new Color(245, 245, 245));
                frmArticle.getTxtTitle().setEditable(false);
                frmArticle.getCbCategory().setEnabled(false);
                frmArticle.getPnlSetDateTime().setVisible(false);
                break;
        }
    }

    private void prepareView(FrmMode frmMode) throws Exception {
        frmArticle.getCbCategory().removeAllItems();
        ArrayList<Kategorija> kategorije = Communication.getInstance().ucitajListuKategorija();
        for (Kategorija kategorija : kategorije) {
            frmArticle.getCbCategory().addItem(kategorija);
        }
        switch (frmMode) {
            case ARTICLE_READ:
            case ARTICLE_ADD:
                break;
            case ARTICLE_EDIT:
            case ARTICLE_DELETE:
                frmArticle.getCbArticles().removeAllItems();
                ArrayList<NeobjavljenClanak> neobjavljeniClanci = Communication.getInstance().pronadjiClanke("");
                for (NeobjavljenClanak neobjavljenClanak : neobjavljeniClanci) {
                    if (((Autor) ViewCoordinator.getInstance().getParam(Constants.CURRENT_AUTOR)).equals(neobjavljenClanak.getAutor())) {
                        frmArticle.getCbArticles().addItem(neobjavljenClanak);
                    }
                }
                break;
            case ARTICLE_PUBLISH:
                frmArticle.getCbArticles().removeAllItems();
                ArrayList<NeobjavljenClanak> neobjavljeniClanciObjavljivanje = Communication.getInstance().pronadjiClanke("");
                for (NeobjavljenClanak neobjavljenClanak : neobjavljeniClanciObjavljivanje) {
                    frmArticle.getCbArticles().addItem(neobjavljenClanak);
                }
                break;
            case ARTICLE_UNPUBLISH:
                frmArticle.getCbArticles().removeAllItems();
                ArrayList<ObjavljenClanak> objavljeniClanci = Communication.getInstance().ucitajListuObjavljenihClanaka("");
                for (ObjavljenClanak objavljenClanak: objavljeniClanci) {
                    frmArticle.getCbArticles().addItem(objavljenClanak);
                }
                break;
        }
        
        frmArticle.getCbCategory().setSelectedIndex(-1);
        frmArticle.getCbArticles().setSelectedIndex(-1);
    }

    private void setArticle(Clanak clanak, FrmMode frmMode) {
        frmArticle.getTxtTitle().setText(clanak.getNaslov());
        frmArticle.getTxtBody().setText(clanak.getTekst());
        frmArticle.getCbCategory().setSelectedItem(clanak.getKategorija());
        frmArticle.getLblWriter().setText(clanak.getAutor().toString());
        frmArticle.getLblDate().setText(clanak.getStringDatum());
        
        switch (frmMode) {
            case ARTICLE_EDIT:
            case ARTICLE_DELETE:
            case ARTICLE_PUBLISH:
                frmArticle.getTxtDateTime().setText(clanak.getStringDatum());
                break;
        }
    }

    private void addActionListeners(FrmMode frmMode) {
        frmArticle.getCbArticles().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setArticle((Clanak) e.getItem(), frmMode);
            }
        });
        
        switch(frmMode) {
            case ARTICLE_ADD:
                frmArticle.getBtnAction().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        try {
                            String naslov = frmArticle.getTxtTitle().getText();
                            String tekst = frmArticle.getTxtBody().getText();
                            Autor autor = (Autor) ViewCoordinator.getInstance().getParam(Constants.CURRENT_AUTOR);
                            Kategorija kategorija = (Kategorija) frmArticle.getCbCategory().getSelectedItem();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime datumVreme;
                            try {
                                datumVreme = LocalDateTime.parse(frmArticle.getTxtDateTime().getText(), formatter);
                            }
                            catch (Exception ex) {
                                frmArticle.showErrorMessage("Datum i vreme moraju biti uneti u datom formatu (npr. 2021-07-21 13:15)");
                                return;
                            }
                            NeobjavljenClanak clanak = new NeobjavljenClanak(naslov, tekst, autor, kategorija, datumVreme);
                            
                            if (clanak.getNaslov().trim().equals("") || clanak.getTekst().trim().equals("")) {
                                frmArticle.showErrorMessage("Naslov i tekst clanka ne smeju biti prazni");
                                return;
                            }
                            
                            boolean success = Communication.getInstance().kreirajClanak(clanak);
                            if (success) {
                                frmArticle.showInfoMessage("Uspesno ste dodali clanak!");
                                frmArticle.dispose();
                            }
                        } catch (Exception ex) {
                            frmArticle.showErrorMessage(ex.getMessage());
                        }
                    }
                });
                break;
            case ARTICLE_EDIT:
                frmArticle.getBtnAction().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        try {
                            NeobjavljenClanak clanak = (NeobjavljenClanak) frmArticle.getCbArticles().getSelectedItem();
                            clanak.setNaslov(frmArticle.getTxtTitle().getText());
                            clanak.setTekst(frmArticle.getTxtBody().getText());
                            clanak.setKategorija((Kategorija) frmArticle.getCbCategory().getSelectedItem());
                            
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime datumVreme;
                            try {
                                datumVreme = LocalDateTime.parse(frmArticle.getTxtDateTime().getText(), formatter);
                            }
                            catch (Exception ex) {
                                frmArticle.showErrorMessage("Datum i vreme moraju biti uneti u datom formatu (npr. 2021-07-21 13:15)");
                                return;
                            }
                            clanak.setDatum(datumVreme);
                            
                            if (clanak.getNaslov().trim().equals("") || clanak.getTekst().trim().equals("")) {
                                frmArticle.showErrorMessage("Naslov i tekst clanka ne smeju biti prazni!");
                                return;
                            }
                                                        
                            boolean success = Communication.getInstance().azurirajClanak(clanak);
                            if (success) {
                                frmArticle.showInfoMessage("Uspesno ste azurirali clanak!");
                                frmArticle.dispose();
                            }
                        } catch (Exception ex) {
                            frmArticle.showErrorMessage(ex.getMessage());
                        }
                    }
                });
                break;
            case ARTICLE_DELETE:
                frmArticle.getBtnAction().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        try {                
                            boolean success = Communication.getInstance().obrisiClanak((NeobjavljenClanak) frmArticle.getCbArticles().getSelectedItem());
                            if (success) {
                                frmArticle.showInfoMessage("Uspesno ste obrisali clanak!");
                                frmArticle.dispose();
                            }
                        } catch (Exception ex) {
                            frmArticle.showErrorMessage(ex.getMessage());
                        }
                    }
                });
                break;
            case ARTICLE_PUBLISH:
                frmArticle.getBtnAction().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        try {                
                            boolean success = Communication.getInstance().objaviClanak((NeobjavljenClanak) frmArticle.getCbArticles().getSelectedItem());
                            if (success) {
                                frmArticle.showInfoMessage("Uspesno ste objavili clanak!");
                                frmArticle.dispose();
                                ViewCoordinator.getInstance().repaintMainForm();
                            }
                        } catch (Exception ex) {
                            frmArticle.showErrorMessage(ex.getMessage());
                        }
                    }
                });
                break;
            case ARTICLE_UNPUBLISH:
                frmArticle.getBtnAction().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        try {                
                            boolean success = Communication.getInstance().ukloniObjavljeniClanak((ObjavljenClanak) frmArticle.getCbArticles().getSelectedItem());
                            if (success) {
                                frmArticle.showInfoMessage("Uspesno ste uklonili objavljen clanak!");
                                frmArticle.dispose();
                                ViewCoordinator.getInstance().repaintMainForm();
                            }
                        } catch (Exception ex) {
                            frmArticle.showErrorMessage(ex.getMessage());
                        }
                    }
                });
                break;
        }

    }
    
}
