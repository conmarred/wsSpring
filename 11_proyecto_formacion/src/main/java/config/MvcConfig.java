package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"controller"})
//Los metodos de la interfaz WebMvcConfigurer no son estáticos, sino default, por eso no me obliga a implementarlos
public class MvcConfig implements WebMvcConfigurer{
	
	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	//navegaciones estáticas
	//sobreescribimos el método addViewControllers para que al ejecutar por defecto me lleve a inicio
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("login");
	}

	//registro de los recursos estaticos (imagenes, videos, hojas de estilo,...)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/*").addResourceLocations("/");
		/*registry.addResourceHandler("/*.gif").addResourceLocations("/imagenes");
		registry.addResourceHandler("/*.css").addResourceLocations("/estilos");*/
	}

	
	
}
