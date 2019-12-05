/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.ServerSocket;
import java.net.Socket;
import threads.ReadingThread;
import threads.WritingThread;
import ui.GuiKontroler;

/**
 *
 * @author Marko
 */
public class Server {

    private ServerSocket serverSocket;
    GuiKontroler kontroler;

    public Server() {
        kontroler = new GuiKontroler("Server");
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void start() throws Exception {
        serverSocket = new ServerSocket(8555);
        System.out.println("Waiting clients");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected");
        ReadingThread readingThread = new ReadingThread(socket, kontroler);
        readingThread.start();
        WritingThread writtingThread = new WritingThread(socket, kontroler);
        writtingThread.start();
        kontroler.setWritingThread(writtingThread);

        readingThread.join();
        writtingThread.join();
        System.out.println("End");

    }
}
