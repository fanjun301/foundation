function test(str){
	if( typeof v === 'undefined' ){
		print("call test method ");
	}else{
		print("call test method , and have variable : "+v+" ");
	}
	
//	var sb = new java.lang.StringBuffer();
//	sb.append("================");
//	print(sb.toString());
	
	return "hello:"+str;
}

function maptest(tmpMap){
	tmpMap.put("key","value");
	return tmpMap;
}
