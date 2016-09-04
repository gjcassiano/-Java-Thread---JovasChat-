/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

    Socket requestSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;
    private FrameCliente framecliente;

    void connectServidor() {
        try {
            requestSocket = new Socket("localhost", 8280);
            sendtextArea("Conectando no Servidor..");
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            out.flush();
            
            in = new ObjectInputStream(requestSocket.getInputStream());

            do {
                try {
                    message = (String) in.readObject();
                    sendtextArea("server>" + message);

                } catch (ClassNotFoundException classNot) {
                    sendtextArea("data received in unknown format");
                }
            } while (!message.equals("bye"));
        } catch (IOException e) {

        } finally {
            try {
                in.close();
                out.close();
                requestSocket.close();
            } catch (IOException ioException) {
            }
        }
    }

    void sendMessage(String msg) {
        try {
            out.writeObject(msg);
            out.flush();
        } catch (IOException ioException) {
        }
    }

    void sendtextArea(String txt) {
        this.framecliente.jTextArea1.append(txt + "\n");

    }

    public Cliente(FrameCliente fc) {
        this.framecliente = fc;
    }

}
