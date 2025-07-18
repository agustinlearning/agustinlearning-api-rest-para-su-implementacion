package med.voll.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    MedicoRepository medicoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroMedico datos, UriComponentsBuilder uriComponentsBuilder){
        var medico = new Medico(datos);
        medicoRepository.save(medico);

        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetallesMedico(medico));
    }
    @GetMapping
    public ResponseEntity<Page<DatosListaMedicos>> listar(@PageableDefault(size=10, sort={"nombre"}) Pageable paginacion){
        var page = medicoRepository.findAllByActivoTrue(paginacion).map(DatosListaMedicos::new);

        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionMedico datos){
        var medico = medicoRepository.getReferenceById(datos.id());
        medico.actualizarInformaciones(datos);

        return ResponseEntity.ok(new DatosDetallesMedico(medico));
    }



    // para hacer una eliminacion fisica del medico
//    @Transactional
//    @DeleteMapping("/{id}")
//    public void eliminar(@PathVariable Long id){
//        repository.deleteById(id);
//    }

    //para hacer eliminacion logica del medico
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.elimiar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DatosDetallesMedico(medico));
    }
}
