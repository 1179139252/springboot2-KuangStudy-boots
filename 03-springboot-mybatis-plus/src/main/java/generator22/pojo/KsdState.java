package generator22.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName ksd_state
 */
@TableName(value ="ksd_state")
public class KsdState implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 主题标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 统计描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 参与人数
     */
    @TableField(value = "personnum")
    private Integer personnum;

    /**
     * 统计结束时间
     */
    @TableField(value = "endtime")
    private String endtime;

    /**
     * 0未发布 1 发布
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 
     */
    @TableField(value = "create_time")
    private LocalDateTime create_time;

    /**
     * 
     */
    @TableField(value = "update_time")
    private LocalDateTime update_time;

    /**
     * 添加用户
     */
    @TableField(value = "userid")
    private Integer userid;

    /**
     * 添加人的名称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 
     */
    @TableField(value = "bgimg")
    private String bgimg;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 主题标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 主题标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 统计描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 统计描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 参与人数
     */
    public Integer getPersonnum() {
        return personnum;
    }

    /**
     * 参与人数
     */
    public void setPersonnum(Integer personnum) {
        this.personnum = personnum;
    }

    /**
     * 统计结束时间
     */
    public String getEndtime() {
        return endtime;
    }

    /**
     * 统计结束时间
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    /**
     * 0未发布 1 发布
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 0未发布 1 发布
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     */
    public LocalDateTime getCreate_time() {
        return create_time;
    }

    /**
     * 
     */
    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    /**
     * 
     */
    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    /**
     * 
     */
    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }

    /**
     * 添加用户
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 添加用户
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 添加人的名称
     */
    public String getUsername() {
        return username;
    }

    /**
     * 添加人的名称
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     */
    public String getBgimg() {
        return bgimg;
    }

    /**
     * 
     */
    public void setBgimg(String bgimg) {
        this.bgimg = bgimg;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        KsdState other = (KsdState) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getPersonnum() == null ? other.getPersonnum() == null : this.getPersonnum().equals(other.getPersonnum()))
            && (this.getEndtime() == null ? other.getEndtime() == null : this.getEndtime().equals(other.getEndtime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
            && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getBgimg() == null ? other.getBgimg() == null : this.getBgimg().equals(other.getBgimg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getPersonnum() == null) ? 0 : getPersonnum().hashCode());
        result = prime * result + ((getEndtime() == null) ? 0 : getEndtime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getBgimg() == null) ? 0 : getBgimg().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", personnum=").append(personnum);
        sb.append(", endtime=").append(endtime);
        sb.append(", status=").append(status);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", userid=").append(userid);
        sb.append(", username=").append(username);
        sb.append(", bgimg=").append(bgimg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}