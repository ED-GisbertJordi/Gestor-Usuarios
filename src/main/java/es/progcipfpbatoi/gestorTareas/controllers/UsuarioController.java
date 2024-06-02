package es.progcipfpbatoi.gestorTareas.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.progcipfpbatoi.gestorTareas.modelo.entidades.Prioridad;
import es.progcipfpbatoi.gestorTareas.modelo.entidades.Tarea;
import es.progcipfpbatoi.gestorTareas.modelo.entidades.Usuario;
import es.progcipfpbatoi.gestorTareas.modelo.repositorios.UsuariosRepositoriy;

import es.progcipfpbatoi.gestorTareas.modelo.entidades.Prioridad;
import es.progcipfpbatoi.gestorTareas.modelo.entidades.Tarea;
import es.progcipfpbatoi.gestorTareas.modelo.repositorios.TareaRepository;



@Controller
public class UsuarioController {

    @Autowired
    private UsuariosRepositoriy usuariosRepo = new UsuariosRepositoriy();
	
    // Muestra el menu de gestion de Usuarios
	@GetMapping("/")
	private String postMenuUserAction(@RequestParam Map<String, String> params, Model model) {
		String accion = params.get("accion");
		if (accion == null) {
            return "menuUsuairos";
        }

		switch(accion) {
		case "crear":
			return "redirect:/insertar";
		case "eliminar":
			return "redirect:/borrar";
		case "mostrar":
			return "redirect:/buscar";
		case "mostrarTodos":
			return "redirect:/listar";
		default:
			return "notImplemented_View";
		}
	}

    // Mostrara el formulario para insertar un usuario
    @GetMapping("/insertar")  
    private String addUsuarios() {
        return "insertarUsuario";
    }

    // Mostara el formulario para borrar un usuario
    @GetMapping("/borrar")  
    private String deleteUser() {
        return "borrarUsuario";
    }

    // Mostara el formulario para buscar un usuario
    @GetMapping("/buscar")  
    private String searchUsario() {
        return "buscarUsario";
    }
    
    // Mostrara el listado de usuarios
    @GetMapping("/listar")  
    private String getUsarios() {
        return "listaUsuarios";
    }


    // Logica de los formularios
    // Insertar un usuario
    @ResponseBody
    @PostMapping("/addUser")
	private String postAddUser(@RequestParam Map<String, String> params) {
		
        // Datos del usuario
        String nombre = params.get("nombre");
        String apellidos = params.get("apellidos");
		String dni = String.valueOf(params.get("dni"));
        String email = params.get("email");
        String prefijo = params.get("prefijo");
        String telefono = params.get("telefono");
        String fechaNacimiento = params.get("anyo");
        String codPostal = params.get("codPostal");
        String password = params.get("password");
        String confPassword = params.get("confPassword");

		
		// Fecha de Nacimiento
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
		//Creación del Usuario
		Usuario u = new Usuario(nombre, apellidos, dni, email, prefijo + telefono, LocalDate.parse(fechaNacimiento), codPostal, password);
		usuariosRepo.addUsuario(u);
        System.out.println("Exito creando: " + u.toString());
		
		String respuestaHtml = "<html>" + "<body> Usuario " + u.getDni() + " recibida con exito</body></html>";
		return respuestaHtml;
	}



    @ResponseBody
	@PostMapping("/deleteUser")
	private String postDeleteUser(@RequestParam Map<String, String> params) {
		String dni = String.valueOf(params.get("dni"));
		Usuario usuario = usuariosRepo.getUsuario(dni);
		
		if(usuario != null) {
			
			//Eliminar el Usuario:
			usuariosRepo.deleteUsuario(dni);
			
			return "<html><body><h1>Usuarios con DNI: " + usuario.getDni() + " Eliminado</h1></body></html>";
		}else {
			return "<html><body><h1>No se ha encontrado el Usuariocon DNI: " + dni + "</h1></body></html>";
		}
	}

    @ResponseBody
	@PostMapping("/searchUser")
	private String postSearchUser(@RequestParam Map<String, String> params) {
		String dni = String.valueOf(params.get("dni"));
		Usuario usuario = usuariosRepo.getUsuario(dni);
		
		if(usuariosRepo.existeUsuario(dni)) {
			return "<html><body><h1>Usuarios con DNI: " + usuario.getDni() + " Encontrado</h1></body></html>";
		}else {
			return "<html><body><h1>No se ha encontrado el Usuariocon DNI: " + dni + "</h1></body></html>";
		}
	}

    /*
	
	
	@GetMapping("/deleteTask")
	private String getEraseAction(@RequestParam Map<String, String> params) {
		
		int codigo = Integer.parseInt(params.get("codigo"));
		
		Tarea tarea = tareaRepo.getTarea(codigo);
		
		if(tarea != null) {
			//Eliminar la tarea:
			tareaRepo.deleteTarea(codigo);
		}
		
		return "redirect:/showAllTasks";
	}
	
	
	@PostMapping("/showTask")
	private String showTaskAction(@RequestParam Map<String, String> params, Model model) {
		
		int codigo = Integer.parseInt(params.get("codigo"));
		
		Tarea tareabuscada = tareaRepo.getTarea(codigo);
		
		model.addAttribute("t", tareabuscada);
		
		return "showTaskDetails_View";
	}
	
	@GetMapping("/showTask")
	private String showTaskActionRedirection(@RequestParam Map<String, String> params, Model model) {
		
		int codigo = Integer.parseInt(params.get("codigo"));
		
		Tarea tareabuscada = tareaRepo.getTarea(codigo);
		
		model.addAttribute("t", tareabuscada);
		
		return "showTaskDetails_View";
	}
	
	

	//Esto ha sido necesario añadirlo para implementar la redirección (GET)
	@GetMapping("/showAllTasks")  
	private String mostrarTodasTareas(Model model) {
		
		model.addAttribute("todasTareas", tareaRepo.getTodasTareas());
		
		return "showAllTaskDetails_View";
	}









*/








}
