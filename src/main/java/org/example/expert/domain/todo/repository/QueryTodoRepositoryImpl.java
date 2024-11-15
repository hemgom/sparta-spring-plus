package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.expert.domain.common.convertor.DateTimeConvertor.convertStringToLocalDatetime;
import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class QueryTodoRepositoryImpl implements QueryTodoRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Todo> findAllWithSearchCondition(
            Pageable pageable, String weather, String periodStart, String periodEnd
    ) {
        String jpql = "SELECT t FROM Todo AS t LEFT JOIN FETCH t.user";
        List<String> conditions = new ArrayList<>();

        if (StringUtils.hasText(weather)) {
            conditions.add("t.weather = :weather");
        }

        if (StringUtils.hasText(periodStart)) {
            conditions.add("t.modifiedAt >= :start");
        }

        if (StringUtils.hasText(periodEnd)) {
            conditions.add("t.modifiedAt <= :end");
        }

        if (!conditions.isEmpty()) {
            jpql += " WHERE " + String.join(" AND ", conditions);
        }

        jpql += " ORDER BY t.modifiedAt DESC LIMIT :size OFFSET :page";
        TypedQuery<Todo> query = entityManager.createQuery(jpql, Todo.class);

        if (StringUtils.hasText(weather)) {
            query.setParameter("weather", weather);
        }

        if (StringUtils.hasText(periodStart)) {
            query.setParameter("start", convertStringToLocalDatetime(periodStart));
        }

        if (StringUtils.hasText(periodEnd)) {
            query.setParameter("end", convertStringToLocalDatetime(periodEnd));
        }

        query.setParameter("page", (int) pageable.getOffset());
        query.setParameter("size", pageable.getPageSize());

        List<Todo> todos = query.getResultList();
        long totalRows = countTotalRow();

        return new PageImpl<>(todos, pageable, totalRows);
    }

    private long countTotalRow() {
        String jpql = "SELECT COUNT(*) FROM Todo AS t";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        return query.getSingleResult();
    }

    @Override
    public Todo findByTodoId(Long todoId) {
        return Optional.ofNullable(jpaQueryFactory
                        .selectFrom(todo)
                        .join(todo.user, user).fetchJoin()
                        .where(todo.id.eq(todoId))
                        .fetchOne())
                .orElseThrow(() -> new InvalidRequestException("Todo not found"));
    }
}
