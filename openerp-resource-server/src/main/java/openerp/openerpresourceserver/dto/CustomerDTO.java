package openerp.openerpresourceserver.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CustomerDTO {
    private UUID id;
    private String name;
    private String email;
    private String phoneNumber;
    private String avatarUrl;
    private String bod;
    private boolean isOwner;
    private String status;
    private String password;
}
