var auth_token;

var localURL = "http://localhost:8082/Thermometer/";
var testURL = "http://test.ikangtai.com/test/";
var releaseURL = "http://ikangtai.com/Thermometer/";
var baseURL = testURL;

//return XMLHttp
function createXMLHttp() {
	var XMLHttp;
	// alert( 'createXMLHttp....' );
	if (window.XMLHttpRequest) {
		// alert( 'XMLHttpRequest....' );
		XMLHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {// ie seris
		// alert('ActiveXObject....');
		var versions = [ "MSXML2.XMLHttp.5.0", "MSXML2.XMLHttp.4.0",
				"MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp", "Microsoft.XMLHttp" ];
		for ( var i = 0; i < versions.length; i++) {
			try {
				XMLHttp = new ActiveXObject(versions[i]);
				if (XMLHttp) {
					break;
				}
			} catch (e) {
				// alert("exeption");
			}
		}
	}
	// alert( XMLHttp );
	return XMLHttp;
}

// test login
function login() {
	var xmlhttp = new createXMLHttp();
	var URL = "user/login.json";
//	var data_send = '{"username":"jyhu", "password":"E10ADC3949BA59ABBE56E057F20F883E", "type" : 0}';
	var data_send = '{"username":"wade", "password":"E10ADC3949BA59ABBE56E057F20F883E", "type" : 0}';
	//alert(data_send);

	xmlhttp.open("POST", baseURL + URL, true); // async style
	// xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
	// to store the user data via ajax by imitating the form, the second parameter is a must.
	xmlhttp.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=UTF-8');

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			alert(xmlhttp.responseText);
			var responseObject = eval("(" + xmlhttp.responseText + ")");
			auth_token = responseObject.auth_token;
		}
	};
	xmlhttp.send(data_send);
}

function sendPrivateNotification() {

	// test send notification
	var xmlhttp = new createXMLHttp();
	var URL = "notification/send_notification.json";
	var data_send = '{"alert":"wade", "badge":12, "sound":"default"}';
	alert(data_send);

	xmlhttp.open("POST", baseURL + URL, true); // async style
	// xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
	// to store the user data via ajax by imitating the form, the second
	// parameter is a must.
	xmlhttp.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=UTF-8');

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			alert(xmlhttp.responseText);
		}
	};
	xmlhttp.send(data_send);
}

function bindPhone() {
	var xmlhttp = new createXMLHttp();
	var URL = "user/bind_phone.json";
	var data_send = '{"auth_token":"' + auth_token + '", "new_device_token" : "c94ede63ddfc9824a324fd52fe6efd5aea801a20c35ba930031d4d3c046d5ae0", "cur_badge":0}';	
//	var data_send = '{"auth_token":"' + auth_token + '","old_device_token":"ecb8bc8e1a51280af972a409fcf773225226f5be63511d635e7090d842c7f4c5", "cur_badge":100}';
	
	alert(data_send);
	xmlhttp.open("POST", baseURL + URL, true); // async style
	// xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
	// to store the user data via ajax by imitating the form, the second
	// parameter is a must.
	xmlhttp.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=UTF-8');

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			alert(xmlhttp.responseText);
		}
	};
	xmlhttp.send(data_send);
}


function getNotification() {
	var xmlhttp = new createXMLHttp();
	var URL = "notification/get_new_notifications.json";
	var data_send = '{"auth_token":"' + auth_token + '", "device_token" : "cb939393a8a9039c13e91750c54fe4b712127c80aaf8e72dee3235dd60f868a3", "timestamp":"1980-12-01 12:12:12"}';		

	alert(data_send);
	xmlhttp.open("POST", baseURL + URL, true); // async style
	// xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
	// to store the user data via ajax by imitating the form, the second
	// parameter is a must.
	xmlhttp.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=UTF-8');

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.write(xmlhttp.responseText);
//			alert(xmlhttp.responseText);
		}
	};
	xmlhttp.send(data_send);
}

function getCalculatedData() {
	var xmlhttp = new createXMLHttp();
	var URL = "analysis/get_calculated_data.json?auth_token=" + auth_token;
	var data_send = '{"end_date" : "2014-07-16 00:00:00"}';		

	alert(data_send);
	xmlhttp.open("POST", baseURL + URL, true); // async style
	// xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
	// to store the user data via ajax by imitating the form, the second
	// parameter is a must.
	xmlhttp.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=UTF-8');

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			//document.write(xmlhttp.responseText);
//			alert(xmlhttp.responseText);
			
			var lResponseJson  = $.parseJSON(xmlhttp.responseText);
			var rString = "";
			
			for (var tJson in lResponseJson.records){
				if (lResponseJson.records[tJson].menstrualType != 0 ||
						lResponseJson.records[tJson].ovulationType == 1 ||
						lResponseJson.records[tJson].ovulationType == 2 ||
						lResponseJson.records[tJson].isOvulationDay == 1
						)					
					rString += "period: " + lResponseJson.records[tJson].menstrualType + 
					" date: " + lResponseJson.records[tJson].date +
					" ovulation: " + lResponseJson.records[tJson].ovulationType + 
					" ovulation day: " + lResponseJson.records[tJson].isOvulationDay + 
					" index: " + lResponseJson.records[tJson].pregnancyIndex + 
					" isolated: " + lResponseJson.records[tJson].isMenstrualBoundIsolated + "<br/>";
			}		
			
			document.write(rString);
		}
	};
	xmlhttp.send(data_send);
}



function buttonclick() {

	// test send notification
	var xmlhttp = new createXMLHttp();
	// var URL =
	// "http://test.ikangtai.com/test/notification/send_notification.json";
	var URL = "http://localhost:8080/Thermometer/notification/send_notification.json";
	// var URL = "http://ikangtai.com/Thermometer/user/login.json";
	var data_send = '{"alert":"wade", "badge":12, "sound":"default"}';
	alert(data_send);

	xmlhttp.open("POST", URL, true); // async style
	// xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
	// to store the user data via ajax by imitating the form, the second
	// parameter is a must.
	xmlhttp.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=UTF-8');

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			alert(xmlhttp.responseText);
		}
	};
	xmlhttp.send(data_send);

	// //test add device
	// var xmlhttp = new createXMLHttp();
	// var URL = "http://test.ikangtai.com/test/device/add.json";
	// // var URL = "http://ikangtai.com/Thermometer/device/add.json";
	// //var data_send = '{"serial_number":"9304050607080909", "ADC_35" :
	// 3692.3, "ADC_37" : 3855.1, "ADC_39" : 4020.0, "ADC_41" : 4182.5}';
	// //var data_send = '{"serial_number":"8304050607080908", "ADC_35" :
	// 3673.9, "ADC_37" : 3838.3, "ADC_39" : 3998.4, "ADC_41" : 4162.0}';
	// //var data_send = '{"serial_number":"7304050607080907", "ADC_35" :
	// 3680.0, "ADC_37" : 3844.5, "ADC_39" : 4011.0, "ADC_41" : 4177.0}';
	// //var data_send = '{"serial_number":"6304050607080906", "ADC_35" :
	// 3713.0, "ADC_37" : 3877.5, "ADC_39" : 4043.0, "ADC_41" : 4208.2}';
	// //var data_send = '{"serial_number":"5304050607080905", "ADC_35" :
	// 3700.0, "ADC_37" : 3863.5, "ADC_39" : 4028.5, "ADC_41" : 4193.0}';
	// //var data_send = '{"serial_number":"4304050607080904", "ADC_35" :
	// 3692.0, "ADC_37" : 3859.4, "ADC_39" : 4025.0, "ADC_41" : 4193.0}';
	// //var data_send = '{"serial_number":"3304050607080903", "ADC_35" :
	// 3691.0, "ADC_37" : 3856.8, "ADC_39" : 4022.3, "ADC_41" : 4186.5}';
	// //var data_send = '{"serial_number":"2304050607080902", "ADC_35" :
	// 3712.0, "ADC_37" : 3878.5, "ADC_39" : 4043.5, "ADC_41" : 4208.7}';
	// //var data_send = '{"serial_number":"1304050607080901", "ADC_35" :
	// 3711.0, "ADC_37" : 3879.5, "ADC_39" : 4046.5, "ADC_41" : 4214.0}';
	// // var data_send = '{"serial_number":"0000000000000010", "ADC_35" :
	// 3686.6, "ADC_37" : 3845.9, "ADC_39" : 4008.9, "ADC_41" : 4173.2}';
	// // var data_send = '{"serial_number":"0000000000000011", "ADC_35" :
	// 3677.2, "ADC_37" : 3845.1, "ADC_39" : 4008.6, "ADC_41" : 4172.4}';
	// // var data_send = '{"serial_number":"0000000000000012", "ADC_35" :
	// 3674.2, "ADC_37" : 3835.4, "ADC_39" : 3997.9, "ADC_41" : 4160.5}';
	// var data_send = '{"serial_number":"0000000000000013", "ADC_35" : 3673.1,
	// "ADC_37" : 3834.6, "ADC_39" : 3997.2, "ADC_41" : 4159.79}';
	//
	//
	// alert(data_send);
	//
	// xmlhttp.open("POST", URL, true); //async style
	// //xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
	// //to store the user data via ajax by imitating the form, the second
	// parameter is a must.
	// xmlhttp.setRequestHeader('Content-Type',
	// 'application/x-www-form-urlencoded;charset=UTF-8');
	//
	// xmlhttp.onreadystatechange = function() {
	// if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	// alert(xmlhttp.responseText);
	// }
	// };
	// xmlhttp.send(data_send);

	// test get veryfication code
	// var xmlhttp = new createXMLHttp();
	// var URL =
	// "http://test.ikangtai.com/test/user/get_code.json?phone_number=18001138097&type=0";
	//		
	// xmlhttp.open("GET", URL, true); //async style
	// //xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
	// //to store the user data via ajax by imitating the form, the second
	// parameter is a must.
	// xmlhttp.setRequestHeader('Content-Type',
	// 'application/x-www-form-urlencoded;charset=UTF-8');
	//		
	// xmlhttp.onreadystatechange = function() {
	// if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	// alert(xmlhttp.responseText);
	// }
	// };
	// xmlhttp.send(null);

	// test register
	// var xmlhttp = new createXMLHttp();
	// var URL = "http://test.ikangtai.com/test/user/register.json";
	// //var URL = "http://ikangtai.com/Thermometer/user/register.json";
	// var data_send = '{"username":"wade123", "password":"123456",
	// "phonenumber" : "18001138097", "email" : "liuqw@mail.tsinghua.edu.cn",
	// "code" : "719412"}';
	// alert(data_send);
	//		
	// xmlhttp.open("POST", URL, true); //async style
	// //xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
	// //to store the user data via ajax by imitating the form, the second
	// parameter is a must.
	// xmlhttp.setRequestHeader('Content-Type',
	// 'application/x-www-form-urlencoded;charset=UTF-8');
	//		
	// xmlhttp.onreadystatechange = function() {
	// if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	// alert(xmlhttp.responseText);
	// }
	// };
	// xmlhttp.send(data_send);

	// test login
	// var xmlhttp = new createXMLHttp();
	// var URL = "http://test.ikangtai.com/test/user/login.json";
	// //var URL = "http://ikangtai.com/Thermometer/user/login.json";
	// var data_send = '{"username":"wade",
	// "password":"E10ADC3949BA59ABBE56E057F20F883E"}';
	// alert(data_send);
	//
	// xmlhttp.open("POST", URL, true); //async style
	// //xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
	// //to store the user data via ajax by imitating the form, the second
	// parameter is a must.
	// xmlhttp.setRequestHeader('Content-Type',
	// 'application/x-www-form-urlencoded;charset=UTF-8');
	//
	// xmlhttp.onreadystatechange = function() {
	// if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	// alert(xmlhttp.responseText);
	// }
	// };
	// xmlhttp.send(data_send);

	// test logout
	// var xmlhttp = new createXMLHttp();
	// var URL =
	// "http://ikangtai.com:8082/Thermometer/user/logout.json?auth_token=39743B1D99132041C5B8369B7B92C0FD";
	// // var data_send = '{"username":"Wei.Wang", "password":"12346"}';
	// // alert(data_send);
	//
	// xmlhttp.open("GET", URL, true); //async style
	// //xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
	// //to store the user data via ajax by imitating the form, the second
	// parameter is a must.
	// xmlhttp.setRequestHeader('Content-Type',
	// 'application/x-www-form-urlencoded;charset=UTF-8');
	//
	// xmlhttp.onreadystatechange = function() {
	// if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	// alert(xmlhttp.responseText);
	// }
	// };
	// xmlhttp.send(null);

	// test new add record
	// var xmlhttp = new createXMLHttp();
	// var URL =
	// "http://ikangtai.com:8082/Thermometer/sync/add.json?auth_token=10F91F1D68A14E7F701B220C7C8343EE";
	// // var data_send =
	// '{"records":[{"id":"5967FDF6-F5CB-4E8A-9FF4-F5A322926D44",
	// "date":"2013-08-25 11:36:50", "temperature":35.6, "periodType":1,
	// "hadSex":0, "memo":"today is a good day.", "condition":3},
	// {"id":"733D2386-EC09-4C4B-916D-651E53883896", "date":"2013-08-25
	// 11:39:50", "temperature":36.6, "periodType":2, "hadSex":1, "memo":"today
	// is not a good day.", "condition":4}]}';
	// var data_send =
	// '{"records":[{"id":"5917FDF6-F5CB-4E8A-9FF4-F5A322926D44",
	// "date":"2013-08-27 11:36:50", "temperature":35.6, "periodType":1,
	// "hadSex":1, "memo":"today is a good day.", "condition":3},
	// {"id":"723D2386-EC09-4C4B-916D-651E53883896", "date":"2013-08-29
	// 11:39:50", "temperature":36.6, "periodType":2, "hadSex":0, "memo":"today
	// is not a good day.", "condition":4}]}';
	// alert(data_send);
	//
	// xmlhttp.open("POST", URL, true); //async style
	// //xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
	// //to store the user data via ajax by imitating the form, the second
	// parameter is a must.
	// xmlhttp.setRequestHeader('Content-Type',
	// 'application/x-www-form-urlencoded;charset=UTF-8');
	//
	// xmlhttp.onreadystatechange = function() {
	// if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	// alert(xmlhttp.responseText);
	// }
	// };
	// xmlhttp.send(data_send);

	// test the architecture
	// var xmlhttp = new createXMLHttp();
	// var URL =
	// "http://42.96.169.51/Thermometer/sync/add.json?auth_token=10F91F1D68A14E7F701B220C7C8343EE";
	// var data_send =
	// '{"records":[{"id":"5967FDF6-F5CB-4E8A-9FF4-F5A322926D44",
	// "date":"2013-08-25 11:36:50", "temperature":35.6, "periodType":1,
	// "hadSex":0, "memo":"today is a good day.", "condition":3},
	// {"id":"733D2386-EC09-4C4B-916D-651E53883896", "date":"2013-08-25
	// 11:39:50", "temperature":36.6, "periodType":2, "hadSex":1, "memo":"today
	// is not a good day.", "condition":4}]}';
	// var data_send =
	// '{"records":[{"id":"5917FDF6-F5CB-4E8A-9FF4-F5A322926D44",
	// "date":"2013-08-27 11:36:50", "temperature":35.6, "periodType":1,
	// "hadSex":1, "memo":"today is a good day.", "condition":3},
	// {"id":"723D2386-EC09-4C4B-916D-651E53883896", "date":"2013-08-29
	// 11:39:50", "temperature":36.6, "periodType":2, "hadSex":0, "memo":"today
	// is not a good day.", "condition":4}]}';
	// alert(data_send);

	// xmlhttp.open("POST", URL, true); //async style
	// xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
	// to store the user data via ajax by imitating the form, the second
	// parameter is a must.
	// xmlhttp.setRequestHeader('Content-Type',
	// 'application/x-www-form-urlencoded;charset=UTF-8');

	// xmlhttp.onreadystatechange = function() {
	// if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	// alert(xmlhttp.responseText);
	// }
	// };
	// xmlhttp.send(data_send);

	// test fetch
	// var xmlhttp = new createXMLHttp();
	// var URL =
	// "http://ikangtai.com:8082/Thermometer/sync/get.json?auth_token=10F91F1D68A14E7F701B220C7C8343EE";
	// //var data_send = '{"records":[{"id":-1, "date":"2013-08-25 11:36:50",
	// "temperature":35.6, "periodType":1, "hadSex":0, "memo":"today is a good
	// day.", "condition":3}, {"id":-1, "date":"2013-08-25 11:39:50",
	// "temperature":36.6, "periodType":2, "hadSex":1, "memo":"today is not a
	// good day.", "condition":4}]}';
	// //alert(data_send);
	//
	// xmlhttp.open("GET", URL, true); //async style
	// //xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
	// //to store the user data via ajax by imitating the form, the second
	// parameter is a must.
	// xmlhttp.setRequestHeader('Content-Type',
	// 'application/x-www-form-urlencoded;charset=UTF-8');
	//
	// xmlhttp.onreadystatechange = function() {
	// if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	// alert(xmlhttp.responseText);
	// }
	// };
	// xmlhttp.send(null);

	// test delete record
	// var xmlhttp = new createXMLHttp();
	// var URL =
	// "http://ikangtai.com:8082/Thermometer/sync/delete.json?auth_token=86D0E3B866D7AEA520EECBCDD59D990A";
	// var data_send = '{"records":["5967FDF6-F5CB-4E8A-9FF4-F5A322926D44"]}';
	// alert(data_send);
	//
	// xmlhttp.open("POST", URL, true); //async style
	// //xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
	// //to store the user data via ajax by imitating the form, the second
	// parameter is a must.
	// xmlhttp.setRequestHeader('Content-Type',
	// 'application/x-www-form-urlencoded;charset=UTF-8');
	//
	// xmlhttp.onreadystatechange = function() {
	// if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	// alert(xmlhttp.responseText);
	// }
	// };
	// xmlhttp.send(data_send);

	// test fetch by date records
	// var xmlhttp = new createXMLHttp();
	// var URL =
	// "http://ikangtai.com:8082/Thermometer/sync/get_by_range.json?auth_token=10F91F1D68A14E7F701B220C7C8343EE";
	// var data_send = '{"start_date":"2013-08-28 00:00:00",
	// "end_date":"2013-09-29 00:00:00"}';
	// alert(data_send);
	//
	// xmlhttp.open("POST", URL, true); //async style
	// //xmlhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
	// //to store the user data via ajax by imitating the form, the second
	// parameter is a must.
	// xmlhttp.setRequestHeader('Content-Type',
	// 'application/x-www-form-urlencoded;charset=UTF-8');
	//
	// xmlhttp.onreadystatechange = function() {
	// if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	// alert(xmlhttp.responseText);
	// }
	// };
	// xmlhttp.send(data_send);

	// xmlhttp.SetRequestHeader ("Content-Type","text/xml; charset=utf-8");
	// xmlhttp.SetRequestHeader ("SOAPAction","http://tempuri.org/SayHelloTo");
	// xmlhttp.send(null);

	// var result = xmlhttp.status;
	// // OK
	// if(result==200) {
	// document.write(xmlhttp.responseText);
	// }
	// xmlhttp = null;

	// alert(temp_url.replace(/&/g, " "));

	// xmlhttp.onreadystatechange = function() {
	// if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	// alert("hello world!");
	// alert(xmlhttp.responseText);
	// }
	// };

	// xmlhttp.open("GET",URL, true);
	// xmlhttp.SetRequestHeader ("Content-Type","text/xml; charset=utf-8");
	// xmlhttp.SetRequestHeader ("SOAPAction","http://tempuri.org/SayHelloTo");
	// xmlhttp.send(null);
	// var result = xmlhttp.status;
	// OK
	// if(result==200) {
	// document.write(xmlhttp.responseText);
	// }
	// xmlhttp = null;

	// var b = Integer.toString(a);
	// alert(b);

	// alert(hashtable.containsValue('testsdfzz'));

	// var array_temp = hashtable.values();
	// alert("sdfd");

	// alert(hashtable.toString());

	// var array_temp = hashtable.keys();
	// alert("sdfd");

	// alert(hashtable.isEmpty());
	// hashtable.add("test", "test");
	// hashtable.add("test1", "test1");
	// alert(hashtable.isEmpty());
	// var tempvalue = hashtable.get("test")
	// alert(tempvalue);
	// hashtable.remove("test");
	// alert(hashtable.get("test"));

}


$(document).ready(function() {
					$("input[name='destination']").click(
						function(){
							var dest = $("input[name='destination']:checked").val();
							if (dest == 'test')
								baseURL = testURL;
							else if (dest == 'formal')
								baseURL = releaseURL;
							else if (dest == 'local')
								baseURL = localURL;
							else
								alert('wrong detination value!');
						});
					});