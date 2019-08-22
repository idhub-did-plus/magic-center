# magic-center

1.客户端要设置一个默认address和一个recovery address，默认address是1484的associateaddresses之一。协议本身在associateaddresses中并不区分，但客户端（钱包）可以有default的概念。
2. 默认address和recovery address由一个CredentialProvider接口来提供，该接口由ProviderFactory获得。clientlib项目中提供了mockprovider，android环境中要用真实的provider替换
3. 两个接口：IdentityChain和IdentityChainViewer.其中IdentityChain提供了本地实现和代理实现。viewer都是本地实现。
4.IdentityChain的createIdentity方法有两个，一个是带有全部参数的，一个是不带参数的。不带参数的，recoveryAddress, associateAddress从credential provider中获得，resolver中放1056 resolver合约地址。1484合约本身支持更复杂的场景，但在我们目前的设计中，只是唯一的一个resolver，因此调用不带参数的方法就够了
5.请求的autherntication签名处理和合约代理方法的erc191签名，都是在clientlib内部做处理，使用时不必关心


有关异步处理
1. 根据目前主网交易确认情况，任何链上交易，包括vew类调用，同步方式没有意义，一律是异步调用
2.代理交易的异步机制，目前是轮询
3.合约事件不用于处理调用异步结果，不可靠，造成的链上负载剧增
4.delegation代理的链操作考虑只提供首次开通1484账号，后续添加关联地址等操作一概由用户自己负担了。从应用场景来看，当用户开始用到这些操作时，他应该已经有了真实“业务”，而不会在意相关费用



关于投资人资格申请资料的设计：
1.投资人的特征信息放在FinancialProfile中，比如年收入是否超过30万，个人资产是否超过100万等
2.资料Material：type， name， 和material。目前data是byte【】，文件内容直接存二进制。后面要放进别的存储机制
3.资料的type，资料类型，比如资产证明。资产证明可以有多个资料，那就给它一个名字，比如不动产，银行存款。type和name一起，对同一用户（identity）有唯一性
4.年收入超30万需要银行流水，资产超100万要有资产证明，这种约束和逻辑是体现在用户录入操作上，跟存储不发生关系



