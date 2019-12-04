* [接口说明文档 ](#接口说明文档 )
   * [响应格式](#响应格式)
   * [magic-backend](#magic-backend)
       * [发起claim申请](#发起claim申请)
       * [创建1484身份](#创建1484身份)
       * [事件查询](#事件查询)
       * [存储档案](#存储档案)
       * [上传投资人类型](#上传投资人类型)
       * [检索存档](#检索存档)
       * [上传文件](#上传文件)
       * [检索材料](#检索材料)
       * [删除材料](#删除材料)
       * [读取文件](#读取文件)
       * [通过id读取文件](#通过id读取文件)
       * [聚合身份](#聚合身份)
       * [恢复身份信息](#恢复身份信息)
       * [请求模板数据](#请求模板数据)
       * [请求身份信息](#请求身份信息)
  *  [magic-provider](#magic-provider)
       * [请求订单列表](#请求订单列表)
       * [请求订单总数](#请求订单总数)
       * [请求身份信息](#请求身份信息)
       * [接单](#接单)
       * [拒绝接单](#拒绝接单)
       * [拒绝颁发claim](#拒绝颁发claim)
       * [颁发claim](#颁发claim)	
       * [请求文件信息数据流](#请求文件信息数据流)
       * [获取kyc状态](#获取kyc状态)
       * [请求kyc调用记录](#请求kyc调用记录)
       * [文件信息审核接口](#文件信息审核接口)
  *  [magic-clientlib](#magic-clientlib)
       * [说明](#说明)
       * [介绍](#介绍)

# 接口说明文档 

#### 响应格式

- `success`-`boolean`：请求是否成功
- `msg`-`string`：状态码
- `message`-`String`： 状态信息 
- `data`-`mixed`：响应结果

## magic-backend

### 发起claim申请

#### 说明

用于发起claim申请

#### 地址

/claim/order

#### 请求方式

POST

#### 方法

```JAVA
public MagicResponse order(@RequestBody ClaimOrder order, String identity)
```

#### 参数

- `ClaimOrder` `Object`：
-   `identity`  `String`:钱包地址
  
-  `requestedClaimType` `String`: claim类型
  
- `identity` `String`:钱包地址

#### 返回值示例

```json
{
	"success": true,
	"message": "success!",
	"data": null
}
```

### 创建1484身份

#### 说明

用delegation方式创建1484身份

#### 地址

/delegation/create_identity

#### 请求方式

POST

#### 方法

```java
public MagicResponse createIdentity(@RequestBody String json)
```

#### 参数

`json` `String`: 参数json串

```json
  {
    "recoveryAddress": "0x0000000",
    "associatedAddress": "0x00000",
    "providers": [
       "0x0000"
     ],
    "resolvers": [
      "0x00000"
     ],
     "v": "",
     "r": "",
     "s": "",
     "timestamp": "",
     "async": "boolean"
  }
```
####  返回值示例

```json
{
	"success": true,
	"message": "success!",
	"data": null
}
```

### 事件查询

#### 说明

用于查询事件结果，可轮询做监听

#### 地址

/event/query_events

#### 请求方式

GET

#### 方法

```java
public MagicResponse getChainEvent(String identity)
```

#### 参数

`identity` `String`:数字身份的默认关联地址

#### 返回值示例

```json
{
	"success": true,
	"message": "success!",
	"data": null
}
```

### 存储档案

#### 说明

用来上传身份信息

#### 地址

/storage/store_archive

#### 请求方式

POST

#### 方法

```java
public MagicResponse storeArchive(@RequestBody IdentityArchive archive, String identity)
```

#### 参数

`IdentityArchive` `Object`:

​                         `BasicInfo` `Object`

​                                             `email` `String` ：邮箱

​                                              `taxId`  `String` ：纳税号

​                                               `ssn` `String` ：社保号

​                         `IdentityInfo` `Object` ：

​                                                `Name` `Object` ：

​                                                           `firstName` `String` ：名字第一个字

​                                                           `lastName` `String` 名字最后一个

​                                                           `middleName` `String` ：名字中间

​                                                  `Address` `Object` ：

​                                                            `addressSequence` `List<String>`：地址集合

​                                                             `postalCode` `String`  ：邮编号码

​                                                   `birthday` `Date` ：生日

​                                                   `country` `String` ： 国籍

​                                                   `residenceCountry` `String`  ：居住国

​                                                   `idcardNumber` `String` ： 身份证号

​                                                   `passportNumber` `String` ：护照号码

​                                                   `phoneNumber` `String` ：手机号

​                                                   `gender` `String` ：性别

​                              `extension` `Map<String,Object>` : 扩展字段

`identity` `String` ：数字身份关联关联地址

#### 返回值示例

```json
{
	"success": true,
	"message": "success!",
	"data": null
}
```

### 上传投资人类型

#### 说明

上传合格购买者或合格投资人所属类型

#### 地址

/storage/store_financial_profile

#### 请求方式

POST

#### 方法

```java
public MagicResponse storeFinancialProfile(@RequestBody FinancialProfile profile,   String identity)
```

#### 参数

`FinancialProfile` `Object` :

​                           `buyerType` `String` ：合格购买者类型

​                           `investorType` `String`：合格投资人类型 

#### 返回值示例

```json
{
	"success": true,
	"message": "success!",
	"data": null
}
```

### 检索存档

#### 说明

用来查询存档

#### 地址

/storage/store_financial_profile

#### 请求方式

GET

#### 方法

```java
public MagicResponse<IdentityArchive> retrieveArchive(String identity)
```

#### 参数

`identity` `String` : 数字身份的默认关联地址

#### 返回参数

`IdentityArchive` `Object`:

​                         `BasicInfo` `Object`

​                                             `email` `String` ：邮箱

​                                              `taxId`  `String` ：纳税号

​                                               `ssn` `String` ：社保号

​                         `IdentityInfo` `Object` ：

​                                                `Name` `Object` ：

​                                                           `firstName` `String` ：名字第一个字

​                                                           `lastName` `String` 名字最后一个

​                                                           `middleName` `String` ：名字中间

​                                                  `Address` `Object` ：

​                                                            `addressSequence` `List<String>`：地址集合

​                                                             `postalCode` `String`  ：邮编号码

​                                                   `birthday` `Date` ：生日

​                                                   `country` `String` ： 国籍

​                                                   `residenceCountry` `String`  ：居住国

​                                                   `idcardNumber` `String` ： 身份证号

​                                                   `passportNumber` `String` ：护照号码

​                                                   `phoneNumber` `String` ：手机号

​                                                   `gender` `String` ：性别

#### 返回json示例

```json
{
	"success": true,
	"message": null,
	"data": {
		"identityInfo": {
			"name": {
				"firstName": "",
				"lastName": "李",
				"middleName": ""
			},
			"address": {
				"addressSequence": [{
					"name": "国家",
					"value": null
				}, {
					"name": "省份",
					"value": ""
				}, {
					"name": "城市",
					"value": ""
				}, {
					"name": "区县",
					"value": ""
				}, {
					"name": "详细地址",
					"value": ""
				}],
				"postalCode": ""
			},
			"birthday": null,
			"country": null,
			"residenceCountry": null,
			"idcardNumber": "",
			"passportNumber": "",
			"phoneNumber": "",
			"gender": ""
		},
		"basicInfo": {
			"email": "",
			"taxId": "",
			"ssn": ""
		},
		"financialProfile": null
	}
}
```

### 上传文件

#### 说明

用来上传文件

#### 地址

/storage/upload_material

#### 请求方式

POST

#### 方法

```java
public MagicResponse upload(@RequestParam("file") MultipartFile file, String identity, String type, String name)
```

#### 参数

`MultipartFile` `String` : 文件

`identity` `String` ：数字身份默认关联地址

`type` `String` ：文件类型

`name` `String` ：文件名

#### 返回值示例

```json
{
	"success": true,
	"message": "success!",
	"data": null
}
```

### 检索材料

#### 说明

用来查询材料

#### 地址

/storage/retrieve_materials

#### 请求方式

GET

#### 方法

```java
public MagicResponse<List<Material>> retrieveMaterials(String identity)
```

#### 参数

`identity` `String` ：数字身份默认关联地址

#### 返回参数

`Material` `Object` :

​                    `id` `String` :文件id

​                    `identity` `String` ：上传文件的地址

​                    `type` `String` ：文件类型

​                    `name` `String` ：文件名字

​                    `url` `String` ：文件url

​                    `ext` `String` ：文件ext

#### 返回json示例

```json
{
	"success": true,
	"message": "success!",
	"data": [{
		"id": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87daBankStatement测试",
		"identity": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87da",
		"type": "BankStatement",
		"name": "测试",
		"url": null,
		"ext": "jpg"
	}, {
		"id": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87daBankBalanceStatement测试大文件",
		"identity": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87da",
		"type": "BankBalanceStatement",
		"name": "测试大文件",
		"url": null,
		"ext": "mp3"
	}]
}
```

### 删除材料

#### 说明

删除提交的材料

#### 地址

/storage/remove_material

#### 请求方式

GET

#### 方法

```java
public MagicResponse removeMaterial(String identity, String type, String name)
```

#### 参数

`identity` `String` :数字身份默认关联地址

`type` `String` : 文件类型

`name` `String` : 文件名

#### 返回值示例

```json
{
	"success": true,
	"message": "success!",
	"data": null
}
```

### 读取文件

#### 说明

通过地址，文件类型，名字获取文件

#### 地址

/storage/material_stream

#### 请求方式

GET

#### 方法

```java
public void materialStream(String identity, String type, String name, HttpServletResponse response)
```

#### 参数

`identity` `String` :数字身份默认关联地址

`type` `String` : 文件类型

`name` `String` : 文件名

#### 返回值示例

文件流

### 通过id读取文件

#### 说明

通过文件的id获取文件

#### 地址

/storage/material_stream_id

#### 请求方式

GET

#### 方法

```java
private void stream(HttpServletResponse response, String id)
```

#### 参数

`id` `String` :文件的id

#### 返回值示例

文件流

### 聚合身份

#### 说明

讲身份地址和ein绑定

#### 地址

/storage/aggregate_identity

#### 请求方式

GET

#### 方法

```java
public MagicResponse aggregateIdentity(String identity, long ein)
```

#### 参数

`identity` `String` :地址

`ein` `long` : 数字身份的EIN

#### 返回值示例

```json
{
	"success": true,
	"message": "success!",
	"data": null
}
```

### 恢复身份信息

#### 说明

恢复身份之后调用，获取身份上传的信息

#### 地址 

/storage/recover

#### 请求方式

GET

#### 方法

```java
public MagicResponse<IdentityEverything> recover(String identity)
```

#### 参数

`identity` `String` ：身份恢复之后新的关联地址

#### 返回参数

`IdentityEverything` `Object` ：

​                                      `IdentityArchive` `Object`:

​                                                `BasicInfo` `Object`

​                                                          `email` `String` ：邮箱

​                                                          `taxId`  `String` ：纳税号

​                                                           `ssn` `String` ：社保号

​                                              `IdentityInfo` `Object` ：

​                                                    `Name` `Object` ：

​                                                           `firstName` `String` ：名字第一个字

​                                                           `lastName` `String` 名字最后一个

​                                                           `middleName` `String` ：名字中间

​                                                   `Address` `Object` ：

​                                                            `addressSequence` `List<String>`：地址集合

​                                                             `postalCode` `String`  ：邮编号码

​                                                   `birthday` `Date` ：生日

​                                                   `country` `String` ： 国籍

​                                                   `residenceCountry` `String`  ：居住国

​                                                   `idcardNumber` `String` ： 身份证号

​                                                   `passportNumber` `String` ：护照号码

​                                                   `phoneNumber` `String` ：手机号

​                                                   `gender` `String` ：性别

​                                      `List<Material>` `List` :

​                                                  `Material` `Object` :

​                                                               `id` `String` :文件id

​                                                               `identity` `String` ：上传文件的地址

​                                                               `type` `String` ：文件类型

​                                                               `name` `String` ：文件名字

​                                                               `url` `String` ：文件url

​                                                               `ext` `String` ：文件ext

​                                    `List<String> claims` `List` ：申请的claim列表

#### 返回参数示例

```json
{
    "success": true,
    "message": null,
    "data": {
        "archive": {
            "identityInfo": {
                "name": {
                    "firstName": "",
                    "lastName": "李",
                    "middleName": ""
                },
                "address": {
                    "addressSequence": [
                        {
                            "name": "国家",
                            "value": null
                        },
                        {
                            "name": "省份",
                            "value": ""
                        },
                        {
                            "name": "城市",
                            "value": ""
                        },
                        {
                            "name": "区县",
                            "value": ""
                        },
                        {
                            "name": "详细地址",
                            "value": ""
                        }
                    ],
                    "postalCode": ""
                },
                "birthday": null,
                "country": null,
                "residenceCountry": null,
                "idcardNumber": "",
                "passportNumber": "",
                "phoneNumber": "",
                "gender": ""
            },
            "basicInfo": {
                "email": "",
                "taxId": "",
                "ssn": ""
            },
            "financialProfile": null
        },
        "materials": [
            {
                "id": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87daBankStatement测试",
                "identity": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87da",
                "type": "BankStatement",
                "name": "测试",
                "url": null,
                "ext": "jpg"
            },
            {
                "id": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87daBankBalanceStatement测试大文件",
                "identity": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87da",
                "type": "BankBalanceStatement",
                "name": "测试大文件",
                "url": null,
                "ext": "mp3"
            }
        ],
        "claims": [
            "\"{\\\"@context\\\":[\\\"https://w3id.org/credentials/v1\\\",\\\"https://idhub.com/credentials/v1\\\"],\\\"type\\\":[\\\"Credential\\\"],\\\"claim\\\":{\\\"id\\\":\\\"did:erc1056:did:erc1056:0x5f9eb509efc563b55fcf959b6e19e6d3342d87da\\\",\\\"claimType\\\":\\\"IDHub_SVIP\\\",\\\"country\\\":\\\"US\\\",\\\"jurisdiction\\\":\\\"unknown\\\"},\\\"issued\\\":\\\"2019-11-09\\\",\\\"issuer\\\":\\\"did:erc1056:0x458b6862cac349a47658ef7251f22054ffa0d4ed\\\",\\\"expires\\\":\\\"2020-01-08\\\",\\\"signature\\\":{\\\"created\\\":\\\"2019-11-09\\\",\\\"creator\\\":\\\"did:erc1056:0x458b6862cac349a47658ef7251f22054ffa0d4ed\\\",\\\"nonce\\\":\\\"0b9fc022-20ff-4445-b185-94d10cadfaa8\\\",\\\"domain\\\":null,\\\"type\\\":\\\"ES256\\\",\\\"signatureValue\\\":\\\"MEcCIPBh0gSBsa5DdnkLrxLUHa2UNcxfQgzZJp2u2Q0x/ip6AiBWQoDZesYwsGXZTGj/TdYWGtJvfxhXeUReoL6w2aes/gIBHA==\\\"}}\"",
            "\"{\\\"@context\\\":[\\\"https://w3id.org/credentials/v1\\\",\\\"https://idhub.com/credentials/v1\\\"],\\\"type\\\":[\\\"Credential\\\"],\\\"claim\\\":{\\\"id\\\":\\\"did:erc1056:did:erc1056:0x5f9eb509efc563b55fcf959b6e19e6d3342d87da\\\",\\\"claimType\\\":\\\"IDHub_VIP\\\",\\\"country\\\":null,\\\"jurisdiction\\\":\\\"unknown\\\"},\\\"issued\\\":\\\"2019-12-04\\\",\\\"issuer\\\":\\\"did:erc1056:0x458b6862cac349a47658ef7251f22054ffa0d4ed\\\",\\\"expires\\\":\\\"2020-02-02\\\",\\\"signature\\\":{\\\"created\\\":\\\"2019-12-04\\\",\\\"creator\\\":\\\"did:erc1056:0x458b6862cac349a47658ef7251f22054ffa0d4ed\\\",\\\"nonce\\\":\\\"d13a1425-b7b8-4af2-be8c-69916f1a8ed4\\\",\\\"domain\\\":null,\\\"type\\\":\\\"ES256\\\",\\\"signatureValue\\\":\\\"MEcCIOCJBqwYmBow6giCdOfGWe6gBc6wxW69XbdAGBOr5RO6AiA1qzey8+UBx1XB/qHR2+JMtfrnx/q8ujXVZIReLL63nQIBGw==\\\"}}\""
        ]
    }
}
```



### 请求模板数据

#### 说明

用于请求后端配置的模板数据

#### 地址

/template/template_data

#### 请求方式

GET

#### 方法

```JAVA
public MagicResponse<TemplateData> retrieveArchive(String identity)
```

#### 参数

- `identity` `String`: 地址

#### 返回值示例

```json
{
	"success": true,
	"message": null,
	"data": {
		"templates": {
			"default": [{
				"name": "state",
				"value": null
			}, {
				"name": "city",
				"value": null
			}, {
				"name": "neighbourhood",
				"value": null
			}, {
				"name": "streetNumber",
				"value": null
			}, {
				"name": "houseNumber",
				"value": null
			}, {
				"name": "apartmentNumber",
				"value": null
			}],
			"china": [{
				"name": "province",
				"value": null
			}, {
				"name": "city",
				"value": null
			}, {
				"name": "county",
				"value": null
			}, {
				"name": "street",
				"value": null
			}, {
				"name": "residence",
				"value": null
			}, {
				"name": "menpaihao",
				"value": null
			}],
			"us": [{
				"name": "state",
				"value": null
			}, {
				"name": "city",
				"value": null
			}, {
				"name": "neighbourhood",
				"value": null
			}, {
				"name": "streetNumber",
				"value": null
			}, {
				"name": "houseNumber",
				"value": null
			}, {
				"name": "apartmentNumber",
				"value": null
			}]
		},
		"enumsets": {
			"province@china": ["beijing", "shanghai", "tianjin"]
		}
	}
}
```



### 请求身份信息

#### 说明

用于三方平台请求用户信息

#### 地址

/thirdparty/identity_information

#### 请求方式

POST

#### 方法

```JAVA
public MagicResponse<IdentityInformation> retrieveArchive(@RequestBody String jwt, String identity)
```

#### 参数

- `jwt` `String`：向认证服务器轮询返回的jwt字段
- `identity` `String`: 用户的identity字段，从jwt中解码得到

#### 返回值类型

```json
{
	"success": true,
	"message": null,
	"data": {
		"archive": {
			"identityInfo": {
				"name": {
					"firstName": "赖",
					"lastName": "赖",
					"middleName": ""
				},
				"address": {
					"addressSequence": [{
						"name": "国家",
						"value": "US"
					}, {
						"name": "省份",
						"value": ""
					}, {
						"name": "城市",
						"value": ""
					}, {
						"name": "区县",
						"value": ""
					}, {
						"name": "详细地址",
						"value": ""
					}],
					"postalCode": ""
				},
				"birthday": null,
				"country": null,
				"residenceCountry": null,
				"idcardNumber": "",
				"passportNumber": "",
				"phoneNumber": "00000",
				"gender": "女"
			},
			"basicInfo": {
				"email": "",
				"taxId": "",
				"ssn": ""
			},
			"financialProfile": null
		},
		"materials": [{
			"id": "0x1803d919477cc5635bb8b92c78e4cf59b03e4124IDFront身份证",
			"identity": "0x1803d919477cc5635bb8b92c78e4cf59b03e4124",
			"type": "IDFront",
			"name": "身份证",
			"url": null,
			"ext": "png"
		}],
		"claims": []
	}
}
```



## magic-Provider



### 请求订单列表

#### 说明

用于申请不同状态的订单列表

#### 地址

/order/list

#### 请求方式

GET

#### 方法

```JAVA
public MagicResponse<List<ProviderOrder>> list(ProviderOrderState state, int startPage, int pageSize)
```

#### 参数

- `state` `String`：

  - unreceived
  - dropped
  - processing
  - issued
  - refused

  可传如上值，分别对应不同状态的列表数据

- `startPage` `int`: 分页起始页

- `pageSize ` `int`: 每页数据个数

#### 返回值类型

```json
{
	"success":true,
	"message":null,
	"data":[{
		"id":"7462005f-e3f2-43a5-9db0-18bd68474d66",
		"owner":null,
		"state":"unreceived",
		"createTime":"2019-12-03T08:41:02.545+0000",
		"order":{
			"id":"7462005f-e3f2-43a5-9db0-18bd68474d66",
			"identity":"0x5f9eb509efc563b55fcf959b6e19e6d3342d87da",
			"claimType":"IDHub_VIP",
			"country":null,
			"jurisdiction":null
			}
	}]
}
```

### 请求订单总数

#### 说明

用于申请不同状态的订单的总数

#### 地址

/order/size

#### 请求方式

GET

#### 方法

```JAVA
public MagicResponse<Integer> size(ProviderOrderState state)
```

#### 参数

- `state` `String`：

  - unreceived
  - dropped
  - processing
  - issued
  - refused

  可传如上值，分别对应不同状态的列表数据

#### 返回值类型

```json
{
	"success":true,
	"message":null,
	"data":1
}
```

### 请求身份信息

#### 说明

用于申请用户的身份信息

#### 地址

/order/identity_archive

#### 请求方式

GET

#### 方法

```JAVA
public MagicResponse<IdentityEntity> identityArchive(String identity)
```

#### 参数

- `identity` `String`：十六进制身份地址

#### 返回值类型

```json
{
	"success": true,
	"message": null,
	"data": {
		"id": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87da",
		"transactionId": null,
		"data": {
			"identity": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87da",
			"archive": {
				"identityInfo": {
					"name": {
						"firstName": "",
						"lastName": "李",
						"middleName": ""
					},
					"address": {
						"addressSequence": [{
							"name": "国家",
							"value": null
						}, {
							"name": "省份",
							"value": ""
						}, {
							"name": "城市",
							"value": ""
						}, {
							"name": "区县",
							"value": ""
						}, {
							"name": "详细地址",
							"value": ""
						}],
						"postalCode": ""
					},
					"birthday": null,
					"country": null,
					"residenceCountry": null,
					"idcardNumber": "",
					"passportNumber": "",
					"phoneNumber": "",
					"gender": ""
				},
				"basicInfo": {
					"email": "",
					"taxId": "",
					"ssn": ""
				},
				"financialProfile": null
			},
			"materials": [{
				"id": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87daBankStatement测试",
				"identity": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87da",
				"type": "BankStatement",
				"name": "测试",
				"url": null,
				"ext": "jpg"
			}, {
				"id": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87daBankBalanceStatement测试大文件",
				"identity": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87da",
				"type": "BankBalanceStatement",
				"name": "测试大文件",
				"url": null,
				"ext": "mp3"
			}]
		},
		"updateTime": null
	}
}
```

### 接单

#### 说明

对列表中的订单进行接单

#### 地址

/order/receive

#### 请求方式

GET

#### 方法

```JAVA
public MagicResponse<IdentityEntity> receive(String orderId)
```

#### 参数

- `orderId` `String`：订单列表返回数据中的id

#### 返回值类型

```json
{
	"success": true,
	"message": null,
	"data": {
		"id": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87da",
		"transactionId": null,
		"data": {
			"identity": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87da",
			"archive": {
				"identityInfo": {
					"name": {
						"firstName": "",
						"lastName": "李",
						"middleName": ""
					},
					"address": {
						"addressSequence": [{
							"name": "国家",
							"value": null
						}, {
							"name": "省份",
							"value": ""
						}, {
							"name": "城市",
							"value": ""
						}, {
							"name": "区县",
							"value": ""
						}, {
							"name": "详细地址",
							"value": ""
						}],
						"postalCode": ""
					},
					"birthday": null,
					"country": null,
					"residenceCountry": null,
					"idcardNumber": "",
					"passportNumber": "",
					"phoneNumber": "",
					"gender": ""
				},
				"basicInfo": {
					"email": "",
					"taxId": "",
					"ssn": ""
				},
				"financialProfile": null
			},
			"materials": [{
				"id": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87daBankStatement测试",
				"identity": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87da",
				"type": "BankStatement",
				"name": "测试",
				"url": null,
				"ext": "jpg"
			}, {
				"id": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87daBankBalanceStatement测试大文件",
				"identity": "0x5f9eb509efc563b55fcf959b6e19e6d3342d87da",
				"type": "BankBalanceStatement",
				"name": "测试大文件",
				"url": null,
				"ext": "mp3"
			}]
		},
		"updateTime": null
	}
}
```

### 拒绝接单

#### 说明

对列表中的订单进行拒绝接单

#### 地址

/order/drop

#### 请求方式

GET

#### 方法

```JAVA
public MagicResponse drop(String orderId)
```

#### 参数

- `orderId` `String`：订单列表返回数据中的id

#### 返回值示例

```json
{
	"success": true,
	"message": "success!",
	"data": null
}
```

### 拒绝颁发claim

#### 说明

拒绝颁发订单中申请的claim

#### 地址

/order/refuse_claim

#### 请求方式

GET

#### 方法

```JAVA
public MagicResponse refuseClaim(String orderId)
```

#### 参数

- `orderId` `String`：订单列表返回数据中的id

#### 返回值示例

```json
{
	"success": true,
	"message": "success!",
	"data": null
}
```

### 颁发claim

#### 说明

颁发订单中申请的claim

#### 地址

/order/issue_claim

#### 请求方式

GET

#### 方法

```JAVA
public MagicResponse<String> issueClaim(String orderId)
```

#### 参数

- `orderId` `String`：订单列表返回数据中的id

#### 返回值示例

```json
{
    "success": true,
    "message": null,
    "data": "{\"@context\":[\"https://w3id.org/credentials/v1\",\"https://idhub.com/credentials/v1\"],\"type\":[\"Credential\"],\"claim\":{\"id\":\"did:erc1056:did:erc1056:0x5f9eb509efc563b55fcf959b6e19e6d3342d87da\",\"claimType\":\"IDHub_SVIP\",\"country\":null,\"jurisdiction\":\"unknown\"},\"issued\":\"2019-12-04\",\"issuer\":\"did:erc1056:0x458b6862cac349a47658ef7251f22054ffa0d4ed\",\"expires\":\"2020-02-02\",\"signature\":{\"created\":\"2019-12-04\",\"creator\":\"did:erc1056:0x458b6862cac349a47658ef7251f22054ffa0d4ed\",\"nonce\":\"90490057-c175-452e-9482-6c562aab3692\",\"domain\":null,\"type\":\"ES256\",\"signatureValue\":\"MEcCIGmhgwhwLFALRlUNRGyO1aWvDCASH9hBmrYcq+DN09SbAiBKMgAyyrNvbD/poX9kQdMec17v4hkXVL74bfBoUPTf7wIBGw==\"}}"
}
```

### 请求文件信息数据流

#### 说明

请求文件信息数据流

#### 地址

/order/material_stream

#### 请求方式

GET

#### 方法

```JAVA
public void stream(String id, HttpServletResponse response)
```

#### 参数

- `id` `String`：身份信息中文件信息的id
- `response` `HttpServletResponse`：**可传true**

#### 返回值类型

文件流

### 获取kyc状态

#### 说明

获取kyc状态

#### 地址

/idm/evaluate

#### 请求方式

GET

#### 方法

```JAVA
public MagicResponse<Interaction> evaluate(String orderId)
```

#### 参数

- `orderId` `String`：订单列表返回数据中的id

#### 返回参数

`Interaction` `Object`:

​             `id` `String`:

​             `orderId` `String`:

​             `transactionId` `String`:

​             `request` `Object`

​             `response` `Object`

#### 返回值示例

```json
{
    "success": true,
    "message": null,
    "data": {
        "id": "",
        "orderId": "",
        "transactionId": "",
        "request": "",
        "response": ""
    }
}
```

### 请求kyc调用记录

#### 说明

用于请求kyc调用记录

#### 地址

/order/interactions

#### 请求方式

GET

#### 方法

```JAVA
public MagicResponse<List<Interaction>> interactions(String orderId)
```

#### 参数

- `orderId` `String`：订单列表返回数据中的id

#### 返回值示例

```
{
    "success": true,
    "message": null,
    "data": [
      {
        "id": "",
        "orderId": "",
        "transactionId": "",
        "request": "",
        "response": ""
      }
    ]
}
```

### 文件信息审核接口

#### 说明

用于服务器验证文件信息是否验证通过

#### 地址

/idm/document_verification

#### 请求方式

GET

#### 方法

```JAVA
public MagicResponse documentVerification(String orderId, String materialId,String description)
```

#### 参数

- `orderId` `String`：订单列表返回数据中的id
- `id ` `String`：身份信息中文件信息的id
- `description` `String`: 对于审核结果的描述

#### 返回值示例

```json
{
	"success": true,
	"message": "success!",
	"data": null
}
```



## magic-clientlib

#### 说明

调用后台服务,以及与合约交互的代码库，主要用于APP端

#### 介绍

1. `ApiFactory`类是整个调用的入口类
2. `ProviderFactory` 类，用于获取CredentialProvider类对象，需要配置此类构造CredentialProvider

与后端接口相关：

```java
ApiFactory.getIncomingService();//获取IncomingService实例， 用于EtherScan轮询读取钱包的交易信息
```

```java
ApiFactory.getArchiveStorage();//获取IdentityStorage实例， 用于调用APP与后端交互接口
```

```java
ApiFactory.getIdentityChainDelegate();//获取IdentityChainDelegate实例，创建时可以身份调用
```

```java
ApiFactory.getEventListenerService();//获取EventListenerService实例，轮询监听服务器下发事件
```

```java
ApiFactory.getKycService();//获取ClaimService实例，用于APP的Cliam相关调用
```

```java
ApiFactory.getTemplateService();//获取TemplateService实例，获取模板数据
```

与合约交互相关：

```java
ApiFactory.getIdentityChainLocal();//获取IdentityChain实例，用于APP身份相关合约调用
```

```java
ApiFactory.getIdentityChainViewer();//获取IdentityChain实例，用于APP身份相关合约调用
```



