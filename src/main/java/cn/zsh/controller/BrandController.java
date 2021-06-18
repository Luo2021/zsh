package cn.zsh.controller;
import cn.zsh.pojo.Brand;
import cn.zsh.pojo.BrandQueryByPageParameter;
import cn.zsh.pojo.PageResult;
import cn.zsh.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author luoli
 * @date 2021/5/7 10:11
 * Feature:
 */
@RestController
@RequestMapping("/brand")
@Api(tags = "品牌管理")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 分页查询品牌
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    @ApiOperation(value= "分页查询品牌接口")
    @GetMapping("/page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                              @RequestParam(value = "rows", defaultValue = "5") Integer rows,
                                                              @RequestParam(value = "sortBy", required = false) String sortBy,
                                                              @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                              @RequestParam(value = "key", required = false) String key){
        BrandQueryByPageParameter brandQueryByPageParameter=new BrandQueryByPageParameter(page,rows,sortBy,desc,key);
        PageResult<Brand> result = this.brandService.queryBrandByPage(brandQueryByPageParameter);
        if(result == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 品牌新增
     * @param brand
     * @param categories
     * @return
     */
    @ApiOperation(value= "品牌新增接口")
    @PostMapping("/add")
    public ResponseEntity<Void>  saveBrand(Brand brand, @RequestParam("categories") List<Long> categories){
        this.brandService.saveBrand(brand, categories);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 品牌修改
     * @param brand
     * @param categories
     * @return
     */
    @ApiOperation(value= "品牌修改接口")
    @PutMapping("update")
    public ResponseEntity<Void> updateBrand(Brand brand,@RequestParam("categories") List<Long> categories){
        this.brandService.updateBrand(brand,categories);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * 删除tb_category_brand中的数据
     * @param bid
     * @return
     */
    @ApiOperation(value= "删除中间表tb_category_brand中的数据接口")
    @DeleteMapping("cid_bid/{bid}")
    public ResponseEntity<Void> deleteByBrandIdInCategoryBrand(@PathVariable("bid") Long bid){
        System.out.println("删除中间表");
        this.brandService.deleteByBrandIdInCategoryBrand(bid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    /**
     * 删除tb_brand中的数据,单个删除、多个删除二合一
     * @param bid
     * @return
     */
    @ApiOperation(value= "通过品牌bid删除品牌的接口")
    @DeleteMapping("bid/{bid}")
    public ResponseEntity<Void> deleteBrand(@PathVariable("bid") String bid){
        String separator="-";
        if(bid.contains(separator)){
            String[] ids=bid.split(separator);
            for (String id:ids){
                this.brandService.deleteBrand(Long.parseLong(id));
            }
        }
        else {
            this.brandService.deleteBrand(Long.parseLong(bid));
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 根据category的id查询品牌信息
     * @param cid
     * @return
     */
    @ApiOperation(value= "根据category的id查询品牌信息的接口")
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCategoryId(@PathVariable("cid") Long cid){
        List<Brand> list = this.brandService.queryBrandByCategoryId(cid);
        if (list == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }

    /**
     * 根据品牌id集合，查询品牌信息
     * @param ids
     * @return
     */
    @ApiOperation(value= "根据品牌id集合，查询品牌信息的接口")
    @GetMapping("list")
    public ResponseEntity<List<Brand>> queryBrandByIds(@RequestParam("ids") List<Long> ids){
        List<Brand> list = this.brandService.queryBrandByBrandIds(ids);
        if (list == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }

}
