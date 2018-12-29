package wanandroid.com.wanandroidtest.mvp.model.bean;

import java.util.List;


/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2018/12/29 ADD
 */

public class LoginData extends BaseResponse{
    private String username;
    private String password;
    private String icon;
    private int type;
    private List<Integer> collectIds;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }
}
