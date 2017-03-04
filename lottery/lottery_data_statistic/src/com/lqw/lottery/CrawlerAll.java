package com.lqw.lottery;

import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

public class CrawlerAll {

	public static NodeList getNodeList(String url) {

		Parser parser = null;
		HtmlPage visitor = null;
		try {
			parser = new Parser(url);
			parser.setEncoding("utf-8");
			visitor = new HtmlPage(parser);
			parser.visitAllNodesWith(visitor);
		} catch (ParserException e) {
			e.printStackTrace();
		}

		NodeList nodeList = visitor.getBody();
		return nodeList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://1x2.7m.hk/list_gb.shtml?id=1127906";
//		String url = "http://1x2.7m.cn/data/detail/1127906.js";
		
		System.out.println(getNodeList(url).toHtml());
		

	}

}
