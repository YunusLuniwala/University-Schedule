package orare.universitate;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class List_facultati extends ListActivity {

	DAO dao;
	List<Facultate> facultati;
	ArrayAdapter<Facultate> viewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setTitle("Facultăți");
		dao = new DAO(this);
		facultati = dao.getListFacultati();
		viewAdapter = new ArrayAdapter<Facultate>(this,
				android.R.layout.simple_list_item_1, facultati);
		setListAdapter(viewAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Facultate facultate = facultati.get(position);
		Bundle faculta_bundle = new Bundle();
		faculta_bundle.putInt(Database.FACULTATI_COLUMN_ID, facultate.getID());
		faculta_bundle.putString(Database.FACULTATI_COLUMN_NUME,
				facultate.getNume());
		Intent intent_sectii = new Intent(List_facultati.this,
				List_sectii.class);
		intent_sectii.putExtras(faculta_bundle);
		startActivity(intent_sectii);
		List_facultati.this.finish();
	}
}