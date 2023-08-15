package org.example.useCases.delete;

import org.example.contract.repository.RegionRepo;
import org.example.contract.response.RegionResponse;
import org.example.mappers.RegionDomainMapper;
import org.example.model.Region;

import java.util.NoSuchElementException;

public class DeleteRegion {
    private final RegionRepo repo;
    private final RegionDomainMapper mapper;
    public DeleteRegion(RegionRepo repo, RegionDomainMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }
    public RegionResponse execute(Long id){
        final Region region = repo.findById(id).orElseThrow(NoSuchElementException::new);
        repo.delete(region);
        return mapper.domainToResponse(region);
    }
}
