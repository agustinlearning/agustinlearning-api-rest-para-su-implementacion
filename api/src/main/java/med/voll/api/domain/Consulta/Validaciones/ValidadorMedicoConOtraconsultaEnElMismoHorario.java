package med.voll.api.domain.Consulta.Validaciones;

import med.voll.api.domain.Consulta.ConsultaRepository;
import med.voll.api.domain.Consulta.DatosReservarConsulta;
import med.voll.api.domain.ValidacionException;

public class ValidadorMedicoConOtraconsultaEnElMismoHorario {
    private ConsultaRepository repository;

    private void validar(DatosReservarConsulta datos){

        var medicoTieneOtraConsultaEnElMismoHorario = repository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());

        if (medicoTieneOtraConsultaEnElMismoHorario) {
            throw new ValidacionException("El Medico Ya tiene una consulta en esta fecha y hora");
        }
    }
}
