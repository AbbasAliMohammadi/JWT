package af.gov.AuthService.security.DTO;

import java.util.Set;

import jakarta.validation.constraints.NotNull;

public class MapRoleDTO {
    @NotNull
    private Long userId;
    @NotNull
    private Set<Long> roleIds;
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Set<Long> getRoleIds() {
        return roleIds;
    }
    public void setRoleIds(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }
    

}
