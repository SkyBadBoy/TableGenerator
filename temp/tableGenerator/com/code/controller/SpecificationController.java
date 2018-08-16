package com.code.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import com.code.domain.Specification;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.code.until.CommonStatus;
import com.code.until.CommonUntil;
import com.code.config.rabbit.RabbitUtil;
/**
 * <p> 控制器 Class</p>
 *
 * @author majian 自动构建脚本
 */
@Api("Specification")
@RestController
@RequestMapping("/Specification")
public class SpecificationController extends BaseController {


    @GetMapping("/querySpecificationPage")
    @ApiOperation(value = "获取列表")
    public Map querySpecificationPage(int status,String search,int pageNumber, int pageSize,HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>(2);
        Map<String, Object> queryMap = new HashMap<>(3);
        queryMap.put("search", search);
        if(status!=-1){
            queryMap.put("Status", status);
        }
        PageInfo<Specification> page = this.ReadSpecificationService.queryPage(queryMap, pageNumber, pageSize);
        returnMap.put("rows", page.getList());
        returnMap.put("total", page.getTotal());
        RabbitUtil.getInstance().OperationLog(request.getHeader("Token"),"查看【Specification-querySpecificationPage】列表",ReadOnlineService,OperationService,RabbitTemplate,ReadUserService);
        return returnMap;
    }

    @PostMapping("/setSpecificationStatus")
    @ApiOperation(value = "设置状态")
    public Map setSpecificationStatus(String data,HttpServletRequest request){
        Map<String,Object> returnMap=new HashMap<>(2);
        Map<String,Object> queryMap=new HashMap<>(1);
        Specification temp=JSON.parseObject(data,Specification.class);
        String[] ids=temp.getID().split(",");
        for (String id : ids){
            if(temp.getStatus()==Integer.parseInt(CommonStatus.Status.Ectivity.getid())){
                SpecificationService.recoverByID(id);
            }else{
                SpecificationService.deleteById(id);
            }
        }
        queryMap.clear();
        returnMap.put("code",0);
        returnMap.put("message","操作成功");
        RabbitUtil.getInstance().OperationLog(request.getHeader("Token"),"设置【Specification-setSpecificationStatus】状态",ReadOnlineService,OperationService,RabbitTemplate,ReadUserService);
        return returnMap;
    }

    @GetMapping("/findSpecification/{id}")
    @ApiOperation(value = "根据编号查询内容")
    public Map findSpecification(@PathVariable("id") String id,HttpServletRequest request){
        Map<String,Object> returnMap=new HashMap<>(2);
        Map<String,Object> queryMap=new HashMap<>(1);
        Specification temp=ReadSpecificationService.findById(id);
        if(temp!=null){
	   		returnMap.put("code",0);
	        returnMap.put("data",temp);
	        returnMap.put("message","获取成功");
    	}else{
			returnMap.put("code",-1);
	        returnMap.put("data",temp);
	        returnMap.put("message","获取失败");
		}
        RabbitUtil.getInstance().OperationLog(request.getHeader("Token"),"查询【Specification-findSpecification-"+id+"】内容",ReadOnlineService,OperationService,RabbitTemplate,ReadUserService);
        return returnMap;
    }


    @PostMapping("/modifySpecification")
    @ApiOperation(value = "修改")
    public Map modifySpecification(String data, HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>(2);
        Specification temp = JSON.parseObject(data, Specification.class);
        Specification  obj=new Specification();
        boolean isNew=false;
        if("0".equals(temp.getID())){
            isNew=true;
        }else{
            obj=ReadSpecificationService.findById(String.valueOf(temp.getID()));
            if(obj==null){
                isNew=true;
            }
        }

        obj.setCreateTime(temp.getCreateTime());
        obj.setStatus(temp.getStatus());
        obj.setSpecs(temp.getSpecs());
        obj.setMemo(temp.getMemo());
        obj.setShopID(temp.getShopID());
        obj.setImage(temp.getImage());


        Specification tempObj=null;
        if(isNew){
            obj.setID(CommonUntil.getInstance().CreateNewID());
            obj.setStatus(Integer.parseInt(CommonStatus.Status.Ectivity.getid()));
            tempObj=SpecificationService.insert(obj);
        }else{
            tempObj=SpecificationService.update(obj);
        }
        returnMap.put("code", tempObj!=null?0:-1);
        returnMap.put("message", tempObj!=null?"操作成功":"操作失败");
        RabbitUtil.getInstance().OperationLog(request.getHeader("Token"),"修改【Specification-modifySpecification-"+obj.getID()+"】内容",ReadOnlineService,OperationService,RabbitTemplate,ReadUserService);
        return returnMap;
    }

}
