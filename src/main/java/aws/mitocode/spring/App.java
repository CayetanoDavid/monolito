package aws.mitocode.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.sns.AmazonSNSClient;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
@EnableWebMvc
public class App extends WebMvcConfigurerAdapter{

	@Value("${aws_Region_Cognito}")
	private String regionAWS;


	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	public AmazonSimpleEmailService crearSES() {
		return  new AmazonSimpleEmailServiceClient( new DefaultAWSCredentialsProviderChain() );
	}
	
	@Bean
	public AmazonSNSClient crearSNS() {
		return new AmazonSNSClient(new DefaultAWSCredentialsProviderChain());
	}

	@Bean
	public AWSCognitoIdentityProviderClient CognitoClient() {        
		AWSCognitoIdentityProviderClient prov = new AWSCognitoIdentityProviderClient(new DefaultAWSCredentialsProviderChain());
		prov.withRegion(Regions.fromName(regionAWS));
		return prov;
	}
	/*
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	 	registry.addMapping("/**").allowedOrigins("*");
	}
	*/
}
