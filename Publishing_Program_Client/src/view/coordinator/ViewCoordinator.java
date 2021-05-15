/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.coordinator;

import java.util.HashMap;
import java.util.Map;
import view.controller.MainController;
import view.form.FrmMain;

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
}
