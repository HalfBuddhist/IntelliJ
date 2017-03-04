package com.lqw.lottery;

import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

public class LotteryDataStatistic {

	
	
	public static NodeList getNodeList(String url) {
		
		Parser parser = null;
		HtmlPage visitor = null;
		try {
			parser = new Parser(url);
			parser.setEncoding("gb2312");
			visitor = new HtmlPage(parser);
			parser.visitAllNodesWith(visitor);
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
		NodeList nodeList = visitor.getBody();
		return nodeList;
	}
	
	static void scan(NodeList rt)
	{
		if (rt == null)
			return;
		
		for (int i = 0; i < rt.size(); i++)
		{
			if (rt.elementAt(i).toHtml().trim().equals(""))
			{
				System.out.println("Find null node.");
			}
			
			scan(rt.elementAt(i).getChildren());
		}
	}
	



	public static void main(String[] args) { 
		
		NodeList rt = getNodeList("http://odds.500.com/fenxi/ouzhi-456863.shtml");
		
		scan(rt);
		
	}
}
