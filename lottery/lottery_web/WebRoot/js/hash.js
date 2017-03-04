function Hashtable() {
	this._hashValue = new Object();
	this._iCount = 0;
}

Hashtable.prototype._getCellByIndex = function(iIndex, bIsGetValue) {
	vari = 0;
	if (bIsGetValue == null)
		bIsGetValue = true;
	for ( var key in this._hashValue) {
		if (i == iIndex) {
			return bIsGetValue ? this._hashValue[key] : key;
		}
		i++;
	}
	return null;
}

Hashtable.prototype.get = function(key) {
	if (typeof (key) == "string"
			&& this._hashValue[key] != typeof ('undefined')) {
		return this._hashValue[key];
	}
	if (typeof (key) == "number")
		return this._getCellByIndex(key);
	else
		throw "hash value not allow null!";

	return null;
}

Hashtable.prototype.containsKey = function(key) {
	return this.get(key) != null;
}

Hashtable.prototype.put = function(strKey, value) {
	if (typeof (strKey) == "string") {
		if (!(this.containsKey(strKey))){
			this._iCount++;
		}
		this._hashValue[strKey] = typeof (value) != "undefined" ? value : null;
		return true;
	} else
		throw "hash key not allow null!";
}

Hashtable.prototype.findKey = function(iIndex) {
	if (typeof (iIndex) == "number")
		return this._getCellByIndex(iIndex, false);
	else
		throw "find key parameter must be a number!";
}

Hashtable.prototype.size = function() {
	return this._iCount;
}


Hashtable.prototype.remove = function(key) {
	for ( var strKey in this._hashValue) {
		if (key == strKey) {
			delete this._hashValue[key];
			this._iCount--;
		}
	}
}

Hashtable.prototype.clear = function() {
	for ( var key in this._hashValue) {
		delete this._hashValue[key];
	}
	this._iCount = 0;
}

Hashtable.prototype.isEmpty = function(){
	return this._iCount > 0 ? false : true;
}

Hashtable.prototype.keys = function(){
	var keys = new Array();
	for ( var key in this._hashValue) {
		if (this._hashValue[key] != null)
			keys.push(key);
	}	
	return keys;
}

Hashtable.prototype.toString = function(){
	var result = '';
	for ( var key in this._hashValue) {
		if (this._hashValue[key] != null)
			result += '{' + key + '},{' + this._hashValue[key] + '}\n';
	}
	return result;
}


Hashtable.prototype.values = function(){
	var values = new Array();
	for (var key in this._hashValue) {
		if (this._hashValue[key] != null)
			values.push(this._hashValue[key]);
	}	
	return values;
}


Hashtable.prototype.containsValue = function(value) {
	var contains = false;
	if (value != null) {
		for ( var key in this._hashValue) {
			if (this._hashValue[key] == value) {
				contains = true;
				break;
			}
		}
	}
	return contains;
}