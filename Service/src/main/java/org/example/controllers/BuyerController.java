package org.example.controllers;

import org.example.contract.request.create.CreateBuyerRequest;
import org.example.contract.request.update.UpdateBuyerRequest;
import org.example.contract.response.BuyerResponse;
import org.example.entities.Buyer;
import org.example.mappers.BuyerMapper;
import org.example.repositories.BuyerRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateBuyer;
import org.example.useCases.delete.DeleteBuyer;
import org.example.useCases.update.UpdateBuyer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/buyer")
public class BuyerController {

    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final CreateBuyer createBuyer;
    private final UpdateBuyer updateBuyer;
    private final DeleteBuyer deleteBuyer;
    private final BuyerMapper buyerMapper;
    private final BuyerRepository buyerRepository;

    public BuyerController(PagedResourcesAssembler pagedResourcesAssembler,
                           CreateBuyer createBuyer,
                           UpdateBuyer updateBuyer,
                           DeleteBuyer deleteBuyer,
                           BuyerMapper buyerMapper, BuyerRepository buyerRepository) {
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.createBuyer = createBuyer;
        this.updateBuyer = updateBuyer;
        this.deleteBuyer = deleteBuyer;
        this.buyerMapper = buyerMapper;
        this.buyerRepository = buyerRepository;
    }

    @PostMapping
    public BuyerResponse createBuyer(@RequestBody CreateBuyerRequest request){
        return createBuyer.createBuyer(request);
    }

    @PatchMapping
    public ResponseEntity<BuyerResponse> updateBuyer(@RequestBody UpdateBuyerRequest request){
        return ResponseEntity.ok(updateBuyer.updateBuyer(request));
    }

    @DeleteMapping("/{id}")
    public BuyerResponse deleteBuyer(@PathVariable Long id){return deleteBuyer.delete(id);}

    @GetMapping
    PagedModel<BuyerResponse> getBuyers(SearchFilter filter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<Buyer> specifications = new FilterSpecifications<>(criteriaList);
        final Page<org.example.model.Buyer> page = buyerRepository.findAll(specifications, pageable).map(buyerMapper::entityToDomain);
        return pagedResourcesAssembler.toModel(page);
    }

    @GetMapping("/all")
    public List<org.example.model.Buyer> listAllBuyers(){
        return buyerRepository.findAll().stream().map(buyerMapper::entityToDomain).toList();
    }
}
