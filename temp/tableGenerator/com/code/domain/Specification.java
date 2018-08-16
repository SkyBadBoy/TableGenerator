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
@ApiModel(value = "Specification")
public class Specification implements Serializable {

    public static final String COLUMN_ID = "ID";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_Specs = "Specs";

    public static final String COLUMN_Memo = "Memo";

    public static final String COLUMN_ShopID = "ShopID";

    public static final String COLUMN_Image = "Image";

    @ApiModelProperty(value = "编号")
    private String ID;

    @ApiModelProperty(value = "创建时间")
    private String CreateTime;

    @ApiModelProperty(value = "修改时间")
    private String ModifyTime;

    @ApiModelProperty(value = "状态 【正常：1   删除：88】")
    private int Status;

    @ApiModelProperty(value = "规格")
    private String Specs;

    @ApiModelProperty(value = "备注")
    private String Memo;

    @ApiModelProperty(value = "商品编号")
    private String ShopID;

    @ApiModelProperty(value = "主图")
    private String Image;

	@ApiModelProperty(value = "['ID','编号']['CreateTime','创建时间']['ModifyTime','修改时间']['Status','状态 【正常：1   删除：88】']['Specs','规格']['Memo','备注']['ShopID','商品编号']['Image','主图']")
	@JSONField(serialize = false)
	public String SpecificationField;
}
