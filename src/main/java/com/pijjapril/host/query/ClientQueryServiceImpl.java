package com.pijjapril.host.query;

import com.pijjapril.host.domain.Client;
import com.pijjapril.host.domain.QClient;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClientQueryServiceImpl extends QuerydslRepositorySupport implements ClientQueryService {
    private final JPAQueryFactory query;

    public ClientQueryServiceImpl(JPAQueryFactory query) {
        super(Client.class);
        this.query = query;
    }

    @Override
    public PageImpl<Client> clientList(Pageable pageable) {
        JPAQuery<Client> result = query
                .selectFrom(QClient.client)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchAll();
        int totalCount = result.fetch().size();
        List<Client> results = Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, result).fetch();
        return new PageImpl<>(results, pageable, totalCount);
    }
}
