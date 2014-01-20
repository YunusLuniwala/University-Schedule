package orare.universitate;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class UpdateDB {

	DAO dao;
	
	public UpdateDB(JSONObject receivedJSON, Context context){
		dao = new DAO(context);
		dao.open();
		try {
			JSONObject facultatiJSON = receivedJSON.getJSONObject(Database.T_FACULTATI);
			for(int i = 0; i < facultatiJSON.length(); i++){
				JSONObject facultate = facultatiJSON.getJSONObject(""+i);
				dao.update_facultate(facultate);
			}
//			MainActivity.textS.append(" OK facultati");
			
			JSONObject sectiiJSON = receivedJSON.getJSONObject(Database.T_SECTII);
			for(int i = 0; i < sectiiJSON.length(); i++){
				dao.update_sectie(sectiiJSON.getJSONObject(""+i));
			}
//			MainActivity.textS.append(" OK sectii");
			
			dao.update_orare_disponibile_local();
			
			MainActivity.textS.setText("Actualizat cu success!");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		dao.close();
	}
	
	

	
	
}