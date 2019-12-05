/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.net.Socket;
import threads.ReadingThread;
import threads.WritingThread;
import ui.GuiKontroler;

/**
 *
 * @author Marko
 */
public class Client {

    private Socket socket;
    GuiKontroler kontroler;

    public static void main(String[] args) {
        Client client = new Client();
        client.connect();
    }

    public Client() {
        kontroler = new GuiKontroler("Client");
    }

    private void connect() {
        try {
            socket = new Socket("localhost", 8555);
            ReadingThread readingThread = new ReadingThread(socket, kontroler);
            readingThread.start();
            WritingThread writtingThread = new WritingThread(socket, kontroler);
            writtingThread.start();
            kontroler.setWritingThread(writtingThread);

            readingThread.join();
            writtingThread.join();
            System.out.println("End");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
