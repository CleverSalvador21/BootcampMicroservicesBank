package com.nttdata.client.repository;

import com.nttdata.client.document.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CLientRepository extends ReactiveMongoRepository<Client,String> {
}
