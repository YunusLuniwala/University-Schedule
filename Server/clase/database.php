<?php

class Database {

    public $server = "localhost";
    public $database = "Universitate";
    private $user = "Universitate";
    private $password = "semafor";
    private $con;

    public function connect() {
        $this->con = mysql_connect($this->server, $this->user, $this->password);
        mysql_connect($this->server, $this->user, $this->password) or die('Could not connect: ' . mysql_error());
        mysql_select_db($this->database, $this->con);

		$result = mysql_query('SET NAMES utf8');
		$result = mysql_query('SET CHARACTER SET utf8');

        //echo "<br>Connected!<br>";
    }

    public function disconnect() {
        mysql_close($this->con);

        //echo "<br>Disconnected!<br>";
    }

}

?>
