package com.zengc.department.entity;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
@ApiModel(value ="")
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long organizationId;
    private String name;

    public Long getId() {
        return id;
    }

    public Department setId(Long id) {
        this.id = id;
        return this;
    }
    public Long getOrganizationId() {
        return organizationId;
    }

    public Department setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
        return this;
    }
    public String getName() {
        return name;
    }

    public Department setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Department{" +
            ", id=" + id +
            ", organizationId=" + organizationId +
            ", name=" + name +
        "}";
    }
}