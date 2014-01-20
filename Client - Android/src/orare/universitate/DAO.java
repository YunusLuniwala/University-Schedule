package orare.universitate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DAO {

	private SQLiteDatabase sqLiteDb;
	private Database database;
	private String[] allColumnsFacultati = { Database.FACULTATI_COLUMN_ID,
			Database.FACULTATI_COLUMN_NUME };
	private String[] allColumnsSectii = { Database.SECTII_COLUMN_ID,
			Database.SECTII_COLUMN_FACULTATE_ID, Database.SECTII_COLUMN_NUME,
			Database.SECTII_COLUMN_FORMA, Database.SECTII_COLUMN_ANI };
	private String[] allColumnsOrar = { Database.ORAR_COLUMN_ORA,
			Database.ORAR_COLUMN_LUNI, Database.ORAR_COLUMN_MARTI,
			Database.ORAR_COLUMN_MIERCURI, Database.ORAR_COLUMN_JOI,
			Database.ORAR_COLUMN_VINERI, Database.ORAR_COLUMN_SAMBATA,
			Database.ORAR_COLUMN_DUMINICA };

	private Socket socket;
	private BufferedReader br;
	private BufferedWriter bw;

	public DAO(Context context) {
		database = new Database(context);
	}

	public void open() throws SQLException {
		sqLiteDb = database.getWritableDatabase();
	}

	public void close() {
		database.close();
	}

	public void update_facultate(JSONObject tableJSON) {
		ContentValues values = new ContentValues();
		try {
			values.put(Database.FACULTATI_COLUMN_ID,
					tableJSON.getInt(Database.FACULTATI_COLUMN_ID));
			values.put(Database.FACULTATI_COLUMN_NUME,
					tableJSON.getString(Database.FACULTATI_COLUMN_NUME));
			Cursor cursor = sqLiteDb.query(Database.T_FACULTATI,
					new String[] { "*" }, null, null, null, null, null);
			if (cursor.moveToFirst()) {
				boolean sw = false;
				while (!cursor.isAfterLast()) {
					if (cursor.getInt(cursor
							.getColumnIndex(Database.FACULTATI_COLUMN_ID)) == tableJSON
							.getInt(Database.FACULTATI_COLUMN_ID)) {
						sqLiteDb.update(
								Database.T_FACULTATI,
								values,
								Database.FACULTATI_COLUMN_ID + "=?",
								new String[] { ""
										+ tableJSON
												.getInt(Database.FACULTATI_COLUMN_ID) });
						sw = true;
						break;
					}
					cursor.moveToNext();
				}
				if (!sw)
					sqLiteDb.insert(Database.T_FACULTATI, null, values);
			} else {
				sqLiteDb.insert(Database.T_FACULTATI, null, values);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void update_sectie(JSONObject sectiiJSON) {
		ContentValues values = new ContentValues();
		try {
			values.put(Database.SECTII_COLUMN_ID,
					sectiiJSON.getInt(Database.SECTII_COLUMN_ID));
			values.put(Database.SECTII_COLUMN_FACULTATE_ID,
					sectiiJSON.getInt(Database.SECTII_COLUMN_FACULTATE_ID));
			values.put(Database.SECTII_COLUMN_NUME,
					sectiiJSON.getString(Database.SECTII_COLUMN_NUME));
			values.put(Database.SECTII_COLUMN_FORMA,
					sectiiJSON.getString(Database.SECTII_COLUMN_FORMA));
			values.put(Database.SECTII_COLUMN_ANI,
					sectiiJSON.getInt(Database.SECTII_COLUMN_ANI));
			Cursor cursor = sqLiteDb.query(Database.T_SECTII,
					new String[] { "*" }, null, null, null, null, null);
			if (cursor.moveToFirst()) {
				boolean sw = false;
				while (!cursor.isAfterLast()) {
					if (cursor.getInt(cursor
							.getColumnIndex(Database.SECTII_COLUMN_ID)) == sectiiJSON
							.getInt(Database.SECTII_COLUMN_ID)) {
						sqLiteDb.update(
								Database.T_SECTII,
								values,
								Database.SECTII_COLUMN_ID + "=?",
								new String[] { ""
										+ sectiiJSON
												.getInt(Database.SECTII_COLUMN_ID) });
						sw = true;
						break;
					}
					cursor.moveToNext();
				}
				if (!sw)
					sqLiteDb.insert(Database.T_SECTII, null, values);
			} else {
				sqLiteDb.insert(Database.T_SECTII, null, values);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public List<Facultate> getListFacultati() {
		open();
		List<Facultate> facultati = new ArrayList<Facultate>();
		Cursor cursor = sqLiteDb.query(Database.T_FACULTATI,
				allColumnsFacultati, null, null, null, null, null);
		cursor.moveToFirst();
		int colIndexID = cursor.getColumnIndex(Database.FACULTATI_COLUMN_ID);
		int colIndexNume = cursor
				.getColumnIndex(Database.FACULTATI_COLUMN_NUME);
		while (!cursor.isAfterLast()) {
			Facultate facultate = new Facultate();
			facultate.setID(cursor.getInt(colIndexID));
			facultate.setNume(cursor.getString(colIndexNume));
			facultati.add(facultate);
			cursor.moveToNext();
		}
		close();
		return facultati;
	}

	public List<Sectie> getListSectii(int facultate_id) {
		List<Sectie> sectii = new ArrayList<Sectie>();
		open();
		Cursor cursor = sqLiteDb.query(Database.T_SECTII, allColumnsSectii,
				Database.SECTII_COLUMN_FACULTATE_ID + "=?", new String[] { ""
						+ facultate_id }, null, null, null);
		cursor.moveToFirst();
		int colIndexID = cursor.getColumnIndex(Database.SECTII_COLUMN_ID);
		int colIndexFacultate = cursor
				.getColumnIndex(Database.SECTII_COLUMN_FACULTATE_ID);
		int colIndexNume = cursor.getColumnIndex(Database.SECTII_COLUMN_NUME);
		int colIndexAni = cursor.getColumnIndex(Database.SECTII_COLUMN_ANI);
		int colIndexForma = cursor.getColumnIndex(Database.SECTII_COLUMN_FORMA);
		while (!cursor.isAfterLast()) {
			Sectie sectie = new Sectie();
			sectie.setID(cursor.getInt(colIndexID));
			sectie.setFacultate_ID(cursor.getInt(colIndexFacultate));
			sectie.setNume(cursor.getString(colIndexNume));
			sectie.setAni(cursor.getInt(colIndexAni));
			sectie.setForma(cursor.getString(colIndexForma));
			sectii.add(sectie);
			cursor.moveToNext();
		}
		close();
		return sectii;
	}

	public List<Orar> getListSemestre(Sectie sectie, Facultate facultate) {
		/*
		 * returnează o listă generată cu semestrele, conform numărului de ani
		 * pe care îl are secția
		 */
		List<Orar> orare_sectie = new ArrayList<Orar>();
		int nrSemestre = sectie.getAni() * 2;
		for (int i = 1; i <= nrSemestre; i++) {
			Orar orar = new Orar(facultate, sectie, i);
			orare_sectie.add(orar);
		}
		return orare_sectie;
	}

	public boolean connect() {
		try {
			InetAddress server = InetAddress.getByName(MainActivity.serverURL);
			this.socket = new Socket(server, MainActivity.port);
			this.br = new BufferedReader(new InputStreamReader(
					this.socket.getInputStream()));
			this.bw = new BufferedWriter(new OutputStreamWriter(
					this.socket.getOutputStream()));
			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void close_connection() {
		try {
			this.br.close();
			this.bw.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String[] save_update_orar(Orar orar) {
		open();
		ContentValues values = new ContentValues();
		values.put(Database.ORARE_COLUMN_NUME_TABEL, orar.getTable_name());
		values.put(Database.ORARE_COLUMN_AN_SEMESTRU, orar.getSectie()
				.getNume() + " - " + orar.getAn_semestru());

		Cursor cursor1 = sqLiteDb.query(Database.T_ORARE_DISPONIOBILE_LOCAL,
				new String[] { Database.ORARE_COLUMN_NUME_TABEL },
				Database.ORARE_COLUMN_NUME_TABEL + "=?",
				new String[] { orar.getTable_name() }, null, null, null);
		if (cursor1.moveToFirst()) {
			return new String[] { "Orar Duplicat", "Deja aveji acest orar." };
		} else {

			sqLiteDb.insert(Database.T_ORARE_DISPONIOBILE_LOCAL, null, values);

			database.createTableOrarIfNotExists(sqLiteDb, orar);

			update_orar(orar);
			close();

			return new String[] { "Salvat", "Orarul a fost salvat cu succes." };
		}
	}

	public List<Orar> getOrareDisponibileLocal() {
		List<Orar> orare_disponibile_local = new ArrayList<Orar>();
		open();

		Cursor cursor = sqLiteDb.query(Database.T_ORARE_DISPONIOBILE_LOCAL,
				new String[] { "*" }, null, null, null, null, null);

		if (cursor.moveToFirst()) {
			int colIndexTblName = cursor
					.getColumnIndex(Database.ORARE_COLUMN_NUME_TABEL);
			int colIndexAnSemestru = cursor
					.getColumnIndex(Database.ORARE_COLUMN_AN_SEMESTRU);
			while (!cursor.isAfterLast()) {
				String table_name = cursor.getString(colIndexTblName);
				String an_semestru = cursor.getString(colIndexAnSemestru);

				Orar orar = new Orar(table_name, an_semestru);
				orare_disponibile_local.add(orar);

				cursor.moveToNext();
			}
			close();
			return orare_disponibile_local;
		} else
			return null;
	}

	public Orar getOrar(String table_name, String an_semestru) {
		Orar orar = new Orar(table_name, an_semestru);
		String[] ora = new String[13];
		String[] luni = new String[13];
		String[] marti = new String[13];
		String[] miercuri = new String[13];
		String[] joi = new String[13];
		String[] vineri = new String[13];
		String[] sambata = new String[13];
		String[] duminica = new String[13];

		open();
		Cursor cursor = sqLiteDb.query(table_name, new String[] { "*" }, null,
				null, null, null, null);
		cursor.moveToFirst();
		int colIndOra = cursor.getColumnIndex(Database.ORAR_COLUMN_ORA);
		int colIndLuni = cursor.getColumnIndex(Database.ORAR_COLUMN_LUNI);
		int colIndMarti = cursor.getColumnIndex(Database.ORAR_COLUMN_MARTI);
		int colIndMiercuri = cursor
				.getColumnIndex(Database.ORAR_COLUMN_MIERCURI);
		int colIndJoi = cursor.getColumnIndex(Database.ORAR_COLUMN_JOI);
		int colIndVienri = cursor.getColumnIndex(Database.ORAR_COLUMN_VINERI);
		int colIndSambata = cursor.getColumnIndex(Database.ORAR_COLUMN_SAMBATA);
		int colIndDuminica = cursor
				.getColumnIndex(Database.ORAR_COLUMN_DUMINICA);
		int i = 0;
		while (!cursor.isAfterLast()) {
			ora[i] = cursor.getString(colIndOra);
			luni[i] = cursor.getString(colIndLuni);
			marti[i] = cursor.getString(colIndMarti);
			miercuri[i] = cursor.getString(colIndMiercuri);
			joi[i] = cursor.getString(colIndJoi);
			vineri[i] = cursor.getString(colIndVienri);
			sambata[i] = cursor.getString(colIndSambata);
			duminica[i] = cursor.getString(colIndDuminica);
			i++;
			cursor.moveToNext();
		}

		orar.setOra(ora);
		orar.setLuni(luni);
		orar.setMarti(marti);
		orar.setMiercuri(miercuri);
		orar.setJoi(joi);
		orar.setVineri(vineri);
		orar.setSambata(sambata);
		orar.setDuminica(duminica);

		close();
		return orar;
	}

	public void update_orar(Orar orar) {
		open();

		connect();
		JSONObject received_orar;
		try {
			JSONObject s = new JSONObject("{\"status\":2 , \"orar\":\""
					+ orar.getTable_name() + "\"}");
			this.bw.write(s.toString() + "\n");
			this.bw.flush();
			received_orar = new JSONObject(this.br.readLine());
			for (int i = 0; i < received_orar.length(); i++) {
				JSONObject rowJSON = received_orar.getJSONObject("" + i);
				ContentValues row_values = new ContentValues();
				row_values.put(Database.ORAR_COLUMN_ORA,
						rowJSON.getString(Database.ORAR_COLUMN_ORA));
				row_values.put(Database.ORAR_COLUMN_LUNI,
						rowJSON.getString(Database.ORAR_COLUMN_LUNI));
				row_values.put(Database.ORAR_COLUMN_MARTI,
						rowJSON.getString(Database.ORAR_COLUMN_MARTI));
				row_values.put(Database.ORAR_COLUMN_MIERCURI,
						rowJSON.getString(Database.ORAR_COLUMN_MIERCURI));
				row_values.put(Database.ORAR_COLUMN_JOI,
						rowJSON.getString(Database.ORAR_COLUMN_JOI));
				row_values.put(Database.ORAR_COLUMN_VINERI,
						rowJSON.getString(Database.ORAR_COLUMN_VINERI));
				row_values.put(Database.ORAR_COLUMN_SAMBATA,
						rowJSON.getString(Database.ORAR_COLUMN_SAMBATA));
				row_values.put(Database.ORAR_COLUMN_DUMINICA,
						rowJSON.getString(Database.ORAR_COLUMN_DUMINICA));
				Cursor cursor = sqLiteDb.query(orar.getTable_name(),
						new String[] { "*" }, null, null, null, null, null);
				if (cursor.moveToFirst()) {
					boolean sw = false;
					while (!cursor.isAfterLast()) {
						String rvs = row_values
								.getAsString(Database.ORAR_COLUMN_ORA);
						int clmnIndex = cursor
								.getColumnIndex(Database.ORAR_COLUMN_ORA);
						if (cursor.getString(clmnIndex).equalsIgnoreCase(rvs)) {
							sqLiteDb.update(
									orar.getTable_name(),
									row_values,
									Database.ORAR_COLUMN_ORA + "=?",
									new String[] { ""
											+ rowJSON
													.getString(Database.ORAR_COLUMN_ORA) });
							sw = true;
							break;
						}
						cursor.moveToNext();
					}
					if (!sw)
						sqLiteDb.insert(orar.getTable_name(), null, row_values);
				} else {
					sqLiteDb.insert(orar.getTable_name(), null, row_values);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		close_connection();

		close();
	}

	public void update_orare_disponibile_local() {
		open();
		Cursor cursor = sqLiteDb.query(Database.T_ORARE_DISPONIOBILE_LOCAL,
				new String[] { "*" }, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			int colIndexTblName = cursor
					.getColumnIndex(Database.ORARE_COLUMN_NUME_TABEL);
			int colIndexAnSemestru = cursor
					.getColumnIndex(Database.ORARE_COLUMN_AN_SEMESTRU);
			while (!cursor.isAfterLast()) {
				String table_name = cursor.getString(colIndexTblName);
				String an_semestru = cursor.getString(colIndexAnSemestru);
				Orar orar = new Orar(table_name, an_semestru);
				update_orar(orar);
				cursor.moveToNext();
			}
		}
		close();
	}

}
