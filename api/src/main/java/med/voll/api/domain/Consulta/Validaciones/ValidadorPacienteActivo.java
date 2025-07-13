package med.voll.api.domain.Consulta.Validaciones;

import med.voll.api.domain.Consulta.DatosReservarConsulta;
import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteActivo implements ValidadorDeConsultas{

    @Autowired
    private PacienteRepository repository;

    public void validar(DatosReservarConsulta datos){

        var pacienteEstaActivo = repository.findActivoById(datos.idPaciente());

        if (!pacienteEstaActivo) {
            throw new ValidacionException("Consulta no puede ser reservada con paciente inactivo");
        }
    }
}
