// package com.organizacion.componentes.back.controller;

// import java.util.List;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.organizacion.componentes.back.model.Ejercicio;
// import com.organizacion.componentes.back.service.EjercicioService;

// import org.springframework.http.HttpStatus;




// @RestController
// @RequestMapping("/ejercicios")
// public class EjercicioController {

//     @Autowired
//     private EjercicioService ejercicioService;

//     // Obtener todos los ejercicios
//     @GetMapping
//     public ResponseEntity<List<Ejercicio>> getAllEjercicios() {
//         List<Ejercicio> ejercicios = ejercicioService.getAllEjercicios();
//         return new ResponseEntity<>(ejercicios, HttpStatus.OK);
//     }

//     // Obtener un ejercicio por su ID
//     @GetMapping("/{id}")
//     public ResponseEntity<Ejercicio> getEjercicioById(@PathVariable Long id) {
//         Ejercicio ejercicio = ejercicioService.getEjercicioById(id);
//         return new ResponseEntity<>(ejercicio, HttpStatus.OK);
//     }

//     // Crear un nuevo ejercicio
//     @PostMapping
//     public ResponseEntity<Ejercicio> createEjercicio(@RequestBody Ejercicio ejercicio) {
//         Ejercicio nuevoEjercicio = ejercicioService.createEjercicio(ejercicio);
//         return new ResponseEntity<>(nuevoEjercicio, HttpStatus.CREATED);
//     }

//     // Actualizar un ejercicio existente
//     @PutMapping("/{id}")
//     public ResponseEntity<Ejercicio> updateEjercicio(
//             @PathVariable Long id, @RequestBody Ejercicio ejercicioActualizado) {
//         Ejercicio ejercicio = ejercicioService.updateEjercicio(id, ejercicioActualizado);
//         return new ResponseEntity<>(ejercicio, HttpStatus.OK);
//     }

//     // Eliminar un ejercicio por su ID
//     @DeleteMapping("/{id}")
//     public ResponseEntity<String> deleteEjercicio(@PathVariable Long id) {
//         ejercicioService.deleteEjercicio(id);
//         return new ResponseEntity<>("Ejercicio eliminado con éxito", HttpStatus.OK);
//     }
// }
