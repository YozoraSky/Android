<?php
	$localhost = '127.0.0.1';
	$user = 'root';
	$password = '';
	$database = 'test';
	
	//進行連線
	$db = mysqli_connect($localhost,$user,$password,$database);
	if(mysqli_connect_errno())
	{
		die("Database connection failed: " . mysqli_connect_error());
	}
	
	//連線狀態中更換資料庫;
	mysqli_select_db($db,$database);
	
	//設定編碼
	mysqli_set_charset($db,"utf8");
	
	//mysql_close();
?>