package apirest.com.aplicacionrest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import apirest.com.aplicacionrest.Entidades.Producto;
import apirest.com.aplicacionrest.Repositorios.RepositoryProducto;

@RestController
@RequestMapping("/productos")
public class ControladoresProducto {

    @Autowired
    private RepositoryProducto repositorio;

    @GetMapping
    public List<Producto> obtenerProductos() {
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerPorID(@PathVariable Long id) {
        return repositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Error."));
    }


    @PostMapping("/{id}")
    public Producto crearProducto(@RequestBody Producto producto) {
        return repositorio.save(producto);
        
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        Producto producto = repositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Error en el codigo."));

       producto.setNombre(productoActualizado.getNombre());
       producto.setPrecio(productoActualizado.getPrecio()); 

        return repositorio.save(producto);
    }

    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id) {
       Producto producto = repositorio.findById(id)
       .orElseThrow(() -> new RuntimeException("No se pudo encontrar"));

       repositorio.delete(producto);
       
       return "Elemento borrado exitosamente";

       
    }   

}
