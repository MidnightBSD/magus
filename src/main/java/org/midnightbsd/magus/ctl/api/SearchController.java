package org.midnightbsd.magus.ctl.api;

import org.midnightbsd.magus.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucas Holt
 */
@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(final SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public Page<org.midnightbsd.magus.model.search.MagusItem> find(@RequestParam("term") String term, Pageable page) {
        return searchService.find(term, page);
    }
}
