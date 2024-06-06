package com.tds.shortener.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tds.shortener.config.inputConfig;
import com.tds.shortener.controller.dto.createDto;
import com.tds.shortener.controller.dto.responseDto;
import com.tds.shortener.model.url;
import com.tds.shortener.service.DailyService;
import com.tds.shortener.service.urlService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@Tag(name = "URL Controller", description = "Operações relativas ao gerenciamento de URL")
public class urlController implements inputConfig {

    @Autowired
    private urlService urlService;
    
    @Autowired
    private DailyService dailyService;

    @Operation(summary = "Cadastro")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "URL cadastrado com sucesso!", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = responseDto.class))),
        @ApiResponse(responseCode = "400", description = "O input url deve conter https: e .com !", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = responseDto.class)))
    })
    @PostMapping("/urls")
    public ResponseEntity<responseDto> createUrl(@RequestBody createDto dto) {

        String urlValidation = urlValidation(dto.url());

        if (urlValidation != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new responseDto(HttpStatus.BAD_REQUEST.value(), List.of(urlValidation)));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new responseDto(HttpStatus.CREATED.value(), List.of(urlService.createUrl(dto))));

    }
    
    @Operation(summary = "Lista todas urls")
    @ApiResponse(responseCode = "200", description = "Lista todas urls", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = responseDto.class)))
    @GetMapping("/urls/all")
    public ResponseEntity<responseDto> listUrls(){

        List<url> listUrls = urlService.listUrl();
        return ResponseEntity.status(HttpStatus.OK).body(new responseDto(HttpStatus.OK.value(),listUrls));
    }

    @Operation(summary = "Lista urls por nome")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista urls por nome", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = responseDto.class))),
        @ApiResponse(responseCode = "400", description = "O input url deve conter https: e .com !", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = responseDto.class)))
    })
    @GetMapping("/urls/byname/")
    public ResponseEntity<responseDto> listByname(@RequestParam String url){

        String urlValidation = urlValidation(url);

        if (urlValidation != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new responseDto(HttpStatus.BAD_REQUEST.value(), List.of(urlValidation)));
        }

        List<url> listUrls = urlService.listByname(url);
        return ResponseEntity.status(HttpStatus.OK).body(new responseDto(HttpStatus.OK.value(),listUrls));
    }

    @Operation(summary = "Redirecionamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Redireciona para url cadastrada"),
        @ApiResponse(responseCode = "400", description = "O input url deve conter https: e .com !", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = responseDto.class)))
    })
    @GetMapping("/urls/redirect")
    public void redirect(@RequestParam String url, HttpServletResponse response) throws IOException {

        String decodedUrl = URLDecoder.decode(url, StandardCharsets.UTF_8);

        String urlValidation = urlValidation(decodedUrl);

        if (urlValidation != null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new responseDto(HttpStatus.CREATED.value(), List.of(urlValidation)));
        }
        
        urlService.redirect(url, response);
    }

    @Operation(summary = "estátistica de acesso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorno das estátistica", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = responseDto.class))),
        @ApiResponse(responseCode = "400", description = "O input url deve conter https: e .com !", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = responseDto.class)))
    })
    @GetMapping("/urls/getstats/")
    public ResponseEntity<responseDto> getAccessStats(@RequestParam String url){

        String urlValidation = urlValidation(url);

        if (urlValidation != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new responseDto(HttpStatus.BAD_REQUEST.value(), List.of(urlValidation)));
        }

        Map<String, Object> accessStats =  dailyService.getAccessStats(url);
        return ResponseEntity.status(HttpStatus.OK).body(new responseDto(HttpStatus.OK.value(), List.of(accessStats)));
    }    
}
