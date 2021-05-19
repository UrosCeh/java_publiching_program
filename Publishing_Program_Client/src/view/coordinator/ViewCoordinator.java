/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.coordinator;

import java.util.HashMap;
import java.util.Map;
import view.controller.ArticleController;
import view.controller.LoginController;
import view.controller.MainController;
import view.form.FrmArticle;
import view.form.FrmLogin;
import view.form.FrmMain;
import view.form.util.FrmMode;

/**
 *
 * @author hatch
 */
public class ViewCoordinator {
 
    private static ViewCoordinator instance;
    
    private final MainController mainController;
    
    private final Map<String, Object> params;
    
    private ViewCoordinator() {
        mainController = new MainController(new FrmMain());
        params = new HashMap<>();
    }
    
    public static ViewCoordinator getInstance() {
        if(instance == null) {
            instance = new ViewCoordinator();
        }
        return instance;
    }
    
    public void openMainForm() {
        mainController.openForm();
    }
    
    public void repaintMainForm() {
        mainController.repaint();
        mainController.openForm();
    }
    
    public void openReadArticleForm() {
        ArticleController articleController = new ArticleController(new FrmArticle(mainController.getFrmMain(), true));
        articleController.openForm(FrmMode.ARTICLE_READ);
    }
    
    public void openAddArticleForm() {
        ArticleController articleController = new ArticleController(new FrmArticle(mainController.getFrmMain(), true));
        articleController.openForm(FrmMode.ARTICLE_ADD);
    }
    
    public void openEditArticleForm() {
        ArticleController articleController = new ArticleController(new FrmArticle(mainController.getFrmMain(), true));
        articleController.openForm(FrmMode.ARTICLE_EDIT);
    }
    
    public void openPublishArticleForm() {
        ArticleController articleController = new ArticleController(new FrmArticle(mainController.getFrmMain(), true));
        articleController.openForm(FrmMode.ARTICLE_PUBLISH);
    }
    
    public void openLoginForm() {
        LoginController loginController = new LoginController(new FrmLogin(mainController.getFrmMain(), true));
        loginController.openForm();
    }
    
    public void addParam(String name, Object key) {
        params.put(name, key);
    }

    public Object getParam(String name) {
        return params.get(name);
    }
}
