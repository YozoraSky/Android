<?php
	
	include"connectSQL.php";
	$orderno = $_POST['ORDERNO'];//android將會傳值到orderno(單號)
	$ordersn = $_POST['ORDERSN'];
	
	$num = sqlsrv_query("SELECT count(ORDERNO) FROM product where ORDERNO = '$orderno'"); //計算同單號的資料共有幾筆!
	$row = sqlsrv_fetch_array($num);

	if($stmt = sqlsrv_query("SELECT * FROM product where ORDERNO='$orderno' and ORDERSN = '$ordersn'"))//如果ordersn是數字的話，則不須加上單引號，但若包含了字母，則須加上單引號
	{
		while($result = sqlsrv_fetch_object($stmt))
		{
			//空白絕對不可以刪除
			/*echo "單號:".$result->ORDERNO." \n"; 
			echo "序號:".$result->ORDERSN." \n";
			echo "客戶單號:".$result->CASENUMBER." \n";
			echo "客戶簡稱:".$result->CUSTOMER." \n";
			echo "訂單數量:".$result->QTY." \n";
			echo "單位:".$result->UNIT." \n";
			echo "品號:".$result->PRODUCT." \n";
			echo "型號:".$result->PRODUCTTYPE." \n";
			echo "尺寸:".$result->PRODUCTSIZE." \n";
			echo "閥號:".$result->TAGNO." \n";
			echo "試壓等級:".$result->TESTPRESSURE." \n";
			echo "部門名稱:".$result->DEPT." \n";
			echo "作業項目:".$result->WORKITEM." \n";*/
			$arr = array('orderno' => $result->ORDERNO,
						 'ordersn' => $result->ORDERSN,
						 'caseNumber' => $result->CASENUMBER, 
						 'customer' => $result->CUSTOMER,
						 'qty' => $result->QTY,
						 'unit' => $result->UNIT,
						 'product' => $result->PRODUCT,
						 'productType' => $result->PRODUCTTYPE,
						 'productSize' => $result->PRODUCTSIZE,
						 'tagno' => $result->TAGNO,
						 'testPressure' => $result->TESTPRESSURE,
						 'dept' => $result->DEPT,
						 'workItem' => $result->WORKITEM,
						 'ordersnNumber' => $row[0]);
			$json = json_encode($arr);
			echo decodeUnicode($json);
		}				
	}
	else
		echo "";
	
	//編碼成中文
	function decodeUnicode($str){
		return preg_replace_callback('/\\\\u([0-9a-f]{4})/i',create_function('$matches','return mb_convert_encoding(pack("H*", $matches[1]), "UTF-8", "UCS-2BE");'),$str);
	}
?>