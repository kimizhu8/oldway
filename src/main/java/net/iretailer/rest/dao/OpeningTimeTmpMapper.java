package net.iretailer.rest.dao;

import net.iretailer.rest.model.OpeningTimeTmp;
import net.iretailer.rest.model.OpeningTimeTmpKey;

public interface OpeningTimeTmpMapper {
    int deleteByPrimaryKey(OpeningTimeTmpKey key);

    int insert(OpeningTimeTmp record);

    int insertSelective(OpeningTimeTmp record);

    OpeningTimeTmp selectByPrimaryKey(OpeningTimeTmpKey key);

    int updateByPrimaryKeySelective(OpeningTimeTmp record);

    int updateByPrimaryKey(OpeningTimeTmp record);
}