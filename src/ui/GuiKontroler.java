/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import threads.WritingThread;

/**
 *
 * @author Marko
 */
public class GuiKontroler {

    FChat gui;

    WritingThread writingThread;

    public GuiKontroler(String title) {
        prepareGui(title);
    }

    public void prepareGui(String title) {
        gui = new FChat();
        gui.setVisible(true);
        gui.getBtnSend().setEnabled(true);
        gui.setTitle(title);
        gui.getBtnSend().addActionListener(new Osluskivac());

    }

    public void setWritingThread(WritingThread writingThread) {
        this.writingThread = writingThread;
    }

    public void addSentMessage(String m) {
        //txtLastMessage.setText(m);
        if (gui.getTxtAllMessages().getText().isEmpty()) {
            gui.getTxtAllMessages().append(m);
        } else {
            gui.getTxtAllMessages().append("\n" + m);
        }
    }

    public void addRecievedMessage(String m) {
        gui.getTxtLastMessage().setText(m);

        if (gui.getTxtAllMessages().getText().isEmpty()) {
            gui.getTxtAllMessages().append(m);
        } else {
            gui.getTxtAllMessages().append("\n" + m);
        }
    }

    public String getTxtMessage() {
        return gui.getTxtMessage().getText();
    }

    class Osluskivac implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            writingThread.messageReady();
        }
    }

}
