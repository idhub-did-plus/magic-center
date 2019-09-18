# magic-provider
	provider是一个web应用，而不是dapp，它是一个使用者是律所等投资claim证书颁发机构。一个provider持有一个数字身份，以这个身份签发证书。这个证书存在于web服务器中，而不是dapp的钱包中。基本功能：
	
 1. 账号登陆
 2. 显示claim订单列表，显示申请的claim类型，是否已接受等信息，按照收单日期排序
 3. 接单
 4. 选择一个订单，显示申请的claim类型，显示浏览申请人档案（信息以及文档资料列表），显示kyc api调用记录，包括请求和返回值
 5. 首次kyc evaluation调用以及evaluation重新调用，上传验证kyc文档
 
 6. 获取kyc状态
 7. 颁发证书或者拒绝颁发
 8. 历史查询等功能
 9. 账号管理
 
 除ui交互功能，provider是由后台逻辑轮询backend的orderbook api，自动将订单接入。但真正接受需要receive动作（ui接单），并且有失败可能（已被别的provider接受）
 directTo订单是定向发送给某个provider，但也需要接受动作
 只有接单之后才有权限获取用户档案，因此订单信息中应有国家、司法区等信息，便于provider判断决定是否接单



