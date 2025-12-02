package io.github.trident.common.domain;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T extends BaseEntity> {
    private String id;
    private String name;
    private Boolean leaf;
    private String parentId;
    private Boolean checked;
    //显示是否子节点被选
    private Boolean childChecked;
    //显示图标
    private String iconCls;
    private String cls;
    //节点对象数据
    private T data;
    //孩子节点
    @SuppressWarnings("rawtypes")
    private List<TreeNode> children = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getChildChecked() {
        return childChecked;
    }

    public void setChildChecked(Boolean childChecked) {
        this.childChecked = childChecked;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
