package snsProject.photogram.config.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import snsProject.photogram.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Setter
@Getter
public class PrincipalDetails implements UserDetails, OAuth2User {

    private User user;
    private Map<String, Object> attributes;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    public PrincipalDetails(User user, Map<String, Object> attributes) {
        this.user = user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes; // {id:232223232, name: 구코딩, email:ssar@nate.com}
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    // 권한 : 한개가 아닐 수 있음
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> {
            return user.getRole();
        });
        return collector;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override // 계정이 만료 되었나요?
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // ex) 10년 로그인 안할 시, 계정 비활성화
    public boolean isEnabled() {
        return true;
    }


}
