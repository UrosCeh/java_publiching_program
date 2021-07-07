/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communication.Communication;
import domain.classes.Kategorija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.form.FrmAddCategory;

/**
 *
 * @author hatch
 */
public class AddCategoryController {
    private final FrmAddCategory frmAddCategory;

    public AddCategoryController(FrmAddCategory frmAddCategory) {
        this.frmAddCategory = frmAddCategory;
    }
    
    public void openForm() {
        try {
            prepareView();
            addActionListeners();
            frmAddCategory.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepareView() throws Exception {
        frmAddCategory.getCbCategories().removeAllItems();
        ArrayList<Kategorija> kategorije = Communication.getInstance().ucitajListuKategorija();
        for (Kategorija kategorija : kategorije) {
            frmAddCategory.getCbCategories().addItem(kategorija);
        }
    }

    private void addActionListeners() {
        frmAddCategory.getBtnAddCategory().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String name = frmAddCategory.getTxtName().getText().trim();
                    if (name.equals("")) {
                        frmAddCategory.showErrorMessage("Naziv kategorije ne sme biti prazan!");
                        return;
                    }
                    boolean success = Communication.getInstance().kreirajNovuKategoriju(frmAddCategory.getTxtName().getText());
                    if (success) {
                        frmAddCategory.showInfoMessage("Uspesno ste dodali novu kategoriju");
                        frmAddCategory.dispose();
                    }
                } catch (Exception ex) {
                    frmAddCategory.showErrorMessage(ex.getMessage());
                }
            }
        });
    }
}
