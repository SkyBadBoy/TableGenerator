package com.code.service.write;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

import com.code.domain.Shop;
import com.code.dao.read.ReadShopMapper;
import com.code.dao.write.ShopMapper;

/**
 * <p>Service class。</p>
 *
 * @author majian 自动生成器
 * @version 1.00
 */
@Service
@CacheConfig(cacheNames="ShopCache") 
@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
public class ShopService {

   
    @Autowired
	private ShopMapper WriteMapper;

    @Autowired
	private ReadShopMapper ReadMapper;
 

	@CachePut(key="'Shop_'+#p0.ID")  
	@CacheEvict(value = "ReadShopCache",allEntries = true)
	public Shop insert(Shop obj){
		WriteMapper.insert(obj);
		return ReadMapper.findById(obj.getID());
	}

	@CachePut(key="'Shop_'+#p0.ID")  
	@CacheEvict(value = "ReadShopCache",allEntries = true)
	public Shop update(Shop obj){
		WriteMapper.update(obj);
		return ReadMapper.findById(obj.getID());
	}

	@CachePut(key="'Shop_'+#p0")  
	@CacheEvict(value = "ReadShopCache",allEntries = true)
	public Shop deleteById(String id){
		WriteMapper.deleteById(id);
		return ReadMapper.findById(id);
	}

	@CachePut(key="'Shop_'+#p0")  
	@CacheEvict(value = "ReadShopCache",allEntries = true)
	public Shop recoverByID(String id){
		WriteMapper.recoverByID(id);
		return ReadMapper.findById(id);
	}

	@CacheEvict(value = {"ReadShopCache","ShopCache"},allEntries = true)
	public int deleteByCondition(Map<String,Object> queryMap){
		return WriteMapper.deleteByCondition(queryMap);
	}

	@CacheEvict(value = {"ReadShopCache","ShopCache"},allEntries = true)
	public int recoverByCondition(Map<String,Object> queryMap){
		return WriteMapper.recoverByCondition(queryMap);
	}

}
