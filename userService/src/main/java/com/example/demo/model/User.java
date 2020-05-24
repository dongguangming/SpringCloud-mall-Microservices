package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Date;
//import java.sql.Date;

/**
 * 
 * @author dgm
 * @describe ""
 * @date 2020年5月20日
 */
@Table(name = "user")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 登录名
     */
    private String username;
    
    //用户头像
    private String avatar; 
    
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date uptime;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 用户级别
     */
    private Integer level;
    
    /**
     * 当前正在进行的实验id
     */
    private Integer eid;
    
    /**
     * 当前正在进行的实验步骤id
     */
    private Integer stepid;
    
    /**
     * 当前应用的状态
     */
    private Integer appstatus;
    
    /**
     * 尝试获取的次数
     */
    private Integer retrytimes;
    
    /**
     * 登录时间
     */
    private Date logintime;
    
    /**
     * 应用名称
     */
    private String appname;
    
    /**
     * 应用ID
     */
    private Integer appid;
    
    /**
     * 自由集群的服务器数量，实验模式填0
     */
    private Integer mode;
    
    
    /**
     * 登录token
     */
    private String token = "";
    
    /**
	*课程id
	**/
    @Column(name = "course_id")
	private Integer cid;
    
    /**
   	*老师课程id
   	**/
    @Column(name = "teacher_course_id")
   	private Integer tcid;
    
    public Integer getTcid() {
		return tcid;
	}

	public void setTcid(Integer tcid) {
		this.tcid = tcid;
	}

	/**
   	*课程的老师id
   	**/
    @Column(name = "course_teacher_id")
   	private Integer ctid;

    /**
     * 启动实验环境的来源（0课程里启动，1教材里启动），默认0从课程里启动实验
     */
    private Integer comefrom;

    /**
     * 系统类型1为大数据，2为人工智能
     */
    private Integer system;

    /**
	 * notebook报告
	 */
	private String notebook="";
	
	public String getNotebook() {
		return notebook;
	}

	public void setNotebook(String notebook) {
		this.notebook = notebook;
	}

	public Integer getSystem() {
		return system;
	}

	public void setSystem(Integer system) {
		this.system = system;
	}
	
	public Integer getComefrom() {
		return comefrom;
	}

	public void setComefrom(Integer comefrom) {
		this.comefrom = comefrom;
	}

	public Integer getCtid() {
		return ctid;
	}

	public void setCtid(Integer ctid) {
		this.ctid = ctid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the uptime
	 */
	public Date getUptime() {
		return uptime;
	}

	/**
	 * @param uptime the uptime to set
	 */
	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return the eid
	 */
	public Integer getEid() {
		return eid;
	}

	/**
	 * @param eid the eid to set
	 */
	public void setEid(Integer eid) {
		this.eid = eid;
	}

	/**
	 * @return the stepid
	 */
	public Integer getStepid() {
		return stepid;
	}

	/**
	 * @param stepid the stepid to set
	 */
	public void setStepid(Integer stepid) {
		this.stepid = stepid;
	}

	/**
	 * @return the appstatus
	 */
	public Integer getAppstatus() {
		return appstatus;
	}

	/**
	 * @param appstatus the appstatus to set
	 */
	public void setAppstatus(Integer appstatus) {
		this.appstatus = appstatus;
	}

	/**
	 * @return the retrytimes
	 */
	public Integer getRetrytimes() {
		return retrytimes;
	}

	/**
	 * @param retrytimes the retrytimes to set
	 */
	public void setRetrytimes(Integer retrytimes) {
		this.retrytimes = retrytimes;
	}

	/**
	 * @return the logintime
	 */
	public Date getLogintime() {
		return logintime;
	}

	/**
	 * @param logintime the logintime to set
	 */
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	/**
	 * @return the appname
	 */
	public String getAppname() {
		return appname;
	}

	/**
	 * @param appname the appname to set
	 */
	public void setAppname(String appname) {
		this.appname = appname;
	}

	/**
	 * @return the appid
	 */
	public Integer getAppid() {
		return appid;
	}

	/**
	 * @param appid the appid to set
	 */
	public void setAppid(Integer appid) {
		this.appid = appid;
	}

	/**
	 * @return the mode
	 */
	public Integer getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", createtime=" + createtime + ", uptime="
				+ uptime + ", state=" + state + ", level=" + level + ", eid="
				+ eid + ", stepid=" + stepid + ", appstatus=" + appstatus
				+ ", retrytimes=" + retrytimes + ", logintime=" + logintime
				+ ", appname=" + appname + ", appid=" + appid + ", mode="
				+ mode + ", token=" + token + ", cid=" + cid + ", tcid=" + tcid
				+ ", ctid=" + ctid + ", comefrom=" + comefrom + ", system="
				+ system + ", notebook=" + notebook + "]";
	}
	
}
