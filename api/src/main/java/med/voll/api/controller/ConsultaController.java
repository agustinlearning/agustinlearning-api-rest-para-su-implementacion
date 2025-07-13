package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.Consulta.DatosCancelamientoConsulta;
import med.voll.api.domain.Consulta.DatosDetallesConsulta;
import med.voll.api.domain.Consulta.DatosReservarConsulta;
import med.voll.api.domain.Consulta.ReservaDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private ReservaDeConsultas reserva;

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid DatosReservarConsulta datos){


        var detalleConsulta = reserva.reservar(datos);

        return ResponseEntity.ok(detalleConsulta);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelamientoConsulta datos) {
        reserva.cancelar(datos);
        return ResponseEntity.noContent().build();
    }
}
