#!/usr/bin/php5 -q
<?php

error_reporting(E_ALL);

/* Allow the script to hang around waiting for connections. */
set_time_limit(0);

/* Turn on implicit output flushing so we see what we're getting
 * as it comes in. */
ob_implicit_flush();

// ====== functions ==========
require '../clase/connection.php';
$con = new Connection();

function get_table_facultate($con) {

	$sql_result = $con->getToateFacultatile();
	$array_facultati = array();

	while ($row = mysql_fetch_assoc($sql_result)){
		$array_facultati[]= $row;
	}
// 	print json_encode($json_string_facultati);
	return $array_facultati;

}

function get_table_sectii($con) {

	$sql_result = $con->getToateSectile();
	$array_sectii= array();

	while ($row = mysql_fetch_assoc($sql_result)){
		$array_sectii[]= $row;
	}
// 	print json_encode($json_string_sectii);
	return $array_sectii;

}

function get_table_orar( $con, $table_name ) {
	$con->create_if_not_exists2($table_name);
	$sql_result = $con->getOrarTable($table_name);
	$array_orar = array();
	while ($row = mysql_fetch_assoc($sql_result)){
		$array_orar[] = $row;
	}
	return $array_orar;
}



// ====== connection =========
// $host = "127.0.0.1";
$host = "192.168.2.65";
$port = 2400;

$socket = socket_create(AF_INET, SOCK_STREAM, 0) or die("Could not create socket\n");
$result = socket_bind($socket, $host, $port) or die("Could not bind to socket\n");
$result = socket_listen($socket, SOMAXCONN) or die("Could not set up socket listener\n");

echo "\nRunningon host: $host on port $port\n";

while (true) {

	$clientConnection = socket_accept($socket) or die("Could not accept incoming connection\n");
	echo "Client Connected\n";
	$input = socket_read($clientConnection, 10000, PHP_NORMAL_READ);
	echo "message received:\n";
	$object_from_json = json_decode($input, true);

	switch($object_from_json['status']){
		case 1:
			$array_facultati_sectii = array('facultati' => get_table_facultate($con), 'sectii' => get_table_sectii($con));
			$json_object_for_sending = json_encode($array_facultati_sectii, JSON_FORCE_OBJECT);
			echo "\nSending message\n";
// 			echo $json_object_for_sending."\n";
//			print_r($array_facultati_sectii);
			echo "Sending main table for update.\n";
			socket_write($clientConnection,$json_object_for_sending."\n", strlen($json_object_for_sending) + 1) or die("Could not write output\n");
			echo "\nMessage sent\n";
		break;
		case 2:
//			echo $input."\n";
			$table_name = $object_from_json['orar'];
			$array_orar = get_table_orar($con, $table_name);
//			print_r($array_orar);
			$json_send_orar = json_encode($array_orar, JSON_FORCE_OBJECT);
//			echo $json_send_orar."\n";
			echo "\nSending message\n";
			echo "Sending orar ".$table_name."\n";
			socket_write($clientConnection, $json_send_orar."\n", strlen($json_send_orar) + 1) or die ("Could not write output\n");
			echo "\nMessage sent\n";
		break;
}

	socket_close($clientConnection);
	echo "\nConnection closed.\n";
	echo "==========================================\n\n";

}

socket_close($socket);
?>
