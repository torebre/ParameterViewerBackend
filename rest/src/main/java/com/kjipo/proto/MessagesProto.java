// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: messages.proto

package com.kjipo.proto;

public final class MessagesProto {
  private MessagesProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface DoubleDataBlockOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.kjipo.proto.DoubleDataBlock)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required double average = 1;</code>
     */
    boolean hasAverage();
    /**
     * <code>required double average = 1;</code>
     */
    double getAverage();

    /**
     * <code>required double minimum = 2;</code>
     */
    boolean hasMinimum();
    /**
     * <code>required double minimum = 2;</code>
     */
    double getMinimum();

    /**
     * <code>required double maximum = 3;</code>
     */
    boolean hasMaximum();
    /**
     * <code>required double maximum = 3;</code>
     */
    double getMaximum();
  }
  /**
   * Protobuf type {@code com.kjipo.proto.DoubleDataBlock}
   */
  public static final class DoubleDataBlock extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:com.kjipo.proto.DoubleDataBlock)
      DoubleDataBlockOrBuilder {
    // Use DoubleDataBlock.newBuilder() to construct.
    private DoubleDataBlock(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private DoubleDataBlock(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final DoubleDataBlock defaultInstance;
    public static DoubleDataBlock getDefaultInstance() {
      return defaultInstance;
    }

    public DoubleDataBlock getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private DoubleDataBlock(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 9: {
              bitField0_ |= 0x00000001;
              average_ = input.readDouble();
              break;
            }
            case 17: {
              bitField0_ |= 0x00000002;
              minimum_ = input.readDouble();
              break;
            }
            case 25: {
              bitField0_ |= 0x00000004;
              maximum_ = input.readDouble();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.kjipo.proto.MessagesProto.internal_static_com_kjipo_proto_DoubleDataBlock_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.kjipo.proto.MessagesProto.internal_static_com_kjipo_proto_DoubleDataBlock_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.kjipo.proto.MessagesProto.DoubleDataBlock.class, com.kjipo.proto.MessagesProto.DoubleDataBlock.Builder.class);
    }

    public static com.google.protobuf.Parser<DoubleDataBlock> PARSER =
        new com.google.protobuf.AbstractParser<DoubleDataBlock>() {
      public DoubleDataBlock parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new DoubleDataBlock(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<DoubleDataBlock> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int AVERAGE_FIELD_NUMBER = 1;
    private double average_;
    /**
     * <code>required double average = 1;</code>
     */
    public boolean hasAverage() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required double average = 1;</code>
     */
    public double getAverage() {
      return average_;
    }

    public static final int MINIMUM_FIELD_NUMBER = 2;
    private double minimum_;
    /**
     * <code>required double minimum = 2;</code>
     */
    public boolean hasMinimum() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required double minimum = 2;</code>
     */
    public double getMinimum() {
      return minimum_;
    }

    public static final int MAXIMUM_FIELD_NUMBER = 3;
    private double maximum_;
    /**
     * <code>required double maximum = 3;</code>
     */
    public boolean hasMaximum() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required double maximum = 3;</code>
     */
    public double getMaximum() {
      return maximum_;
    }

    private void initFields() {
      average_ = 0D;
      minimum_ = 0D;
      maximum_ = 0D;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasAverage()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasMinimum()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasMaximum()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeDouble(1, average_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeDouble(2, minimum_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeDouble(3, maximum_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeDoubleSize(1, average_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeDoubleSize(2, minimum_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeDoubleSize(3, maximum_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.kjipo.proto.MessagesProto.DoubleDataBlock parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kjipo.proto.MessagesProto.DoubleDataBlock parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kjipo.proto.MessagesProto.DoubleDataBlock parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kjipo.proto.MessagesProto.DoubleDataBlock parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kjipo.proto.MessagesProto.DoubleDataBlock parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kjipo.proto.MessagesProto.DoubleDataBlock parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.kjipo.proto.MessagesProto.DoubleDataBlock parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.kjipo.proto.MessagesProto.DoubleDataBlock parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.kjipo.proto.MessagesProto.DoubleDataBlock parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kjipo.proto.MessagesProto.DoubleDataBlock parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.kjipo.proto.MessagesProto.DoubleDataBlock prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.kjipo.proto.DoubleDataBlock}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.kjipo.proto.DoubleDataBlock)
        com.kjipo.proto.MessagesProto.DoubleDataBlockOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.kjipo.proto.MessagesProto.internal_static_com_kjipo_proto_DoubleDataBlock_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.kjipo.proto.MessagesProto.internal_static_com_kjipo_proto_DoubleDataBlock_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.kjipo.proto.MessagesProto.DoubleDataBlock.class, com.kjipo.proto.MessagesProto.DoubleDataBlock.Builder.class);
      }

      // Construct using com.kjipo.proto.MessagesProto.DoubleDataBlock.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        average_ = 0D;
        bitField0_ = (bitField0_ & ~0x00000001);
        minimum_ = 0D;
        bitField0_ = (bitField0_ & ~0x00000002);
        maximum_ = 0D;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.kjipo.proto.MessagesProto.internal_static_com_kjipo_proto_DoubleDataBlock_descriptor;
      }

      public com.kjipo.proto.MessagesProto.DoubleDataBlock getDefaultInstanceForType() {
        return com.kjipo.proto.MessagesProto.DoubleDataBlock.getDefaultInstance();
      }

      public com.kjipo.proto.MessagesProto.DoubleDataBlock build() {
        com.kjipo.proto.MessagesProto.DoubleDataBlock result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.kjipo.proto.MessagesProto.DoubleDataBlock buildPartial() {
        com.kjipo.proto.MessagesProto.DoubleDataBlock result = new com.kjipo.proto.MessagesProto.DoubleDataBlock(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.average_ = average_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.minimum_ = minimum_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.maximum_ = maximum_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.kjipo.proto.MessagesProto.DoubleDataBlock) {
          return mergeFrom((com.kjipo.proto.MessagesProto.DoubleDataBlock)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.kjipo.proto.MessagesProto.DoubleDataBlock other) {
        if (other == com.kjipo.proto.MessagesProto.DoubleDataBlock.getDefaultInstance()) return this;
        if (other.hasAverage()) {
          setAverage(other.getAverage());
        }
        if (other.hasMinimum()) {
          setMinimum(other.getMinimum());
        }
        if (other.hasMaximum()) {
          setMaximum(other.getMaximum());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasAverage()) {
          
          return false;
        }
        if (!hasMinimum()) {
          
          return false;
        }
        if (!hasMaximum()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.kjipo.proto.MessagesProto.DoubleDataBlock parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.kjipo.proto.MessagesProto.DoubleDataBlock) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private double average_ ;
      /**
       * <code>required double average = 1;</code>
       */
      public boolean hasAverage() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required double average = 1;</code>
       */
      public double getAverage() {
        return average_;
      }
      /**
       * <code>required double average = 1;</code>
       */
      public Builder setAverage(double value) {
        bitField0_ |= 0x00000001;
        average_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required double average = 1;</code>
       */
      public Builder clearAverage() {
        bitField0_ = (bitField0_ & ~0x00000001);
        average_ = 0D;
        onChanged();
        return this;
      }

      private double minimum_ ;
      /**
       * <code>required double minimum = 2;</code>
       */
      public boolean hasMinimum() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required double minimum = 2;</code>
       */
      public double getMinimum() {
        return minimum_;
      }
      /**
       * <code>required double minimum = 2;</code>
       */
      public Builder setMinimum(double value) {
        bitField0_ |= 0x00000002;
        minimum_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required double minimum = 2;</code>
       */
      public Builder clearMinimum() {
        bitField0_ = (bitField0_ & ~0x00000002);
        minimum_ = 0D;
        onChanged();
        return this;
      }

      private double maximum_ ;
      /**
       * <code>required double maximum = 3;</code>
       */
      public boolean hasMaximum() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required double maximum = 3;</code>
       */
      public double getMaximum() {
        return maximum_;
      }
      /**
       * <code>required double maximum = 3;</code>
       */
      public Builder setMaximum(double value) {
        bitField0_ |= 0x00000004;
        maximum_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required double maximum = 3;</code>
       */
      public Builder clearMaximum() {
        bitField0_ = (bitField0_ & ~0x00000004);
        maximum_ = 0D;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.kjipo.proto.DoubleDataBlock)
    }

    static {
      defaultInstance = new DoubleDataBlock(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.kjipo.proto.DoubleDataBlock)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_kjipo_proto_DoubleDataBlock_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_kjipo_proto_DoubleDataBlock_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016messages.proto\022\017com.kjipo.proto\"D\n\017Dou" +
      "bleDataBlock\022\017\n\007average\030\001 \002(\001\022\017\n\007minimum" +
      "\030\002 \002(\001\022\017\n\007maximum\030\003 \002(\001B \n\017com.kjipo.pro" +
      "toB\rMessagesProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_kjipo_proto_DoubleDataBlock_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_kjipo_proto_DoubleDataBlock_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_com_kjipo_proto_DoubleDataBlock_descriptor,
        new java.lang.String[] { "Average", "Minimum", "Maximum", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
