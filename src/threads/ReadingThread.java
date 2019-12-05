/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import ui.FChat;
import ui.GuiKontroler;

/**
 *
 * @author Marko
 */
public class ReadingThread extends Thread {

    private final Socket socket;
    private GuiKontroler kontroler = null;

    public ReadingThread(Socket socket, GuiKontroler kontroler) {
        super();
        this.socket = socket;
        this.kontroler = kontroler;
    }

    @Override
    public void run() {
        while (true) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                kontroler.addRecievedMessage("Other: " + bufferedReader.readLine());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
