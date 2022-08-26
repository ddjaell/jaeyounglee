package com.example.jaeyounglee.layout.controller;

import com.example.jaeyounglee.layout.model.LayoutRequest;
import com.example.jaeyounglee.layout.model.LayoutResponse;
import com.example.jaeyounglee.layout.service.LayoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;

@RestController
@RequiredArgsConstructor
public class LayoutController {
    private final LayoutService layoutService;

    @GetMapping("/layout/{id}")
    public ResponseEntity<LayoutResponse> getLayoutDetail(@PathVariable final String id) {
        return new ResponseEntity<>(layoutService.getLayoutDetail(id), HttpStatus.OK);
    }

    @PostMapping("/layout")
    public ResponseEntity<String> createLayout(@RequestBody @Valid final LayoutRequest request) {
        layoutService.createLayout(request);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @PatchMapping("/layout")
    public ResponseEntity<String> updateLayout(@RequestBody @Valid final LayoutRequest request) {
        layoutService.updateLayout(request);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @DeleteMapping("/layout/{id}")
    public ResponseEntity<String> deleteLayout(@PathVariable final String id) {
        layoutService.deleteLayout(id);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}
