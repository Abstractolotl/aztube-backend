package de.united.aztube.backend.rest.api;

import de.united.aztube.backend.model.request.PollRequest;
import de.united.aztube.backend.model.request.RegisterRequest;
import de.united.aztube.backend.model.request.StatusRequest;
import de.united.aztube.backend.model.response.GeneralResponse;
import de.united.aztube.backend.model.response.GenerateResponse;
import de.united.aztube.backend.model.response.RegisterResponse;
import de.united.aztube.backend.model.response.StatusResponse;
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
@Tag(name = "DeviceAPI", description = "Endpoints for Device Management")
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
@RequestMapping("devices")
public interface DeviceAPI {

    @PostMapping(value = "/register",
            consumes = "application/aztube.v2+json", produces = "application/aztube.v2+json")
    @Operation(summary = "Register a new device", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = RegisterRequest.class))))
    @ApiResponse(responseCode = "200", description = "Device registered",
            content = @Content(schema = @Schema(implementation = RegisterResponse.class)))
    RegisterResponse register(@RequestBody RegisterRequest request);

    @DeleteMapping(value = "/unregister",
            consumes = "application/aztube.v2+json", produces = "application/aztube.v2+json")
    @Operation(summary = "Delete a device", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = PollRequest.class))))
    @ApiResponse(responseCode = "200", description = "Device deleted",
            content = @Content(schema = @Schema(implementation = GeneralResponse.class)))
    GeneralResponse unregister(@RequestBody PollRequest request);

    @PostMapping(value = "/status",
            consumes = "application/aztube.v2+json", produces = "application/aztube.v2+json")
    @Operation(summary = "Get the status of a device", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = StatusRequest.class))))
    @ApiResponse(responseCode = "200", description = "Device status",
            content = @Content(schema = @Schema(implementation = StatusResponse.class)))
    StatusResponse status(@RequestBody StatusRequest request);

}
