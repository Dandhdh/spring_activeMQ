
����activemq
tomcat���г���

��postman 
post��ʽ��������http://localhost/spring_activeMQ/SendMessage
����body����msg��"��Ҫ��������""

��ȡ������Ϣ
get��������http://localhost/spring_activeMQ/ReceiveMessage

���ü����� QueueMessageListener
������Ϣ�����е�ʱ���Զ���ȡ

    <bean id="queueMessageListener" class="com.danyy.Filter.QueueMessageListener" />

    <!-- ��ʾע����Ϣ����������Queue�����������ӹ�����������Ŀ����demoQueueDestination�������������涨��ļ����� -->
    <bean id="queueListenerContainer"
    class="org.springframework.jms.listener.DefaultMessageListenerContainer">
    <property name="connectionFactory" ref="connectionFactory" />
    <property name="destination" ref="demoQueueDestination" />
    <property name="messageListener" ref="queueMessageListener" />
    </bean>
    