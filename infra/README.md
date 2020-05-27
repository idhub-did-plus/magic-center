首页
1. 已部署ST列表查询接口
2. 登记信息列表查询接口（已登记信息未部署，包含已审核和未审核状态）

/issue_project/list?status=
/issue_project/list_page?status=startPage=pageSize=

/issue_project/size?status=

登记信息页
3. 登记信息上传接口
4. 登记信息审核接口
/issue_project/save_issuer_information?pid=
/issue_project/save_token_config?pid=

/issue_project/audit?pid=agree=comment=


合约部署页
5. 已部署ST存储接口（分普通股和partition）
/issue_project/token_deployed

发行页
6. ST发行信息存储接口 （Partition \Amount \Receiving address）
7. ST发行信息查询接口

/issurance/list?pid=
/issurance/list_page?pid=startPage=pageSize=

/issurance/size?pid=

文件披露页面
8. 披露文件上传接口
9. 披露文件查询接口


/material/upload_material?pid=name=type=
/material/retrieve_materials?pid=
/material/material_stream?id=




有关broker dealer注册功能：
1. 快速注册，不经审批，即刻完成。这时只填写简单信息。
2. 高级功能须申请专用claim。这时要补充完整相应资料。
接口参见RegistrationController.
其中的signup是不需要登录状态

