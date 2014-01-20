<?php

require 'database.php';

class Connection {

private $db;

	function __construct() {
		$this->db = new Database();
	}

	public function getToateFacultatile(){

		$this->db->connect();

		$query = "SELECT * FROM facultati";
		$result = mysql_query($query) or die(mysql_error());

		$this->db->disconnect();

		return $result;
	}

	public function getToateSectile(){
		$this->db->connect();

		$query = "SELECT * FROM sectii";
		$result = mysql_query($query) or die(mysql_error());

		$this->db->disconnect();

		return $result;
	}

	public function getSectii($id){

		$this->db->connect();

		$query = "SELECT * FROM sectii WHERE Facultate=".$id;
		$result = mysql_query($query) or die(mysql_error());

		$this->db->disconnect();

		return $result;

	}

	public function getFacultate($id){

		$this->db->connect();

		$query = "SELECT * FROM facultati WHERE ID=".$id;
		$result = mysql_query($query) or die(mysql_error());

		$this->db->disconnect();

		return $result;

	}

	public function getSectie($id_sectie){

		$this->db->connect();

		$query = "SELECT * FROM sectii WHERE ID=".$id_sectie;
		$result = mysql_query($query) or die(mysql_error());

		$sectie = mysql_fetch_array($result);

		$this->db->disconnect();

		return $sectie;

	}

	public function getOrare($id_sectie){

		$this->db->connect();

		$query = "SELECT `Numar_ani` FROM sectii WHERE ID=".$id_sectie;
		$result = mysql_query($query) or die(mysql_error());
		$nrSemestre = mysql_fetch_array($result);
		$nrSemestre = $nrSemestre['Numar_ani'] * 2;

		$orare['numar_semestre'] = $nrSemestre;

		for ($i = 1; $i <= $nrSemestre; $i++){

			$tableName = "orar_sectie". $id_sectie ."_sem". $i;

			$val = mysql_query("SELECT 1 FROM ". $tableName);

			if($val !== FALSE){

			$query = "SELECT * FROM " . $tableName;
			$result = mysql_query($query) or die(mysql_error());
			if($result)
				$orare[$i] = $result;
			}
			else{
				$orare['nu exista'] = "";
				$orare[$i] = "";
			}
		}

		$this->db->disconnect();

		return $orare;


	}

	public function create_if_not_exists($id_sectie, $id_semestru) {
		$this->db->connect();
		$query = "CREATE TABLE IF NOT EXISTS `orar_sectie".$id_sectie."_sem".$id_semestru."` (
				`ora` varchar(7) NOT NULL DEFAULT ' - ',
				`luni` varchar(100) NOT NULL DEFAULT ' ',
				`marti` varchar(100) NOT NULL DEFAULT ' ',
				`miercuri` varchar(100) NOT NULL DEFAULT ' ',
				`joi` varchar(100) NOT NULL DEFAULT ' ',
				`vineri` varchar(100) NOT NULL DEFAULT ' ',
				`sambata` varchar(100) NOT NULL DEFAULT ' ',
				`duminica` varchar(100) NOT NULL DEFAULT ' '
			) ENGINE=MyISAM DEFAULT CHARSET=utf8;";

		$result = mysql_query($query) or die(mysql_error());

		$query = "SELECT * FROM `orar_sectie".$id_sectie."_sem".$id_semestru."`";
		$result = mysql_query($query) or die(mysql_error());

		if(!mysql_num_rows($result)){
			$query = "INSERT INTO `orar_sectie".$id_sectie
			."_sem".$id_semestru
			."` (`ora`, `luni`, `marti`, `miercuri`, `joi`,
			 `vineri`, `sambata`, `duminica`) 
			 VALUES	('7 - 8', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('8 - 9', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('9 - 10', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('10 - 11', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('11 - 12', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('12 - 13', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('13 - 14', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('14 - 15', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('15 - 16', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('16 - 17', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('17 - 18', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('18 - 19', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('19 - 20', ' ', ' ', ' ', ' ', ' ', ' ', ' ') ";
			$result = mysql_query($query) or die(mysql_error());
		}

		$this->db->disconnect();
	}
	
	public function create_if_not_exists2($table_name) {
		$this->db->connect();
		$query = "CREATE TABLE IF NOT EXISTS `$table_name` (
				`ora` varchar(7) NOT NULL DEFAULT ' - ',
				`luni` varchar(100) NOT NULL DEFAULT ' ',
				`marti` varchar(100) NOT NULL DEFAULT ' ',
				`miercuri` varchar(100) NOT NULL DEFAULT ' ',
				`joi` varchar(100) NOT NULL DEFAULT ' ',
				`vineri` varchar(100) NOT NULL DEFAULT ' ',
				`sambata` varchar(100) NOT NULL DEFAULT ' ',
				`duminica` varchar(100) NOT NULL DEFAULT ' '
			) ENGINE=MyISAM DEFAULT CHARSET=utf8;";

		$result = mysql_query($query) or die(mysql_error());

		$query = "SELECT * FROM `$table_name`";
		$result = mysql_query($query) or die(mysql_error());

		if(!mysql_num_rows($result)){
			$query = "INSERT INTO `$table_name` (`ora`, `luni`, `marti`, `miercuri`, `joi`,
			 `vineri`, `sambata`, `duminica`) 
			 VALUES	('7 - 8', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('8 - 9', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('9 - 10', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('10 - 11', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('11 - 12', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('12 - 13', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('13 - 14', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('14 - 15', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('15 - 16', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('16 - 17', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('17 - 18', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('18 - 19', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
				('19 - 20', ' ', ' ', ' ', ' ', ' ', ' ', ' ') ";
			$result = mysql_query($query) or die(mysql_error());
		}

		$this->db->disconnect();
	}

	public function getOrar($id_sectie, $id_semestru) {
		$this->db->connect();
		$query = "SELECT * FROM `orar_sectie".$id_sectie."_sem".$id_semestru."`";
		$result = mysql_query($query) or die(mysql_error());
		$this->db->disconnect();
		return $result;
	}
	
	public function getOrarTable($table_name) {
		$this->db->connect();
		$query = "SELECT * FROM `".$table_name."`";
		$result = mysql_query($query) or die(mysql_error());
		$this->db->disconnect();
		return $result;
	}

// ================================================================
// ================================================================
// ================================================================
// ================================================================

	public function getOrarLuni($id_sectie, $id_semestru) {
		$this->db->connect();
		$query = "SELECT `luni` FROM `orar_sectie".$id_sectie."_sem".$id_semestru."`";
		$result = mysql_query($query) or die(mysql_error());
		$this->db->disconnect();
		return $result;
	}

	public function getOrarMarti($id_sectie, $id_semestru) {
		$this->db->connect();
		$query = "SELECT `marti` FROM `orar_sectie".$id_sectie."_sem".$id_semestru."`";
		$result = mysql_query($query) or die(mysql_error());
		$this->db->disconnect();
		return $result;
	}

	public function getOrarMiercuri($id_sectie, $id_semestru) {
		$this->db->connect();
		$query = "SELECT `miercuri` FROM `orar_sectie".$id_sectie."_sem".$id_semestru."`";
		$result = mysql_query($query) or die(mysql_error());
		$this->db->disconnect();
		return $result;
	}

	public function getOrarJoi($id_sectie, $id_semestru) {
		$this->db->connect();
		$query = "SELECT `joi` FROM `orar_sectie".$id_sectie."_sem".$id_semestru."`";
		$result = mysql_query($query) or die(mysql_error());
		$this->db->disconnect();
		return $result;
	}

	public function getOrarVineri($id_sectie, $id_semestru) {
		$this->db->connect();
		$query = "SELECT `vineri` FROM `orar_sectie".$id_sectie."_sem".$id_semestru."`";
		$result = mysql_query($query) or die(mysql_error());
		$this->db->disconnect();
		return $result;
	}

	public function getOrarSambata($id_sectie, $id_semestru) {
		$this->db->connect();
		$query = "SELECT `sambata` FROM `orar_sectie".$id_sectie."_sem".$id_semestru."`";
		$result = mysql_query($query) or die(mysql_error());
		$this->db->disconnect();
		return $result;
	}

	public function getOrarDuminica($id_sectie, $id_semestru) {
		$this->db->connect();
		$query = "SELECT `duminica` FROM `orar_sectie".$id_sectie."_sem".$id_semestru."`";
		$result = mysql_query($query) or die(mysql_error());
		$this->db->disconnect();
		return $result;
	}

	public function insertIntoDatabase($ora, $zile, $tableName) {
		$this->db->connect();
		$query= "UPDATE Universitate.".$tableName." SET luni='".$zile[0]."', marti='".$zile[1]."', miercuri='".$zile[2]."', joi='".$zile[3]."', vineri='".$zile[4]."', sambata='".$zile[5]."', duminica='".$zile[6]."' WHERE ora LIKE '".$ora."'";
		$result=mysql_query($query) or die(mysql_error());
		$this->db->disconnect();

	}
}

?>
