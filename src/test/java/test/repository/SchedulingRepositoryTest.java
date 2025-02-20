package test.repository;

import br.com.desafio.magalu.ApiNotificacaoAgendamentoApplication;
import br.com.desafio.magalu.mapper.SchedulingMapper;
import br.com.desafio.magalu.model.Scheduling;
import br.com.desafio.magalu.repository.SchedulingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import test.mockscheduling.MockScheduling;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/////-- Loading all context in application (Services, Controllers, etc.)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {
        ApiNotificacaoAgendamentoApplication.class}
)
// -- Forcer in using archive H2 Test
@TestPropertySource(locations = "classpath:application-test.properties")
//-- Not found Configure DataBase Production
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ANNOTATED) // Habilita a injeção via cons
// -- Test Using Junit 5
@ExtendWith(SpringExtension.class)
//@Transactional
//@AutoConfigureTestEntityManager
/*@EnableJpaRepositories(basePackages = {
        "br.com.desafio.magalu.repository"
})*/
//@EnableTransactionManagement
public class SchedulingRepositoryTest {

    @Autowired
    private SchedulingRepository schedulingRepository;

    @Autowired
    private SchedulingMapper schedulingMapper;

    /*@Autowired
    private EntityManager testEntityManager;*/

    private MockScheduling mockScheduling;

    @BeforeEach
    public void inicializarMock() {
        this.mockScheduling = new MockScheduling();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void returnSchedulingStatus() {

        List<Scheduling> schedulingDTOList = schedulingRepository.returnSchedulingStatus("Enviado");

        Assertions.assertNotNull(schedulingDTOList.size() > 0);
        Assertions.assertTrue(!schedulingDTOList.isEmpty());
        assertThat(!schedulingDTOList.isEmpty()).isTrue();
    }

    @Test
    void testReturnSchedulingStatus() {
        //--- Simplismente verifica se existe um devido agendamento para o Horário;
        String valueDateTime = "20/02/2025 15:48:15";
        DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        LocalDateTime localDateTime = LocalDateTime.parse(valueDateTime, simpleDateFormat);

        Boolean valueStatus = schedulingRepository.returnSchedulingStatus(localDateTime);

        assertThat(valueStatus).isTrue();
    }

    @Test
    void returnSchedulingEmail() {

        String nomeDestinatario = "tereza.luiza@com.br";

        List<Scheduling> schedulingDTOList = schedulingRepository.returnSchedulingEmail(nomeDestinatario);

        Assertions.assertNotNull(schedulingDTOList.size() > 0);
        Assertions.assertTrue(!schedulingDTOList.isEmpty());
        assertThat(!schedulingDTOList.isEmpty()).isTrue();
    }

    @Test
    void createDScheduling() {
        //--- Premier Scheduling
        String nomeDestinatario = "tereza.luiza@com.br";

        var valueExistent = mockScheduling.createScheduling(nomeDestinatario);

        var newValueExistentScheduling = schedulingMapper.schedulingDTOForScheduling(valueExistent);

        this.schedulingRepository.save(newValueExistentScheduling);
        this.schedulingRepository.flush();

        //---- Second Scheduling

        nomeDestinatario = "maria.luiza@com.br";

        valueExistent = mockScheduling.createScheduling(nomeDestinatario);

        newValueExistentScheduling = schedulingMapper.schedulingDTOForScheduling(valueExistent);

        this.schedulingRepository.save(newValueExistentScheduling);
        this.schedulingRepository.flush();

    }

}