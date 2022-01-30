package org.altervista.numerone.JWarez;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class JOptionDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1203804880921870782L;
	protected JTextField righe;
	protected JTextField colonne;
	protected JTextField path;
	private JButton annulla;
	private JButton ok;
	private JButton scegli;
	protected File file;
	protected Vector<Integer> numRighe;
	protected Vector<Integer> numColonne;
	protected String nomeFile;
	
	public JOptionDialog(JFrame parent, File f) {
		super(parent);
		setModal(true);
		setTitle("Impostazioni");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JPanel p=new JPanel(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		try {
			BufferedReader r=new BufferedReader(new FileReader(f));
			nomeFile=r.readLine();
			numRighe=WriterParser.explode(r.readLine());
			numColonne=WriterParser.explode(r.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Il file di opzioni non è stato trovato, ne sarà creato uno nuovo.", "Attezione", JOptionPane.WARNING_MESSAGE);
		}
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		p.add(new JLabel("Path dei files: "), c);
		c.gridx=1;
		p.add(path=new JTextField(50), c);
		path.setText(nomeFile);
		c.gridx=2;
		scegli=new JButton("Scegli");
		scegli.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File(".")); // start at application current directory
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showSaveDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
				    file = fc.getSelectedFile();
				    path.setText(file.getPath());
			    }
			}
		});
		p.add(scegli, c);
		c.gridy=1;
		c.gridx=0;
		p.add(new JLabel("Numeri delle righe in cui cercare: "), c);
		c.gridx=1;
		c.gridwidth=2;
		p.add(righe=new JTextField(50), c);
		righe.setText(WriterParser.implode(numRighe));
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=1;
		p.add(new JLabel("Numeri delle colonne in cui cercare: "), c);
		c.gridx=1;
		c.gridwidth=2;
		p.add(colonne=new JTextField(50), c);
		colonne.setText(WriterParser.implode(numColonne));
		c.gridx=0;
		c.gridy=3;
		c.gridwidth=1;
		ok=new JButton("Ok");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				OnOK();
			}});
		p.add(ok, c);
		c.gridx=1;
		annulla=new JButton("Annulla");
		annulla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CloseFrame();
			}});
		p.add(annulla, c);
		add(p);
		pack();
	    SwingUtilities.updateComponentTreeUI(this);
		setVisible(true);
	}
	
	protected void OnOK() {
		BufferedWriter writer;
		if (path.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "La path non può essere nulla", "Errore", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (!righe.getText().isEmpty()) {
			numRighe=WriterParser.parse(righe.getText());
			if (numRighe==null) {
				JOptionPane.showMessageDialog(this, "La stringa numeri delle righe in cui cercare non è corretta", "Errore", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
		if (!colonne.getText().isEmpty()) {
			numColonne=WriterParser.parse(colonne.getText());
			if (numColonne==null) {
				JOptionPane.showMessageDialog(this, "La stringa numeri delle colonne in cui cercare non è corretta", "Errore", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}	
		try {
			writer = new BufferedWriter(new FileWriter("./JWarezReader.ini"));
			writer.write(path.getText()+"\n");
			writer.write(WriterParser.implode(numRighe));
			writer.write("\n");
			writer.write(WriterParser.implode(numColonne));
			writer.write("\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getStackTrace(), "Errore", JOptionPane.ERROR_MESSAGE);
		}
		setVisible(false);
	}
	
	public void CloseFrame() {
		super.dispose();
	}
	
}
