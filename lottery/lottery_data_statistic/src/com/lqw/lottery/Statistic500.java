package com.lqw.lottery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

public class Statistic500 {

    private static Logger logger = Logger.getLogger(Statistic500.class);

    public final static String[] _m_2_statistic = {"Oddset (奥德赛特)", "澳门", "香港马会",
            "PinnacleSports (平博)", "必发", "Bet365"};

    private final static float failThreshold = 1.8f;
    private final static float failThreshold4ReliableIndex = 1.2f;


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

    public static ArrayList<String> getNodeList1(String url) {

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
                    if (getStringsByRegex(node.getText())) {
                        for (int i = 0; i < 6; i++) {
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

    public void startStatistic(String pUrl) {
        final ArrayList<String> result = new ArrayList<String>();
        Parser parser = null;
        NodeList nodeList = null;

        final ArrayList<ArrayList<Float>> _l_arr_pei_win = new ArrayList<ArrayList<Float>>();
        final ArrayList<Float> _l_arr_statistic_pei_win = new ArrayList<Float>();

        final ArrayList<String> teamArrayList = new ArrayList<String>();

        final ArrayList<String> matchArrayList = new ArrayList<String>();


        //parse the html
        try {
            parser = new Parser(pUrl);
            parser.setEncoding("gb2312");

            nodeList = parser.parse(new NodeFilter() {

                @Override
                public boolean accept(Node node) {


                    //get the records specified
                    if (isLotteryRow(node.getText())) {
                        //get the brand
//                        Node brandNode = getBrandNode(node);

                        Node brandNode = node.getChildren().elementAt(3).getFirstChild().getFirstChild().getFirstChild();

                        int index = is2Statistic(brandNode.toPlainTextString());
                        if (index != -1) {
                            //2 statistics
                            //get the pei
                            Node finalRowNode = node.getChildren().elementAt(5).getChildren().elementAt(1).getChildren().elementAt(5);

                            ArrayList<Float> _l_pei_win = new ArrayList<Float>();
                            _l_pei_win.add((float) index);
                            Node peiNode = finalRowNode.getFirstChild();
                            while (peiNode != null) {
                                if (!peiNode.toPlainTextString().trim().equals(""))
                                    _l_pei_win.add(Float.parseFloat(peiNode.toPlainTextString()));

                                peiNode = peiNode.getNextSibling();
                            }

                            //get the win
//                            printChildrenStructure(node.getChildren().elementAt(9).getChildren().elementAt(1));
                            Node finalWinNode = node.getChildren().elementAt(9).getChildren().elementAt(1).getChildren().elementAt(5).getChildren().elementAt(1);
                            String finalWinString = finalWinNode.getFirstChild().toPlainTextString().trim();
                            _l_pei_win.add(Float.parseFloat(finalWinString.substring(0, finalWinString.length() - 1)));
                            _l_arr_pei_win.add(_l_pei_win);
                        }
                    }

                    //get the statistic value
                    if (isStatisticRow(node.getText())) {
                        if (node.getChildren().elementAt(3).toPlainTextString().trim().equals("平均值")) {
                            //get the statistic value
                            Node _l_statistic_pei_row = node.getChildren().elementAt(5).getChildren().elementAt(1).getChildren().elementAt(4);

                            Node _l_statistic_pei = _l_statistic_pei_row.getFirstChild();
                            while (_l_statistic_pei != null) {
                                if (!_l_statistic_pei.toPlainTextString().trim().equals("")) {
                                    _l_arr_statistic_pei_win.add(Float.parseFloat(_l_statistic_pei.toPlainTextString()));
                                }
                                _l_statistic_pei = _l_statistic_pei.getNextSibling();
                            }

                            //get the statistic win value
                            Node _l_node_sta_final_win = node.getChildren().elementAt(9).getChildren().elementAt(1).getChildren().elementAt(5).getChildren().elementAt(1);
                            String _l_str_sta_final_win = _l_node_sta_final_win.toPlainTextString();
                            _l_arr_statistic_pei_win.add(Float.parseFloat(_l_str_sta_final_win.substring(0, _l_str_sta_final_win.length() - 1)));
                        }

                    }


                    //get the team
                    if (isTeam(node.getText())) {
                        logger.debug("team name: " + node.toPlainTextString());
                        teamArrayList.add(node.toPlainTextString().trim());
                    }

                    //get the match info
                    if (isMatch(node.getText())) {
                        logger.debug("match name: " + node.toPlainTextString());
                        matchArrayList.add(node.toPlainTextString().trim());
                    }


                    return false;
                }
            });
        } catch (ParserException e) {
            e.printStackTrace();
        }


        //sort the array
        Collections.sort(_l_arr_pei_win, new Comparator<ArrayList<Float>>() {

            @Override
            public int compare(ArrayList<Float> o1, ArrayList<Float> o2) {
                // TODO Auto-generated method stub
                return (int) (o1.get(0) - o2.get(0));
            }

        });


        //print the statistic result
        int startIndex = 0;
        String outputString = matchArrayList.get(0) + "	" + teamArrayList.get(0) + "	"
                + teamArrayList.get(1) + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";


        for (int i = 0; i < _m_2_statistic.length; i++) {
            if (_l_arr_pei_win.size() == 0) {
                outputString += "X	";
            } else {
                ArrayList<Float> currentPeiWinArray = _l_arr_pei_win.get(startIndex);
                int index = (int) (currentPeiWinArray.get(0).floatValue());
                if (index == i) {
                    if (((i == 3 || i == 4 || i == 5) && currentPeiWinArray.get(4) - _l_arr_statistic_pei_win.get(3) < failThreshold) ||
                            ((i == 1 || i == 0 || i == 2) && (_l_arr_statistic_pei_win.get(3) - currentPeiWinArray.get(4)) < failThreshold4ReliableIndex)) {
                        //could neglect it
                        outputString += "X	";
                    } else {
                        String outputStringTemp = "";
                        switch (index) {
                            case 0:
                            case 1:
                            case 2: {
                                //oddset
                                //1
                                float distance = currentPeiWinArray.get(1) - _l_arr_statistic_pei_win.get(0);

                                if (distance > 0f) {
                                    outputStringTemp += "23|";
                                } else if (distance >= -0.02f) {
                                    outputStringTemp += "n-23|";
                                } else {
                                    outputStringTemp += "n|";
                                }

                                //2
                                distance = currentPeiWinArray.get(2) - _l_arr_statistic_pei_win.get(1);

                                if (distance > 0f) {
                                    outputStringTemp += "13|";
                                } else if (distance >= -0.02f) {
                                    outputStringTemp += "n-13|";
                                } else {
                                    outputStringTemp += "n|";
                                }

                                //3
                                distance = currentPeiWinArray.get(3) - _l_arr_statistic_pei_win.get(2);

                                if (distance > 0f) {
                                    outputStringTemp += "12|";
                                } else if (distance >= -0.02) {
                                    outputStringTemp += "n-12|";
                                } else {
                                    outputStringTemp += "n|";
                                }

                            }
                            break;

                            case 3:
                            case 4:
                            case 5: {
                                //pin
                                //1
                                float distance = currentPeiWinArray.get(1) - _l_arr_statistic_pei_win.get(0);

                                if (distance < 0f) {
                                    outputStringTemp += "23|";
                                } else if (distance <= 0.02f) {
                                    outputStringTemp += "n-23|";
                                } else {
                                    outputStringTemp += "n|";
                                }

                                //2
                                distance = currentPeiWinArray.get(2) - _l_arr_statistic_pei_win.get(1);

                                if (distance < 0f) {
                                    outputStringTemp += "13|";
                                } else if (distance <= 0.02f) {
                                    outputStringTemp += "n-13|";
                                } else {
                                    outputStringTemp += "n|";
                                }

                                //3
                                distance = currentPeiWinArray.get(3) - _l_arr_statistic_pei_win.get(2);

                                if (distance < 0f) {
                                    outputStringTemp += "12|";
                                } else if (distance <= 0.02) {
                                    outputStringTemp += "n-12|";
                                } else {
                                    outputStringTemp += "n|";
                                }

                            }
                            break;

                            default:
                                break;
                        }

                        outputString = outputString + outputStringTemp + "	";
                    }

                    startIndex += 1;
                } else {
                    outputString += "X\t";
                }
            }
        }

        //print it
        outputString = "\n" + outputString
                + _l_arr_statistic_pei_win.get(0) + "	"
                + _l_arr_statistic_pei_win.get(1) + "	"
                + _l_arr_statistic_pei_win.get(2) + "	"
                + _l_arr_statistic_pei_win.get(3) + "	"
                + pUrl;
        logger.info(outputString);
    }

    private Node getBrandNode(Node node) {
        NodeList children = node.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Node child = children.elementAt(i);
            if (child.getText().matches("td row=\"1\" class=\"tb_plgs\" title=\".*")) {
                //is the brand. find the brand text
                return child.getFirstChild().getFirstChild().getFirstChild();
            }
        }
        return null;
    }

    protected boolean isLotteryRow(String text) {
        // TODO Auto-generated method stub
//		String regex = "tr class=\"tr2|1\" id=\"656\" ttl=\"zy\" data-time=\"2014-10-17 00:53:39\" xls=\"row\">";
        String regex = "tr class=\"tr(2|1)\".*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        if (m.find()) {
            return true;
        }
        return false;
    }

    protected boolean isStatisticRow(String text) {
        // TODO Auto-generated method stub
        // String regex =
        // "tr class=\"tr2|1\" id=\"656\" ttl=\"zy\" data-time=\"2014-10-17 00:53:39\" xls=\"row\">";
        String regex = "tr xls=\"footer\".*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        if (m.find()) {
            return true;
        }
        return false;
    }

    protected boolean isTeam(String text) {
        // TODO Auto-generated method stub
        // String regex =
        // "tr class=\"tr2|1\" id=\"656\" ttl=\"zy\" data-time=\"2014-10-17 00:53:39\" xls=\"row\">";
        //<a class="hd_name" href="http://liansai.500.com/team_data-teamid-700"
        String regex = "a class=\"hd_name\" href=\"http://liansai.500.com/team_data-teamid.*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        if (m.find()) {
            return true;
        }
        return false;
    }

    protected boolean isMatch(String text) {
        // TODO Auto-generated method stub
        //<a class="hd_name" href="http://liansai.500.com/seasonindex-seasonid-2947" target="_bla
        String regex = "a class=\"hd_name\" href=\"http://liansai.500.com/seasonindex-seasonid.*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        if (m.find()) {
            return true;
        }
        return false;
    }

    protected void printChildrenStructure(Node node) {
        logger.info("0th children  " + node.getChildren().size());

        for (int i = 0; i < node.getChildren().size(); i++) {
            if (node.getChildren().elementAt(i).toHtml().trim().equals(""))
                System.out.println("Found null children.");
            logger.info(i + "th children" + node.getChildren().elementAt(i).toHtml());
        }
    }

    /**
     * is to statistic this brand
     *
     * @param pBrand
     * @return -1 no n yes and the brand
     */
    public static int is2Statistic(String pBrand) {
        // TODO Auto-generated method stub
        for (int i = 0; i < _m_2_statistic.length; i++) {
            if (pBrand.trim().equals((String) (_m_2_statistic[i]).trim())) {
                return i;
            }
        }

        return -1;
    }

    public static boolean isLotteryBrand(String text) {
        // TODO Auto-generated method stub
        String regex = "span class=\"quancheng\" style=\"display:;\">";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.print("Please input the url:");
        Scanner scanner = new Scanner(System.in);

        String url = null;
        Statistic500 s500 = new Statistic500();
        while ((url = scanner.next()) != null) {
            if (!url.startsWith("http"))
                break;
            s500.startStatistic(url);
            System.out.print("\nPlease input the url:");
        }

        System.out.print("Thanks for using!");
    }
}
