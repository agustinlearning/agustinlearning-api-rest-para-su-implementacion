package med.voll.api.domain.Consulta;

import java.time.LocalDateTime;

public record DatosDetallesConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime fecha) {
}
