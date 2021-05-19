/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import server.Server;
import view.controller.ServerViewController;

/**
 *
 * @author hatch
 */
public class MainServer {
    public static void main(String[] args) {
        new Server().startServer();
    }
}
