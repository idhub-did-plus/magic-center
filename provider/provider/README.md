# provider
### 环境依赖
1. node.js版本8或者8+（如v8.15.0）  
2. 安装npm 
3. 安装yarn
```
npm install -g yarn
```
### 安装工具包
```
npm install
```
### 项目启动
```
yarn serve
```
### 项目打包
```
yarn build
```
### 全局baseURL配置
在src路径下，创建modules文件夹，在新建的baseURL.js文件中进行api配置
src/modules/baseURL.js

```
const baseURL = "接口地址"; //用于请求用户信息的数据托管平台api   

export default {
  baseURL
}
```
### 项目功能描述
1. Metamask钱包签名登录功能
2. 状态为 unreceived 和 processing 的claim订单的展示，及接单功能
3. 用户文件信息，文档信息等用户资料的展示，及claim颁发功能
4. 向合规用户颁发链上claim、向合规用户及其他身份的用户颁发链下claim的功能
5. 对中英双语言的支持