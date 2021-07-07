/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import communication.Communication;
import domain.classes.Autor;
import domain.classes.Kategorija;
import domain.classes.NeobjavljenClanak;
import domain.classes.ObjavljenClanak;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.constants.Constants;
import view.coordinator.ViewCoordinator;

/**
 *
 * @author hatch
 */
public class Main {

    public static void main(String[] args) {
        ViewCoordinator.getInstance().openMainForm();
    }
}
