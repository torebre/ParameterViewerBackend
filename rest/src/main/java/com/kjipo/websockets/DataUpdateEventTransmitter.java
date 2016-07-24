package com.kjipo.websockets;



public interface DataUpdateEventTransmitter {

    void addMessageConsumer(MessageConsumer messageConsumer);

    void removeMessageConsumer(MessageConsumer messageConsumer);

}
