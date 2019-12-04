# Magic-Center

`magic-center`是 `Magic Circle` 构建身份服务和ST服务的网络的基础组件。

## `magic-node`设计原则

1. `identity mind` 调用今后需要剥离出来，作为一个 `kyc provider` 接入网络。`kyc provider` 从网络中订阅或主动获得kyc订单并处理，以弱化中心化的程度。`compliance` 即使不能够短期形成去中心化，弱中心化也是一个小目标。因此 `compliance` 也需要构建类似于0x这样的网络
2. 0x协议支持了一个去中心化交易所网络，目前我们应在关注甚至移植它的relay backend frontend项目
3. `magic-backend` 并不是一个应用中心服务器，而是 `magic network` 的一个网络结点，目前它有一下角色：`chain delegation` 、` 钱包数据托管` 、 `kyc订单中继` ，如同0x的订单中继结点类似。还可以扩充其他角色功能。但这些角色，都是magic网络结点的角色，而不是应用中心服务器。
4. 体系应以革新加务实的态度，找到演进路径

## Requirements

* [Java](https://www.java.com/)
* [MongoDB](https://www.mongodb.com/)
* [Node](https://nodejs.org/en/)
* [yarn](https://www.yarnpkg.com/)
* [Vue](https://vuejs.org/)
* [AWS S3](https://s3.console.aws.amazon.com/s3/home)

## Installation

```
git clone https://github.com/idhub-did-plus/magic-center.git

cd magic-center
```

## Usage

### config

`backend server` 的基本配置项在 `backend/src/main/resources/application.yml` ；
`provider server` 的基本配置项在 `provider/src/main/resources/application.yml` ；
配置项包括：
* server port
* mongodb link
* web3j web3-provider
* aws-s3 bucketname and key

跨域配置分别在`backend/src/main/java/com/idhub/magic/backend/CorsConfig.java`和`backend/src/main/java/com/idhub/magic/backend/CorsConfig.java`

### package

```
// install maven package
cd common
mvn clean install -Dmaven.test.skip=true

cd providerlib
mvn clean install -Dmaven.test.skip=true

cd verclaim
mvn clean install -Dmaven.test.skip=true

cd clientlib
mvn clean install -Dmaven.test.skip=true

// package server code
cd backend
mvn clean package -Dmaven.test.skip=true

cd provider
mvn clean package -Dmaven.test.skip=true

// build proiver web page
cd provider/provider
npm install
yarn build
```

### run

```
cd backend
java -jar ./target/magic-backend-0.0.1-SNAPSHOT.jar

cd ../provider
java -jar ./target/magic-provider-0.0.1-SNAPSHOT.jar
```
