package org.altervista.numerone.JWarez;

import java.util.Vector;

public class WriterParser {
	public static Vector<Integer> parse(String s) {
		Vector<Integer> ritorno= new Vector<Integer>();
		String temp[]=s.split(",");
		String temp1[];
		int fine;
		try {
			for (String str : temp) {
				if (str.contains("-")) {
					temp1=str.split("-");
						ritorno.add(Integer.parseInt(temp1[0]));
						fine=Integer.parseInt(temp1[1]);
						for (int i=ritorno.lastElement()+1; i<=fine; i++)
							ritorno.add(i);
				} else {
					ritorno.add(Integer.parseInt(str));
				}
			}
		} catch (NumberFormatException e) {
				return null;
		}
		return ritorno;
	}
	
	public static String implode(Vector<Integer> i) {
		String s="";
		if (i!=null && i.size()>0) {
			for(int j=0; j<i.size(); j++) {
				if (!s.isEmpty())
					s+=",";
				s+=i.get(j);
			}
		}
		return s;
	}
	
	public static Vector<Integer> explode(String s) {
		Vector<Integer> i=new Vector<Integer>();
		if (s!=null && !s.isEmpty()) {
			String[] str=s.split(",");
			for (String temp : str)
				i.add(Integer.parseInt(temp));
		}
		return i;
	}
}
