package com.lqw.lottery;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class Currencyrate {
	
	public static ArrayList<String> getNodeList(String url) {
		
		final ArrayList<String> result = new ArrayList<String>();
		Parser parser = null;
		NodeList nodeList = null;
		
		try {
			parser = new Parser(url);
			parser.setEncoding("GBK");

			nodeList = parser.parse(new NodeFilter() {
				
				@Override
				public boolean accept(Node node) {
					
					Node need = node;
					if (getStringsByRegex(node.getText())) 
					{
						for (int i = 0; i < 6; i++) 
						{
							result.add(need.toPlainTextString());
							need = need.getPreviousSibling();
						}
						return true;
					}
					return false;
				}
			});
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	
	public static boolean getStringsByRegex(String txt) {
		
		String regex = "td class=\"no\"";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(txt);
				
		if (m.find()) {
			return true;
		}
		return false;
	}

	
	public static void main(String[] args) { 
		
		String url = "http://forex.hexun.com/rmbhl/";
		ArrayList<String> rt = getNodeList(url);
		
		for (int i = 0; i < rt.size(); i++) {
			System.out.println(rt.get(i));
		}
	}
}



