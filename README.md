### 简介

用于快速搭建Android应用，提供了一系列基础功能和部分常见自定义控件，帮助开发者快速搭建稳定的基本框架。建议采用mvp的模式来搭建项目框架

### 关于网络

1.网络框架代码位于network/okhttp目录之下
2.引入okhttp+retrofit网络框架
3.使用方式举例：
post().url(url).addParams("parameter", parameter).build().execute(new StringCallBack());

### 自定义view

1.引入BaseQuickAdapter ，用于快速搭建Recycletview列表
2.提供图片剪裁控件，路径view/cllp_image
3.提供时间选择器控件，路径view/PickTimeView

### 自定义工具类

1.

### 其它

1.引入butterknife
2.引入Glide



