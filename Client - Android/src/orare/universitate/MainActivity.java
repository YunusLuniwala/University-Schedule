package orare.universitate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */

	static TextView textS;
	JSONObject receivedJSON, sentJSON;
	Button add, update;
	ListView lista_orare_disponibile_local;
	List<Orar> orare_disponibile_local;
	public static final String serverURL = "ovidiub13.dyndns.org";
	public static final int port = 2400;
	DAO dao = new DAO(this);
	Socket socket;
	BufferedReader br;
	BufferedWriter bw;
	
	int position;
	String[] saptamana = {"Luni", "Marți", "Miercuri", "Joi", "Vineri", "Sâmbătă", "Duminică"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
		textS.setText("");
		setUpListeners();
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (orare_disponibile_local != null)
			orare_disponibile_local.clear();
		orare_disponibile_local = dao.getOrareDisponibileLocal();
		if (orare_disponibile_local != null) {
			ArrayAdapter<Orar> adapter = new ArrayAdapter<Orar>(this,
					android.R.layout.simple_list_item_1,
					orare_disponibile_local);
			lista_orare_disponibile_local.setAdapter(adapter);
		}
		setUpListeners();
		textS.setText("");
	}

	private void init() {
		textS = (TextView) findViewById(R.id.text_for_test);
		add = (Button) findViewById(R.id.add);
		update = (Button) findViewById(R.id.update);
		lista_orare_disponibile_local = (ListView) findViewById(R.id.lista_orare_disponibile_local);
	}
	
	private void setUpListeners(){
		orare_disponibile_local = dao.getOrareDisponibileLocal();
		if (orare_disponibile_local != null) {
			
			ArrayAdapter<Orar> adapter = new ArrayAdapter<Orar>(this,
					android.R.layout.simple_list_item_1,
					orare_disponibile_local);
			lista_orare_disponibile_local.setAdapter(adapter);
			lista_orare_disponibile_local
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						public void onItemClick(AdapterView<?> arg0, View view,
								int position, long id) {
							MainActivity.this.position = position;

							AlertDialog.Builder builder = new AlertDialog.Builder(
									MainActivity.this);
							builder.setTitle("Alegeți o zi");
							builder.setItems(saptamana,
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {

											Bundle orar_bundle = new Bundle();
											orar_bundle
													.putString(
															Database.ORARE_COLUMN_NUME_TABEL,
															orare_disponibile_local
																	.get(MainActivity.this.position)
																	.getTable_name());
											orar_bundle
													.putString(
															Database.ORARE_COLUMN_AN_SEMESTRU,
															orare_disponibile_local
																	.get(MainActivity.this.position)
																	.getAn_semestru());
											orar_bundle.putInt("ziua", which);
											Intent intent_orar = new Intent(
													MainActivity.this,
													ViewOrar.class);
											intent_orar.putExtras(orar_bundle);
											startActivity(intent_orar);

										}
									});
							AlertDialog alegeZiua = builder.create();
							alegeZiua.show();
						}
					});
		}
		update.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (connect()) {
					// textS.setText("Connected");
					String stringJSON = "{\"status\":1}";
					try {
						sentJSON = new JSONObject(stringJSON);
						bw.write(sentJSON.toString() + "\n");
						bw.flush();
						receivedJSON = new JSONObject(br.readLine());
						// textS.setText(receivedJSON.toString());
						@SuppressWarnings("unused")
						UpdateDB update = new UpdateDB(receivedJSON,
								MainActivity.this);
					} catch (JSONException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					close_connection();
				} else {
					AlertDialog server_offline_alert = new AlertDialog.Builder(
							MainActivity.this).create();
					server_offline_alert
							.setMessage("Server is Offline at the moment. Please try again later.");
					server_offline_alert.setTitle("Server Offline");
					server_offline_alert.show();
				}
			}
		});

		add.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						List_facultati.class);
				startActivity(intent);
			}
		});
	}

	public boolean connect() {
		try {
			//InetAddress server = InetAddress.getByName(serverURL);
			socket = new Socket("172.17.186.72", port);
			br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream()));
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
			br.close();
			bw.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}