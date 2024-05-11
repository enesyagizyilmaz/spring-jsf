package org.xtremebiker.jsfspring;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.ServletContextAware;
import org.xtremebiker.jsfspring.model.Car;
import org.xtremebiker.jsfspring.repository.CarRepository;
import org.xtremebiker.jsfspring.view.ViewScope;

import com.google.common.collect.ImmutableMap;

import java.util.Random;

@SpringBootApplication
public class JsfSpringBootApplication implements ServletContextAware
{

	public static void main(String[] args) {
		SpringApplication.run(JsfSpringBootApplication.class, args);
	}

	@Autowired
	private CarRepository carRepository;

	@Bean
	public CommandLineRunner demoData(CarRepository repository) {
		return args -> {
			Random random = new Random();
			String[] brands = {"audi", "mercedes", "toyota"};
			String[] colors = {"blue", "white", "green"};
			for (int i = 0; i < 10; i++) {
				String brand = brands[random.nextInt(brands.length)];
				String color = colors[random.nextInt(colors.length)];
				int year = 2010 + random.nextInt(5);
				Car car = new Car(0, brand, year, color);
				repository.save(car);
			}
		};
	}

	@Bean
	public static CustomScopeConfigurer viewScope()
	{
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.setScopes(new ImmutableMap.Builder<String, Object>().put("view", new ViewScope()).build());
		return configurer;
	}

	@Bean
	public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
		ServletRegistrationBean<FacesServlet> servletRegistrationBean =
				new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}

	@Override
	public void setServletContext(ServletContext servletContext)
	{
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
		servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
	}

}
