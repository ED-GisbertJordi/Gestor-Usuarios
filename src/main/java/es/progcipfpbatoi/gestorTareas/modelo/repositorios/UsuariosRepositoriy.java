package es.progcipfpbatoi.gestorTareas.modelo.repositorios;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import es.progcipfpbatoi.gestorTareas.modelo.entidades.Usuario;

@Repository
public class UsuariosRepositoriy {

    Map<String, Usuario> usuarios;

    public UsuariosRepositoriy() {
        this.usuarios = new HashMap<>();
        this.init();
    }

    public void addUsuario(Usuario usuario) {
        this.usuarios.put(usuario.getDni(), usuario);
    }

    public Usuario getUsuario(String dni) {
        return this.usuarios.get(dni);
    }

    public void deleteUsuario(String dni) {
        this.usuarios.remove(dni);
    }

    public Set<Usuario> getTodosUsuarios() {
        return new HashSet<>(usuarios.values());
    }

    public int getNumUsuarios() {
        return this.usuarios.size();
    }

    public boolean existeUsuario(String dni) {
        return this.usuarios.containsKey(dni);
    }




    public void init() {
        Usuario[] u1 = {new Usuario("Jordi", "Gisbert", "20527193S", "jota.gvg@gmail.com", "666666666", null, null, null), 
                        new Usuario("Pepe", "Gisbert", "20527192S", "jota.gvg@gmail.com", "666666666", null, null, null), 
                        new Usuario("Pedro", "Gisbert", "20527191S", "jota.gvg@gmail.com", "666666666", null, null, null)};
        for (Usuario u : u1) {
            this.addUsuario(u);
        }
    }
}
