import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import java.util.Base64;

public class ChatClient {
	
	// GUI ELEMENTS
	static ChatClient client;
	String name;
    BufferedReader in;
    PrintWriter out;
    JFrame frame; 
    public static JTextArea userText;
    JTextArea DisplayMessage;
    JLabel connectInfo;
	JToggleButton tglbtnConnect;
	JToggleButton tglbtnDisconnect;
	JLayeredPane layeredPane;
	JLayeredPane layeredPane_1;
	JRadioButton rdbtnAes;
	JRadioButton rdbtnDes;
	JRadioButton rdbtnCbs;
	JRadioButton rdbtnOfb;
	JPanel panel;
	JLabel lblServer;
	JLabel lblText;
	JLabel lblCryptText;
	JTextArea encryptedText;
	JToggleButton tglbtnNewToggleButton;
	JToggleButton tglbtnNewToggleButton_1;

  
    public ChatClient() {
    			frame =new JFrame("Crypto Messenger");

    			frame.setBounds(100, 100, 557, 602);

    			DisplayMessage = new JTextArea();
    			DisplayMessage.setBounds(12, 82, 520, 354);
    			DisplayMessage.setText("");

    			tglbtnConnect = new JToggleButton("Connect");
    			tglbtnConnect.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						connectInfo.setText("Connected:");
						
						
						
					}
				});
    			tglbtnConnect.setBounds(20,36, 106, 25);

    			tglbtnDisconnect = new JToggleButton("Disconnect");
    			tglbtnDisconnect.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    				}
    			});
    			tglbtnDisconnect.setBounds(133, 36, 112, 25);

    			layeredPane = new JLayeredPane();
    			layeredPane.setBorder(new TitledBorder(null, "Method", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    			layeredPane.setBounds(255, 19, 113, 50);

    			layeredPane_1 = new JLayeredPane();
    			layeredPane_1.setBorder(new TitledBorder(null, "Mode", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    			layeredPane_1.setBounds(380, 19, 120, 50);

    			rdbtnAes = new JRadioButton("AES");
    			rdbtnAes.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent arg0) {
    					if(rdbtnDes.isSelected()){
    						rdbtnDes.setSelected(false);
    						rdbtnAes.setSelected(true);
    						
    					}
    					else{
    						rdbtnAes.setSelected(true);
    					}
    				}
    			});
    			rdbtnAes.setBounds(10, 23, 56, 25);
    			layeredPane.add(rdbtnAes);

    			rdbtnDes = new JRadioButton("DES");
    			rdbtnDes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(rdbtnAes.isSelected()){
							rdbtnAes.setSelected(false);
    						rdbtnDes.setSelected(true);
						}
						else{
							rdbtnDes.setSelected(true);
						}
						
					}
				});
    			rdbtnDes.setBounds(68, 23, 51, 25);
    			layeredPane.add(rdbtnDes);
    			frame.getContentPane().setLayout(null);
    			frame.getContentPane().add(tglbtnConnect);
    			frame.getContentPane().add(tglbtnDisconnect);
    			frame.getContentPane().add(layeredPane);
    			frame.getContentPane().add(layeredPane_1);

    			rdbtnCbs = new JRadioButton("CBC");
    			rdbtnCbs.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						if(rdbtnOfb.isSelected()){
							rdbtnOfb.setSelected(false);
    						rdbtnCbs.setSelected(true);
						}
						else{
							rdbtnCbs.setSelected(true);
						}
						
					}
				});
    			rdbtnCbs.setBounds(0, 25, 56, 25);
    			layeredPane_1.add(rdbtnCbs);

    			rdbtnOfb = new JRadioButton("OFB");
    			rdbtnOfb.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						if(rdbtnCbs.isSelected()){
							rdbtnCbs.setSelected(false);
    						rdbtnOfb.setSelected(true);
						}
						else{
							rdbtnOfb.setSelected(true);
						}
						
					}
				});
    			rdbtnOfb.setBounds(60, 25, 56, 25);
    			layeredPane_1.add(rdbtnOfb);
    			frame.getContentPane().add(DisplayMessage);

    			lblServer = new JLabel("Server");
    			lblServer.setFont(new Font("Tahoma", Font.BOLD, 12));
    			lblServer.setBounds(10, 0, 41, 25);
    			frame.getContentPane().add(lblServer);
    			
    			panel = new JPanel();
    			panel.setBounds(0, 436, 532, 100);
    			frame.getContentPane().add(panel);
    			panel.setLayout(null);

    			lblText = new JLabel("Text");
    			lblText.setFont(new Font("Tahoma", Font.BOLD, 12));
    			lblText.setBounds(10, 0, 56, 25);
    			panel.add(lblText);

    			lblCryptText = new JLabel("Crypted Text");
    			lblCryptText.setFont(new Font("Tahoma", Font.BOLD, 12));
    			lblCryptText.setBounds(166, 0, 86, 25);
    			panel.add(lblCryptText);

    			userText = new JTextArea();
    			userText.setBounds(10, 27, 153, 73);
    			panel.add(userText);

    			encryptedText = new JTextArea();
    			encryptedText.setBounds(166, 27, 153, 73);
    			panel.add(encryptedText);

    			tglbtnNewToggleButton = new JToggleButton("Encrypt");
    			tglbtnNewToggleButton.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					String words=userText.getText();
    					if(rdbtnAes.isSelected() && rdbtnCbs.isSelected()){
    	            		 String key="MZygpewJsCpRrfOr";
    	                  	byte[] encryptionKey = "MZygpewJsCpRrfOr".getBytes();
    	              		AES advancedEncryptionStandard = new AES(encryptionKey);
    	              		byte[] cipherText={};
							try {
								cipherText = advancedEncryptionStandard.encrypt(words,key,0);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
    	              		String encrytepdText=new String(cipherText);
							String encodedString = Base64.getEncoder().encodeToString(encrytepdText.getBytes());
							encryptedText.setText(encodedString);
    	              		
    	              
    	            		
    	            	}
    	            	else if(rdbtnAes.isSelected() && rdbtnOfb.isSelected()){
    	            		String key="MZygpewJsCpRrfOr";
    	                  	byte[] encryptionKey = "MZygpewJsCpRrfOr".getBytes();
    	              		AES advancedEncryptionStandard = new AES(encryptionKey);
    	              		byte[] cipherText={};
							try {
								cipherText = advancedEncryptionStandard.encrypt(words,key,1);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
    	              		String encrytepdText=new String(cipherText);
							String encodedString = Base64.getEncoder().encodeToString(encrytepdText.getBytes());
    	              		encryptedText.setText(encodedString);

    	                   
    	            		
    	            	}
    	            	else if(rdbtnDes.isSelected()&& rdbtnCbs.isSelected()){
    	            		String key="MZygpewJsCpRrfOr";
    	            		DES desEncryption=new DES(words,0,key);
							String encodedString = Base64.getEncoder().encodeToString((desEncryption.encryptedData).getBytes());
							encryptedText.setText(encodedString);
    	            	}
    	            	else if(rdbtnDes.isSelected() && rdbtnOfb.isSelected()){
    	            		String key="MZygpewJsCpRrfOr";
    	            		DES desEncryption=new DES(words,1,key);
							String encodedString = Base64.getEncoder().encodeToString((desEncryption.encryptedData).getBytes());
							encryptedText.setText(encodedString);
    	            	}
    				}
    			});
    			tglbtnNewToggleButton.setBounds(329, 60, 107, 25);
    			panel.add(tglbtnNewToggleButton);

    			tglbtnNewToggleButton_1 = new JToggleButton("Send");
    			tglbtnNewToggleButton_1.setBounds(439, 60, 93, 25);
    			panel.add(tglbtnNewToggleButton_1);

    			connectInfo = new JLabel("Not Connected");
    			connectInfo.setBounds(10, 547, 97, 16);
    			frame.getContentPane().add(connectInfo);

        tglbtnNewToggleButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println(userText.getText());
                userText.setText("");
			}
		});

        tglbtnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userText.setEditable(true);
			}
		});
        
        
    }

    private String getName() {
        return JOptionPane.showInputDialog(
            frame,
            "Enter username:",
            "Input",
            JOptionPane.PLAIN_MESSAGE);
    }

    private void run() throws Exception {
    	
        // Make connection and initialize streams
        String serverAddress = "localhost";
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                out.println(getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
                userText.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
            	int idx = line.lastIndexOf(">");
        		String mssg = line.substring(idx + 1);
				connectInfo.setText("Connected: "+line.substring(8,idx + 1));
            	if(rdbtnAes.isSelected() && rdbtnCbs.isSelected()){
            		 String key="MZygpewJsCpRrfOr";
                  	byte[] encryptionKey = "MZygpewJsCpRrfOr".getBytes();
              		//byte[] plainText = input.getBytes();
              		AES advancedEncryptionStandard = new AES(encryptionKey);
              		byte[] cipherText = advancedEncryptionStandard.encrypt(mssg,key,0);
              		String decryptedCipherText = advancedEncryptionStandard.decrypt(cipherText,key,0);
              		String encrytepdText=new String(cipherText);
					String encodedString = Base64.getEncoder().encodeToString(encrytepdText.getBytes());
              		name=line.substring(8,idx + 1);
                     DisplayMessage.append(encodedString + "\n" + line.substring(8,idx + 1) + decryptedCipherText + "\n");
            		
            	}
            	else if(rdbtnAes.isSelected() && rdbtnOfb.isSelected()){
            		String key="MZygpewJsCpRrfOr";
                  	byte[] encryptionKey = "MZygpewJsCpRrfOr".getBytes();
              		//byte[] plainText = input.getBytes();
              		AES advancedEncryptionStandard = new AES(encryptionKey);
              		byte[] cipherText = advancedEncryptionStandard.encrypt(mssg,key,1);
              		String decryptedCipherText = advancedEncryptionStandard.decrypt(cipherText,key,1);
              		String encrytepdText=new String(cipherText);
					String encodedString = Base64.getEncoder().encodeToString(encrytepdText.getBytes());
              		name=line.substring(8,idx + 1);
					DisplayMessage.append(encodedString + "\n" + line.substring(8,idx + 1) + decryptedCipherText+ "\n");
            		
            	}
            	else if(rdbtnDes.isSelected() && rdbtnCbs.isSelected()){
            		String key="MZygpewJsCpRrfOr";
            		DES desEncryption=new DES(mssg,0,key);
            		name=line.substring(8,idx + 1);
					String encodedString = Base64.getEncoder().encodeToString((desEncryption.encryptedData).getBytes());
					DisplayMessage.append(encodedString + "\n" + line.substring(8,idx + 1) + desEncryption.decryptedMessage + "\n");
            	}
            	else if(rdbtnDes.isSelected() && rdbtnOfb.isSelected()){
            		String key="MZygpewJsCpRrfOr";
            		DES desEncryption=new DES(mssg,1,key);
            		name=line.substring(8,idx + 1);
					String encodedString = Base64.getEncoder().encodeToString((desEncryption.encryptedData).getBytes());
            		DisplayMessage.append(encodedString + "\n" + line.substring(8,idx + 1) + desEncryption.decryptedMessage + "\n");
            	}
            	
            }
        }
      
    }
  

    public static void main(String[] ar) throws Exception {
        ChatClient client = new ChatClient();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
}