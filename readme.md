
启动activemq
tomcat运行程序

用postman 
post方式请求链接http://localhost/spring_activeMQ/SendMessage
请求body参数msg："需要传的内容""

获取队列信息
get请求连接http://localhost/spring_activeMQ/ReceiveMessage

配置监听器 QueueMessageListener
当有消息进队列的时候自动获取

    <bean id="queueMessageListener" class="com.danyy.Filter.QueueMessageListener" />

    <!-- 显示注入消息监听容器（Queue），配置连接工厂，监听的目标是demoQueueDestination，监听器是上面定义的监听器 -->
    <bean id="queueListenerContainer"
    class="org.springframework.jms.listener.DefaultMessageListenerContainer">
    <property name="connectionFactory" ref="connectionFactory" />
    <property name="destination" ref="demoQueueDestination" />
    <property name="messageListener" ref="queueMessageListener" />
    </bean>
    