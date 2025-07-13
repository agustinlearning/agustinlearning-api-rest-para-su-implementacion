package med.voll.api.domain.Consulta.Validaciones;

import med.voll.api.domain.Consulta.DatosReservarConsulta;
import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.paciente.PacienteRepository;

public class ValidadorPacienteActivo {
    private PacienteRepository repository;
    public void validar(DatosReservarConsulta datos){
        var pacienteEstaActivo = repository.findActivoById(datos.idPaciente());
        if (!pacienteEstaActivo) {
            throw new ValidacionException("Consulta no puede ser reservada con paciente inactivo");
        }
    }
}
