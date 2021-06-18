package cn.zsh.controller;

import cn.zsh.pojo.Category;
import cn.zsh.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author luoli
 * @date 2021/6/18 16:55
 */
@RestController
@RequestMapping("/category")
@Api(tags = "分类管理")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点查询商品类目
     * @param pid
     * @return
     */
    @ApiOperation(value= "根据父节点查询商品类目接口")
    @GetMapping("/list")
    public ResponseEntity<List<Category>> queryCategoryByPid(@RequestParam("pid") Long pid){

        //如果pid的值为-1那么需要获取数据库中最后一条数据
        if (pid == -1){
            List<Category> last = this.categoryService.queryLast();
            return ResponseEntity.ok(last);
        }
        else {
            List<Category> list = this.categoryService.queryCategoryByPid(pid);
            if (list == null) {
                //没有找到返回404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            //找到返回200
            return ResponseEntity.ok(list);
        }
    }

    /**
     * 用于修改品牌信息时，商品分类信息的回显
     * @param bid
     * @return
     */
    @ApiOperation(value= "用于修改品牌信息时，商品分类信息的回显接口")
    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("bid") Long bid){
        List<Category> list = this.categoryService.queryByBrandId(bid);
        if(list == null || list.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }


    /**
     * 保存
     * @return
     */
    @ApiOperation(value= "保存分类接口")
    @PostMapping("saveCategory")
    public ResponseEntity<Void> saveCategory(Category category){
        this.categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 更新
     * @return
     */
    @ApiOperation(value= "跟新分类接口")
    @PutMapping("updateCategory")
    public ResponseEntity<Void> updateCategory(Category category){
        this.categoryService.updateCategory(category);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * 删除
     * @return
     */
    @ApiOperation(value= "删除分类接口")
    @DeleteMapping("cid/{cid}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("cid") Long id){
        this.categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 根据分类id集合查询分类名称
     * @param ids
     * @return
     */
    @ApiOperation(value= "根据分类id集合查询分类名称接口")
    @GetMapping("names")
    public ResponseEntity<List<String>> queryNameByIds(@RequestParam("ids")List<Long> ids){
        List<String> list = categoryService.queryNameByIds(ids);
        if (list == null || list.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok(list);
        }
    }

    /**
     * 根据分类id集合查询分类
     * @param ids
     * @return
     */
    @ApiOperation(value= "根据分类id集合查询分类信息接口")
    @GetMapping("all")
    public ResponseEntity<List<Category>> queryCategoryByIds(@RequestParam("ids")List<Long> ids){
        List<Category> list = categoryService.queryCategoryByIds(ids);
        if (list == null || list.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok(list);
        }
    }

    /**
     * 根据分类id集合查询分类名称
     * @param id
     * @return
     */
    @ApiOperation(value= "根据cid3查询其所有层级分类接口")
    @GetMapping("all/level/{cid3}")
    public ResponseEntity<List<Category>> queryAllCategoryLevelByCid3(@PathVariable("cid3")Long id){
        List<Category> list = categoryService.queryAllCategoryLevelByCid3(id);
        if (list == null || list.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok(list);
        }
    }

}

