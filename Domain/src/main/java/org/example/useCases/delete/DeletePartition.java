package org.example.useCases.delete;

import com.google.gson.Gson;
import org.example.contract.repository.DeletedRepo;
import org.example.contract.repository.PartitionRepo;
import org.example.contract.response.PartitionResponse;
import org.example.mappers.PartitionDomainMapper;
import org.example.model.Deleted;
import org.example.model.Partition;
import org.example.typeAdapter.AppGson;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class DeletePartition {
    private final PartitionRepo partitionRepo;
    private final PartitionDomainMapper mapper;
    private final DeletedRepo deletedRepo;
    public DeletePartition(PartitionRepo partitionRepo, PartitionDomainMapper mapper, DeletedRepo deletedRepo) {
        this.partitionRepo = partitionRepo;
        this.mapper = mapper;
        this.deletedRepo = deletedRepo;
    }
    public PartitionResponse execute(Long id){
        final Partition partition = partitionRepo.findById(id).orElseThrow(NoSuchElementException::new);
        final Deleted deleted = new Deleted();
        final Gson gson = AppGson.getGson();
        final String json = gson.toJson(partition);
        deleted.setJsonValue(json);
        deleted.setCreatedAt(LocalDateTime.now());
        deleted.setClassName(Partition.class.getName());
        deletedRepo.save(deleted);
        partitionRepo.delete(partition);
        return mapper.domainToResponse(partition);
    }
}
