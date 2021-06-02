package zw.co.malvern.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import zw.co.malvern.business.RegistrationService;
import zw.co.malvern.business.RegistrationServiceImpl;
import zw.co.malvern.domain.DomainMarkerInterface;
import zw.co.malvern.repository.AccountRepository;
import zw.co.malvern.repository.RepositoryMarkerInterface;

@Configuration
@EnableJpaRepositories(basePackageClasses = RepositoryMarkerInterface.class)
@EntityScan(basePackageClasses = DomainMarkerInterface.class)
public class Configurations {
    @Bean
    public RegistrationService registrationService(final AccountRepository accountRepository){
        return new RegistrationServiceImpl(accountRepository);
    }
}
