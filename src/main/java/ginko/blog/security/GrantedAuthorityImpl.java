package ginko.blog.security;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {
    private final boolean isRole;
    private final String authority;

    public GrantedAuthorityImpl(String authority, boolean isRole) {
        this.isRole = isRole;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        if (isRole) {
            return "ROLE_" + authority.toUpperCase();
        }
        return authority.toUpperCase();
    }
}
