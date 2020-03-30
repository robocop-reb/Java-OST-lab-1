
package sample;
import java.net.*;
import java.io.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    private static Socket clientSocket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;
    public Button send;
    public TextField portNum;
    public TextField ipField;
    public TextField numInput;
    public TextArea resultArea;

    public void handleConnectButtonClick() throws IOException {
        int port = Integer.parseInt(portNum.getText());
        String address = ipField.getText();
        clientSocket = new Socket(address,port);
    }
    public void handleSendButtonClick() throws IOException {
        String num = numInput.getText();
        output = new DataOutputStream(Controller.clientSocket.getOutputStream());
        output.writeUTF(num);
        getText();
    }
    public void getText() throws IOException {
        while (resultArea.getText().trim().length() == 0){
            input = new DataInputStream(Controller.clientSocket.getInputStream());
            resultArea.setText(input.readUTF());
        }
    }
}
