package de.united.aztube.backend.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerateResponse {
    boolean success = true;
    String uuid;
    long timeout = 30;

    public GenerateResponse(int timeout) {
        this.timeout = timeout;
        this.uuid = UUID.randomUUID().toString();
    }

}
