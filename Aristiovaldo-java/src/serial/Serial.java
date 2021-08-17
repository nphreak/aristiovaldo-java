package serial;

import gnu.io.CommPortIdentifier;
import java.util.Enumeration;

public class Serial {

	protected String[] portas;
	protected Enumeration listaDePortas;

	
	public Serial() {
		listaDePortas = CommPortIdentifier.getPortIdentifiers();
		ListarPortas();
	}
	
	public String[] ObterPortas() {
		return portas;
	}
	
	public void ListarPortas() {
		int i = 0;
		portas = new String[10];
		while (listaDePortas.hasMoreElements()) {
			CommPortIdentifier porta = (CommPortIdentifier)listaDePortas.nextElement();
			portas[i] = porta.getName();
			i++;
		}
	}
	
	public boolean PortaExiste(String portaName) {
		String temp;
		boolean existe = false;
		while(listaDePortas.hasMoreElements()) {
			CommPortIdentifier porta = (CommPortIdentifier)listaDePortas.nextElement();
			temp = porta.getName();
			if(temp.equals(portaName)==true) {
				existe = true;
			}
		}
		
		return existe;
	}
}
