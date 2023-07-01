package org.example.beans;

import org.example.mappers.DomainTransMapperImpl;
import org.example.mappers.RefineryDomainMapperImpl;
import org.example.mappers.RefineryMapperImpl;
import org.example.mappers.TransMapperImpl;
import org.example.repositories.RefineryRepository;
import org.example.repositories.TransRepoJpa;
import org.example.repositories.adapters.RefineryAdapter;
import org.example.repositories.adapters.TransAdapter;
import org.example.useCases.CreateRefinery;
import org.example.useCases.CreateTrans;
import org.example.validators.CreateRefineryValidator;
import org.example.validators.CreateTransValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public CreateTrans createTrans(TransRepoJpa transRepoJpa){
        return new CreateTrans(new CreateTransValidator(),
                new DomainTransMapperImpl(),
                new TransAdapter(transRepoJpa, new TransMapperImpl()));
    }

    @Bean
    public CreateRefinery createRefinery(RefineryRepository repository){
        return new CreateRefinery(new CreateRefineryValidator(),
                new RefineryDomainMapperImpl(),
                new RefineryAdapter(repository, new RefineryMapperImpl()));
    }

}
