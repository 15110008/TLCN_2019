package com.travel.controller;

import com.travel.api.v1.assembler.TourResourceAssembler;
import com.travel.api.v1.dto.TourDto;
import com.travel.api.v1.resource.TourResource;
import com.travel.service.TourService;
import com.travel.service.impl.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.AbstractMap;

@RestController
@RequestMapping(value = "/api/v1/tours", produces = {"application/hal+json"})
public class TourController {

    private final TourService tourService;

    private final FileStorageService fileStorageService;

    private final TourResourceAssembler tourResourceAssembler;

    @Autowired
    public TourController(TourService tourService, FileStorageService fileStorageService, TourResourceAssembler tourResourceAssembler) {
        this.tourService = tourService;
        this.fileStorageService = fileStorageService;
        this.tourResourceAssembler = tourResourceAssembler;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedResources<TourResource> getAllTours(@PageableDefault(page = 0, size = 10) Pageable pageable,
                                                PagedResourcesAssembler<TourDto> assembler) {
        Page<TourDto> tourList = tourService.getAllTours(pageable);
        return assembler.toResource(tourList, tourResourceAssembler);
    }

    @GetMapping(value = "/{id}")
    public TourResource getTour(@PathVariable long id) {
        TourDto tourDto = tourService.getTourById(id).get();
        return new TourResource(tourDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TourResource createTour(@RequestBody TourDto tourDto) throws Exception {
        TourDto insertedTour = tourService.addTour(tourDto).get();
        return new TourResource(insertedTour);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TourResource updateTour(@PathVariable long id, @RequestBody TourDto tourDto) {
        TourDto updatedTour = tourService.updateTour(id, tourDto).get();
        return new TourResource(updatedTour);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TourDto deleteTour(@PathVariable long id) {
        TourDto deletedTour = tourService.deleteTour(id).get();
        return deletedTour;
    }

    @PostMapping("/file")
    public AbstractMap.SimpleEntry<String, ?> uploadFile(@RequestParam("data") MultipartFile file) throws Exception {
        String fileName = fileStorageService.storeFile(file);
        return new AbstractMap.SimpleEntry<>("link", String.format("%s%s", "/data/contents/", fileName));
    }

}
