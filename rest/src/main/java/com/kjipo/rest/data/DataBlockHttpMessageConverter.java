package com.kjipo.rest.data;

import com.kjipo.data.DataBlock;
import com.kjipo.proto.MessagesProto;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;


public class DataBlockHttpMessageConverter extends AbstractHttpMessageConverter<DataBlock> {
    public static final String X_PROTOBUF_SCHEMA_HEADER = "X-Protobuf-Schema";
    public static final String X_PROTOBUF_MESSAGE_HEADER = "X-Protobuf-Message";


    public DataBlockHttpMessageConverter() {
        super(MediaType.parseMediaType("application/x-protobuf;charset=UTF-8"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return DataBlock.class == clazz;
    }

    @Override
    protected DataBlock readInternal(Class<? extends DataBlock> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        MessagesProto.DoubleDataBlock doubleDataBlock = MessagesProto.DoubleDataBlock.newBuilder()
                .mergeFrom(inputMessage.getBody())
                .build();
        return new DataBlock(doubleDataBlock.getAverage(), doubleDataBlock.getMinimum(), doubleDataBlock.getMaximum());
    }

    @Override
    protected void writeInternal(DataBlock dataBlock, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        MessagesProto.DoubleDataBlock doubleDataBlock = MessagesProto.DoubleDataBlock.newBuilder()
                .setAverage(dataBlock.getAverage())
                .setMinimum(dataBlock.getMin())
                .setMaximum(dataBlock.getMax())
                .build();
        outputMessage.getHeaders().set(X_PROTOBUF_SCHEMA_HEADER,
                doubleDataBlock.getDescriptorForType().getFile().getName());
        outputMessage.getHeaders().set(X_PROTOBUF_MESSAGE_HEADER,
                doubleDataBlock.getDescriptorForType().getFullName());
        FileCopyUtils.copy(doubleDataBlock.toByteArray(), outputMessage.getBody());
    }

}
