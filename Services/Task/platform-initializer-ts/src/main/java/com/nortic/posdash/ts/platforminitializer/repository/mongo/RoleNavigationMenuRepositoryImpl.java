package com.nortic.posdash.ts.platforminitializer.repository.mongo;

import com.nortic.pos.models.document.NavigationMenuDocument;
import com.nortic.pos.models.document.PlatformNavigationMenuDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleNavigationMenuRepositoryImpl implements IRoleNavigationMenuRepository{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public RoleNavigationMenuRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<PlatformNavigationMenuDocument> findByRoleId(String roleId) {
        Query query = new Query(Criteria.where("_id").is(roleId));
        PlatformNavigationMenuDocument entity = mongoTemplate.findOne(query, PlatformNavigationMenuDocument.class);
        return Optional.ofNullable(entity);
    }

    @Override
    public void upsertByRoleId(String roleId, PlatformNavigationMenuDocument entity) {
        Query query = new Query(Criteria.where("_id").is(roleId));
        Update update = new Update()
                .set("navigationMenus", entity.getNavigationMenus());
        mongoTemplate.upsert(query, update, NavigationMenuDocument.class);
    }
}
