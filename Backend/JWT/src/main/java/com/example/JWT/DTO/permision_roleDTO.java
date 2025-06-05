package com.example.JWT.DTO;

public class permision_roleDTO {

    private int id;
    private int roleId;
    private int pageId;

    public permision_roleDTO() {
    }

    public permision_roleDTO(int id, int roleId, int pageId) {
        this.id = id;
        this.roleId = roleId;
        this.pageId = pageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }
}
