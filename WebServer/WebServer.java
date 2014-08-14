//WebServer.java  用JAVA编写Web服务器
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class WebServer extends JFrame 
{
  private JTextArea consolePane;
  
  public WebServer() {
	  super("WebServer");
	  consolePane = new JTextArea();
	  consolePane.setEditable(false);
	  this.getContentPane().add(new JScrollPane(consolePane), BorderLayout.CENTER);
	  System.setOut(new PrintStream(System.out) {
		 public void println(String x) {
			 consolePane.append(x + "\n");
		 }
	  });
	  this.addWindowListener(new WindowAdapter() {
      	public void windowClosing(WindowEvent e) {
      		close();
      	}
      });
  }
  
  public void close() {
	  this.setVisible(false);
	  this.dispose();
	  System.exit(0);
  }
 
  public static void main(String args[]) {
	    int i=1, PORT=8080;
	    ServerSocket server=null;
	    Socket client=null;
	    WebServer webserver = new WebServer();
	    webserver.setSize(800,600);
	    webserver.setVisible(true);
	    try {
	      server=new ServerSocket(PORT);  
	      System.out.println("Web Server is listening on port "+server.getLocalPort());
	      for (;;) {
	        client=server.accept();  //接受客户机的连接请求
	        new ConnectionThread(client,i).start();  
	        i++;
	      }
	    } catch (Exception e) {
	    	System.out.println("Unexpected Error!");
	    }
	  }
}

