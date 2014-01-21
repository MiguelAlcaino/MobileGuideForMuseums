package guiamovil.widgets;

import android.content.Context;
import android.widget.ListView;

public class ListViewId extends ListView {
	private int id_destino;
	public ListViewId(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public int getId_destino() {
		return id_destino;
	}
	public void setId_destino(int id_destino) {
		this.id_destino = id_destino;
	}
	

}
