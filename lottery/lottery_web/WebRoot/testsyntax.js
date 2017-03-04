//return XMLHttp
function createXMLHttp() {
	var XMLHttp;
	// alert( 'createXMLHttp....' );	
	if (window.XMLHttpRequest) {
		// alert( 'XMLHttpRequest....' );	 
		XMLHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {// ie seris
		//alert('ActiveXObject....');
		var versions = [ "MSXML2.XMLHttp.5.0", "MSXML2.XMLHttp.4.0",
				"MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp", "Microsoft.XMLHttp" ];
		for ( var i = 0; i < versions.length; i++) {
			try {
				XMLHttp = new ActiveXObject(versions[i]);
				if (XMLHttp) {
					break;
				}
			} catch (e) {
				//alert("exeption");
			}
		}
	}
	// alert( XMLHttp );
	return XMLHttp;
}

function buttonclick() {

	//test register
	//	var xmlhttp = new createXMLHttp();
	//	var URL = "http://localhost:8082/Thermometer/user/register.json";
	//	var data_send = '{"username":"Wei.Wang", "password":"123456"}';
	//	alert(data_send);
	//
	//	xmlhttp.open("POST", URL, true); //async style
	//	//xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8"); 
	//	//to store the user data via ajax by imitating the form, the second parameter is a must.
	//	xmlhttp.setRequestHeader('Content-Type',
	//			'application/x-www-form-urlencoded;charset=UTF-8');
	//
	//	xmlhttp.onreadystatechange = function() {
	//		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	//			alert(xmlhttp.responseText);
	//		}
	//	}; 
	//	xmlhttp.send(data_send);

	//test login
//				var xmlhttp = new createXMLHttp();
//				var URL = "http://localhost:8082/Thermometer/user/login.json";
//				var data_send = '{"username":"Yin.Wang", "password":"123456"}';
//				alert(data_send);
//			
//				xmlhttp.open("POST", URL, true); //async style
//				//xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8"); 
//				//to store the user data via ajax by imitating the form, the second parameter is a must.
//				xmlhttp.setRequestHeader('Content-Type',
//						'application/x-www-form-urlencoded;charset=UTF-8');
//			
//				xmlhttp.onreadystatechange = function() {
//					if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//						alert(xmlhttp.responseText);
//					}
//				}; 
//				xmlhttp.send(data_send);

	//test logout
	//	var xmlhttp = new createXMLHttp();
	//	var URL = "http://localhost:8082/Thermometer/user/logout.json?auth_token=39743B1D99132041C5B8369B7B92C0FD";
	////	var data_send = '{"username":"Wei.Wang", "password":"12346"}';
	////	alert(data_send);
	//
	//	xmlhttp.open("GET", URL, true); //async style
	//	//xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8"); 
	//	//to store the user data via ajax by imitating the form, the second parameter is a must.
	//	xmlhttp.setRequestHeader('Content-Type',
	//			'application/x-www-form-urlencoded;charset=UTF-8');
	//
	//	xmlhttp.onreadystatechange = function() {
	//		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	//			alert(xmlhttp.responseText);
	//		}
	//	}; 
	//	xmlhttp.send(null);


	//test new add record
//	var xmlhttp = new createXMLHttp();
//	var URL = "http://localhost:8082/Thermometer/sync/add.json?auth_token=0E4B9A469593BD1B44594E1356CD69E9";
////	var data_send = '{"records":[{"id":"5967FDF6-F5CB-4E8A-9FF4-F5A322926D44", "date":"2013-08-25 11:36:50", "temperature":35.6, "periodType":1, "hadSex":0, "memo":"today is a good day.", "condition":3}, {"id":"733D2386-EC09-4C4B-916D-651E53883896", "date":"2013-08-25 11:39:50", "temperature":36.6, "periodType":2, "hadSex":1, "memo":"today is not a good day.", "condition":4}]}';
//	var data_send = '{"records":[{"id":"5967FDF6-F5CB-4E8A-9FF4-F5A322926D44", "date":"2013-08-27 11:36:50", "temperature":35.6, "periodType":1, "hadSex":1, "memo":"today is a good day.", "condition":3}, {"id":"733D2386-EC09-4C4B-916D-651E53883896", "date":"2013-08-29 11:39:50", "temperature":36.6, "periodType":2, "hadSex":0, "memo":"today is not a good day.", "condition":4}]}';
//	alert(data_send);
//
//	xmlhttp.open("POST", URL, true); //async style
//	//xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8"); 
//	//to store the user data via ajax by imitating the form, the second parameter is a must.
//	xmlhttp.setRequestHeader('Content-Type',
//			'application/x-www-form-urlencoded;charset=UTF-8');
//
//	xmlhttp.onreadystatechange = function() {
//		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//			alert(xmlhttp.responseText);
//		}
//	};
//	xmlhttp.send(data_send);
	
	//test fetch
//	var xmlhttp = new createXMLHttp();
//	var URL = "http://localhost:8082/Thermometer/sync/get.json?auth_token=86D0E3B866D7AEA520EECBCDD59D990A";
//	//var data_send = '{"records":[{"id":-1, "date":"2013-08-25 11:36:50", "temperature":35.6, "periodType":1, "hadSex":0, "memo":"today is a good day.", "condition":3}, {"id":-1, "date":"2013-08-25 11:39:50", "temperature":36.6, "periodType":2, "hadSex":1, "memo":"today is not a good day.", "condition":4}]}';
//	//alert(data_send);
//
//	xmlhttp.open("GET", URL, true); //async style
//	//xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8"); 
//	//to store the user data via ajax by imitating the form, the second parameter is a must.
//	xmlhttp.setRequestHeader('Content-Type',
//			'application/x-www-form-urlencoded;charset=UTF-8');
//
//	xmlhttp.onreadystatechange = function() {
//		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//			alert(xmlhttp.responseText);
//		}
//	};
//	xmlhttp.send(null);
	
	
	//test delete record
//	var xmlhttp = new createXMLHttp();
//	var URL = "http://localhost:8082/Thermometer/sync/delete.json?auth_token=86D0E3B866D7AEA520EECBCDD59D990A";
//	var data_send = '{"records":["5967FDF6-F5CB-4E8A-9FF4-F5A322926D44"]}';
//	alert(data_send);
//
//	xmlhttp.open("POST", URL, true); //async style
//	//xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8"); 
//	//to store the user data via ajax by imitating the form, the second parameter is a must.
//	xmlhttp.setRequestHeader('Content-Type',
//			'application/x-www-form-urlencoded;charset=UTF-8');
//
//	xmlhttp.onreadystatechange = function() {
//		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//			alert(xmlhttp.responseText);
//		}
//	};
//	xmlhttp.send(data_send);
	
	
	//test fetch by date records
	var xmlhttp = new createXMLHttp();
	var URL = "http://localhost:8082/Thermometer/sync/get_by_range.json?auth_token=0E4B9A469593BD1B44594E1356CD69E9";
	var data_send = '{"start_date":"2013-07-28 00:00:00", "end_date":"2013-08-28 00:00:00"}';
	alert(data_send);

	xmlhttp.open("POST", URL, true); //async style
	//xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8"); 
	//to store the user data via ajax by imitating the form, the second parameter is a must.
	xmlhttp.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=UTF-8');

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			alert(xmlhttp.responseText);
		}
	};
	xmlhttp.send(data_send);

	//		xmlhttp.SetRequestHeader ("Content-Type","text/xml; charset=utf-8");   
	//		xmlhttp.SetRequestHeader ("SOAPAction","http://tempuri.org/SayHelloTo");   
	//		xmlhttp.send(null);  

	//		var result = xmlhttp.status;   
	////		OK  
	//		if(result==200) {   
	//			document.write(xmlhttp.responseText);   
	//		}   
	//		xmlhttp = null; 

	//	alert(temp_url.replace(/&/g, " "));

	//xmlhttp.onreadystatechange = function() {
	//		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	//				alert("hello world!");
	//alert(xmlhttp.responseText);
	//			}
	//	};

	//xmlhttp.open("GET",URL, true);  
	//xmlhttp.SetRequestHeader ("Content-Type","text/xml; charset=utf-8");   
	//xmlhttp.SetRequestHeader ("SOAPAction","http://tempuri.org/SayHelloTo");   
	//xmlhttp.send(null);   
	//var result = xmlhttp.status;   
	//OK  
	//if(result==200) {   
	//	document.write(xmlhttp.responseText);   
	//}   
	//xmlhttp = null; 

	//	var b  = Integer.toString(a);
	//	alert(b);

	//	alert(hashtable.containsValue('testsdfzz'));

	//	var array_temp = hashtable.values();	
	//	alert("sdfd");

	//	alert(hashtable.toString());

	//	var array_temp = hashtable.keys();	
	//	alert("sdfd");

	//	alert(hashtable.isEmpty());
	//	hashtable.add("test", "test");
	//	hashtable.add("test1", "test1");
	//	alert(hashtable.isEmpty());
	//	var tempvalue = hashtable.get("test")
	//	alert(tempvalue);
	//	hashtable.remove("test");
	//	alert(hashtable.get("test"));

}