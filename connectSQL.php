<?php
$serverName = "192.168.20.2"; //serverName\instanceName
$connectionInfo = array( "Database"=>"VVITS", "UID"=>"appro", "PWD"=>"appro.and", "CharacterSet" => "UTF-8");
$conn = sqlsrv_connect( $serverName, $connectionInfo);

echo "Database connect Test 1.<br />";

if( $conn ) {
 echo "Connection established.<br />";
}else{
 echo "Connection could not be established.<br />";
 die( print_r( sqlsrv_errors(), true));
}

?>