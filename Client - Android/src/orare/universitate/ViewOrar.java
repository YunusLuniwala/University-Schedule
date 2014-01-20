package orare.universitate;

import android.app.Activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ViewOrar extends Activity {

	Orar orar;
	DAO dao = new DAO(this);
	TableLayout tabel;
	TableRow table_row;
	TextView ora_view, ziua_view, ziua_title;
	String[] zi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle orar_bundle = getIntent().getExtras();
		String an_semestru = orar_bundle
				.getString(Database.ORARE_COLUMN_AN_SEMESTRU);
		String table_name = orar_bundle
				.getString(Database.ORARE_COLUMN_NUME_TABEL);
		int ziua = orar_bundle.getInt("ziua");
		orar = dao.getOrar(table_name, an_semestru);
		setContentView(R.layout.orar);

		tabel = (TableLayout) findViewById(R.id.tabel);
		ziua_title = (TextView) findViewById(R.id.Ziua);

		String[] ore = orar.getOra();

		getZiua(ziua);

		for (int i = 0; i < 13; i++) {
			addRow(ore[i], zi[i]);

		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		this.finish();
	}

	public void addRow(String ora, String ziua) {
		table_row = new TableRow(this);
		table_row.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));

		ora_view = new TextView(this);
		ora_view.setText(ora);
		ora_view.setGravity(Gravity.CENTER);
		ziua_view = new TextView(this);
		ziua_view.setText(ziua);
		ziua_view.setGravity(Gravity.CENTER);
		table_row.addView(ora_view);
		table_row.addView(ziua_view);

		tabel.addView(table_row, new TableLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	}

	public void getZiua(int ziua) {
		switch (ziua) {
		case 0:
			zi = orar.getLuni();
			ziua_title.setText("Luni");
			break;

		case 1:
			zi = orar.getMarti();
			ziua_title.setText("Marți");
			break;

		case 2:
			zi = orar.getMiercuri();
			ziua_title.setText("Miercuri");
			break;

		case 3:
			zi = orar.getJoi();
			ziua_title.setText("Joi");
			break;

		case 4:
			zi = orar.getVineri();
			ziua_title.setText("Vineri");
			break;

		case 5:
			zi = orar.getSambata();
			ziua_title.setText("Sâmbătă");
			break;

		case 6:
			zi = orar.getDuminica();
			ziua_title.setText("Duminică");
			break;

		}
	}

}
