package de.united.aztube.backend.rest.api;

import de.united.aztube.backend.model.request.*;
import de.united.aztube.backend.model.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@Tag(name = "ExtensionAPI", description = "Endpoints for interaction with the browser extension")
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "401", description = "Unauthorized",
                content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema())),
        @ApiResponse(
                responseCode = "404", description = "Not found",
                content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema())),
        @ApiResponse(
                responseCode = "400", description = "Invalid data provided",
                content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema())),
        @ApiResponse(
                responseCode = "500", description = "Internal server error",
                content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema()))
})
@RequestMapping("extension")
public interface ExtensionAPI {

    @GetMapping(value = "/generate",
            consumes = "application/aztube.v2+json", produces = "application/aztube.v2+json")
    @Operation(summary = "Generate a extension token")
    @ApiResponse(responseCode = "200", description = "Token generated",
            content = @Content(schema = @Schema(implementation = GenerateResponse.class)))
    GenerateResponse generate();

    @PostMapping(value = "/status",
            consumes = "application/aztube.v2+json", produces = "application/aztube.v2+json")
    @Operation(summary = "Check if a browser token is valid", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = BrowserTokenRequest.class))))
    @ApiResponse(responseCode = "200", description = "Token status",
            content = @Content(schema = @Schema(implementation = BrowserTokenResponse.class)))
    BrowserTokenResponse status(@RequestBody BrowserTokenRequest request);
    
}
