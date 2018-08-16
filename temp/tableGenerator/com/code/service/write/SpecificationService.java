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

import com.code.domain.Specification;
import com.code.dao.read.ReadSpecificationMapper;
import com.code.dao.write.SpecificationMapper;

/**
 * <p>Service class。</p>
 *
 * @author majian 自动生成器
 * @version 1.00
 */
@Service
@CacheConfig(cacheNames="SpecificationCache") 
@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
public class SpecificationService {

   
    @Autowired
	private SpecificationMapper WriteMapper;

    @Autowired
	private ReadSpecificationMapper ReadMapper;
 

	@CachePut(key="'Specification_'+#p0.ID")  
	@CacheEvict(value = "ReadSpecificationCache",allEntries = true)
	public Specification insert(Specification obj){
		WriteMapper.insert(obj);
		return ReadMapper.findById(obj.getID());
	}

	@CachePut(key="'Specification_'+#p0.ID")  
	@CacheEvict(value = "ReadSpecificationCache",allEntries = true)
	public Specification update(Specification obj){
		WriteMapper.update(obj);
		return ReadMapper.findById(obj.getID());
	}

	@CachePut(key="'Specification_'+#p0")  
	@CacheEvict(value = "ReadSpecificationCache",allEntries = true)
	public Specification deleteById(String id){
		WriteMapper.deleteById(id);
		return ReadMapper.findById(id);
	}

	@CachePut(key="'Specification_'+#p0")  
	@CacheEvict(value = "ReadSpecificationCache",allEntries = true)
	public Specification recoverByID(String id){
		WriteMapper.recoverByID(id);
		return ReadMapper.findById(id);
	}

	@CacheEvict(value = {"ReadSpecificationCache","SpecificationCache"},allEntries = true)
	public int deleteByCondition(Map<String,Object> queryMap){
		return WriteMapper.deleteByCondition(queryMap);
	}

	@CacheEvict(value = {"ReadSpecificationCache","SpecificationCache"},allEntries = true)
	public int recoverByCondition(Map<String,Object> queryMap){
		return WriteMapper.recoverByCondition(queryMap);
	}

}
