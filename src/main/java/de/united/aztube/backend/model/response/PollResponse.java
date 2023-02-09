package de.united.aztube.backend.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.united.aztube.backend.database.entity.Download;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PollResponse {

    boolean success;
    private String error;
    private List<Download> downloads;

}
