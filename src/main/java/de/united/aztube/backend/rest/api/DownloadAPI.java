package de.united.aztube.backend.rest.api;

import de.united.aztube.backend.model.request.DownloadRequest;
import de.united.aztube.backend.model.request.StatusRequest;
import de.united.aztube.backend.model.response.DownloadResponse;
import de.united.aztube.backend.model.response.PollResponse;
import de.united.aztube.backend.model.response.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@Tag(name = "DownloadAPI", description = "Endpoints for Download Management")
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
@RequestMapping("downloads")
public interface DownloadAPI {

    @PostMapping(value = "/download",
            consumes = "application/aztube.v2+json", produces = "application/aztube.v2+json")
    @Operation(summary = "Download a video", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = DownloadRequest.class))))
    @ApiResponse(responseCode = "200", description = "Download started",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = DownloadResponse.class)))
    DownloadResponse download(@RequestBody DownloadRequest request);

    @PostMapping(value = "/poll/{deviceToken}", produces = "application/aztube.v2+json")
    @Operation(summary = "Poll for new downloads")
    @ApiResponse(responseCode = "200", description = "New downloads",
            content = @Content(schema = @Schema(implementation = PollResponse.class)))
    PollResponse poll(@PathVariable String deviceToken);
}
