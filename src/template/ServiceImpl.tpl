package ${package}.write;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

import ${entityImport};
import com.code.dao.write.${classdef}Mapper;

/**
 * <p>${tableComment}Service class。</p>
 *
 * @author yanghui majian 自动生成器
 * @version 1.00
 */
@Service
@CacheConfig(cacheNames="${classdef}Cache") 
@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
public class ${classdef}Service {

   
    @Autowired
	private ${classdef}Mapper WriteMapper;

	@CacheEvict(value = "Read${classdef}Cache",allEntries = true)
	public ${classdef} insert(${classdef} obj){return WriteMapper.insert(obj);}

	@Caching(evict={@CacheEvict(value = "Read${classdef}Cache",allEntries = true),@CacheEvict(value = "${classdef}Cache",key="'${classdef}_'+#p0.ID")})
	public int update(${classdef} obj){return WriteMapper.update(obj);}

	@Caching(evict={@CacheEvict(value = "Read${classdef}Cache",allEntries = true),@CacheEvict(value = "${classdef}Cache",key="'${classdef}_'+#p0")})
	public int deleteById(String id){return WriteMapper.deleteById(id);}

	@Caching(evict={@CacheEvict(value = "Read${classdef}Cache",allEntries = true),@CacheEvict(value = "${classdef}Cache",key="'${classdef}_'+#p0")})
	public int recoverByID(String id){return WriteMapper.recoverByID(id);}

	@CacheEvict(value = {"Read${classdef}Cache","${classdef}Cache"},allEntries = true)
	public int deleteByCondition(Map<String,Object> queryMap){
		return WriteMapper.deleteByCondition(queryMap);
	}

	@CacheEvict(value = {"Read${classdef}Cache","${classdef}Cache"},allEntries = true)
	public int recoverByCondition(Map<String,Object> queryMap){
		return WriteMapper.recoverByCondition(queryMap);
	}

}
