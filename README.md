### 简介

用于快速搭建Android应用，提供了一系列基础功能，帮助开发者快速搭建稳定的基本框架。建议采用mvp的模式来搭建项目框架

### 关于网络

1.网络框架代码位于network.okhttp目录之下
2.引入okhttp+retrofit网络框架
3.使用方式举例：
post().url(url).addParams("parameter", parameter).build().execute(new StringCallBack());





