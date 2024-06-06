package es.progcipfpbatoi.gestorTareas.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.progcipfpbatoi.gestorTareas.modelo.entidades.Usuario;
import es.progcipfpbatoi.gestorTareas.modelo.entidades.Validator;
import es.progcipfpbatoi.gestorTareas.modelo.repositorios.UsuariosRepositoriy;



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
    // @GetMapping("/insertar")  
    // private String addUsuarios() {
    //     return "insertarUsuario copy";
    // }

	
	// Mostrara el formulario para insertar un usuario
    @GetMapping("/insertar")  
    private String addUsuarios(@RequestParam Map<String, String> params, Model model) {

		
		if (params.get("nombre") == null) {
			params.put("nombre", "");
		}

		if (params.get("apellidos") == null) {
			params.put("apellidos", "");
		}

		if (params.get("dni") == null) {
			params.put("dni", "");
		}

		if (params.get("email") == null) {
			params.put("email", "");
		}

		if (params.get("prefijo") == null) {
			params.put("prefijo", "");
		}

		if (params.get("telefono") == null) {
			params.put("telefono", "");
		}

		if (params.get("anyo") == null) {
			params.put("anyo", "");
		}

		if (params.get("codPostal") == null) {
			params.put("codPostal", "");
		}
		
		if (params.get("error") == null) {
			params.put("error", "");
		}

        model.addAttribute("usuario", params);
        return "insertarUsuario";
    }
	

    // Mostara el formulario para borrar un usuario
    @GetMapping("/borrar")  
    private String deleteUser(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("dni", params.get("dni"));
        return "borrarUsuario";
    }

    // Mostara el formulario para buscar un usuario
    @GetMapping("/buscar")  
    private String searchUsario(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("dni", params.get("dni"));
		return "buscarUsario";
    }
    
    // Mostrara el listado de usuarios
    @GetMapping("/listar")  
    private String getUsarios(Model model) {
		model.addAttribute("usuarios", usuariosRepo.getTodosUsuarios());
        return "listaUsuarios";
    }


    // Logica de los formularios
    // Insertar un usuario
    @PostMapping("/addUser")
	public String postAddUser(@RequestParam Map<String, String> params, RedirectAttributes redirectAttributes) {
		// Datos del usuario
		String nombre = params.get("nombre");
		String apellidos = params.get("apellidos");
		String dni = params.get("dni");
		String email = params.get("email");
		String prefijo = params.get("prefijo");
		String telefono = params.get("telefono");
		String fechaNacimiento = params.get("anyo");
		String codPostal = params.get("codPostal");
		String password = params.get("password");
		String confPassword = params.get("confPassword");

		String error = "";
		
		//http://localhost:8080/insertar?nombre=hola&&apellidos=a&&dni=a&&email=asd@vb&&prefijo=a&&telefono=1&&anyo=2024-05-29&&codPostal=11111&&password=a&&confPassword=a&&error=HOla&&error=HOla
		
		// Validación de campos
		if (!Validator.isValidNameAndSurname(nombre)) {
			error += "Nombre invalido. ";
		}
		if (!Validator.isValidNameAndSurname(apellidos)) {
			error += "Apellidos invalidos. ";
		}
		if (!Validator.isValidDNI(dni)) {
			error += "DNI invalido. ";
		}
		if (!Validator.isValidEmail(email)) {
			error += "Email invalido. ";
		}
		if (!Validator.isValidSpanishMobilePrefix(prefijo)) {
			error += "Prefijo invalido. ";
		}
		if (!Validator.isValidSpanishMobileNumber(telefono)) {
			error += "Teléfono invalido. ";
		}
		if (!Validator.isValidSpanishPostalCode(codPostal)) {
			error += "Código postal invalido. ";
		}
		if (!Validator.isValidPassword(password) || !password.equals(confPassword)) {
			error += "Contraseña inválida o no coincide con la confirmación. ";
		}

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// Agregar el mensaje de error al mapa de parámetros si existe
		if (error != "") {
			params.put("error", error);
		
			String parametros = "";
			for (Map.Entry<String, String> entry : params.entrySet()) {
				parametros += entry.getKey() + "=" + entry.getValue()+"&&";
			}


			// Redirigir de nuevo al formulario con los errores
			return "redirect:/insertar?"+parametros+"error="+error;
		}

		// Creación del Usuario
		Usuario u = new Usuario(nombre, apellidos, dni, email, prefijo, telefono, LocalDate.parse(fechaNacimiento, formato), codPostal, password);
		usuariosRepo.addUsuario(u);
		System.out.println("Éxito creando: " + u.toString());

		return "redirect:/insertar";
	}



	// Borrar Post
	@PostMapping("/deleteUser")
	private String postDeleteUser(@RequestParam Map<String, String> params, Model model) {
		String dni = String.valueOf(params.get("dni"));
		Usuario usuario = usuariosRepo.getUsuario(dni);
		
		if(usuario != null) {
			
			//Eliminar el Usuario:
			usuariosRepo.deleteUsuario(dni);
			model.addAttribute("mensaje", dni);
			return "exsitoso";
		}else {
			model.addAttribute("error", "No se ha encontrado el Usuario con DNI: " + dni);
			return "error";
		}
	}


	// Buscar Post
	@PostMapping("/searchUser")
	private String postSearchUser(@RequestParam Map<String, String> params, Model model) {
		String dni = String.valueOf(params.get("dni"));
		Usuario usuario = usuariosRepo.getUsuario(dni);
		
		if(usuariosRepo.existeUsuario(dni)) {
			model.addAttribute("usuario", usuario);
			return "detallesUsuario";
		}else {
			model.addAttribute("error", "No se ha encontrado el Usuario con DNI: " + dni);
			return "error";
		}
	}





}
