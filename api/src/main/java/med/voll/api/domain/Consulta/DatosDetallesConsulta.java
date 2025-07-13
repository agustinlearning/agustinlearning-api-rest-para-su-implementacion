package med.voll.api.domain.Consulta;

import java.time.LocalDateTime;

public record DatosDetallesConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime fecha) {
    public DatosDetallesConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getFecha());
    }
}
