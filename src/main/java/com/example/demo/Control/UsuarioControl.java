package com.example.demo.Control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.Modelo.Usuario;

//@CrossOrigin(origins = "http://localhost:8081")
//@RestController
@Controller
@RequestMapping("/api")
public class UsuarioControl {

  @Autowired
  UsuarioRepositorio tutorialRepository;

  @GetMapping("/tutorials")
  public ResponseEntity<List<Usuario>> getAllTutorials(@RequestParam(required = false) String nombre) {
    try {
      List<Usuario> tutorials = new ArrayList<Usuario>();

      if (nombre == null)
        tutorialRepository.findAll().forEach(tutorials::add);
      else
        //tutorialRepository.findByTitleContaining(nombre).forEach(tutorials::add);

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/tutorials/{id}")
  public ResponseEntity<Usuario> getTutorialById(@PathVariable("id") long id) {
    Optional<Usuario> tutorialData = tutorialRepository.findById(id);

    if (tutorialData.isPresent()) {
      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/")
	public String listStudents() {
		return "index";
	}

  @PostMapping("/save")
	public String createStudentForm(Usuario usuario) {
    tutorialRepository.save(usuario);
		return "redirect:/";
		
	}

  @PostMapping("/tutorials")
  public ResponseEntity<Usuario> createTutorial(@RequestBody Usuario usuario) {
    try {
        Usuario _tutorial = tutorialRepository
          .save(new Usuario(usuario.getNombre()));
      return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/tutorials/{id}")
  public ResponseEntity<Usuario> updateTutorial(@PathVariable("id") long id, @RequestBody Usuario tutorial) {
    Optional<Usuario> tutorialData = tutorialRepository.findById(id);

    if (tutorialData.isPresent()) {
        Usuario _tutorial = tutorialData.get();
      _tutorial.setNombre(tutorial.getNombre());
      return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/tutorials/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
    try {
      tutorialRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/tutorials")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
    try {
      tutorialRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  /*@GetMapping("/tutorials/published")
  public ResponseEntity<List<Usuario>> findByPublished() {
    try {
      List<Usuario> tutorials = tutorialRepository.findByPublished(true);

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }*/

}

