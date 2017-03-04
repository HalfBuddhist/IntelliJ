package com.lqw.common;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Utility {
	
	private static Logger logger = Logger.getLogger(Utility.class);
	
	/**< the email regular expression */
	public static String gEmailRE = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";	
	
	/**< the phonenumber regular expression */
	public static String gPhoneNumberRE = "[0-9]{11}";
	
	/**< the user name regular expression */
	public static String gUserNameRE = "[a-zA-Z0-9_\\.\u4e00-\u9fa5]{2,45}";

	public static boolean isDoubleEquals(double a, double b){
		double ZERO = 1e-9;
		return Math.abs(a - b) < ZERO;
	}
	

	/**
	 * generate the verification Code
	 * 
	 * @return
	 */
	public static String generateVerificationCode() {
		String codeString = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			// method 1
			int randomInt = random.nextInt(10);
			codeString += randomInt;

			// method 2
			// double a = Math.random() * 10;
			// a = Math.floor(a);
			// int randomNum = new Double(a).intValue();
			// codeString += randomNum;
		}
		return codeString;

	}
	
	/**
	 * generate the 4 random number string
	 * 
	 * @return
	 */
	public static String generate4RandomNumberString() {
		String codeString = "";
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			// method 1
			int randomInt = random.nextInt(10);
			codeString += randomInt;

			// method 2
			// double a = Math.random() * 10;
			// a = Math.floor(a);
			// int randomNum = new Double(a).intValue();
			// codeString += randomNum;
		}
		return codeString;
	}

	/**
	 * get json from the http servlet request
	 * 
	 * @param request
	 * @return
	 */
	public static JSONObject getJSONFromRequest(HttpServletRequest request, boolean _p_b_log_switch) {
		String _l_str_json = "";
		String _l_str_line = null;
		JSONObject _r_json = null;

		BufferedReader br;
		try {
			// read the text
			br = new BufferedReader(request.getReader());
			while ((_l_str_line = br.readLine()) != null) {
				_l_str_json += _l_str_line;
			}

			if (_p_b_log_switch)
				logger.info("json text: " + _l_str_json);

			// parse to json
			_r_json = new JSONObject(_l_str_json);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return _r_json;
	}
	
	public static void printBackJSONStr(HttpServletResponse response, String pResultStr)
	{
		// send back
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");

		// first method
		// JSONObject ret_json = new JSONObject(result);
		// ret_json.write(response.getWriter());

		// second method		
		try {
			PrintWriter out = response.getWriter();
			out.print(pResultStr);
			out.flush();
			out.close();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		// third method
		// use the out.write()method instead of the out.print() method.
	}
}
