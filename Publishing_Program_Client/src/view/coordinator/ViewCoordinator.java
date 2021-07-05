/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.coordinator;

import domain.classes.Clanak;
import java.util.HashMap;
import java.util.Map;
import view.controller.AddCategoryController;
import view.controller.ArticleController;
import view.controller.LoginController;
import view.controller.MainController;
import view.form.FrmAddCategory;
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
//        mainController.openForm();
    }
    
    public void openReadArticleForm(Clanak c) {
        ArticleController articleController = new ArticleController(new FrmArticle(mainController.getFrmMain(), true));
        articleController.openForm(FrmMode.ARTICLE_READ, c);
    }
    
    public void openAddArticleForm() {
        ArticleController articleController = new ArticleController(new FrmArticle(mainController.getFrmMain(), true));
        articleController.openForm(FrmMode.ARTICLE_ADD, null);
    }
    
    public void openEditArticleForm() {
        ArticleController articleController = new ArticleController(new FrmArticle(mainController.getFrmMain(), true));
        articleController.openForm(FrmMode.ARTICLE_EDIT, null);
    }
    
    public void openDeleteArticleForm() {
        ArticleController articleController = new ArticleController(new FrmArticle(mainController.getFrmMain(), true));
        articleController.openForm(FrmMode.ARTICLE_DELETE, null);
    }
    
    public void openPublishArticleForm() {
        ArticleController articleController = new ArticleController(new FrmArticle(mainController.getFrmMain(), true));
        articleController.openForm(FrmMode.ARTICLE_PUBLISH, null);
    }
    
    public void openUnpublishArticleForm() {
        ArticleController articleController = new ArticleController(new FrmArticle(mainController.getFrmMain(), true));
        articleController.openForm(FrmMode.ARTICLE_UNPUBLISH, null);
    }
    
    public void openLoginForm() {
        LoginController loginController = new LoginController(new FrmLogin(mainController.getFrmMain(), true));
        loginController.openForm();
    }
    
    public void openAddNewCategoryForm() {
        AddCategoryController addCategoryController = new AddCategoryController(new FrmAddCategory(mainController.getFrmMain(), true));
        addCategoryController.openForm();
    }
    
    public void addParam(String name, Object key) {
        params.put(name, key);
    }

    public Object getParam(String name) {
        return params.get(name);
    }
    
    public void removeParam(String name, Object key) {
//        params.remove(name);
        params.remove(name, key);
    }
}
