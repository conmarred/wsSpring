package init;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth
        .inMemoryAuthentication()
        .withUser("test1")
          .password("{noop}test1") //lo de {noop} se pone para no obligar a usar mecanismo de encriptación
          .roles("TEST")
          .and()
        .withUser("test2")
          .password("{noop}test2") //lo de {noop} se pone para no obligar a usar mecanismo de encriptación
          .roles("TEST")
          .and()
        .withUser("test3")
          .password("{noop}test3")
          .roles("MASTER")
          .and()
        .withUser("test4")
          .password("{noop}test4")
          .roles("USERS");
         */
		
		/*lo siguiente sería para el caso de que
		 * quisiéramos encriptar la password:
		String pw1=new BCryptPasswordEncoder().encode("user1");
		System.out.println(pw1);
		  auth
	        .inMemoryAuthentication()
	        .withUser("user1")
	          .password("{bcrypt}"+pw1)
	          //.password(pw1)
	          .roles("USER")
	          .and()
	        .withUser("admin")
	          .password("{bcrypt}"+new BCryptPasswordEncoder().encode("admin"))
	          .roles("USER", "ADMIN");
		 */
		/*la siguiente configuración será para el caso de 
		 * usuarios en una base de datos*/
		  auth.jdbcAuthentication().dataSource(dataSource())
        	.usersByUsernameQuery("select user, pwd, enabled"
            	+ " from users where user=?")
        	.authoritiesByUsernameQuery("select user, rol "
            	+ "from roles where user=?");
		 
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		//solo los miembros del rol admin podrán realizar altas
		//y para eliminar, tendrán que estar autenticados
		.antMatchers(HttpMethod.GET,"/Reservas").authenticated()
		.and()
		.httpBasic();
	}
	
	
	private DataSource dataSource() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/springsecurity?serverTimezone=UTC");
		ds.setUsername("root");
		ds.setPassword("");
		return ds;
	}

}
