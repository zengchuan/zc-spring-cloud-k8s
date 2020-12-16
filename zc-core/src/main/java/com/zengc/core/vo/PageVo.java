package com.zengc.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName PageVo
 * @Description: TODO
 * @Author dingzd
 * @Date 2019/11/20
 * @Version V1.0
 **/
@ApiModel(value="PageVo")
public class PageVo implements Serializable {
    @ApiModelProperty(value = "当前页")
    private Integer pageNum;
    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public PageVo setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public PageVo setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
