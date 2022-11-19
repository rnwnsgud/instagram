package snsProject.photogram.repository.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import snsProject.photogram.domain.QUser;
import snsProject.photogram.domain.User;
import snsProject.photogram.dto.user.UserListDto;

import javax.persistence.EntityManager;
import java.util.List;

import static snsProject.photogram.domain.QUser.*;

public class UserRepositoryCustomImpl implements UserRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public UserRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<UserListDto> findUserWithSearch(String username, String gender) {
        List<UserListDto> content;
        if (gender == "") {
            content = queryFactory
                    .select(Projections.constructor(UserListDto.class,
                            user.id.as("id"),
                            user.username.as("username"),
                            user.name.as("name"),
                            user.bio.as("bio"),
                            user.email.as("email"),
                            user.gender.as("gender")
                    ))
                    .from(user)
                    .where(user.username.eq(username))
                    .fetch();
        } else {
            content = queryFactory
                    .select(Projections.constructor(UserListDto.class,
                            user.id.as("id"),
                            user.username.as("username"),
                            user.name.as("name"),
                            user.bio.as("bio"),
                            user.email.as("email"),
                            user.gender.as("gender")
                    ))
                    .from(user)
                    .where(user.username.eq(username).and(user.gender.eq(gender)))
                    .fetch();
        }
        return content;

    }
}
