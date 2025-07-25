// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Terrain.proto

package DnD.Terrain;

public final class TerrainOuterClass {
  private TerrainOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface TerrainOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Dnd.Terrain.Terrain)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>float posX = 1;</code>
     * @return The posX.
     */
    float getPosX();

    /**
     * <code>float posY = 2;</code>
     * @return The posY.
     */
    float getPosY();

    /**
     * <code>int32 terrainSize = 3;</code>
     * @return The terrainSize.
     */
    int getTerrainSize();

    /**
     * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
     */
    java.util.List<DnD.Terrain.TileOuterClass.Tile> 
        getTileDataList();
    /**
     * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
     */
    DnD.Terrain.TileOuterClass.Tile getTileData(int index);
    /**
     * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
     */
    int getTileDataCount();
    /**
     * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
     */
    java.util.List<? extends DnD.Terrain.TileOuterClass.TileOrBuilder> 
        getTileDataOrBuilderList();
    /**
     * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
     */
    DnD.Terrain.TileOuterClass.TileOrBuilder getTileDataOrBuilder(
        int index);
  }
  /**
   * Protobuf type {@code Dnd.Terrain.Terrain}
   */
  public  static final class Terrain extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Dnd.Terrain.Terrain)
      TerrainOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Terrain.newBuilder() to construct.
    private Terrain(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Terrain() {
      tileData_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Terrain();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Terrain(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
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
            case 13: {

              posX_ = input.readFloat();
              break;
            }
            case 21: {

              posY_ = input.readFloat();
              break;
            }
            case 24: {

              terrainSize_ = input.readInt32();
              break;
            }
            case 34: {
              if (!((mutable_bitField0_ & 0x00000001) != 0)) {
                tileData_ = new java.util.ArrayList<DnD.Terrain.TileOuterClass.Tile>();
                mutable_bitField0_ |= 0x00000001;
              }
              tileData_.add(
                  input.readMessage(DnD.Terrain.TileOuterClass.Tile.parser(), extensionRegistry));
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000001) != 0)) {
          tileData_ = java.util.Collections.unmodifiableList(tileData_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return DnD.Terrain.TerrainOuterClass.internal_static_Dnd_Terrain_Terrain_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return DnD.Terrain.TerrainOuterClass.internal_static_Dnd_Terrain_Terrain_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              DnD.Terrain.TerrainOuterClass.Terrain.class, DnD.Terrain.TerrainOuterClass.Terrain.Builder.class);
    }

    public static final int POSX_FIELD_NUMBER = 1;
    private float posX_;
    /**
     * <code>float posX = 1;</code>
     * @return The posX.
     */
    public float getPosX() {
      return posX_;
    }

    public static final int POSY_FIELD_NUMBER = 2;
    private float posY_;
    /**
     * <code>float posY = 2;</code>
     * @return The posY.
     */
    public float getPosY() {
      return posY_;
    }

    public static final int TERRAINSIZE_FIELD_NUMBER = 3;
    private int terrainSize_;
    /**
     * <code>int32 terrainSize = 3;</code>
     * @return The terrainSize.
     */
    public int getTerrainSize() {
      return terrainSize_;
    }

    public static final int TILEDATA_FIELD_NUMBER = 4;
    private java.util.List<DnD.Terrain.TileOuterClass.Tile> tileData_;
    /**
     * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
     */
    public java.util.List<DnD.Terrain.TileOuterClass.Tile> getTileDataList() {
      return tileData_;
    }
    /**
     * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
     */
    public java.util.List<? extends DnD.Terrain.TileOuterClass.TileOrBuilder> 
        getTileDataOrBuilderList() {
      return tileData_;
    }
    /**
     * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
     */
    public int getTileDataCount() {
      return tileData_.size();
    }
    /**
     * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
     */
    public DnD.Terrain.TileOuterClass.Tile getTileData(int index) {
      return tileData_.get(index);
    }
    /**
     * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
     */
    public DnD.Terrain.TileOuterClass.TileOrBuilder getTileDataOrBuilder(
        int index) {
      return tileData_.get(index);
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (posX_ != 0F) {
        output.writeFloat(1, posX_);
      }
      if (posY_ != 0F) {
        output.writeFloat(2, posY_);
      }
      if (terrainSize_ != 0) {
        output.writeInt32(3, terrainSize_);
      }
      for (int i = 0; i < tileData_.size(); i++) {
        output.writeMessage(4, tileData_.get(i));
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (posX_ != 0F) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(1, posX_);
      }
      if (posY_ != 0F) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(2, posY_);
      }
      if (terrainSize_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, terrainSize_);
      }
      for (int i = 0; i < tileData_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(4, tileData_.get(i));
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof DnD.Terrain.TerrainOuterClass.Terrain)) {
        return super.equals(obj);
      }
      DnD.Terrain.TerrainOuterClass.Terrain other = (DnD.Terrain.TerrainOuterClass.Terrain) obj;

      if (java.lang.Float.floatToIntBits(getPosX())
          != java.lang.Float.floatToIntBits(
              other.getPosX())) return false;
      if (java.lang.Float.floatToIntBits(getPosY())
          != java.lang.Float.floatToIntBits(
              other.getPosY())) return false;
      if (getTerrainSize()
          != other.getTerrainSize()) return false;
      if (!getTileDataList()
          .equals(other.getTileDataList())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + POSX_FIELD_NUMBER;
      hash = (53 * hash) + java.lang.Float.floatToIntBits(
          getPosX());
      hash = (37 * hash) + POSY_FIELD_NUMBER;
      hash = (53 * hash) + java.lang.Float.floatToIntBits(
          getPosY());
      hash = (37 * hash) + TERRAINSIZE_FIELD_NUMBER;
      hash = (53 * hash) + getTerrainSize();
      if (getTileDataCount() > 0) {
        hash = (37 * hash) + TILEDATA_FIELD_NUMBER;
        hash = (53 * hash) + getTileDataList().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static DnD.Terrain.TerrainOuterClass.Terrain parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static DnD.Terrain.TerrainOuterClass.Terrain parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static DnD.Terrain.TerrainOuterClass.Terrain parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static DnD.Terrain.TerrainOuterClass.Terrain parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static DnD.Terrain.TerrainOuterClass.Terrain parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static DnD.Terrain.TerrainOuterClass.Terrain parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static DnD.Terrain.TerrainOuterClass.Terrain parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static DnD.Terrain.TerrainOuterClass.Terrain parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static DnD.Terrain.TerrainOuterClass.Terrain parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static DnD.Terrain.TerrainOuterClass.Terrain parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static DnD.Terrain.TerrainOuterClass.Terrain parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static DnD.Terrain.TerrainOuterClass.Terrain parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(DnD.Terrain.TerrainOuterClass.Terrain prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code Dnd.Terrain.Terrain}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Dnd.Terrain.Terrain)
        DnD.Terrain.TerrainOuterClass.TerrainOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return DnD.Terrain.TerrainOuterClass.internal_static_Dnd_Terrain_Terrain_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return DnD.Terrain.TerrainOuterClass.internal_static_Dnd_Terrain_Terrain_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                DnD.Terrain.TerrainOuterClass.Terrain.class, DnD.Terrain.TerrainOuterClass.Terrain.Builder.class);
      }

      // Construct using DnD.Terrain.TerrainOuterClass.Terrain.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
          getTileDataFieldBuilder();
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        posX_ = 0F;

        posY_ = 0F;

        terrainSize_ = 0;

        if (tileDataBuilder_ == null) {
          tileData_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          tileDataBuilder_.clear();
        }
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return DnD.Terrain.TerrainOuterClass.internal_static_Dnd_Terrain_Terrain_descriptor;
      }

      @java.lang.Override
      public DnD.Terrain.TerrainOuterClass.Terrain getDefaultInstanceForType() {
        return DnD.Terrain.TerrainOuterClass.Terrain.getDefaultInstance();
      }

      @java.lang.Override
      public DnD.Terrain.TerrainOuterClass.Terrain build() {
        DnD.Terrain.TerrainOuterClass.Terrain result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public DnD.Terrain.TerrainOuterClass.Terrain buildPartial() {
        DnD.Terrain.TerrainOuterClass.Terrain result = new DnD.Terrain.TerrainOuterClass.Terrain(this);
        int from_bitField0_ = bitField0_;
        result.posX_ = posX_;
        result.posY_ = posY_;
        result.terrainSize_ = terrainSize_;
        if (tileDataBuilder_ == null) {
          if (((bitField0_ & 0x00000001) != 0)) {
            tileData_ = java.util.Collections.unmodifiableList(tileData_);
            bitField0_ = (bitField0_ & ~0x00000001);
          }
          result.tileData_ = tileData_;
        } else {
          result.tileData_ = tileDataBuilder_.build();
        }
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof DnD.Terrain.TerrainOuterClass.Terrain) {
          return mergeFrom((DnD.Terrain.TerrainOuterClass.Terrain)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(DnD.Terrain.TerrainOuterClass.Terrain other) {
        if (other == DnD.Terrain.TerrainOuterClass.Terrain.getDefaultInstance()) return this;
        if (other.getPosX() != 0F) {
          setPosX(other.getPosX());
        }
        if (other.getPosY() != 0F) {
          setPosY(other.getPosY());
        }
        if (other.getTerrainSize() != 0) {
          setTerrainSize(other.getTerrainSize());
        }
        if (tileDataBuilder_ == null) {
          if (!other.tileData_.isEmpty()) {
            if (tileData_.isEmpty()) {
              tileData_ = other.tileData_;
              bitField0_ = (bitField0_ & ~0x00000001);
            } else {
              ensureTileDataIsMutable();
              tileData_.addAll(other.tileData_);
            }
            onChanged();
          }
        } else {
          if (!other.tileData_.isEmpty()) {
            if (tileDataBuilder_.isEmpty()) {
              tileDataBuilder_.dispose();
              tileDataBuilder_ = null;
              tileData_ = other.tileData_;
              bitField0_ = (bitField0_ & ~0x00000001);
              tileDataBuilder_ = 
                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                   getTileDataFieldBuilder() : null;
            } else {
              tileDataBuilder_.addAllMessages(other.tileData_);
            }
          }
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        DnD.Terrain.TerrainOuterClass.Terrain parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (DnD.Terrain.TerrainOuterClass.Terrain) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private float posX_ ;
      /**
       * <code>float posX = 1;</code>
       * @return The posX.
       */
      public float getPosX() {
        return posX_;
      }
      /**
       * <code>float posX = 1;</code>
       * @param value The posX to set.
       * @return This builder for chaining.
       */
      public Builder setPosX(float value) {
        
        posX_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>float posX = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearPosX() {
        
        posX_ = 0F;
        onChanged();
        return this;
      }

      private float posY_ ;
      /**
       * <code>float posY = 2;</code>
       * @return The posY.
       */
      public float getPosY() {
        return posY_;
      }
      /**
       * <code>float posY = 2;</code>
       * @param value The posY to set.
       * @return This builder for chaining.
       */
      public Builder setPosY(float value) {
        
        posY_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>float posY = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearPosY() {
        
        posY_ = 0F;
        onChanged();
        return this;
      }

      private int terrainSize_ ;
      /**
       * <code>int32 terrainSize = 3;</code>
       * @return The terrainSize.
       */
      public int getTerrainSize() {
        return terrainSize_;
      }
      /**
       * <code>int32 terrainSize = 3;</code>
       * @param value The terrainSize to set.
       * @return This builder for chaining.
       */
      public Builder setTerrainSize(int value) {
        
        terrainSize_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 terrainSize = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearTerrainSize() {
        
        terrainSize_ = 0;
        onChanged();
        return this;
      }

      private java.util.List<DnD.Terrain.TileOuterClass.Tile> tileData_ =
        java.util.Collections.emptyList();
      private void ensureTileDataIsMutable() {
        if (!((bitField0_ & 0x00000001) != 0)) {
          tileData_ = new java.util.ArrayList<DnD.Terrain.TileOuterClass.Tile>(tileData_);
          bitField0_ |= 0x00000001;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilderV3<
          DnD.Terrain.TileOuterClass.Tile, DnD.Terrain.TileOuterClass.Tile.Builder, DnD.Terrain.TileOuterClass.TileOrBuilder> tileDataBuilder_;

      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public java.util.List<DnD.Terrain.TileOuterClass.Tile> getTileDataList() {
        if (tileDataBuilder_ == null) {
          return java.util.Collections.unmodifiableList(tileData_);
        } else {
          return tileDataBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public int getTileDataCount() {
        if (tileDataBuilder_ == null) {
          return tileData_.size();
        } else {
          return tileDataBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public DnD.Terrain.TileOuterClass.Tile getTileData(int index) {
        if (tileDataBuilder_ == null) {
          return tileData_.get(index);
        } else {
          return tileDataBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public Builder setTileData(
          int index, DnD.Terrain.TileOuterClass.Tile value) {
        if (tileDataBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureTileDataIsMutable();
          tileData_.set(index, value);
          onChanged();
        } else {
          tileDataBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public Builder setTileData(
          int index, DnD.Terrain.TileOuterClass.Tile.Builder builderForValue) {
        if (tileDataBuilder_ == null) {
          ensureTileDataIsMutable();
          tileData_.set(index, builderForValue.build());
          onChanged();
        } else {
          tileDataBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public Builder addTileData(DnD.Terrain.TileOuterClass.Tile value) {
        if (tileDataBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureTileDataIsMutable();
          tileData_.add(value);
          onChanged();
        } else {
          tileDataBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public Builder addTileData(
          int index, DnD.Terrain.TileOuterClass.Tile value) {
        if (tileDataBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureTileDataIsMutable();
          tileData_.add(index, value);
          onChanged();
        } else {
          tileDataBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public Builder addTileData(
          DnD.Terrain.TileOuterClass.Tile.Builder builderForValue) {
        if (tileDataBuilder_ == null) {
          ensureTileDataIsMutable();
          tileData_.add(builderForValue.build());
          onChanged();
        } else {
          tileDataBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public Builder addTileData(
          int index, DnD.Terrain.TileOuterClass.Tile.Builder builderForValue) {
        if (tileDataBuilder_ == null) {
          ensureTileDataIsMutable();
          tileData_.add(index, builderForValue.build());
          onChanged();
        } else {
          tileDataBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public Builder addAllTileData(
          java.lang.Iterable<? extends DnD.Terrain.TileOuterClass.Tile> values) {
        if (tileDataBuilder_ == null) {
          ensureTileDataIsMutable();
          com.google.protobuf.AbstractMessageLite.Builder.addAll(
              values, tileData_);
          onChanged();
        } else {
          tileDataBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public Builder clearTileData() {
        if (tileDataBuilder_ == null) {
          tileData_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
          onChanged();
        } else {
          tileDataBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public Builder removeTileData(int index) {
        if (tileDataBuilder_ == null) {
          ensureTileDataIsMutable();
          tileData_.remove(index);
          onChanged();
        } else {
          tileDataBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public DnD.Terrain.TileOuterClass.Tile.Builder getTileDataBuilder(
          int index) {
        return getTileDataFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public DnD.Terrain.TileOuterClass.TileOrBuilder getTileDataOrBuilder(
          int index) {
        if (tileDataBuilder_ == null) {
          return tileData_.get(index);  } else {
          return tileDataBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public java.util.List<? extends DnD.Terrain.TileOuterClass.TileOrBuilder> 
           getTileDataOrBuilderList() {
        if (tileDataBuilder_ != null) {
          return tileDataBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(tileData_);
        }
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public DnD.Terrain.TileOuterClass.Tile.Builder addTileDataBuilder() {
        return getTileDataFieldBuilder().addBuilder(
            DnD.Terrain.TileOuterClass.Tile.getDefaultInstance());
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public DnD.Terrain.TileOuterClass.Tile.Builder addTileDataBuilder(
          int index) {
        return getTileDataFieldBuilder().addBuilder(
            index, DnD.Terrain.TileOuterClass.Tile.getDefaultInstance());
      }
      /**
       * <code>repeated .Dnd.Terrain.Tile tileData = 4;</code>
       */
      public java.util.List<DnD.Terrain.TileOuterClass.Tile.Builder> 
           getTileDataBuilderList() {
        return getTileDataFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilderV3<
          DnD.Terrain.TileOuterClass.Tile, DnD.Terrain.TileOuterClass.Tile.Builder, DnD.Terrain.TileOuterClass.TileOrBuilder> 
          getTileDataFieldBuilder() {
        if (tileDataBuilder_ == null) {
          tileDataBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
              DnD.Terrain.TileOuterClass.Tile, DnD.Terrain.TileOuterClass.Tile.Builder, DnD.Terrain.TileOuterClass.TileOrBuilder>(
                  tileData_,
                  ((bitField0_ & 0x00000001) != 0),
                  getParentForChildren(),
                  isClean());
          tileData_ = null;
        }
        return tileDataBuilder_;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:Dnd.Terrain.Terrain)
    }

    // @@protoc_insertion_point(class_scope:Dnd.Terrain.Terrain)
    private static final DnD.Terrain.TerrainOuterClass.Terrain DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new DnD.Terrain.TerrainOuterClass.Terrain();
    }

    public static DnD.Terrain.TerrainOuterClass.Terrain getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Terrain>
        PARSER = new com.google.protobuf.AbstractParser<Terrain>() {
      @java.lang.Override
      public Terrain parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Terrain(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Terrain> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Terrain> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public DnD.Terrain.TerrainOuterClass.Terrain getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Dnd_Terrain_Terrain_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Dnd_Terrain_Terrain_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rTerrain.proto\022\013Dnd.Terrain\032\nTile.proto" +
      "\"_\n\007Terrain\022\014\n\004posX\030\001 \001(\002\022\014\n\004posY\030\002 \001(\002\022" +
      "\023\n\013terrainSize\030\003 \001(\005\022#\n\010tileData\030\004 \003(\0132\021" +
      ".Dnd.Terrain.TileB \n\013DnD.TerrainB\021Terrai" +
      "nOuterClassb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          DnD.Terrain.TileOuterClass.getDescriptor(),
        });
    internal_static_Dnd_Terrain_Terrain_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Dnd_Terrain_Terrain_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Dnd_Terrain_Terrain_descriptor,
        new java.lang.String[] { "PosX", "PosY", "TerrainSize", "TileData", });
    DnD.Terrain.TileOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
