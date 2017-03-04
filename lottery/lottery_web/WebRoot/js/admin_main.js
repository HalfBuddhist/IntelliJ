function bindImageFileChangeFunc() {
	$('#image').change(function() {
		var file = this.files[0];
		var name = file.name;
		var size = file.size;
		var type = file.type;

		//hide the progress bar
			$("progress[name='upload_bar']").css("display", "none");

			if (size > 5 * 1024 * 1024) {
				alert("文件大小不可以超过5M。");
				setTimeout(clearFileDomain('image'), 10);
			} else if (!name.match(/\.png$/) && !name.match(/\.jpg$/)
					&& !name.match(/\.jpeg$/) && !name.match(/\.bmp$/)
					&& !name.match(/\.PNG$/) && !name.match(/\.JPG$/)
					&& !name.match(/\.JPEG$/) && !name.match(/\.BMP$/)) {
				alert("请选择图片类型的文件: png, jpg, jpeg, bmp.");
				setTimeout(clearFileDomain('image'), 10);
			}

			// your validation
			//alert(name);
			//alert(size);
			console.log(type);
		});
}

function clearFileDomain(domain_id) {
	var file1 = $("#" + domain_id)
	file1.after(file1.clone().val(""));
	file1.remove();

	bindImageFileChangeFunc();

	//	var afile = $("#"+domain_id);
	//	afile.replaceWith(afile.clone());
}

function progressHandlingFunction(e) {
	if (e.lengthComputable) {
		$('progress').attr( 
			{
				value : e.loaded,
				max : e.total
			}
		);
	}
}

var send_pub_url = 'notification/send_pub_notification.json?auth_token=';
var send_pri_url = 'notification/send_pri_notification.json?auth_token=';
var send_url = send_pub_url;

$(document).ready(function() {	
					$("input[name='targetCategory']").click(
						function(){
							$('#target_id').attr("name", $("input[name='targetCategory']:checked").val());
						
							if ($('input[name="targetCategory"]:checked').val() == 'user'){
								send_url = send_pri_url;
							}							
							else if ($('input[name="targetCategory"]:checked').val() == 'team'){
								send_url = send_pub_url;
							}
								
						});
	
					$('#uploadbutton').click(
						function() {
							//check null
							if ($('#image').val() == null || $('#image').val() == ""){
								alert("上传的图片不能为空!");
								return;
							}
							
							
							$("progress[name='upload_bar']").css(
									"display", "block");
							
							var auth_token = $.getUrlParam('auth_token');
							console.log(auth_token);

							var formData = new FormData(
									$('#_form_image')[0]);
							//formData.append("image", $('#image'));					    
							//						formData.append("contentType", "multipart/form-data");
							$.ajax( 
								{
									url : 'notification/upload_image.json?auth_token=' + auth_token,
									type : 'POST',
									xhr : function() { // custom xhr
										myXhr = $.ajaxSettings
												.xhr();
										if (myXhr.upload) { // check if upload property exists
											myXhr.upload
													.addEventListener(
															'progress',
															progressHandlingFunction,
															false);
										}
										return myXhr;
									},
									// Ajax事件
									success : function(data) {
										if (data.code == 200) {
											$("#_form_message input[name='image']").attr("value",data.image_name);
										} else if (data.code == 201) {
											window.location.href = "admin_login.jsp?a=201";
										} else if (data.code == 215) {
											alert('上传文件过多!');
										} else if (data.code == 216) {
											alert('上传文件不能为空!');
										} else if (data.code == 555) {
											alert('服务器内部错误!');
										} else {
											alert('未知错误！');
										}
									},
									error : function() {
										alert("Error happens! ");
									},
									// Form数据
									data : formData,
									// Options to tell JQuery not to process data or
									// worry about content-type
									cache : false,
									contentType : false,
									processData : false
								});
							});

					bindImageFileChangeFunc();

					$('#_btn_submit').click(
						function() {
							//legality checking
							if ($('#target_id').val() == null || $('#target_id').val() == ""){
								alert("组别不能为空!");
								return;
							}
							
							if ($('#title').val() == null || $('#title').val() == ""){
								alert("标题不能为空!");
								return;
							}
							
							if ($('#content').val() == null || $('#content').val() == ""){
								alert("内容不能为空!");
								return;
							}
							
							
							//get the auth_token from the url
							var auth_token = $.getUrlParam('auth_token');
							console.log(auth_token);
							//transfer form to json
							//transfer json to string 
							//submit the form
							$.ajax( 
								{
									url : send_url + auth_token,
									type : 'POST',
									// Options to tell JQuery not to process data or worry about content-type
									cache : false,
									contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
									processData : false,
									// Form数据, must be a string, tested.
									data : JSON.stringify($("#_form_message").serialize2Json()),
									success : function(data) {
										if (data.code == 200) {
											alert("成功发送通知！");
										} else if (data.code == 201) {
											window.location.href = "admin_login.jsp?a=201";
										} else if (data.code == 203) {
											alert('密码错误!');
										} else if (data.code == 555) {
											alert('服务器内部错误!');
										} else {
											alert('未知代码：' + data.code);
										}
									},
									error : function() {
										alert("Error happens while submitting! ");
									}									
								});								
					});
				});
