package xyz.amazingxu;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class WxblogApplication {

//	@Value("${server.port}")
//	Integer port;

	@Autowired
	private RestTemplateBuilder builder;
	@Bean
	public RestTemplate restTemplate() {
		return builder.build();
	}
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return (container -> {
			ErrorPage FORBIDDEN = new ErrorPage(HttpStatus.FORBIDDEN, "/index.html");
			ErrorPage NOT_FOUND = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
			container.addErrorPages(FORBIDDEN,NOT_FOUND);
		});
	}
	/**
	 * multiple-domain mechanism
	 * @return
	 */
	@Bean
	public FilterRegistrationBean corsFilter(){
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**",config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

//	@Bean
//	public Connector httpConnector() {
//		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//		connector.setScheme("http");
//		// Connector监听的http的端口号
//		connector.setPort(8080);
//		connector.setSecure(false);
//		// 监听到http的端口号后转向到的https的端口号
//		connector.setRedirectPort(port);
//		return connector;
//	}

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(WxblogApplication.class, args);
	}
}
