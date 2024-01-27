var operator = "=";

//Read the data in the cookie.
function getCookieValue(keyStr){
	var cookie = document.cookie;
	var arr = cookie.split("; ");
	var value = null;
	for (var i = 0; i < arr.length; i++){
		var str = arr[i];
		var key = str.split(operator)[0];
		var value = str.split(operator)[1];
		if (keyStr === key){
			this.value = value;
			break;
		}
	}
	return value;
}

function setCookieValue(key, value){
	document.cookie = key+operator+value;
}