package openerp.openerpresourceserver.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BuildingDTO {
    private UUID id;
    private String name;
    private Integer floors;
    private String location;
}
