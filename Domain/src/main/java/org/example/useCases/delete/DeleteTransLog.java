package org.example.useCases.delete;

import com.google.gson.Gson;
import org.example.contract.repository.DeletedRepo;
import org.example.contract.repository.TransLogRepo;
import org.example.contract.response.TransLogResponse;
import org.example.mappers.TransLogDomainMapper;
import org.example.model.Deleted;
import org.example.model.TransLog;
import org.example.typeAdapter.AppGson;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class DeleteTransLog {
    private final TransLogDomainMapper mapper;
    private final TransLogRepo transLogRepo;
    private final DeletedRepo deletedRepo;

    public DeleteTransLog(TransLogDomainMapper mapper, TransLogRepo transLogRepo, DeletedRepo deletedRepo) {
        this.mapper = mapper;
        this.transLogRepo = transLogRepo;
        this.deletedRepo = deletedRepo;
    }

    public TransLogResponse execute(Long id){
        TransLog transLog = transLogRepo.findById(id).orElseThrow(NoSuchElementException::new);
        Deleted deleted = new Deleted();
        final Gson gson = AppGson.getGson();
        final String json = gson.toJson(transLog);
        deleted.setClassName(TransLog.class.getName());
        deleted.setJsonValue(json);
        deleted.setCreatedAt(LocalDateTime.now());
        deletedRepo.save(deleted);
        transLogRepo.delete(transLog);
        return mapper.domainToResponse(transLog);
    }
}
