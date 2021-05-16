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
        prepareView(frmMode);
        frmArticle.setVisible(true);
    }

    private void prepareView(FrmMode frmMode) {
        
    }
    
}
