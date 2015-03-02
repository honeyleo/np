// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ArenaReqPro.proto

package cn.huizhi.car.pb.player;

public final class ArenaReq_Protocol {
  private ArenaReq_Protocol() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ArenaReqProOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // optional int32 type = 1;
    /**
     * <code>optional int32 type = 1;</code>
     */
    boolean hasType();
    /**
     * <code>optional int32 type = 1;</code>
     */
    int getType();

    // optional bytes data = 2;
    /**
     * <code>optional bytes data = 2;</code>
     */
    boolean hasData();
    /**
     * <code>optional bytes data = 2;</code>
     */
    com.google.protobuf.ByteString getData();
  }
  /**
   * Protobuf type {@code NetProtocol.ArenaReqPro}
   */
  public static final class ArenaReqPro extends
      com.google.protobuf.GeneratedMessage
      implements ArenaReqProOrBuilder {
    // Use ArenaReqPro.newBuilder() to construct.
    private ArenaReqPro(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ArenaReqPro(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ArenaReqPro defaultInstance;
    public static ArenaReqPro getDefaultInstance() {
      return defaultInstance;
    }

    public ArenaReqPro getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ArenaReqPro(
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
            case 8: {
              bitField0_ |= 0x00000001;
              type_ = input.readInt32();
              break;
            }
            case 18: {
              bitField0_ |= 0x00000002;
              data_ = input.readBytes();
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
      return cn.huizhi.car.pb.player.ArenaReq_Protocol.internal_static_NetProtocol_ArenaReqPro_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return cn.huizhi.car.pb.player.ArenaReq_Protocol.internal_static_NetProtocol_ArenaReqPro_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro.class, cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro.Builder.class);
    }

    public static com.google.protobuf.Parser<ArenaReqPro> PARSER =
        new com.google.protobuf.AbstractParser<ArenaReqPro>() {
      public ArenaReqPro parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ArenaReqPro(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ArenaReqPro> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // optional int32 type = 1;
    public static final int TYPE_FIELD_NUMBER = 1;
    private int type_;
    /**
     * <code>optional int32 type = 1;</code>
     */
    public boolean hasType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 type = 1;</code>
     */
    public int getType() {
      return type_;
    }

    // optional bytes data = 2;
    public static final int DATA_FIELD_NUMBER = 2;
    private com.google.protobuf.ByteString data_;
    /**
     * <code>optional bytes data = 2;</code>
     */
    public boolean hasData() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional bytes data = 2;</code>
     */
    public com.google.protobuf.ByteString getData() {
      return data_;
    }

    private void initFields() {
      type_ = 0;
      data_ = com.google.protobuf.ByteString.EMPTY;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, type_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, data_);
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
          .computeInt32Size(1, type_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, data_);
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

    public static cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro prototype) {
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
     * Protobuf type {@code NetProtocol.ArenaReqPro}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqProOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return cn.huizhi.car.pb.player.ArenaReq_Protocol.internal_static_NetProtocol_ArenaReqPro_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return cn.huizhi.car.pb.player.ArenaReq_Protocol.internal_static_NetProtocol_ArenaReqPro_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro.class, cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro.Builder.class);
      }

      // Construct using cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro.newBuilder()
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
        type_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        data_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return cn.huizhi.car.pb.player.ArenaReq_Protocol.internal_static_NetProtocol_ArenaReqPro_descriptor;
      }

      public cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro getDefaultInstanceForType() {
        return cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro.getDefaultInstance();
      }

      public cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro build() {
        cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro buildPartial() {
        cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro result = new cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.type_ = type_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.data_ = data_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro) {
          return mergeFrom((cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro other) {
        if (other == cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro.getDefaultInstance()) return this;
        if (other.hasType()) {
          setType(other.getType());
        }
        if (other.hasData()) {
          setData(other.getData());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // optional int32 type = 1;
      private int type_ ;
      /**
       * <code>optional int32 type = 1;</code>
       */
      public boolean hasType() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 type = 1;</code>
       */
      public int getType() {
        return type_;
      }
      /**
       * <code>optional int32 type = 1;</code>
       */
      public Builder setType(int value) {
        bitField0_ |= 0x00000001;
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 type = 1;</code>
       */
      public Builder clearType() {
        bitField0_ = (bitField0_ & ~0x00000001);
        type_ = 0;
        onChanged();
        return this;
      }

      // optional bytes data = 2;
      private com.google.protobuf.ByteString data_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>optional bytes data = 2;</code>
       */
      public boolean hasData() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional bytes data = 2;</code>
       */
      public com.google.protobuf.ByteString getData() {
        return data_;
      }
      /**
       * <code>optional bytes data = 2;</code>
       */
      public Builder setData(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        data_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bytes data = 2;</code>
       */
      public Builder clearData() {
        bitField0_ = (bitField0_ & ~0x00000002);
        data_ = getDefaultInstance().getData();
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:NetProtocol.ArenaReqPro)
    }

    static {
      defaultInstance = new ArenaReqPro(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:NetProtocol.ArenaReqPro)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_NetProtocol_ArenaReqPro_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_NetProtocol_ArenaReqPro_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021ArenaReqPro.proto\022\013NetProtocol\")\n\013Aren" +
      "aReqPro\022\014\n\004type\030\001 \001(\005\022\014\n\004data\030\002 \001(\014B.\n\027c" +
      "n.huizhi.car.pb.playerB\021ArenaReq_Protoco" +
      "lH\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_NetProtocol_ArenaReqPro_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_NetProtocol_ArenaReqPro_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_NetProtocol_ArenaReqPro_descriptor,
              new java.lang.String[] { "Type", "Data", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}