package med.voll.api.controller;

import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.direccion.Direccion;
import med.voll.api.domain.medico.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosRegistroMedico> datosRegistroMedicoJson;

    @Autowired
    private JacksonTester<DatosDetallesMedico> datosDetalleMedicoJson;

    @MockBean
    private MedicoRepository repository;

    @Test
    @DisplayName("Debería devolver código http 400 cuando las informaciones son inválidas")
    @WithMockUser
    void registrar_escenario1() throws Exception {
        var response = mvc
                .perform(post("/medicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

//    @Test
//    @DisplayName("Debería devolver código http 200 cuando las informaciones son válidas")
//    @WithMockUser
//    void registrar_escenario2() throws Exception {
//        var datosRegistro = new DatosRegistroMedico(
//                "Medico",
//                "medico@voll.med",
//                "61999999999",
//                "123456",
//                Especialidad.CARDIOLOGIA,
//                datosDireccion());
//
//        when(repository.save(any())).thenReturn(new Medico(datosRegistro));
//
//        var response = mvc
//                .perform(post("/medicos")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(datosRegistroMedicoJson.write(datosRegistro).getJson()))
//                .andReturn().getResponse();
//
//        var datosDetalle= new DatosDetallesMedico(
//                null,
//                datosRegistro.nombre(),
//                datosRegistro.email(),
//                datosRegistro.telefono(),
//                datosRegistro.documento(),
//                datosRegistro.especialidad(),
//                new Direccion(datosRegistro.direccion())
//        );
//        var jsonEsperado = datosDetalleMedicoJson.write(datosDetalle).getJson();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
//        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
//
//
//    }

    // solucion mas simple encontrada en el for de alura

    @Test
    @DisplayName("Debería devolver código http 200 cuando las informaciones son válidas")
    @WithMockUser
    void registrar_escenario2() throws Exception {
        DatosRegistroMedico datosRegistroMedico = new DatosRegistroMedico(
                "Medico1",
                "Medico@Vollmed.com",
                "12345665",
                "documento",
                Especialidad.CARDIOLOGIA,
                datosDireccion()
        );


        when(repository.save(any())).thenReturn(new Medico(datosRegistroMedico));
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion(
                "calle a",
                "6",
                "complemento",
                "barrio6",
                "4211",
                "Santo Domingo",
                "Mendez"
        );
    }


}