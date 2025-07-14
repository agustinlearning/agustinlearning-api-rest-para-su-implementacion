package med.voll.api.domain.Consulta;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Table(name = "Consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicos_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pacientes_id")
    private Paciente paciente;

    private LocalDateTime fecha;

//    @Column(name = "motivo_cancelamiento")
//    @Enumerated(EnumType.STRING)
//    private MotivoCancelamiento motivoCancelamiento;
//
//    public void cancelar(MotivoCancelamiento motivo) {
//        this.motivoCancelamiento = motivo;
//    }
}
