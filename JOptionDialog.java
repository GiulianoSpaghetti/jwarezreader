package org.altervista.numerone.JWarez;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


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
	protected WarezOpzioni w;
	protected ResourceBundle bundle;
	public JOptionDialog(JFrame parent, WarezOpzioni wo, ResourceBundle bundle) {
		super(parent);
		setModal(true);
		setTitle(bundle.getString("Impostazioni"));
		w=wo;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JPanel p=new JPanel(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		this.bundle=bundle;

		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		p.add(new JLabel(bundle.getString("PathFile")+": "), c);
		c.gridx=1;
		p.add(path=new JTextField(50), c);
		path.setText(w.path);
		c.gridx=2;
		scegli=new JButton(bundle.getString("Scegli"));
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
		p.add(new JLabel(bundle.getString("NumeriRighe")+": "), c);
		c.gridx=1;
		c.gridwidth=2;
		p.add(righe=new JTextField(50), c);
		righe.setText(w.righe);
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=1;
		p.add(new JLabel(bundle.getString("NumeriColonne")+": "), c);
		c.gridx=1;
		c.gridwidth=2;
		p.add(colonne=new JTextField(50), c);
		colonne.setText(w.colonne);
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
		annulla=new JButton(bundle.getString("Annulla"));
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
		if (path.getText().isEmpty() || path.getText()=="" || path.getText()==null) {
			JOptionPane.showMessageDialog(this, bundle.getString("pathNulla"), bundle.getString("Errore"), JOptionPane.ERROR_MESSAGE);
			return;
		}
		w.path=path.getText();
		if (!righe.getText().isEmpty()) {
			w.righe=righe.getText();
			if (w.righe=="") {
				JOptionPane.showMessageDialog(this, bundle.getString("righeNonCorrette"), bundle.getString("Errore"), JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
		if (!colonne.getText().isEmpty()) {
			w.colonne=colonne.getText();
			if (w.colonne=="") {
				JOptionPane.showMessageDialog(this, bundle.getString("colonneNonCorrette"), bundle.getString("Errore"), JOptionPane.ERROR_MESSAGE);
				return;
			}
		}	
			w.colonne=colonne.getText();
			w.righe=righe.getText();
			w.path=path.getText();
			setVisible(false);
	}
	
	public void CloseFrame() {
		super.dispose();
	}
	
}
