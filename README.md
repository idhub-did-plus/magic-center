## magic-node

  1. identity mind 调用今后需要剥离出来，作为一个kyc provider接入网络。kyc provider从网络中订阅或主动获得kyc订单并处理，以弱化中心化的成都。compliance即使不能够短期形成去中心化，弱中心化也是一个小目标。因此compliance也需要构建类似于0x这样的网络
  2. 0x协议支持了一个去中心化交易所网络，目前我们应在关注甚至移植它的relay backend frontend项目
  3. magic-backend并不是一个应用中心服务器，而是magic network的一个网络结点，目前它有一下角色：chain delegation、钱包数据托管、kyc订单中继，如同0x的订单中继结点类似。还可以扩充其他角色功能。但这些角色，都是magic网络结点的角色，而不是应用中心服务器。
  4. 体系应以革新加务实的态度，找到演进路径