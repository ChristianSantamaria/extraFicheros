package extraficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Metodos {

    ArrayList<Alumnos> ListaNotas = new ArrayList<Alumnos>();
    ArrayList<Alumnos> ListaAprovados = new ArrayList<Alumnos>();
    ArrayList<Alumnos> ListaSuspensos = new ArrayList<Alumnos>();

    File fichero;
    FileReader lectura;
    Scanner sc;

    public void engadir(String nomefich) {
        PrintWriter fichero = null;
        try {
            fichero = new PrintWriter(new FileWriter(nomefich, true));

            if (nomefich.equals("aprobados.dat")) {
                for (Alumnos a : ListaAprovados) {
                    fichero.println(a.nome + ' ' + a.nota);
                }
            } else if (nomefich.equals("suspensos.dat")) {
                for (Alumnos a : ListaSuspensos) {
                    fichero.println(a.nome + ' ' + a.nota);
                }
            } else if (nomefich.equals("notas.dat")) {
                for (Alumnos a : ListaNotas) {
                    fichero.println(a.nome + ' ' + a.nota);
                }
            }

        } catch (IOException ex) {
            System.out.println("Erro2");
        } finally {
            fichero.close();
        }
    }

    public void ler(String nomefich) {
        String[] matriz;
        try {
            sc = new Scanner(new File(nomefich));

            while (sc.hasNextLine()) {

                String linea = sc.nextLine();
                matriz = linea.split(",");

                if (Integer.parseInt(matriz[1]) >= 5) {
                    ListaAprovados.add(new Alumnos(matriz[0], Integer.parseInt(matriz[1])));
                } else {
                    ListaSuspensos.add(new Alumnos(matriz[0], Integer.parseInt(matriz[1])));
                }
                ListaNotas.add(new Alumnos(matriz[0], Integer.parseInt(matriz[1])));

            }
            if (ListaAprovados.size() > 0) {
                engadir("aprobados.dat");
            }
            if (ListaSuspensos.size() > 0) {
                engadir("suspensos.dat");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
