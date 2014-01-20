package orare.universitate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

	private static final String DATABASE_NAME 				= "Orare.db";
	private static final int DATABASE_VERSION 				= 1;

	// tabelul cu facultățile
	public static final String T_FACULTATI 					= "facultati";
	public static final String FACULTATI_COLUMN_ID 			= "_id_facultate";
	public static final String FACULTATI_COLUMN_NUME 			= "nume_facultate";

	// tabelul cu sectile
	public static final String T_SECTII 						= "sectii";
	public static final String SECTII_COLUMN_ID 				= "_id_sectie";
	public static final String SECTII_COLUMN_FACULTATE_ID 	= "id_facultate";
	public static final String SECTII_COLUMN_NUME 			= "nume_sectie";
	public static final String SECTII_COLUMN_FORMA 			= "forma_de_invatamant";
	public static final String SECTII_COLUMN_ANI 				= "numar_ani";

	/* tabelul cu lista de orare care 
	 * sunt disponibile local 
	 * conținând numele tabelului 
	 * și aliasul numelui pentru afișareîn listă
	 */
	public static final String T_ORARE_DISPONIOBILE_LOCAL 	= "orare_disponibile_local";
	public static final String ORARE_COLUMN_ID 				= "_id_orar";
	public static final String ORARE_COLUMN_NUME_TABEL 		= "nume_tabel";
	public static final String ORARE_COLUMN_AN_SEMESTRU 		= "an_semestru";
	
	// coloanele pentru tabelele cu orarele
	public static final String ORAR_COLUMN_ORA 				= "ora";
	public static final String ORAR_COLUMN_LUNI 				= "luni";
	public static final String ORAR_COLUMN_MARTI 				= "marti";
	public static final String ORAR_COLUMN_MIERCURI 			= "miercuri";
	public static final String ORAR_COLUMN_JOI 				= "joi";
	public static final String ORAR_COLUMN_VINERI 			= "vineri";
	public static final String ORAR_COLUMN_SAMBATA 			= "sambata";
	public static final String ORAR_COLUMN_DUMINICA 			= "duminica";
	

	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE IF NOT EXISTS " + T_FACULTATI + " ("
				+ FACULTATI_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ FACULTATI_COLUMN_NUME + " text not null );";
		db.execSQL(query);
		query = "create table if not exists " + T_SECTII + " ( "
				+ SECTII_COLUMN_ID + " integer primary key autoincrement, "
				+ SECTII_COLUMN_FACULTATE_ID + " integer, "
				+ SECTII_COLUMN_NUME + " text not null, " + SECTII_COLUMN_FORMA
				+ " text not null, " + SECTII_COLUMN_ANI + " integer);";
		db.execSQL(query);
		query = "create table if not exists " + T_ORARE_DISPONIOBILE_LOCAL
				+ " (" + ORARE_COLUMN_ID
				+ " integer primary key autoincrement, "
				+ ORARE_COLUMN_NUME_TABEL + " text not null, "
				+ ORARE_COLUMN_AN_SEMESTRU + " text not null);";
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(Database.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS" + T_FACULTATI);
		db.execSQL("DROP TABLE IF EXISTS" + T_SECTII);
		onCreate(db);
	}

	public void createTableOrarIfNotExists(SQLiteDatabase db, Orar orar) {
		String query = "create table if not exists " + orar.getTable_name() + " ( "
				+ ORAR_COLUMN_ORA + " text, " 
				+ ORAR_COLUMN_LUNI + " text, "
				+ ORAR_COLUMN_MARTI + " text, "
				+ ORAR_COLUMN_MIERCURI + " text, "
				+ ORAR_COLUMN_JOI + " text, "
				+ ORAR_COLUMN_VINERI + " text, "
				+ ORAR_COLUMN_SAMBATA + " text, "
				+ ORAR_COLUMN_DUMINICA + " text); ";
		db.execSQL(query);
	}

}
