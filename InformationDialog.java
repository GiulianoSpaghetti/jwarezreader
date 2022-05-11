package org.altervista.numerone.JWarez;

import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class InformationDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3508617257586299950L;
	private JButton Ok;
	private JButton sitoMio;
	private JButton sitoWarez;
	public InformationDialog(JFrame parent, String vers) {
		super(parent);
		setModal(true);
		setTitle("Informazioni");
//		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JPanel p=new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		p.add(new JLabel("Autore: "), c);
		c.gridx=1;
		p.add(new JLabel("Giulio Sorrentino (aka numerone)"), c);
		c.gridx=0;
		c.gridy=1;
		p.add(new JLabel("Copyright:"), c);
		c.gridx=1;
		p.add(new JLabel("GPL versione 3 o, secondo la tua opinione, qualsiasi versione successiva"), c);
		c.gridx=0;
		c.gridy=2;
		p.add(new JLabel("Versione: "), c);
		c.gridx=1;
		p.add(new JLabel(""+vers), c);
		c.gridx=0;
		c.gridy=3;
		p.add(new JLabel("Anno di creazione:"), c);
		c.gridx=1;
		p.add(new JLabel("2022"), c);
		c.gridx=0;
		c.gridy=4;
		p.add(new JLabel("Idea di: "), c);
		c.gridx=1;
		p.add(new JLabel("Antonio Adamo di Warez, via Solimena 26-28, Napoli"), c);
		Ok=new JButton("Ok");
		Ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CloseFrame();
			}});
		
		c.gridx=0;
		c.gridy=5;
		sitoMio=new JButton("Sito di numerone");
		sitoMio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				openBrowser("https://github.com/numerunix");
			}
		});
		p.add(sitoMio, c);
		c.gridx=1;
		sitoWarez = new JButton("Sito di Antonio");
		sitoWarez.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				openBrowser("http://warenapoli.com");
				
			}
			
		});
		p.add(sitoWarez, c);
		c.gridx=0;
		c.gridy=6;
		c.gridwidth=2;
		p.add(Ok, c);
		add(p);
		pack();
	    SwingUtilities.updateComponentTreeUI(this);
		setVisible(true);
	}
	
	protected void openBrowser(String url) {
	        if(Desktop.isDesktopSupported()){
	            Desktop desktop = Desktop.getDesktop();
	            try {
	                desktop.browse(new URI(url));
	            } catch (IOException | URISyntaxException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }else{
	            Runtime runtime = Runtime.getRuntime();
	            try {
	                runtime.exec("xdg-open " + url);
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }

	        }
	}
	        
	public void CloseFrame() {
		super.dispose();
	}
	
	/*public static void main(String[] args) {
	try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
			| UnsupportedLookAndFeelException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
        		InformationDialog d=new InformationDialog("0.1");
            }
        });
}*/
}
