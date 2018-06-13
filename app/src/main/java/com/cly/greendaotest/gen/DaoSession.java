package com.cly.greendaotest.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.goodsure.frameworkdemo.model.ChatItem;
import com.goodsure.frameworkdemo.model.Shop;

import com.cly.greendaotest.gen.ChatItemDao;
import com.cly.greendaotest.gen.ShopDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig chatItemDaoConfig;
    private final DaoConfig shopDaoConfig;

    private final ChatItemDao chatItemDao;
    private final ShopDao shopDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        chatItemDaoConfig = daoConfigMap.get(ChatItemDao.class).clone();
        chatItemDaoConfig.initIdentityScope(type);

        shopDaoConfig = daoConfigMap.get(ShopDao.class).clone();
        shopDaoConfig.initIdentityScope(type);

        chatItemDao = new ChatItemDao(chatItemDaoConfig, this);
        shopDao = new ShopDao(shopDaoConfig, this);

        registerDao(ChatItem.class, chatItemDao);
        registerDao(Shop.class, shopDao);
    }
    
    public void clear() {
        chatItemDaoConfig.clearIdentityScope();
        shopDaoConfig.clearIdentityScope();
    }

    public ChatItemDao getChatItemDao() {
        return chatItemDao;
    }

    public ShopDao getShopDao() {
        return shopDao;
    }

}