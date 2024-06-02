package es.progcipfpbatoi.gestorTareas.modelo.repositorios;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import es.progcipfpbatoi.gestorTareas.modelo.entidades.*;

@Repository
public class TareaRepository {
	
	private ArrayList<Tarea> tareas;
	
	public TareaRepository() {
		this.tareas = new ArrayList<>();
	}
	
	public void addTarea(Tarea tarea) {
		this.tareas.add(tarea);
	}
	
	public int getNumTareas() {
		return this.tareas.size();
	}
	
	public Tarea getTarea(int codTarea){
		
		for(Tarea tar: tareas) {
			if(tar.getCodigo() == codTarea) {
				System.out.println("Tarea con código " +  codTarea + " encontrada"); 
				return tar;
			}
		}
		return null;
	}
	
	
	public void deleteTarea(int codTarea) {

		for(Tarea tar: tareas) {
			if(tar.getCodigo() == codTarea) {
				tareas.remove(tar);
				System.out.println("TAREAREPOSITORY - Tarea " + codTarea + " encontrada y eliminada");
				return;
			}
		}
	
	}
	
	
	public ArrayList<Tarea> getTodasTareas(){		
		return tareas;		
	}

}
