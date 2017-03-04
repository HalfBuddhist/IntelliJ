String.prototype.startsWith = function(str) {
	return this.indexOf(str) == 0;
};

//  if (typeof String.prototype.startsWith != 'function') {
//	  // see below for better implementation!
//	  String.prototype.startsWith = function (str){
//	    return this.indexOf(str) == 0;
//	  };
//	}

//if (typeof String.prototype.startsWith != 'function') {
//	  String.prototype.startsWith = function (str){
//	    return this.slice(0, str.length) == str;
//	  };
//	}


//之所以在将整个代码放在if判断中是因为避免以后原生的js版本中增加了同类方法而导致代码效率降低。
//因为对于同名方法来说，js原生的代码效率要高于用户自己扩展的方法
//之所以使用slice方法而不使用indexof方法，是由于indexof方法在处理较长的字符串时效率比较低

String.prototype.endsWith = function(str) {
	return this.slice(-str.length) == str;
};

//if (typeof String.prototype.endsWith != 'function') {
//	  String.prototype.endsWith = function (str){
//	    return this.slice(-str.length) == str;
//	  };
//	}