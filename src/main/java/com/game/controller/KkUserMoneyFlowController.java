package com.game.controller;


import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.work.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Collection;

import com.game.service.KkUserMoneyFlowService;
import com.game.entity.KkUserMoneyFlow;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
* <p>
    *  前端控制器
    * </p>
*
* @author yeats
* @since 2019-05-04
*/

    @RestController
@RequestMapping("/game/kkUserMoneyFlow")
@Slf4j
@Api(value = "", description = "控制器")
    public class KkUserMoneyFlowController {
@Autowired
private KkUserMoneyFlowService kkUserMoneyFlowService;

/**
* 获取数据列表
*/
@GetMapping("/listKkUserMoneyFlow/{pageIndex}/{pageSize}")
@ResponseBody
@ApiOperation(value = "获取数据列表")
public Response listKkUserMoneyFlow( @PathVariable(name = "pageIndex") int pageIndex, @PathVariable(name = "pageSize") int size){
Page<KkUserMoneyFlow> page= new Page(pageIndex,size);
QueryWrapper<KkUserMoneyFlow> queryWrapper = new QueryWrapper<KkUserMoneyFlow>();
//queryWrapper.lambda().eq(KkUserMoneyFlow::getChangeTypeStatus, "05").or(obj1 -> obj1.eq(Student::getName, "冯文议")).or(obj2 -> obj2.eq(KkUserMoneyFlow::getName, "1"));;
return Response.success(kkUserMoneyFlowService.page(page,queryWrapper));

}


/**
* 获取全部数据
*/
@GetMapping("/allKkUserMoneyFlow")
@ResponseBody
@ApiOperation(value = "获取全部数据")
public Response allKkUserMoneyFlow(){
List<KkUserMoneyFlow> models = kkUserMoneyFlowService.list(null);
return Response.success(models);
}


/**
* 根据ID查找数据
*/
@GetMapping("/getKkUserMoneyFlow")
@ResponseBody
@ApiOperation(value = "根据ID查找数据")
public Response getKkUserMoneyFlow(@PathVariable Long id){
KkUserMoneyFlow kkUserMoneyFlow = kkUserMoneyFlowService.getById(id);
if(kkUserMoneyFlow==null){
return Response.error("尚未查询到此ID");
}
return Response.success(kkUserMoneyFlow);
}


/**
* 根据条件查找数据
*/
@PostMapping("/findKkUserMoneyFlow")
@ResponseBody
@ApiOperation(value = "根据条件查找数据")
public Response findKkUserMoneyFlow(Map columnMap){
columnMap.put("last_name","东方不败");//写表中的列名
columnMap.put("gender","1");
Collection<KkUserMoneyFlow> kkUserMoneyFlowList = kkUserMoneyFlowService.listByMap(columnMap);
return Response.success(kkUserMoneyFlowList);
}


/**
* 添加数据
*/
@PostMapping("/addKkUserMoneyFlow")
@ResponseBody
@ApiOperation(value = "添加数据")
public Response addKkUserMoneyFlow(@RequestBody KkUserMoneyFlow kkUserMoneyFlow){
boolean isOk = kkUserMoneyFlowService.save(kkUserMoneyFlow);
if(isOk){
return Response.success("数据添加成功！");
}
return Response.error("数据添加失败");
}


/**
* 更新数据
*/
@PostMapping("/updateKkUserMoneyFlow")
@ResponseBody
@ApiOperation(value = "更新数据")
public Response updateKkUserMoneyFlow(@RequestBody KkUserMoneyFlow kkUserMoneyFlow){
boolean isOk = kkUserMoneyFlowService.updateById(kkUserMoneyFlow);
if(isOk){
return Response.success("数据更改成功！");
}
return Response.error("数据更改失败");
}


/**
* 删除数据
*/
@PostMapping("/delKkUserMoneyFlow")
@ResponseBody
@ApiOperation(value = "删除数据")
public Response delKkUserMoneyFlow(@RequestParam("ids") List <Long> ids){
    boolean isOk = kkUserMoneyFlowService.removeByIds(ids);
    if(isOk){
    return Response.success("数据删除成功！");
    }
    return Response.error("数据删除失败");
    }
    }


