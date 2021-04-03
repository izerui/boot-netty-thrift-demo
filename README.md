# spring boot 整合 thrift、drift(原facebook的swift、nifty的替代品)、netty 构建高性能服务示例

* thrift: https://thrift.apache.org/ (稳定)
* nifty: https://github.com/facebookarchive/nifty (不再维护)
* swift: https://github.com/facebookarchive/swift (不再维护)
* drift: https://github.com/prestodb/drift.git (活跃)
* netty: https://netty.io/ (稳定)


运行示例：mvn install

客户端调用同时支持基于注释的drift调用方式，也支持原生的 thrift 通过 example.thrift 生成的客户端调用，本例只提供第一种用例。