package snsProject.photogram.repository.custom;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import snsProject.photogram.domain.Image;
import snsProject.photogram.domain.QImage;
import snsProject.photogram.domain.QSubscribe;

import javax.persistence.EntityManager;
import java.util.List;

import static snsProject.photogram.domain.QImage.*;
import static snsProject.photogram.domain.QSubscribe.*;

public class ImageRepositoryCustomImpl implements ImageRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public ImageRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<Image> getStory(Integer principalId, Pageable pageable, int page) {

        List<Image> content = queryFactory
                .select(image)
                .from(image)
                .where(image.user.id.in(
                        JPAExpressions.select(subscribe.toUser.id)
                                .from(subscribe)
                                .where(subscribe.fromUser.id.castToNum(Integer.class).eq(principalId))
                        )
                )
                .offset(page * 3)
                .limit(3)
                .orderBy(image.id.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(image.count())
                .from(image)
                .where(image.user.id.in(
                        JPAExpressions.select(subscribe.toUser.id)
                                .from(subscribe)
                                .where(subscribe.fromUser.id.castToNum(Integer.class).eq(principalId))
                        )
                );

        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchOne());

    }



}
