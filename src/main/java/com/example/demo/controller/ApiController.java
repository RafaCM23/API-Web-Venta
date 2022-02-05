package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.service.LineaPedidoService;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;

@RestController
public class ApiController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private LineaPedidoService lineaPedidoService;
	
	@Autowired
	private ProductoService productoService;
	
	
	
	
	 //                                Usuarios                                    //
	//----------------------------------------------------------------------------//
	
	
	
	/**
	 *Busca los usuarios guardados, si no hay ninguno devuelve not found
	 * @return Lista de usuarios guardados
	 */
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> findUsuarios(){
		
		List<Usuario> result = usuarioService.findAll();
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(result);
		}
	}
	
	
	/**
	 * Busca un usuario por id que se le pasa en la uri, si no lo encuentra devuelve not found
	 * @param id int
	 * @return Usuario con esa id
	 */
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> userById(@PathVariable int id) {
		
		Usuario result=usuarioService.getById(id);
		if(result==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(result);
		}
	}
	
//		@PostMapping("/usuarios")
//		public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario user) {
//
//			if(user==null) {
//				return ResponseEntity.notFound().build();
//			}
//			else {
//				usuarioService.guardaUsuario(user);
//				return ResponseEntity.ok(user);
//			}
//			
//		}
	

	
	
     //                              Pedidos                                       //
	//----------------------------------------------------------------------------//
	
	
	
	/**
	 * Busca los pedidos guardados, si no encuentra ninguno devuelve not found
	 * @return Lista de pedidos guardados
	 */
	@GetMapping("/pedidos")
	public ResponseEntity<List<Pedido>> findPedidos(){
		
		List<Pedido> result = pedidoService.findAll();
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(result);
		}
	}
	
	/**
	 * Crea un pedido vacio ligado a un usuario que recibe por RequestBody, si no encuentra el usuario
	 * devuelve no content
	 * @param Usuario user
	 * @return Pedido creado
	 */
	@PostMapping("/pedidos")
	public ResponseEntity<Pedido> addPedido(@RequestBody Usuario user) {
		Usuario usuario= usuarioService.getById(user.getId());
		Pedido result = pedidoService.creaVacio(usuario);
		if (result==null || usuario==null) {
			return ResponseEntity.noContent().build();
		}
		else {
			
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		}
	}
	
	
	/**
	 * Busca un pedido por id que se le pasa por la uri, si no lo encuentra devuelve not found
	 * @param id int
	 * @return Pedido buscado
	 */
	@GetMapping("/pedidos/{id}")
	public ResponseEntity<Pedido> pedidoById(@PathVariable int id) {
		
		Pedido result=pedidoService.getById(id);
		if(result==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(result);
		}
	}
	
	/**
	 * Busca pedido por id pasada por uri y le cambia los datos de envio por datos nuevos que se le pasa
	 * en un pedido auxiliar por Request Body, si el pedido no existe devuelve not found, si aux no existe
	 * devuelve bad request
	 * @param id int
	 * @param  aux Pedido
	 * @return Pedido modificado
	 */
	@PutMapping("pedidos/{id}")
	public ResponseEntity<Pedido> modificaPedido(@PathVariable int id,@RequestBody Pedido aux){
		
		Pedido p = pedidoService.getById(id);
		if(p==null) {
			return ResponseEntity.notFound().build();
		}
		else if(aux==null) {
			return ResponseEntity.badRequest().build();
		}
		else {
			p=pedidoService.modificaInfo(p, aux);
			return ResponseEntity.ok(p);
		}
		
	}
	
	
	/**
	 * Borra un pedido con la id pasara por uri, si no lo encuentra devuelve not found
	 * @param id int
	 * @return no content status
	 */
	@DeleteMapping("/pedidos/{id}")
	public ResponseEntity<?> borraPedido(@PathVariable int id) {
		
		int respuesta =pedidoService.borraPedido(id);
		if(respuesta==-1) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
	
	
	
	
	 //                           Linea de pedido                                  //
	//----------------------------------------------------------------------------//
	
	
	
	
	
	/**
	 * Busca todas las lineas de un pedido cuyo id se pasa por uri, si no encuentra el pedido devuelve
	 * no content
	 * @param id int
	 * @return Lista de lineas 
	 */
	@GetMapping("/pedidos/{id}/lineas")
	public ResponseEntity<List<LineaPedido>> findLineas(@PathVariable int id){
		
		Pedido p = pedidoService.getById(id);
		if(p.getLineas().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		else {
			List<LineaPedido> resp = lineaPedidoService.findByPedido(p);
			return ResponseEntity.ok(resp);
		}
		
	}
	
	/**
	 * Añade una linea a un pedido cuya id se pasa por uri, si no encuentra el pedido o el producto es nulo
	 * devuelve not null
	 * @param id int
	 * @param linea LineaPedido
	 * @return linea añadida
	 */
	@PostMapping("/pedidos/{id}/lineas")
	public ResponseEntity<LineaPedido> addLineas(@PathVariable int id,@RequestBody LineaPedido linea) {
		Producto producto = productoService.getById(linea.producto.getId());
		Pedido p = pedidoService.getById(id);
		if(p==null || producto==null) {
			return ResponseEntity.notFound().build();
		}
		else if(linea.getProducto()==null) {
			return ResponseEntity.badRequest().build();
		}
		else {
			LineaPedido resp=lineaPedidoService.creaLinea(p, linea);
			pedidoService.addLinea(id,resp);
			return ResponseEntity.status(HttpStatus.CREATED).body(resp);
		}
	}
	/**
	 * Sobreescribe los datos de una linea (producto y cantidad) sobre un pedido cuyo id se pasa por uri
	 * a partir de una lineaPedido que se pasa por Request body. Si no encuentra el pedido, la linea
	 * original o la nuevaLinea es null devuelve not found
	 * @param id int
	 * @param nuevaLinea LineaPedido
	 * @return Linea pedido modificada
	 */
	@PutMapping("pedidos/{id}/lineas")
	public ResponseEntity<?> modificaLinea(@PathVariable int id, @RequestBody LineaPedido nuevaLinea){
		
	
		Pedido p = pedidoService.getById(id);
		LineaPedido original = pedidoService.existeProducto(p,nuevaLinea);
	
		if(original==null || nuevaLinea==null || p==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			nuevaLinea=lineaPedidoService.sobreescribe(original,nuevaLinea);
			return ResponseEntity.ok(nuevaLinea);
		}
		
	}
	
	/**
	 * Busca una linea en un pedido, cuyas ids se pasan por uri, y la Borra. Si la linea o el pedido
	 * no existe, devuelve not found
	 * @param id int
	 * @param idLinea int
	 * @return no content status
	 */
	@DeleteMapping("/pedidos/{id}/lineas/{idLinea}")
	public ResponseEntity<?> deleteLinea(@PathVariable int id, @PathVariable int idLinea){
		
		LineaPedido lp= lineaPedidoService.getById(idLinea);
		if(lp==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			try {
				pedidoService.borraLinea(lp);				
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}
				return ResponseEntity.noContent().build();
			
		
		}
	}
	
	
	
	
     //                              Productos                                     //
	//----------------------------------------------------------------------------//
	
	
	
	
	
	/**
	 * Busca todos los productos guardados
	 * @return Lista de productos
	 */
	@GetMapping("/productos")
	public ResponseEntity<List<Producto>> findProductos(){
		
		List<Producto> result= productoService.findAll();
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(result);
		}
	}
	
	
	
	
	/**
	 * Busca un producto por id que se le pasa en la uri, si no encuentra el producto devuevle 
	 * not found
	 * @param id int
	 * @return Producto buscado
	 */
	@GetMapping("/productos/{id}")
	public ResponseEntity<Producto> productoById(@PathVariable int id) {
		
		Producto p =productoService.getById(id);
		if(p==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(p);
		}
		

	}
	
//	@PostMapping("/productos")
//	public ResponseEntity<Producto> addProducto(@RequestBody Producto p) {
//		
//		int resp = productoService.guardaProducto(p);
//		if(resp==-1) {
//			return ResponseEntity.badRequest().build();
//		}
//		else {
//			return ResponseEntity.ok(p);
//		}
//	}
	
	
	
	

}

