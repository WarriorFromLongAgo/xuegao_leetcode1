# 拉源码

github：

gitee：https://gitee.com/apache/rocketmq

# 源码编译与启动

## 全局install

```
mvn -Prelease-all -DskipTests clean install -U
```

## 启动namesrv

```
进入org.apache.rocketmq.namesrv.NamesrvStartup，执行main方法

会抛出错误, 原因是没有设置配置ROCKETMQ_HOME文件路径

Please set the ROCKETMQ_HOME variable in your environment to match the lo
cation of the RocketMQ installation  
```

解决办法

修改NamesrvStartup类createNamesrvController方法

```java
if (null == namesrvConfig.getRocketmqHome()) {
    System.out.printf("Please set the %s variable in your environment to match the location of the RocketMQ installation%n", MixAll.ROCKETMQ_HOME_ENV);
    System.exit(-2);
}

namesrvConfig.setRocketmqHome("E:\\GiteeDemo\\rocketmq\\distribution");
if (null == namesrvConfig.getRocketmqHome()) {
    System.out.printf("Please set the %s variable in your environment to match the location of the RocketMQ installation%n", MixAll.ROCKETMQ_HOME_ENV);
    System.exit(-2);
}
```

![在这里插入图片描述](rocketmq/16f7411197c0573a)

可以修改源码，增加rocketHome的配置，distribution是一个子项目的名称

也可以通过修改启动参数设置rocketHome， 在idea的run configurators中配置 VM参数为

-Drocketmq.home.dir=D:\code\java_yuanma\rocketmq\distribution

同时还需要设置user.home，指定日志文件路径

E:\GiteeDemo\xuegao-rocketmq-read\rocketmq\user.home

-Duser.home=D:\code\java_yuanma\rocketmq\user.home

还需要配置namesrv的ip和port



-n 127.0.0.1：9876

![在这里插入图片描述](rocketmq/16f7411197e090f5)

然后运行NamesrvStartup#main启动namesrv

![img](rocketmq/16f7411199dc9ad5)

![image-20210315155718101](rocketmq/image-20210315155718101.png)

## 启动broker

进入org.apache.rocketmq.broker.BrokerStartup，可以执行main方法，同样需要先配置rocketHome

在org.apache.rocketmq.broker.BrokerStartup#createBrokerController中修改源码，增加rocketHome的配置

```java
brokerConfig.setRocketmqHome("E:\\GiteeDemo\\rocketmq\\distribution");
if (null == brokerConfig.getRocketmqHome()) {
    System.out.printf("Please set the %s variable in your environment to match the location of the RocketMQ installation", MixAll.ROCKETMQ_HOME_ENV);
    System.exit(-2);
}
```

也可以通过修改启动参数设置rocketHome， 在idea的run configurators中配置 VM参数为

-Drocketmq.home.dir=D:\code\java_yuanma\rocketmq\distribution

同时还需要设置user.home，指定日志文件路径

-Duser.home=E:\GiteeDemo\rocketmq\user.home

还需要配置程序参数

‐n 127.0.0.0:9876 -c E:\GiteeDemo\rocketmq\distribution\conf\broker.conf

![img](rocketmq/16f741119b9219eb)

然后运行BrokerStartup#main启动broker

![broker启动成功](rocketmq/image-20210315161235109.png)

如果出现以下情况，ip不对，是因为多网卡的问题

![在这里插入图片描述](rocketmq/16f74111c549bc2e)

这时需要修改D:\code\java_yuanma\rocketmq\distribution\conf\broker.conf 此配置文件

增加brokerIP1 = 127.0.0.1

![img](rocketmq/16f74111c9d88592)

### 测试

启动Producer发消息，成功

如果出现No Topic Route Info 错误：

broker.conf 中增加配置

```
autoCreateTopicEnable = true
namesrvAddr = 127.0.0.1:9876
```



# 源码结构

1）broker:broker模块（broker启动进程）。

2）client：消息客户端，包含消息生产者、消息消费者相关类。

3）common：公共包。

4）dev：开发者信息（非源代码）。

5）distribution：部署实例文件夹（非源代码）。

6）example:RocketMQ示例代码。

7）filter：消息过滤相关基础类。

8）filtersrv：消息过滤服务器实现相关类（Filter启动进程）。

9）logappender：日志实现相关类。

10）namesrv:NameServer实现相关类（NameServer启动进程）。

11）openmessaging：消息开放标准，正在制定中。

12）remoting：远程通信模块，基于Netty。

13）srvutil：服务器工具类。

14）store：消息存储实现相关类。

15）style:checkstyle相关实现。

16）test：测试相关类。17）tools：工具类，监控命令相关实现类。

# 设计理念和目标

基于主题的发布与订阅模式，其核心功能包括消息发送、消息存储（Broker）、消息消费，

整体设计追求简单与性能第一，主要体现在如下三个方面。

1，NameServer设计极其简单，抛弃zookeeper充当注册中心，自研NameServer来实现元数据的管理（Topic路由信息等）

从实际需求出发，因为Topic路由信息无须在集群之间保持强一致，追求最终一致性，并且能容忍分钟级的不一致。正是基于此种情况，RocketMQ的NameServer集群之间互不通信，极大地降低了NameServer实现的复杂程度，对网络的要求也降低了不少，但是性能相比较Zookeeper有了极大的提升。

2，高效的IO存储机制，RocketMQ追求消息发送的高吞吐量，RocketMQ的消息存储文件设计成文件组的概念，组内单个文件大小固定，方便引入内存映射机制，所有主题的消息存储基于顺序写，极大地提供了消息写性能，同时为了兼顾消息消费与消息查找，引入了消息消费队列文件与索引文件。

3，容忍存在设计缺陷，适当将某些工作下放给RocketMQ使用者。如何保证消息一定能被消息消费者消费，并且保证只消费一次。

RocketMQ的设计者给出的解决办法是不解决这个难题，而是退而求其次，只保证消息被消费者消费，但设计上允许消息被重复消费，这样极大地简化了消息中间件的内核，使得实现消息发送高可用变得非常简单与高效，消息重复问题由消费者在消息消费时实现幂等。



# NameServer

brocker在启动时向所有的nameserver注册自己

producer在发送消息的时候，先去nameserver获取所有的broker地址，然后根据负载均衡算法选择一个broker发送

nameserver和所有的broker保持长连接，同时保持心跳的检测，30秒一次，检测到broker宕机，将其从路由表移除

但是路由变化不会马上通知消息生产者

## NameServer启动流程

### NameServerConfig

（NameServer业务参数）

```
参数来源

1）-c configFile通过-c命令指定配置文件的路径。
2）使用“--属性名 属性值”，例如 --listenPort 9876。
```

NameServerConfig

```
相关参数

rocketmqhome：rocketmq主目录，可以通过-Drocketmq.home.dir=path或通过设置环境变量ROCKETMQ_HOME来配置
kvConfigPath：NameServer存储KV配置属性的持久化路径
configStorePath：orderMessageEnable：是否支持顺序消息，默认是不支持。nameServer默认配置文件路径，不生效。
orderMessageEnable：是否支持顺序消息，默认是不支持。
```

### NettyServerConfig

（NameServer网络参数）

```
listenPort：NameServer监听端口，该值默认会被初始化为9876
serverWorkerThreads：Netty业务线程池线程个数
serverCallbackExecutorThreads：Netty public任务线程池线程个数，Netty网络设计，根据业务类型会创建不同的线程池，
	比如处理消息发送、消息消费、心跳检测等。如果该业务类型（RequestCode）未注册线程池，则由public线程池执行。
serverSelectorThreads：IO线程池线程个数，主要是NameServer、Broker端解析请求、返回相应的线程个数，
	这类线程主要是处理网络请求的，解析请求包，然后转发到各个业务线程池完成具体的业务操作，然后将结果再返回调用方。
serverOnewaySemaphoreValue：send oneway消息请求并发度（Broker端参数）
serverAsyncSemaphoreValue：异步消息发送最大并发度（Broker端参数
serverChannelMaxIdleTimeSeconds：网络连接最大空闲时间，默认120s。如果连接空闲时间超过该参数设置的值，连接将被关闭。由netty实现
serverSocketSndBufSize：网络socket发送缓存区大小，默认64k。
serverSocketRcvBufSize：网络socket接收缓存区大小，默认64k。
serverPooledByteBufAllocatorEnable：ByteBuffer是否开启缓存，建议开启
useEpollNativeSelector：是否启用Epoll IO模型，Linux环境建议开启
```



在启动NameServer时，可以先使用．/mqnameserver -c configFile -p打印当前加载的配置属性。



### 创建NameServerController

根据启动属性创建NamesrvController实例，并初始化该实例，NameServerController实例为NameServer核心控制器。

NamesrvStartup#start

```
调用NamesrvController#Initialize
注册JVM钩子函数并启动服务器，以便监听Broker、消息生产者的网络请求。

这里主要是向读者展示一种常用的编程技巧
如果代码中使用了线程池，一种优雅停机的方式就是注册一个JVM钩子函数，在JVM进程关闭之前，先将线程池关闭，及时释放资源。
```

NamesrvController#Initialize

```
 public boolean initialize() {
        // 加载KV的配置
        this.kvConfigManager.load();
        // 创建NettyServer网络处理对象
        this.remotingServer = new NettyRemotingServer(this.nettyServerConfig, this.brokerHousekeepingService);

        // 创建一个线程容量为 serverWorkerThreads 的固定长度的线程池，
        // 该线程池供 DefaultRequestProcessor 类使用，实现具体的默认的请求命令处理。
        this.remotingExecutor = Executors.newFixedThreadPool(
        	nettyServerConfig.getServerWorkerThreads(), new ThreadFactoryImpl("RemotingExecutorThread_"));

        this.registerProcessor();

        // 然后开启两个定时任务，在RocketMQ中此类定时任务统称为心跳检测。
        // 定时任务1:NameServer每隔10s扫描一次Broker，移除处于不激活状态的Broker。
        // 定时任务2:nameServer每隔10分钟打印一次KV配置。
        this.scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                NamesrvController.this.routeInfoManager.scanNotActiveBroker();
            }
        }, 5, 10, TimeUnit.SECONDS);

        this.scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                NamesrvController.this.kvConfigManager.printAllPeriodically();
            }
        }, 1, 10, TimeUnit.MINUTES);
	 return true;
}
```

## NameServer路由注册、故障剔除

NameServer主要作用是为消息生产者和消息消费者提供关于主题Topic的路由信息，那么NameServer需要存储路由的基础信息，还要能够管理Broker节点，包括路由注册、路由删除等功能。

NameServer路由实现类：org.apache.rocketmq.namesrv.routeinfo.RouteInfoManager

```java
private static final InternalLogger log = InternalLoggerFactory.getLogger(LoggerName.NAMESRV_LOGGER_NAME);
// NameServer 与 Broker 空闲时长，默认2分钟，在2分钟内 Nameserver 没有收到 Broker 的心跳包，则关闭该连接。
private final static long BROKER_CHANNEL_EXPIRED_TIME = 1000 * 60 * 2;
// 读写锁，用来保护非线程安全容器 HashMap。
private final ReadWriteLock lock = new ReentrantReadWriteLock();

// 每个Broker上存在该主题的队列个数。QueueData队列描述信息，对应如下属性：
// Topic消息队列路由信息，消息发送时根据路由表进行负载均衡。主题与队列关系，记录一个主题的队列分布在哪些Broker上，
private final HashMap<String/* topic */, List<QueueData>> topicQueueTable;
// Broker基础信息，包含brokerName、所属集群名称、主备Broker地址。
private final HashMap<String/* brokerName */, BrokerData> brokerAddrTable;
// Broker集群信息，存储集群中所有Broker名称。
private final HashMap<String/* clusterName */, Set<String/* brokerName */>> clusterAddrTable;
// 当前存活的 Broker,该信息不是实时的，
// Broker状态信息。NameServer 每10S扫描一次所有的 broker,根据心跳包的时间得知 broker的状态，NameServer每次收到心跳包时会替换该信息。
private final HashMap<String/* brokerAddr */, BrokerLiveInfo> brokerLiveTable;
// Broker上的FilterServer列表，用于类模式消息过滤，详细介绍请参考第6章的内容
private final HashMap<String/* brokerAddr */, List<String>/* Filter Server */> filterServerTable;
```

RocketMQ基于订阅发布机制，一个Topic拥有多个消息队列，一个Broker为每一主题默认创建4个读队列4个写队列。

多个Broker组成一个集群，BrokerName由相同的多台Broker组成Master-Slave架构，brokerId为0代表Master，大于0表示Slave。

BrokerLiveInfo中的lastUpdateTimestamp存储上次收到Broker心跳包的时间。



RocketMQ 2主2从数据结构展示图

![RocketMQ 2主2从数据结构展示图](rocketmq/epub_23768928_41)



TopicQueueTable、brokerAddrTable运行时内存结构

![TopicQueueTable、brokerAddrTable运行时内存结构](rocketmq/epub_23768928_42)

BrokerLiveTable、clusterAddrTable运行时内存结构

![BrokerLiveTable、clusterAddrTable运行时内存结构](rocketmq/epub_23768928_43)

## 路由注册

RocketMQ路由注册是通过Broker与NameServer的心跳功能实现的。

Broker启动时向集群中所有的NameServer发送心跳语句，每隔30s向集群中所有NameServer发送心跳包，NameServer收到Broker心跳包时会更新brokerLiveTable缓存中BrokerLiveInfo的lastUpdate Timestamp，然后NameServer每隔10s扫描brokerLiveTable，如果连续120s没有收到心跳包，NameServer将移除该Broker的路由信息同时关闭Socket连接。



Broker发送心跳包

```java
// BrokerController#start

this.scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

    @Override
    public void run() {
        try {
            BrokerController.this.registerBrokerAll(true, false, brokerConfig.isForceRegister());
        } catch (Throwable e) {
            log.error("registerBrokerAll Exception", e);
        }
    }
}, 1000 * 10, Math.max(10000, Math.min(brokerConfig.getRegisterNameServerPeriod(), 60000)), TimeUnit.MILLISECONDS);

```

遍历NameServer列表，Broker消息服务器依次向NameServer发送心跳包。

```java
// BrokerOuterAPI#registerBrokerAll
public List<RegisterBrokerResult> registerBrokerAll(
    final String clusterName,
    final String brokerAddr,
    final String brokerName,
    final long brokerId,
    final String haServerAddr,
    final TopicConfigSerializeWrapper topicConfigWrapper,
    final List<String> filterServerList,
    final boolean oneway,
    final int timeoutMills,
    final boolean compressed) {

    final List<RegisterBrokerResult> registerBrokerResultList = new CopyOnWriteArrayList<>();
    List<String> nameServerAddressList = this.remotingClient.getNameServerAddressList();
    if (nameServerAddressList != null && nameServerAddressList.size() > 0) {

        final RegisterBrokerRequestHeader requestHeader = new RegisterBrokerRequestHeader();
        requestHeader.setBrokerAddr(brokerAddr);
        requestHeader.setBrokerId(brokerId);
        requestHeader.setBrokerName(brokerName);
        requestHeader.setClusterName(clusterName);
        requestHeader.setHaServerAddr(haServerAddr);
        requestHeader.setCompressed(compressed);

        RegisterBrokerBody requestBody = new RegisterBrokerBody();
        requestBody.setTopicConfigSerializeWrapper(topicConfigWrapper);
        requestBody.setFilterServerList(filterServerList);
        final byte[] body = requestBody.encode(compressed);
        final int bodyCrc32 = UtilAll.crc32(body);
        requestHeader.setBodyCrc32(bodyCrc32);
        final CountDownLatch countDownLatch = new CountDownLatch(nameServerAddressList.size());
        for (final String namesrvAddr : nameServerAddressList) {
            brokerOuterExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        RegisterBrokerResult result = registerBroker(namesrvAddr,oneway, timeoutMills,requestHeader,body);
                        if (result != null) {
                            registerBrokerResultList.add(result);
                        }

                        log.info("register broker[{}]to name server {} OK", brokerId, namesrvAddr);
                    } catch (Exception e) {
                        log.warn("registerBroker Exception, {}", namesrvAddr, e);
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }

        try {
            countDownLatch.await(timeoutMills, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
        }
    }

    return registerBrokerResultList;
}
```

网络发送代码

```java
// BrokerOuterAPI#registerBroker
private RegisterBrokerResult registerBroker(
        final String namesrvAddr,
        final boolean oneway,
        final int timeoutMills,
        final RegisterBrokerRequestHeader requestHeader,
        final byte[] body
    ) throws RemotingCommandException, MQBrokerException, RemotingConnectException, RemotingSendRequestException, RemotingTimeoutException,
        InterruptedException {
        RemotingCommand request = RemotingCommand.createRequestCommand(RequestCode.REGISTER_BROKER, requestHeader);
        request.setBody(body);

        if (oneway) {
            try {
                this.remotingClient.invokeOneway(namesrvAddr, request, timeoutMills);
            } catch (RemotingTooMuchRequestException e) {
                // Ignore
            }
            return null;
        }

        RemotingCommand response = this.remotingClient.invokeSync(namesrvAddr, request, timeoutMills);
        assert response != null;
        switch (response.getCode()) {
            case ResponseCode.SUCCESS: {
                RegisterBrokerResponseHeader responseHeader =
                    (RegisterBrokerResponseHeader) response.decodeCommandCustomHeader(RegisterBrokerResponseHeader.class);
                RegisterBrokerResult result = new RegisterBrokerResult();
                result.setMasterAddr(responseHeader.getMasterAddr());
                result.setHaServerAddr(responseHeader.getHaServerAddr());
                if (response.getBody() != null) {
                    result.setKvTable(KVTable.decode(response.getBody(), KVTable.class));
                }
                return result;
            }
            default:
                break;
        }

        throw new MQBrokerException(response.getCode(), response.getRemark(), requestHeader == null ? null : requestHeader.getBrokerAddr());
    }
```

发送心跳包具体逻辑，首先封装请求包头（Header）。

□ brokerAddr:broker地址。

□ brokerId:brokerId, 0:Master；大于0:Slave。

□ brokerName:broker名称。

□ clusterName：集群名称。

□ haServerAddr:master地址，初次请求时该值为空，slave向Nameserver注册后返回。

□ requestBody：



filterServerList。消息过滤服务器列表

topicConfigWrapper。主题配置，topicConfigWrapper内部封装的是Topic Config-Manager中的topicConfigTable，内部存储的是Broker启动时默认的一些Topic, MixAll.SELF_TEST_TOPIC、MixAll.DEFAULT_TOPIC（AutoCreateTopic-Enable=true）、MixAll.BENCHMARK_TOPIC、MixAll.OFFSET_MOVED_EVENT、BrokerConfig#brokerClusterName、BrokerConfig#brokerName。Broker中Topic默认存储在${Rocket_Home}/store/confg/topic.json中。



RocketMQ网络传输基于Netty，每一个请求，RocketMQ都会定义一个RequestCode，然后在服务端会对应相应的网络处理器（processor包中）,只需整库搜索RequestCode即可找到相应的处理逻辑。

## NameServer处理心跳包

org.apache.rocketmq.namesrv.processor.DefaultRequestProcessor网络处理器解析请求类型

如果请求类型为RequestCode.REGISTER_BROKER，则请求最终转发到RouteInfoMan ager#registerBroker。

```java
@Override
public RemotingCommand processRequest(ChannelHandlerContext ctx,
                                      RemotingCommand request) throws RemotingCommandException {

    if (ctx != null) {
        log.debug("receive request, {} {} {}",
                  request.getCode(),
                  RemotingHelper.parseChannelRemoteAddr(ctx.channel()),
                  request);
    }


    switch (request.getCode()) {
        case RequestCode.PUT_KV_CONFIG:
            return this.putKVConfig(ctx, request);
        case RequestCode.GET_KV_CONFIG:
            return this.getKVConfig(ctx, request);
        case RequestCode.DELETE_KV_CONFIG:
            return this.deleteKVConfig(ctx, request);
        case RequestCode.QUERY_DATA_VERSION:
            return queryBrokerTopicConfig(ctx, request);
        case RequestCode.REGISTER_BROKER:
            Version brokerVersion = MQVersion.value2Version(request.getVersion());
            if (brokerVersion.ordinal() >= MQVersion.Version.V3_0_11.ordinal()) {
                return this.registerBrokerWithFilterServer(ctx, request);
            } else {
                return this.registerBroker(ctx, request);
            }
        case RequestCode.UNREGISTER_BROKER:
            return this.unregisterBroker(ctx, request);
        case RequestCode.GET_ROUTEINFO_BY_TOPIC:
            return this.getRouteInfoByTopic(ctx, request);
        case RequestCode.GET_BROKER_CLUSTER_INFO:
            return this.getBrokerClusterInfo(ctx, request);
        case RequestCode.WIPE_WRITE_PERM_OF_BROKER:
            return this.wipeWritePermOfBroker(ctx, request);
        case RequestCode.GET_ALL_TOPIC_LIST_FROM_NAMESERVER:
            return getAllTopicListFromNameserver(ctx, request);
        case RequestCode.DELETE_TOPIC_IN_NAMESRV:
            return deleteTopicInNamesrv(ctx, request);
        case RequestCode.GET_KVLIST_BY_NAMESPACE:
            return this.getKVListByNamespace(ctx, request);
        case RequestCode.GET_TOPICS_BY_CLUSTER:
            return this.getTopicsByCluster(ctx, request);
        case RequestCode.GET_SYSTEM_TOPIC_LIST_FROM_NS:
            return this.getSystemTopicListFromNs(ctx, request);
        case RequestCode.GET_UNIT_TOPIC_LIST:
            return this.getUnitTopicList(ctx, request);
        case RequestCode.GET_HAS_UNIT_SUB_TOPIC_LIST:
            return this.getHasUnitSubTopicList(ctx, request);
        case RequestCode.GET_HAS_UNIT_SUB_UNUNIT_TOPIC_LIST:
            return this.getHasUnitSubUnUnitTopicList(ctx, request);
        case RequestCode.UPDATE_NAMESRV_CONFIG:
            return this.updateConfig(ctx, request);
        case RequestCode.GET_NAMESRV_CONFIG:
            return this.getConfig(ctx, request);
        default:
            break;
    }
    return null;
}
```



RouteInfoManager#registerBroker clusterAddrTable维护

```java
public RegisterBrokerResult registerBroker(
        final String clusterName,
        final String brokerAddr,
        final String brokerName,
        final long brokerId,
        final String haServerAddr,
        final TopicConfigSerializeWrapper topicConfigWrapper,
        final List<String> filterServerList,
        final Channel channel) {
        RegisterBrokerResult result = new RegisterBrokerResult();
        try {
            try {
                this.lock.writeLock().lockInterruptibly();

                Set<String> brokerNames = this.clusterAddrTable.get(clusterName);
                if (null == brokerNames) {
                    brokerNames = new HashSet<String>();
                    this.clusterAddrTable.put(clusterName, brokerNames);
                }
                brokerNames.add(brokerName);

                boolean registerFirst = false;

                BrokerData brokerData = this.brokerAddrTable.get(brokerName);
                if (null == brokerData) {
                    registerFirst = true;
                    brokerData = new BrokerData(clusterName, brokerName, new HashMap<Long, String>());
                    this.brokerAddrTable.put(brokerName, brokerData);
                }
                Map<Long, String> brokerAddrsMap = brokerData.getBrokerAddrs();
                //Switch slave to master: first remove <1, IP:PORT> in namesrv, then add <0, IP:PORT>
                //The same IP:PORT must only have one record in brokerAddrTable
                Iterator<Entry<Long, String>> it = brokerAddrsMap.entrySet().iterator();
                while (it.hasNext()) {
                    Entry<Long, String> item = it.next();
                    if (null != brokerAddr && brokerAddr.equals(item.getValue()) && brokerId != item.getKey()) {
                        it.remove();
                    }
                }

                String oldAddr = brokerData.getBrokerAddrs().put(brokerId, brokerAddr);
                registerFirst = registerFirst || (null == oldAddr);

                if (null != topicConfigWrapper
                    && MixAll.MASTER_ID == brokerId) {
                    if (this.isBrokerTopicConfigChanged(brokerAddr, topicConfigWrapper.getDataVersion())
                        || registerFirst) {
                        ConcurrentMap<String, TopicConfig> tcTable =
                            topicConfigWrapper.getTopicConfigTable();
                        if (tcTable != null) {
                            for (Map.Entry<String, TopicConfig> entry : tcTable.entrySet()) {
                                this.createAndUpdateQueueData(brokerName, entry.getValue());
                            }
                        }
                    }
                }

                BrokerLiveInfo prevBrokerLiveInfo = this.brokerLiveTable.put(brokerAddr,
                    new BrokerLiveInfo(
                        System.currentTimeMillis(),
                        topicConfigWrapper.getDataVersion(),
                        channel,
                        haServerAddr));
                if (null == prevBrokerLiveInfo) {
                    log.info("new broker registered, {} HAServer: {}", brokerAddr, haServerAddr);
                }

                if (filterServerList != null) {
                    if (filterServerList.isEmpty()) {
                        this.filterServerTable.remove(brokerAddr);
                    } else {
                        this.filterServerTable.put(brokerAddr, filterServerList);
                    }
                }

                if (MixAll.MASTER_ID != brokerId) {
                    String masterAddr = brokerData.getBrokerAddrs().get(MixAll.MASTER_ID);
                    if (masterAddr != null) {
                        BrokerLiveInfo brokerLiveInfo = this.brokerLiveTable.get(masterAddr);
                        if (brokerLiveInfo != null) {
                            result.setHaServerAddr(brokerLiveInfo.getHaServerAddr());
                            result.setMasterAddr(masterAddr);
                        }
                    }
                }
            } finally {
                this.lock.writeLock().unlock();
            }
        } catch (Exception e) {
            log.error("registerBroker Exception", e);
        }

        return result;
    }
```



























