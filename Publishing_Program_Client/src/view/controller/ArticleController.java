/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

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
    
    public void openForm(FrmMode frmMode) {
        setupComponents(frmMode);
        prepareView(frmMode);
        fillForm(frmMode);
        frmArticle.setVisible(true);
    }

    private void setupComponents(FrmMode frmMode) {
        switch (frmMode) {
            case ARTICLE_READ:
                frmArticle.getBtnAction().setVisible(false);
                frmArticle.getBtnSelectArticle().setVisible(false);
                frmArticle.getCbArticles().setVisible(false);
                frmArticle.getCbCategory().setEnabled(false);
                break;
            case ARTICLE_ADD:
                frmArticle.getBtnAction().setText("Kreiraj clanak");
                break;
            case ARTICLE_EDIT:
                frmArticle.getBtnAction().setText("Izmeni clanak");
                break;
            case ARTICLE_DELETE:
                frmArticle.getBtnAction().setText("Obrisi clanak");
                break;
            case ARTICLE_PUBLISH:
                frmArticle.getBtnAction().setText("Objavi clanak");
                break;
            case ARTICLE_UNPUBLISH:
                frmArticle.getBtnAction().setText("Ukloni objvljen clanak");
                break;
        }
    }

    private void prepareView(FrmMode frmMode) {
        
    }
    
    
    private void fillForm(FrmMode frmMode) {
        
    }
    
}
