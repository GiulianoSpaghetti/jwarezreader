package org.altervista.numerone.JWarez;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JTextArea;

import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

public class WarezThread extends Thread {
    private Sheet sheet;
    private boolean found=false;
    private int ColCount=0;
    private int RowCount=0;
    private int RowIndex = 0;
    private int ColIndex = 0;
    private MutableCell cell = null;
    private Vector<Integer> righe;
    private Vector<Integer> colonne;
    private JTextArea testo;
    private File f;
    private String Pattern;
    public WarezThread(File file, JTextArea a, Vector<Integer> c, Vector<Integer> r, String pattern) { f=file; testo=a; colonne=c; righe=r; Pattern=pattern;}
    
	public void run() {
	
	try {
		sheet = SpreadSheet.createFromFile(f).getSheet(0);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    //Get row count and column count
    ColCount = sheet.getColumnCount();
    RowCount = sheet.getRowCount();
    testo.setText(testo.getText()+"Ricerca nel file: "+f.getName() + ". Il file contiene "+ RowCount + " righe e "+ ColCount + " colonne.\n");
    found=false;
    if (righe.size()==0) {
    	for(int i=0; i<RowCount; i++)
    		righe.add(i);
    }
    if (colonne.size()==0) {
    	for(int i=0; i<ColCount; i++)
    		colonne.add(i);
    }
    //Iterating through each row of the selected sheet
    for(RowIndex=0; RowIndex < righe.size(); RowIndex++)
    {
   	 if (righe.get(RowIndex)>RowCount-1)
   		 continue;
      //Iterating through each column
      ColIndex = 0;
      for( ;ColIndex < colonne.size(); ColIndex++)
      {
   	   if (colonne.get(ColIndex)>ColCount-1)
   		   continue;
        cell = sheet.getCellAt(colonne.get(ColIndex), righe.get(RowIndex));
        if (cell.getTextValue().toLowerCase().contains(Pattern)) {
       	 testo.setText(testo.getText()+"Riga: "+RowIndex+" "+ "Colonna: "+ ColIndex+ " Valore: "+ cell.getTextValue()+ "\n");
       	 found=true;
        }
       }
     }
    if (!found)
   	 testo.setText(testo.getText()+"Il dato non è stato trovato.\n");
    }
	
}
