package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.work.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Collection;

import ${package.Service}.${table.serviceName};
import ${package.Entity}.${table.entityName};
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
* <p>
    * ${table.comment!} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/

<#if restControllerStyle>
    @RestController
<#else>
    @Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@Slf4j
@Api(value = "${table.comment!}", description = "${table.comment!}控制器")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
    public class ${table.controllerName} extends ${superControllerClass} {
<#else>
    public class ${table.controllerName} {
</#if>
@Autowired
private ${table.serviceName} ${entity?uncap_first}Service;

/**
* 获取数据列表
*/
@GetMapping("/list${entity}/{pageIndex}/{pageSize}")
@ResponseBody
@ApiOperation(value = "获取数据列表")
public Response list${entity}( @PathVariable(name = "pageIndex") int pageIndex, @PathVariable(name = "pageSize") int size){
Page<${entity}> page= new Page(pageIndex,size);
QueryWrapper<${entity}> queryWrapper = new QueryWrapper<${entity}>();
//queryWrapper.lambda().eq(${entity}::getChangeTypeStatus, "05").or(obj1 -> obj1.eq(Student::getName, "冯文议")).or(obj2 -> obj2.eq(${entity}::getName, "1"));;
return Response.success(${entity?uncap_first}Service.page(page,queryWrapper));

}


/**
* 获取全部数据
*/
@GetMapping("/all${entity}")
@ResponseBody
@ApiOperation(value = "获取全部数据")
public Response all${entity}(){
List<${entity}> models = ${entity?uncap_first}Service.list(null);
return Response.success(models);
}


/**
* 根据ID查找数据
*/
@GetMapping("/get${entity}")
@ResponseBody
@ApiOperation(value = "根据ID查找数据")
public Response get${entity}(@PathVariable Long id){
${entity} ${entity?uncap_first} = ${entity?uncap_first}Service.getById(id);
if(${entity?uncap_first}==null){
return Response.error("尚未查询到此ID");
}
return Response.success(${entity?uncap_first});
}


/**
* 根据条件查找数据
*/
@PostMapping("/find${entity}")
@ResponseBody
@ApiOperation(value = "根据条件查找数据")
public Response find${entity}(Map columnMap){
columnMap.put("last_name","东方不败");//写表中的列名
columnMap.put("gender","1");
Collection<${entity}> ${entity?uncap_first}List = ${entity?uncap_first}Service.listByMap(columnMap);
return Response.success(${entity?uncap_first}List);
}


/**
* 添加数据
*/
@PostMapping("/add${entity}")
@ResponseBody
@ApiOperation(value = "添加数据")
public Response add${entity}(@RequestBody ${entity} ${entity?uncap_first}){
boolean isOk = ${entity?uncap_first}Service.save(${entity?uncap_first});
if(isOk){
return Response.success("数据添加成功！");
}
return Response.error("数据添加失败");
}


/**
* 更新数据
*/
@PostMapping("/update${entity}")
@ResponseBody
@ApiOperation(value = "更新数据")
public Response update${entity}(@RequestBody ${entity} ${entity?uncap_first}){
boolean isOk = ${entity?uncap_first}Service.updateById(${entity?uncap_first});
if(isOk){
return Response.success("数据更改成功！");
}
return Response.error("数据更改失败");
}


/**
* 删除数据
*/
@PostMapping("/del${entity}")
@ResponseBody
@ApiOperation(value = "删除数据")
public Response del${entity}(@RequestParam("ids") List <Long> ids){
    boolean isOk = ${entity?uncap_first}Service.removeByIds(ids);
    if(isOk){
    return Response.success("数据删除成功！");
    }
    return Response.error("数据删除失败");
    }
    }


    </#if>
