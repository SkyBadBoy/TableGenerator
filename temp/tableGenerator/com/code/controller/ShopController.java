package com.code.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import com.code.domain.Shop;
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
@Api("Shop")
@RestController
@RequestMapping("/Shop")
public class ShopController extends BaseController {


    @GetMapping("/queryShopPage")
    @ApiOperation(value = "获取列表")
    public Map queryShopPage(int status,String search,int pageNumber, int pageSize,HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>(2);
        Map<String, Object> queryMap = new HashMap<>(3);
        queryMap.put("search", search);
        if(status!=-1){
            queryMap.put("Status", status);
        }
        PageInfo<Shop> page = this.ReadShopService.queryPage(queryMap, pageNumber, pageSize);
        returnMap.put("rows", page.getList());
        returnMap.put("total", page.getTotal());
        RabbitUtil.getInstance().OperationLog(request.getHeader("Token"),"查看【Shop-queryShopPage】列表",ReadOnlineService,OperationService,RabbitTemplate,ReadUserService);
        return returnMap;
    }

    @PostMapping("/setShopStatus")
    @ApiOperation(value = "设置状态")
    public Map setShopStatus(String data,HttpServletRequest request){
        Map<String,Object> returnMap=new HashMap<>(2);
        Map<String,Object> queryMap=new HashMap<>(1);
        Shop temp=JSON.parseObject(data,Shop.class);
        String[] ids=temp.getID().split(",");
        for (String id : ids){
            if(temp.getStatus()==Integer.parseInt(CommonStatus.Status.Ectivity.getid())){
                ShopService.recoverByID(id);
            }else{
                ShopService.deleteById(id);
            }
        }
        queryMap.clear();
        returnMap.put("code",0);
        returnMap.put("message","操作成功");
        RabbitUtil.getInstance().OperationLog(request.getHeader("Token"),"设置【Shop-setShopStatus】状态",ReadOnlineService,OperationService,RabbitTemplate,ReadUserService);
        return returnMap;
    }

    @GetMapping("/findShop/{id}")
    @ApiOperation(value = "根据编号查询内容")
    public Map findShop(@PathVariable("id") String id,HttpServletRequest request){
        Map<String,Object> returnMap=new HashMap<>(2);
        Map<String,Object> queryMap=new HashMap<>(1);
        Shop temp=ReadShopService.findById(id);
        if(temp!=null){
	   		returnMap.put("code",0);
	        returnMap.put("data",temp);
	        returnMap.put("message","获取成功");
    	}else{
			returnMap.put("code",-1);
	        returnMap.put("data",temp);
	        returnMap.put("message","获取失败");
		}
        RabbitUtil.getInstance().OperationLog(request.getHeader("Token"),"查询【Shop-findShop-"+id+"】内容",ReadOnlineService,OperationService,RabbitTemplate,ReadUserService);
        return returnMap;
    }


    @PostMapping("/modifyShop")
    @ApiOperation(value = "修改")
    public Map modifyShop(String data, HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>(2);
        Shop temp = JSON.parseObject(data, Shop.class);
        Shop  obj=new Shop();
        boolean isNew=false;
        if("0".equals(temp.getID())){
            isNew=true;
        }else{
            obj=ReadShopService.findById(String.valueOf(temp.getID()));
            if(obj==null){
                isNew=true;
            }
        }

        obj.setCreateTime(temp.getCreateTime());
        obj.setStatus(temp.getStatus());
        obj.setName(temp.getName());
        obj.setMemo(temp.getMemo());
        obj.setClassifyName(temp.getClassifyName());
        obj.setClassifyID(temp.getClassifyID());
        obj.setTagName(temp.getTagName());
        obj.setTagID(temp.getTagID());
        obj.setBrandName(temp.getBrandName());
        obj.setBrandID(temp.getBrandID());
        obj.setUnitName(temp.getUnitName());
        obj.setUnitID(temp.getUnitID());
        obj.setContent(temp.getContent());
        obj.setPutaway(temp.getPutaway());
        obj.setImages(temp.getImages());
        obj.setOrder(temp.getOrder());
        obj.setNO(temp.getNO());
        obj.setNNum(temp.getNNum());


        Shop tempObj=null;
        if(isNew){
            obj.setID(CommonUntil.getInstance().CreateNewID());
            obj.setStatus(Integer.parseInt(CommonStatus.Status.Ectivity.getid()));
            tempObj=ShopService.insert(obj);
        }else{
            tempObj=ShopService.update(obj);
        }
        returnMap.put("code", tempObj!=null?0:-1);
        returnMap.put("message", tempObj!=null?"操作成功":"操作失败");
        RabbitUtil.getInstance().OperationLog(request.getHeader("Token"),"修改【Shop-modifyShop-"+obj.getID()+"】内容",ReadOnlineService,OperationService,RabbitTemplate,ReadUserService);
        return returnMap;
    }

}
