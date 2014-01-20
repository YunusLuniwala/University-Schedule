package orare.universitate;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class List_sectii extends ListActivity{
	
	Facultate facultate = new Facultate();
	DAO dao = new DAO(this);
	List<Sectie> sectii;
	ArrayAdapter<Sectie> viewAdapter;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle facultate_bundle = getIntent().getExtras();
		facultate.setID(facultate_bundle.getInt(Database.FACULTATI_COLUMN_ID));
		facultate.setNume(facultate_bundle.getString(Database.FACULTATI_COLUMN_NUME));
		this.setTitle(facultate.getNume());
		sectii = dao.getListSectii(facultate.getID());
		viewAdapter = new ArrayAdapter<Sectie>(List_sectii.this, android.R.layout.simple_list_item_1, sectii);
		setListAdapter(viewAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Sectie sectie = sectii.get(position);
		
		Bundle sectie_bundle = new Bundle();
		sectie_bundle.putInt(Database.SECTII_COLUMN_ID, sectie.getID());
		sectie_bundle.putString(Database.SECTII_COLUMN_NUME, sectie.getNume());
		sectie_bundle.putString(Database.SECTII_COLUMN_FORMA, sectie.getForma());
		sectie_bundle.putInt(Database.SECTII_COLUMN_FACULTATE_ID, sectie.getFacultate_ID());
		sectie_bundle.putInt(Database.SECTII_COLUMN_ANI, sectie.getAni());
		
		sectie_bundle.putInt(Database.FACULTATI_COLUMN_ID, facultate.getID());
		sectie_bundle.putString(Database.FACULTATI_COLUMN_NUME, facultate.getNume());
		
		Intent intent_semestre = new Intent(List_sectii.this, List_semestre.class);
		intent_semestre.putExtras(sectie_bundle);
		startActivity(intent_semestre);
		List_sectii.this.finish();
		
	}
	
	
}
