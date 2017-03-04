var threshold_sta = 1.8;

function printToPage(content){
	$("body").append("<br/>" + content);
}

function comparePeiRet(index, cur_return, cur_win, cur_pk, cur_lose, tar_return, tar_win, tar_pk, tar_lose)
{
	var outputStringTemp = "";
	if ((tar_return - cur_return) > threshold_sta){
		console.debug("Find the low index. " + arrCOMPANY[index]); 
//		printToPage("Find the low index. " + arrCOMPANY[index]); 
		outputStringTemp += "-";
		//1
		var distance = cur_win - tar_win;
		
		if (distance > 0)
		{
			outputStringTemp += "23|";
		}
		else if (distance >= -0.02)
		{
			outputStringTemp += "n-23|";
		}
		else
		{
			outputStringTemp += "n|";
		}
		
		//2
		distance = cur_pk - tar_pk;
		
		if (distance > 0)
		{
			outputStringTemp += "13|";
		}
		else if (distance >= -0.02)
		{
			outputStringTemp += "n-13|";
		}
		else
		{
			outputStringTemp += "n|";
		}
		
		//3
		distance = cur_lose - tar_lose;
		
		if (distance > 0)
		{
			outputStringTemp += "12";
		}
		else if (distance >= -0.02)
		{
			outputStringTemp += "n-12";
		}
		else
		{
			outputStringTemp += "n";
		} 
	}else if ((cur_return - tar_return) > threshold_sta){
		console.debug("Find the high index. " + arrCOMPANY[index]); 
//		printToPage("Find the high index. " + arrCOMPANY[index]);
			//the bet and betfair
			outputStringTemp += "+";
			//1
			var distance = cur_win - tar_win;
			
			if (distance < 0)
			{
				outputStringTemp += "23|";
			}
			else if (distance <= 0.02)
			{
				outputStringTemp += "n-23|";
			}
			else
			{
				outputStringTemp += "n|";
			}
			
			//2
			distance = cur_pk - tar_pk; 
			
			if (distance < 0)
			{
				outputStringTemp += "13|";
			}
			else if (distance <= 0.02)
			{
				outputStringTemp += "n-13|";
			}
			else
			{
				outputStringTemp += "n|";
			}
			
			//3
			distance = cur_lose - tar_lose;
			
			if (distance < 0)
			{
				outputStringTemp += "12";
			}
			else if (distance <= 0.02)
			{
				outputStringTemp += "n-12";
			}
			else
			{
				outputStringTemp += "n";
			}
		}
		
	return outputStringTemp;
}

function table_output(sta_array, n_sta_array){
	var minIndex = -1;
	var min = 1000;
	var ifSame = false;
	for (var index in sta_array)
	{
		if (index == 0)
			continue;
		else{
			if (sta_array[index] < min){
				minIndex = index;
				min = sta_array[index];
			} 
			else if (sta_array[index] == min)
				ifSame = true;
		}
	}
	
	var outputStr = "";
	for (var index in sta_array)
	{
		if (index == 0)
			continue;
        else{
            if (index == minIndex){
                outputStr = outputStr + index + "(" + sta_array[index] + "+" + n_sta_array[index] + ")" + " 小| ";
            }else{
                outputStr = outputStr + index + "(" + sta_array[index] + "+" + n_sta_array[index] + ")" + " | ";
            }
        }
//		else if (index == minIndex && !ifSame)
//			continue;
//		else{
//			outputStr = outputStr + index + "(" + sta_array[index] + "+" + n_sta_array[index] + ")" + " | ";
//		}
	}

    if (ifSame)
        outputStr += "有平";
	
	return outputStr;
}

function addThemUp(hashTable, keys, obvious_positive_arr, implicit_positive_arr, obvious_negtive_arr, implicit_negtive_arr){
	for (var _keyIndex in keys){
		var key = keys[_keyIndex];
		if (key.charAt(0) == "+"){
			var wpl_arr = key.slice(1, key.length).split("|");
			for (var index in wpl_arr){
				if (wpl_arr[index].indexOf("n") == -1){
					//no n
					if (wpl_arr[index].indexOf("1") != -1)
						obvious_positive_arr[1] += hashTable.get(key);
					if (wpl_arr[index].indexOf("2") != -1)
						obvious_positive_arr[2] += hashTable.get(key);
					if (wpl_arr[index].indexOf("3") != -1)
						obvious_positive_arr[3] += hashTable.get(key);
				}else{
					//with n
					if (wpl_arr[index].indexOf("1") != -1)
						implicit_positive_arr[1] += hashTable.get(key);
					if (wpl_arr[index].indexOf("2") != -1)
						implicit_positive_arr[2] += hashTable.get(key);
					if (wpl_arr[index].indexOf("3") != -1)
						implicit_positive_arr[3] += hashTable.get(key);
				}
			}
		}else if (key.charAt(0) == "-"){
			var wpl_arr = key.slice(1, key.length).split("|");
			for (var index in wpl_arr){
				if (wpl_arr[index].indexOf("n") == -1){
					//no n
					if (wpl_arr[index].indexOf("1") != -1)
						obvious_negtive_arr[1] += hashTable.get(key);
					if (wpl_arr[index].indexOf("2") != -1)
						obvious_negtive_arr[2] += hashTable.get(key);
					if (wpl_arr[index].indexOf("3") != -1)
						obvious_negtive_arr[3] += hashTable.get(key);
				}else{
					//with n
					if (wpl_arr[index].indexOf("1") != -1)
						implicit_negtive_arr[1] += hashTable.get(key);
					if (wpl_arr[index].indexOf("2") != -1)
						implicit_negtive_arr[2] += hashTable.get(key);
					if (wpl_arr[index].indexOf("3") != -1)
						implicit_negtive_arr[3] += hashTable.get(key);
				}
			}
		}else{
			consle.debug("Wrong branch.");
		}
	}
}



$(document).ready(function() {
					$('#load_html').click(
						function() {
							printToPage("helo");
							console.debug("come to load."); 
							printToPage("come to load.");
							
							//get the id 
							var url_complete = $('#address').val();
							console.debug(url_complete); 
							printToPage(url_complete);
							var temp = url_complete.split("?id=");
							
							//load
							var index_base_url = 'http://1x2.7m.cn/data/detail/';
							var title_base_url = 'http://1x2.7m.cn/data/gb/';
							try {
								var x = document.createElement('SCRIPT');
								x.type = 'text/javascript';
								x.src = index_base_url + temp[1]+".js";
								x.charset = 'utf-8';
								document.getElementsByTagName('head')[0].appendChild(x);
								
								x = document.createElement('SCRIPT');
								x.type = 'text/javascript';
								x.src = title_base_url + temp[1]+".js";
								x.charset = 'utf-8';
								document.getElementsByTagName('head')[0].appendChild(x);
							} catch (e) {
								alert(e);
							} 
							
							console.debug("load complete!"); 
							printToPage("load complete!");
						}); 
					
					
					$('#calculate').click(
							function(){
								console.debug("come to cal."); 
								printToPage("come to cal.");
                                printToPage(mn + "\t" + an + "\t" + bn);
								
								//calculate the average 
								var min_v = new Array();	//初盘最小
								var max_v = new Array();	//初盘最大
								var last_v = new Array();	//data array for the initial pei

								var l_min_v = new Array();	//终盘最小
								var l_max_v = new Array();	//终盘最大
								var l_last_v = new Array();	//data array for the final pei.

								var sum_v = [ 0, 0, 0, 0, 0, 0, 0 ];	//初盘和
								var l_sum_v = [ 0, 0, 0, 0, 0, 0, 0 ];	//终盘和
								var com_count = 0;
								
								
								for ( var i = 0; i < dt.length; ++i) {
									
									var dt2 = dt[i].split('|');
									
									++com_count; 
									 
									if (dt2[2] != "") {
										for ( var j = 0; j < 7; ++j) {
											
											if (typeof (last_v[i]) == "undefined")
												last_v[i] = new Array();
											
											if (typeof (last_v[i][j]) == "undefined")
												last_v[i][j] = Number(dt2[j + 1]) * 100;
											
											if (typeof (min_v[j]) == "undefined")
												min_v[j] = Number(dt2[j + 1]) * 100;
											
											if (typeof (max_v[j]) == "undefined")
												max_v[j] = Number(dt2[j + 1]) * 100;

											//update the min and max value for each volume.
											if (last_v[i][j] < min_v[j])
												min_v[j] = last_v[i][j];
											if (last_v[i][j] > max_v[j])
												max_v[j] = last_v[i][j];

											last_v[i][j] = Number(dt2[j + 1]) * 100;

											sum_v[j] += Number(dt2[j + 1]) * 100;
											
											
											if (dt2[8] == "")
												l_sum_v[j] += Number(dt2[j + 1]) * 100;
											else
												l_sum_v[j] += Number(dt2[j + 8]) * 100;
										}

									}
									
									if (dt2[8] != "") 
									{
										//exist the final pei.
										for ( var j = 0; j < 7; ++j) {
											//create the row for this final pei array 
											if (typeof (l_last_v[i]) == "undefined")
												l_last_v[i] = new Array();
											
											//store it into
											if (typeof (l_last_v[i][j]) == "undefined")
												l_last_v[i][j] = Number(dt2[j + 8]) * 100;
											
											//get the min and max for the final pei.
											if (typeof (l_min_v[j]) == "undefined")
												l_min_v[j] = Number(dt2[j + 8]) * 100;
											if (typeof (l_max_v[j]) == "undefined")
												l_max_v[j] = Number(dt2[j + 8]) * 100;

											if (l_last_v[i][j] < l_min_v[j])
												l_min_v[j] = l_last_v[i][j];
											if (l_last_v[i][j] > l_max_v[j])
												l_max_v[j] = l_last_v[i][j];

											l_last_v[i][j] = Number(dt2[j + 8]) * 100;
										}
									}
								}
								
								var tempStr = "";
								for(var i = 0; i < 7; i++)
								{
									tempStr = tempStr + format(min_v[i] / 100, 2) + " ";
								}
									
								console.debug("初盘最小：" + tempStr); 
								printToPage("初盘最小：" + tempStr);
								
								tempStr = "";
								for(var i = 0; i < 7; i++)
								{
									tempStr = tempStr + format(max_v[i] / 100, 2) + " ";
								}
									
								console.debug("初盘最大：" + tempStr); 
								printToPage("初盘最大：" + tempStr);
								
								tempStr = "";
								for(var i = 0; i < 7; i++)
								{
									tempStr = tempStr + format(sum_v[i] / 100 / com_count, 2) + " ";
								}
									
								console.debug("初盘平均：" + tempStr); 
								printToPage("初盘平均：" + tempStr);
								
								tempStr = "";
								for(var i = 0; i < 7; i++)
								{
									tempStr = tempStr + format(l_min_v[i] / 100, 2) + " ";
								}
									
								console.debug("即时最小：" + tempStr); 
								printToPage("即时最小：" + tempStr);
								
								tempStr = "";
								for(var i = 0; i < 7; i++)
								{
									tempStr = tempStr + format(l_max_v[i] / 100, 2) + " ";
								}
									
								console.debug("即时最大：" + tempStr); 
								printToPage("即时最大：" + tempStr);
								
								tempStr = "";
								for(var i = 0; i < 7; i++)
								{
									tempStr = tempStr + format(l_sum_v[i] / 100 / com_count, 2) + " ";
								}
									
								console.debug("即时平均：" + tempStr); 
								printToPage("即时平均：" + tempStr);  
								
								
								//statistic the data.
								var average_return_first = sum_v[6] / 100 / com_count;
								var average_win_pei_first = sum_v[0] / 100 /com_count;
								var average_pk_pei_first = sum_v[1] / 100 /com_count;
								var average_lose_pei_first = sum_v[2] / 100 /com_count;

								
								var average_return_final = l_sum_v[6] / 100 / com_count;
								var average_win_pei_final = l_sum_v[0] / 100 /com_count;
								var average_pk_pei_final = l_sum_v[1] / 100 /com_count;
								var average_lose_pei_final = l_sum_v[2] / 100 /com_count;
								
								var sta_result = new Hashtable();
								var l_sta_result = new Hashtable();
								
								var outputStringTemp = "";
								for (var i = 0; i < dt.length; i++)
								{
									var dt2 = dt[i].split('|');

									if (dt2[1] != "") 
									{
										//statistic the first
										outputStringTemp = comparePeiRet(dt2[0], dt2[7], dt2[1], dt2[2], dt2[3], average_return_first,
												average_win_pei_first, average_pk_pei_first, average_lose_pei_first);										
										
										//store in the result in the table
										if (outputStringTemp != "")
										{
											if (sta_result.containsKey(outputStringTemp)){
												var a = sta_result.get(outputStringTemp);
												a += 1;
												sta_result.put(outputStringTemp, a);
											} else{
												sta_result.put(outputStringTemp, 1);
											} 
										}
										
										//statistic the final
										if (dt2[8] != ""){
											outputStringTemp = comparePeiRet(dt2[0], dt2[14], dt2[8], dt2[9], dt2[10], average_return_final,
													average_win_pei_final, average_pk_pei_final, average_lose_pei_final);
										}else{
											outputStringTemp = comparePeiRet(dt2[0], dt2[7], dt2[1], dt2[2], dt2[3], average_return_final,
													average_win_pei_final, average_pk_pei_final, average_lose_pei_final);
										}
										  
											
										//store in the result in the table
										if (outputStringTemp != "")
										{
											if (l_sta_result.containsKey(outputStringTemp)){
												var a = l_sta_result.get(outputStringTemp);
												a += 1;
												l_sta_result.put(outputStringTemp, a);
											} else{
												l_sta_result.put(outputStringTemp, 1);
											} 
										} 
										 
									}
								}
								
								console.debug(com_count + "  total."); 
								printToPage(com_count + "  total.");
								//after sta,outputit
								console.debug("初盘统计如下："); 
								printToPage("初盘统计如下：");
								var keys = sta_result.keys().sort();
								var mount = 0;
								for (var _key in keys){
									console.debug("" + keys[_key] + ": " + sta_result.get(keys[_key])); 
									printToPage("" + keys[_key] + ": " + sta_result.get(keys[_key]));
									mount += sta_result.get(keys[_key]);
								}
								console.debug("共: " + mount + ", 占 " + format(mount/com_count*100,2) + "%"); 
								printToPage("共: " + mount + ", 占 " + format(mount/com_count*100,2) + "%");
								
								//statistic into one table
								var begin_positive_array = new Array(0,0,0,0);
								var n_begin_positive_array = new Array(0,0,0,0);

								var begin_negtive_array = new Array(0,0,0,0);
								var n_begin_negtive_array = new Array(0,0,0,0);

								addThemUp(sta_result, keys, begin_positive_array, n_begin_positive_array, begin_negtive_array, n_begin_negtive_array);

								
								
								//after sta,outputit
								console.debug("终盘统计如下："); 
								printToPage("终盘统计如下：");
								mount = 0;
								keys = l_sta_result.keys().sort();
								for (var _key in keys){
									console.debug("" + keys[_key] + ": " + l_sta_result.get(keys[_key])); 
									printToPage("" + keys[_key] + ": " + l_sta_result.get(keys[_key]));
									mount += l_sta_result.get(keys[_key]); 
								}
								console.debug("共: " + mount + ", 占 " + format(mount/com_count*100,2) + "%"); 
								printToPage("共: " + mount + ", 占 " + format(mount/com_count*100,2) + "%");

 								 
								
								var end_positive_array = new Array(0,0,0,0);
								var n_end_positive_array = new Array(0,0,0,0);
								var end_negtive_array = new Array(0,0,0,0);
								var n_end_negtive_array = new Array(0,0,0,0);
								
								addThemUp(l_sta_result, keys, end_positive_array, n_end_positive_array, end_negtive_array, n_end_negtive_array);

								
								//out put the result.
								//begin +
								var outputStr = table_output(begin_positive_array, n_begin_positive_array);								
								printToPage(outputStr);
								
								//begin -
								var outputStr = table_output(begin_negtive_array, n_begin_negtive_array);								
								printToPage(outputStr);
								
								//end +
								var outputStr = table_output(end_positive_array, n_end_positive_array);								
								printToPage(outputStr);
								//end -
								var outputStr = table_output(end_negtive_array, n_end_negtive_array);								
								printToPage(outputStr);
						});
					


				});

 