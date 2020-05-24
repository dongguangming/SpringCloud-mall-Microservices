package com.example.demo.common;

/**
 * 
 * @author dgm
 * @describe "公用常量"
 * @date 2020年5月21日
 */
public class ConstantUtil {
	
	/**
	 * 默认null为空字符串
	 */
	public static final String DEFAULT_NULL = "";
	
	/**
	 * 默认密码
	 */
	public static final String DEFAULT_PASSWORD = "123456";

	/**
	 * 账号级别
	 */
	public static final int USERLEVEL_ADMIN = 0;
	public static final int USERLEVEL_TEACHER = 1;
	public static final int USERLEVEL_STUDENT = 2;

	/**
	 * 账号状态
	 */
	public static final int USERSTATE_DISABLED = 0;
	public static final int USERSTATE_ENABLED = 1;

	/**
	 * 班级状态
	 */
	public static final int CLASSSTATE_DISABLED = 0;
	public static final int CLASSSTATE_ENABLED = 1;
	
	/**
	 * 应用类型
	 */
	public static final int APPTYPE_HADOOP = 1;
	public static final int APPTYPE_HBASE = 2;
	public static final int APPTYPE_HIVE = 3;
	public static final int APPTYPE_SPARK = 4;
	public static final int APPTYPE_STORM = 5;
	public static final int APPTYPE_PYTHON = 6;
	public static final int APPTYPE_R = 7;//预留（python-r分开）
	public static final int APPTYPE_VNC = 8;//vnc可视化
	public static final int APPTYPE_HADOOP_273 = 9;//Hadoop 2.7.3
	public static final int APPTYPE_HBASE_112 = 10;//Hbase 1.1.2
	public static final int APPTYPE_SPARK_211 = 11;//Spark 2.1.1
	public static final int APPTYPE_NOTEBOOK = 12;//notebook
	public static final int APPTYPE_HADOOP_MEETING = 13;
	public static final int APPTYPE_HBASE_MEETING = 14;
	public static final int APPTYPE_HIVE_MEETING = 15;
	public static final int APPTYPE_SPARK_MEETING = 16;
	public static final int APPTYPE_STORM_MEETING = 17;
	public static final int APPTYPE_MYSQL = 18;
	public static final int APPTYPE_TF17 = 101;//Tensorflow1.7+CUDA9
	public static final int APPTYPE_TF14 = 102;//Tensorflow1.4+CUDA8
	public static final int APPTYPE_CAFFE = 103;//Caffe+CUDA8
	public static final int APPTYPE_CAFFEEDU = 104;//Caffe+CUDA8
	public static final int APPTYPE_TFEDU = 105;//Tensorflow1.4+CUDA8
	
	/**
	 * 应用类型的组件版本号
	 */
	public static final String APPTYPE_HADOOP_VERSON = "Hadoop 2.7.1";
	public static final String APPTYPE_HBASE_VERSON = "Hbase 1.1.2";
	public static final String APPTYPE_HIVE_VERSON = "Hive 1.2.1";
	public static final String APPTYPE_SPARK_VERSON = "Spark 1.6.0";
	public static final String APPTYPE_STORM_VERSON = "Storm 0.1.0";
	public static final String APPTYPE_PYTHON_VERSON = "Python 2.7.5和3.5.2";
	public static final String APPTYPE_R_VERSON = "R 3.4.4";//预留（python-r分开）
	public static final String APPTYPE_VNC_VERSON = "Ubuntu LTS 16.04";//vnc可视化
	public static final String APPTYPE_HADOOP_273_VERSION = "Hadoop 2.7.3";//Hadoop 2.7.3
	public static final String APPTYPE_HBASE_112_VERSION = "Hbase 1.1.2";//Hbase 1.1.2
	public static final String APPTYPE_SPARK_211_VERSION = "Spark 2.1.1";//Spark 2.1.1
	public static final String APPTYPE_NOTEBOOK_VERSION = "Notebook";//Notebook
	public static final String APPTYPE_HADOOP_VERSON_MEETING = "Hadoop";
	public static final String APPTYPE_HBASE_VERSON_MEETING = "Hbase";
	public static final String APPTYPE_HIVE_VERSON_MEETING = "Hive";
	public static final String APPTYPE_SPARK_VERSON_MEETING = "Spark";
	public static final String APPTYPE_STORM_VERSON_MEETING = "Storm";
	public static final String APPTYPE_MYSQL_VERSON = "Mysql 5.7";
	public static final String APPTYPE_TF17_VERSION = "Tensorflow1.7+CUDA9";//Tensorflow1.7+CUDA9
	public static final String APPTYPE_TF14_VERSION = "Tensorflow1.4+CUDA8";//Tensorflow1.4+CUDA8
	public static final String APPTYPE_CAFFE_VERSION = "Caffe+CUDA8";//Caffe+CUDA8
	public static final String APPTYPE_CAFFEEDU_VERSION = "Caffe+CUDA8";//Caffe+CUDA8
	public static final String APPTYPE_TF14EDU_VERSION = "Tensorflow1.4+CUDA8";//Tensorflow1.4+CUDA8
	
	/**
	 * 应用名称
	 */
	public static final String APPNAME_HADOOP = "Hadoop 2.7.1集群";
	public static final String APPNAME_HBASE = "Hbase 1.1.2集群";
	public static final String APPNAME_HIVE = "Hive 1.2.1集群";
	public static final String APPNAME_SPARK = "Spark 1.6.0集群";
	public static final String APPNAME_STORM = "Storm 0.1.0集群";
	public static final String APPNAME_PYTHON = "Python 2.7.5和3.5.2语言";
	public static final String APPNAME_R = "R 3.4.4语言";//预留（python-r分开）
	public static final String APPNAME_VNC = "Ubuntu LTS 16.04  桌面";
	public static final String APPNAME_HADOOP_273 = "Hadoop 2.7.3集群";//Hadoop 2.7.3
	public static final String APPNAME_HBASE_112 = "Hbase 1.1.2新版集群";//Hbase 1.1.2
	public static final String APPNAME_SPARK_211 = "Spark 2.1.1集群";//Spark 2.1.1
	public static final String APPNAME_NOTEBOOK = "Notebook";//Spark 2.1.1
	public static final String APPNAME_HADOOP_MEETING = "培训大会专用Hadoop集群";
	public static final String APPNAME_HBASE_MEETING = "培训大会专用Hbase集群";
	public static final String APPNAME_HIVE_MEETING = "培训大会专用Hive集群";
	public static final String APPNAME_SPARK_MEETING = "培训大会专用Spark集群";
	public static final String APPNAME_STORM_MEETING = "培训大会专用Storm集群";
	public static final String APPNAME_MYSQL = "mysql环境";
	public static final String APPNAME_TF17 = "Tensorflow1.7+CUDA9";//Tensorflow1.7+CUDA9
	public static final String APPNAME_TF14 = "Tensorflow1.4+CUDA8";//Tensorflow1.4+CUDA8
	public static final String APPNAME_CAFFE = "Caffe+CUDA8";//Caffe+CUDA8
	public static final String APPNAME_CAFFEEDU = "CaffeEdu";//Caffe+CUDA8
	public static final String APPNAME_TF14EDU = "Tensorflow1.4+CUDA8";//Tensorflow1.4+CUDA8
	
	/**
	 * 应用描述
	 */
	public static final String APPDESC_HADOOP = "包含常用大数据组件，支持一键搭建Hadoop集群";
	public static final String APPDESC_HBASE = "包含常用大数据组件，支持一键搭建HBase集群";
	public static final String APPDESC_HIVE = "包含常用大数据组件，支持一键搭建Hive集群";
	public static final String APPDESC_SPARK = "包含常用大数据组件，支持一键搭建Spark集群";
	public static final String APPDESC_STORM = "包含常用大数据组件，支持一键搭建Storm集群";
	public static final String APPDESC_PYTHON = "提供Python语言环境，内置常用挖掘算法包";
	public static final String APPDESC_R = "提供R语言环境，内置常用挖掘算法包";//预留（python-r分开）
	public static final String APPDESC_VNC = "Ubuntu LTS 16.04  桌面";
	public static final String APPDESC_HADOOP_273 = "包含jdk 1.8.0, scala 2.11.12, Hadoop 2.7.3, Hbase 1.1.2, Spark 2.1.1, phoenix 4.14.0, sqoop 1.4.7";//Hadoop 2.7.3
	public static final String APPDESC_HBASE_112 = "包含jdk 1.8.0, scala 2.11.12, Hadoop 2.7.3, Hbase 1.1.2, Spark 2.1.1, phoenix 4.14.0, sqoop 1.4.7";//Hbase 1.1.2
	public static final String APPDESC_SPARK_211 = "包含jdk 1.8.0, scala 2.11.12, Hadoop 2.7.3, Hbase 1.1.2, Spark 2.1.1, phoenix 4.14.0, sqoop 1.4.7";//Spark 2.1.1
	public static final String APPDESC_NOTEBOOK = "提供Notebook环境";
	public static final String APPDESC_HADOOP_MEETING = "包含常用大数据组件，支持一键搭建Hadoop集群";
	public static final String APPDESC_HBASE_MEETING = "包含常用大数据组件，支持一键搭建HBase集群";
	public static final String APPDESC_HIVE_MEETING = "包含常用大数据组件，支持一键搭建Hive集群";
	public static final String APPDESC_SPARK_MEETING = "包含常用大数据组件，支持一键搭建Spark集群";
	public static final String APPDESC_STORM_MEETING = "包含常用大数据组件，支持一键搭建Storm集群";
	public static final String APPDESC_MYSQL = "mysql环境";
	public static final String APPDESC_TF17 = "包含Tensorflow1.7+CUDA9的Ubuntu服务器";
	public static final String APPDESC_TF14 = "包含Tensorflow1.4+CUDA8的Ubuntu服务器";
	public static final String APPDESC_CAFFE = "包含Caffe+CUDA8组件的CentOS7服务器";
	public static final String APPDESC_CAFFEEDU = "包含Caffe+CUDA8组件的CentOS7服务器大会专用";
	public static final String APPDESC_TF14EDU = "包含Tensorflow1.4+CUDA8的Ubuntu服务器大会专用";
	
	/**
	 * 应用状态
	 */
	public static final int APPSTATUS_UNUSED = 0; // 未使用
	public static final int APPSTATUS_CREATING = 1; // 创建中
	public static final int APPSTATUS_SUCCESS = 2; // 创建成功
	public static final int APPSTATUS_FAILED = 3; // 创建失败

	/**
	 * 镜像名称
	 */
	public static final String APPIMAGE_BDRACK = "bdrack"; //六合一镜像
	public static final String APPIMAGE_PR = "experiment"; //python-r镜像包含python和r算法包
	public static final String APPIMAGE_VNC = "ubuntu-desktop"; //vnc可视化
	public static final String APPIMAGE_BDRACK_PART = "bd_part"; //bdrack_part
	public static final String APPIMAGE_NOTEBOOK = "pjupyter"; //Notebook
	public static final String APPIMAGE_BDRACK_MEETING = "bdrackmeeting"; //培训大会bdrack meeting
	public static final String APPIMAGE_BDRACK_MYSQL = "mysql"; //bdrack mysql
	public static final String APPIMAGE_TF17 = "tf17_cuda9"; //Tensorflow1.7+CUDA9
	public static final String APPIMAGE_TF14 = "tf14_cuda8"; //Tensorflow1.4+CUDA8
	public static final String APPIMAGE_CAFFE = "caffe_cuda8"; //Caffe+CUDA8
	public static final String APPIMAGE_CAFFEEDU = "caffe_edu"; //培训大会Caffe+CUDA8
	public static final String APPIMAGE_TF14EDU = "tf_edu"; //培训大会Tensorflow1.4+CUDA8
	
	/**
	 * 镜像状态
	 */
	public static final int APPSTATE_FREE = 1;
	public static final int APPSTATE_USING = 2;

	/**
	 * 一键搭建支持
	 */
	public static final int INSTALL_DISABLED = 0;
	public static final int INSTALL_ENABLED = 1;

	/**
	 * 容器类型
	 */
	public static final int CONTAINER_MASTER = 0;// O为master
	public static final int CONTAINER_SLAVE = 1;// 1为slaveN(1...n)
	public static final int CONTAINER_CLIENT = 2;// 2为client

	/**
	 * 报告状态
	 */
	public static final int REPORT_SAVED = 0;// 已保存
	public static final int REPORT_SUBMITTED = 1;// 已提交
	public static final int REPORT_MARKED = 2;// 老师已给报告打分

	/**
	 * 一键搭建类型
	 */
	public static final int INSTALL_HADOOP = 1;//1为hadoop
	public static final int INSTALL_HBASE = 2;//2为hbase
	public static final int INSTALL_HIVE_HBASE = 3;//3为hive_hbase
	public static final int INSTALL_SPARK = 4;//4为spark
	public static final int INSTALL_STORM = 5;//5为storm
    
	/**
	 * 一键搭建任务的状态
	 */
	public static final int INSTALL_TASKSTATUS_RUNNING = 1;//1为正在搭建
	public static final int INSTALL_TASKSTATUS_SUCCESS = 2;//2为搭建成功
	public static final int INSTALL_TASKSTATUS_FAIL = 3;//3为搭建失败
	
	/**
	 * 自由模式下集群类型图片url
	 */
	public static final String APPTYPE_IMAGE_URL_PREFIX = "/static/assets/images/";// 集群类型图片url前缀
	public static final String APPTYPE_IMAGE_URL_SUFFIX = ".jpg";// 集群类型图片url后缀
	
	/**
	 * 题目类型(1-N,1为单选题,2为简答题,3 多选择题，4为是非题)
	 */
	public static final Integer QUESTION_TYPE_SINGLE_CHOICE = 1;//1单选择题
	public static final Integer QUESTION_TYPE_QA = 2;//2简答题
	public static final Integer QUESTION_TYPE_MULTIPLE_CHOICE = 3;//3 多选择题
	public static final Integer QUESTION_TYPE_YN = 4;//4 是非题题
	
	/**
	 * 试卷类型(1自动评分，2非自动评分)
	 */
	public static final Integer PAPER_TYPE_CHOICE = 1;//1自动评分
	public static final Integer PAPER_TYPE_QA = 2;//2,非自动评分
	
	/**
	 * 试卷的状态
	 */
	public static final Integer PAPER_STATUS_NOPUBLISH = 1;//1. 未发布，可编辑和删除
	public static final Integer PAPER_STATUS_PUBLISH = 2;//2. 已发布，可查看和停用
	public static final Integer PAPER_STATUS_FINISH = 3;//3. 已结束，可审阅
	
	public static final Integer DEFAULT_TEACHER_ID = 1;//初始化sql加入默认老师
	
	/**
	 * 显示状态类型(-1已废弃, 0不可见, 1可用可见,2待审核状态,3被退回状态)
	 */
	public static final Integer DISPLAY_DISCARD = -1;//已废弃
	public static final Integer DISPLAY_DISABLE = 0;//不可见
	public static final Integer DISPLAY_ENABLE = 1;//可用可见
	public static final Integer DISPLAY_CHECK = 2;//实验待审核状态
	public static final Integer DISPLAY_BACK = 3;//实验被退回状态
	public static final Integer DISPLAY_ADD = 4;//实验新增状态
	
	/**
	 * 实验组开启状态
	 */
	public static final Integer EXP_GROUP_ENABLE = 2;//可用可见
	
	/**
	 * 标签类型
	 */
	public static final int TAG_FREE = 0;// O为自由标签
	public static final int TAG_FIRST = 1;// 1为一级标签
	public static final int TAG_SECOND = 2;// 2为二级标签
}
