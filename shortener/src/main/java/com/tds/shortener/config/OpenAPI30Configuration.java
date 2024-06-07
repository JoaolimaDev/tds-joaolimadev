package com.tds.shortener.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration

@OpenAPIDefinition(
  info =@Info(
    title = "desafio-de-back-end-encurtador-de-url",
    version = "1.3",
    contact = @Contact(
      name = "João vitor de lima", email = "joaolimaprofissional@hotmail.com", url = "https://github.com/JoaolimaDev"
    ),
    description = "Projeto Spring Boot, desafio tds company."
  ),
  servers = @Server(
    url = "http://localhost:5000",
    description = "Produção"
  )
)
public class OpenAPI30Configuration {}