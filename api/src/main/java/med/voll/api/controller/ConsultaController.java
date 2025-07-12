package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.Consulta.DatosDetallesConsulta;
import med.voll.api.domain.Consulta.DatosReservarConsulta;
import med.voll.api.domain.Consulta.ReservaDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private ReservaDeConsultas reserva;

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid DatosReservarConsulta datos){
        reserva.reservar(datos);

        System.out.println(datos);
        return ResponseEntity.ok(new DatosDetallesConsulta(null, null, null, null));
    }
}
