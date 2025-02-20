
package br.com.desafio.magalu.executavel;

import br.com.desafio.magalu.ApiNotificacaoAgendamentoApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApiNotificacaoAgendamentoApplication.class);
	}

}

