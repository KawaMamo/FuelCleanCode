package org.example.useCases.delete;

import com.google.gson.Gson;
import org.example.contract.repository.DeletedRepo;
import org.example.contract.repository.TransRepo;
import org.example.contract.response.TransResponse;
import org.example.mappers.DomainTransMapper;
import org.example.model.Deleted;
import org.example.model.Transportation;
import org.example.typeAdapter.AppGson;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class DeleteTrans {
    private final TransRepo transRepo;
    private final DomainTransMapper mapper;
    private final DeletedRepo deletedRepo;

    public DeleteTrans(TransRepo transRepo, DomainTransMapper mapper, DeletedRepo deletedRepo) {
        this.transRepo = transRepo;
        this.mapper = mapper;
        this.deletedRepo = deletedRepo;
    }

    public TransResponse execute(Long id){
        final Transportation transportation = transRepo.findById(id).orElseThrow(NoSuchElementException::new);
        Deleted deleted = new Deleted();
        final Gson gson = AppGson.getGson();
        final String json = gson.toJson(transportation);
        deleted.setClassName(Transportation.class.getName());
        deleted.setJsonValue(json);
        deleted.setCreatedAt(LocalDateTime.now());
        deletedRepo.save(deleted);
        transRepo.delete(transportation);
        return mapper.toResponse(transportation);
    }
}
