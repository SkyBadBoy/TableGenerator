package com.code.domain;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
/**
 * <p> Entity Class</p>
 *
 * @author majian
 */
@Getter
@Setter
@ApiModel(value = "Shop")
public class Shop implements Serializable {

    public static final String COLUMN_ID = "ID";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_Name = "Name";

    public static final String COLUMN_Memo = "Memo";

    public static final String COLUMN_ClassifyName = "ClassifyName";

    public static final String COLUMN_ClassifyID = "ClassifyID";

    public static final String COLUMN_TagName = "TagName";

    public static final String COLUMN_TagID = "TagID";

    public static final String COLUMN_BrandName = "BrandName";

    public static final String COLUMN_BrandID = "BrandID";

    public static final String COLUMN_UnitName = "UnitName";

    public static final String COLUMN_UnitID = "UnitID";

    public static final String COLUMN_Content = "Content";

    public static final String COLUMN_Putaway = "Putaway";

    public static final String COLUMN_Images = "Images";

    public static final String COLUMN_Order = "Order";

    public static final String COLUMN_NO = "NO";

    public static final String COLUMN_NNum = "NNum";

    @ApiModelProperty(value = "编号")
    private String ID;

    @ApiModelProperty(value = "创建时间")
    private String CreateTime;

    @ApiModelProperty(value = "修改时间")
    private String ModifyTime;

    @ApiModelProperty(value = "状态 【正常：1   删除：88】")
    private int Status;

    @ApiModelProperty(value = "商品名字")
    private String Name;

    @ApiModelProperty(value = "备注")
    private String Memo;

    @ApiModelProperty(value = "商品分类名字")
    private String ClassifyName;

    @ApiModelProperty(value = "商品分类")
    private String ClassifyID;

    @ApiModelProperty(value = "标签名字")
    private String TagName;

    @ApiModelProperty(value = "标签")
    private String TagID;

    @ApiModelProperty(value = "品牌名字")
    private String BrandName;

    @ApiModelProperty(value = "品牌")
    private String BrandID;

    @ApiModelProperty(value = "单位名字")
    private String UnitName;

    @ApiModelProperty(value = "单位")
    private String UnitID;

    @ApiModelProperty(value = "商品属性")
    private String Content;

    @ApiModelProperty(value = "上架 ［未上架0.上架1］")
    private int Putaway;

    @ApiModelProperty(value = "JSON图片字段")
    private String Images;

    @ApiModelProperty(value = "排序")
    private int Order;

    @ApiModelProperty(value = "商品的编码")
    private String NO;

    @ApiModelProperty(value = "商品编码的计数")
    private int NNum;

	@ApiModelProperty(value = "['ID','编号']['CreateTime','创建时间']['ModifyTime','修改时间']['Status','状态 【正常：1   删除：88】']['Name','商品名字']['Memo','备注']['ClassifyName','商品分类名字']['ClassifyID','商品分类']['TagName','标签名字']['TagID','标签']['BrandName','品牌名字']['BrandID','品牌']['UnitName','单位名字']['UnitID','单位']['Content','商品属性']['Putaway','上架 ［未上架0.上架1］']['Images','JSON图片字段']['Order','排序']['NO','商品的编码']['NNum','商品编码的计数']")
	@JSONField(serialize = false)
	public String ShopField;
}
