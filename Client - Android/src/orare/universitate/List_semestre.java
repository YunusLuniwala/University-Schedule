package orare.universitate;

import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class List_semestre extends ListActivity{

	DAO dao = new DAO(this);
	Sectie sectie = new Sectie();
	Facultate facultate = new Facultate();
	List<Orar> orare_semestre;
	ArrayAdapter<Orar> viewAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle sectie_bundle = getIntent().getExtras();
		facultate.setID(sectie_bundle.getInt(Database.FACULTATI_COLUMN_ID));
		facultate.setNume(sectie_bundle.getString(Database.FACULTATI_COLUMN_NUME));
		sectie.setID(sectie_bundle.getInt(Database.SECTII_COLUMN_ID));
		sectie.setNume(sectie_bundle.getString(Database.SECTII_COLUMN_NUME));
		sectie.setFacultate_ID(sectie_bundle.getInt(Database.SECTII_COLUMN_FACULTATE_ID));
		sectie.setForma(sectie_bundle.getString(Database.SECTII_COLUMN_FORMA));
		sectie.setAni(sectie_bundle.getInt(Database.SECTII_COLUMN_ANI));
		this.setTitle(sectie.getNume());
		orare_semestre = dao.getListSemestre(sectie, facultate);
		viewAdapter = new ArrayAdapter<Orar>(List_semestre.this, android.R.layout.simple_list_item_1, orare_semestre);
		setListAdapter(viewAdapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		String[] alert = dao.save_update_orar(orare_semestre.get(position));
		
		AlertDialog table_exests_alert = new AlertDialog.Builder(List_semestre.this).create();
		table_exests_alert.setMessage(alert[1]);
		table_exests_alert.setTitle(alert[0]);
		table_exests_alert.show();

		List_semestre.this.finish();		
	}	
}