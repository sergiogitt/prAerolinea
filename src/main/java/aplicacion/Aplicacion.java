package aplicacion;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import controladores.ControladorAvion;
import controladores.ControladorCompanya;
import controladores.ControladorDirector;

public class Aplicacion {

	public static void main(String[] args) {
		ControladorDirector cd=new ControladorDirector();
		ControladorAvion ca=new ControladorAvion();
		ControladorCompanya cc=new ControladorCompanya();
		String [] botonesOeraciones={"A�adir registro","Borrar registro","Modificar registro","Buscar"};
		String [] botonesTablas={"Aviones","Compa�ias","Directores"};
		int anyadirRegistroEnTabla;
		int operacion = JOptionPane.showOptionDialog (null, "Elige la operaci�n que deseas realizar", "Aerolinea", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null/*icono*/, botonesOeraciones, botonesOeraciones[3]);
		switch(operacion) {
		case 0:
			anyadirRegistroEnTabla =mostrarTablas("a�adir");
			break;
		case 1:
			anyadirRegistroEnTabla =mostrarTablas("borrar");
			break;
		case 2:
			anyadirRegistroEnTabla =mostrarTablas("modificar");
			break;
		case 3:
			anyadirRegistroEnTabla =mostrarTablas("buscar");
			break;
		}

	}
	public static int mostrarTablas(String texto) {
		String [] botonesTablas={"Aviones","Compa�ias","Directores"};
		int anyadirRegistroEnTabla = JOptionPane.showOptionDialog (null, "Elige la tabla en la que deseas "+texto+" el registro", "Insercion dato", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null/*icono*/, botonesTablas, botonesTablas[0]);
		return anyadirRegistroEnTabla;
	}

}
