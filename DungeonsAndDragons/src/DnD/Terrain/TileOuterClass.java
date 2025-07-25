// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Tile.proto

package DnD.Terrain;

public final class TileOuterClass {
  private TileOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface TileOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Dnd.Terrain.Tile)
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
     * <code>.Dnd.Terrain.TileType type = 3;</code>
     * @return The enum numeric value on the wire for type.
     */
    int getTypeValue();
    /**
     * <code>.Dnd.Terrain.TileType type = 3;</code>
     * @return The type.
     */
    DnD.Terrain.TileTypeOuterClass.TileType getType();
  }
  /**
   * Protobuf type {@code Dnd.Terrain.Tile}
   */
  public  static final class Tile extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Dnd.Terrain.Tile)
      TileOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Tile.newBuilder() to construct.
    private Tile(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Tile() {
      type_ = 0;
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Tile();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Tile(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
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
              int rawValue = input.readEnum();

              type_ = rawValue;
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
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return DnD.Terrain.TileOuterClass.internal_static_Dnd_Terrain_Tile_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return DnD.Terrain.TileOuterClass.internal_static_Dnd_Terrain_Tile_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              DnD.Terrain.TileOuterClass.Tile.class, DnD.Terrain.TileOuterClass.Tile.Builder.class);
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

    public static final int TYPE_FIELD_NUMBER = 3;
    private int type_;
    /**
     * <code>.Dnd.Terrain.TileType type = 3;</code>
     * @return The enum numeric value on the wire for type.
     */
    public int getTypeValue() {
      return type_;
    }
    /**
     * <code>.Dnd.Terrain.TileType type = 3;</code>
     * @return The type.
     */
    public DnD.Terrain.TileTypeOuterClass.TileType getType() {
      @SuppressWarnings("deprecation")
      DnD.Terrain.TileTypeOuterClass.TileType result = DnD.Terrain.TileTypeOuterClass.TileType.valueOf(type_);
      return result == null ? DnD.Terrain.TileTypeOuterClass.TileType.UNRECOGNIZED : result;
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
      if (type_ != DnD.Terrain.TileTypeOuterClass.TileType.STANDARD_GRASS.getNumber()) {
        output.writeEnum(3, type_);
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
      if (type_ != DnD.Terrain.TileTypeOuterClass.TileType.STANDARD_GRASS.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(3, type_);
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
      if (!(obj instanceof DnD.Terrain.TileOuterClass.Tile)) {
        return super.equals(obj);
      }
      DnD.Terrain.TileOuterClass.Tile other = (DnD.Terrain.TileOuterClass.Tile) obj;

      if (java.lang.Float.floatToIntBits(getPosX())
          != java.lang.Float.floatToIntBits(
              other.getPosX())) return false;
      if (java.lang.Float.floatToIntBits(getPosY())
          != java.lang.Float.floatToIntBits(
              other.getPosY())) return false;
      if (type_ != other.type_) return false;
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
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + type_;
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static DnD.Terrain.TileOuterClass.Tile parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static DnD.Terrain.TileOuterClass.Tile parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static DnD.Terrain.TileOuterClass.Tile parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static DnD.Terrain.TileOuterClass.Tile parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static DnD.Terrain.TileOuterClass.Tile parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static DnD.Terrain.TileOuterClass.Tile parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static DnD.Terrain.TileOuterClass.Tile parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static DnD.Terrain.TileOuterClass.Tile parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static DnD.Terrain.TileOuterClass.Tile parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static DnD.Terrain.TileOuterClass.Tile parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static DnD.Terrain.TileOuterClass.Tile parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static DnD.Terrain.TileOuterClass.Tile parseFrom(
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
    public static Builder newBuilder(DnD.Terrain.TileOuterClass.Tile prototype) {
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
     * Protobuf type {@code Dnd.Terrain.Tile}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Dnd.Terrain.Tile)
        DnD.Terrain.TileOuterClass.TileOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return DnD.Terrain.TileOuterClass.internal_static_Dnd_Terrain_Tile_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return DnD.Terrain.TileOuterClass.internal_static_Dnd_Terrain_Tile_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                DnD.Terrain.TileOuterClass.Tile.class, DnD.Terrain.TileOuterClass.Tile.Builder.class);
      }

      // Construct using DnD.Terrain.TileOuterClass.Tile.newBuilder()
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
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        posX_ = 0F;

        posY_ = 0F;

        type_ = 0;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return DnD.Terrain.TileOuterClass.internal_static_Dnd_Terrain_Tile_descriptor;
      }

      @java.lang.Override
      public DnD.Terrain.TileOuterClass.Tile getDefaultInstanceForType() {
        return DnD.Terrain.TileOuterClass.Tile.getDefaultInstance();
      }

      @java.lang.Override
      public DnD.Terrain.TileOuterClass.Tile build() {
        DnD.Terrain.TileOuterClass.Tile result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public DnD.Terrain.TileOuterClass.Tile buildPartial() {
        DnD.Terrain.TileOuterClass.Tile result = new DnD.Terrain.TileOuterClass.Tile(this);
        result.posX_ = posX_;
        result.posY_ = posY_;
        result.type_ = type_;
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
        if (other instanceof DnD.Terrain.TileOuterClass.Tile) {
          return mergeFrom((DnD.Terrain.TileOuterClass.Tile)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(DnD.Terrain.TileOuterClass.Tile other) {
        if (other == DnD.Terrain.TileOuterClass.Tile.getDefaultInstance()) return this;
        if (other.getPosX() != 0F) {
          setPosX(other.getPosX());
        }
        if (other.getPosY() != 0F) {
          setPosY(other.getPosY());
        }
        if (other.type_ != 0) {
          setTypeValue(other.getTypeValue());
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
        DnD.Terrain.TileOuterClass.Tile parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (DnD.Terrain.TileOuterClass.Tile) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

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

      private int type_ = 0;
      /**
       * <code>.Dnd.Terrain.TileType type = 3;</code>
       * @return The enum numeric value on the wire for type.
       */
      public int getTypeValue() {
        return type_;
      }
      /**
       * <code>.Dnd.Terrain.TileType type = 3;</code>
       * @param value The enum numeric value on the wire for type to set.
       * @return This builder for chaining.
       */
      public Builder setTypeValue(int value) {
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>.Dnd.Terrain.TileType type = 3;</code>
       * @return The type.
       */
      public DnD.Terrain.TileTypeOuterClass.TileType getType() {
        @SuppressWarnings("deprecation")
        DnD.Terrain.TileTypeOuterClass.TileType result = DnD.Terrain.TileTypeOuterClass.TileType.valueOf(type_);
        return result == null ? DnD.Terrain.TileTypeOuterClass.TileType.UNRECOGNIZED : result;
      }
      /**
       * <code>.Dnd.Terrain.TileType type = 3;</code>
       * @param value The type to set.
       * @return This builder for chaining.
       */
      public Builder setType(DnD.Terrain.TileTypeOuterClass.TileType value) {
        if (value == null) {
          throw new NullPointerException();
        }
        
        type_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>.Dnd.Terrain.TileType type = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearType() {
        
        type_ = 0;
        onChanged();
        return this;
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


      // @@protoc_insertion_point(builder_scope:Dnd.Terrain.Tile)
    }

    // @@protoc_insertion_point(class_scope:Dnd.Terrain.Tile)
    private static final DnD.Terrain.TileOuterClass.Tile DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new DnD.Terrain.TileOuterClass.Tile();
    }

    public static DnD.Terrain.TileOuterClass.Tile getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Tile>
        PARSER = new com.google.protobuf.AbstractParser<Tile>() {
      @java.lang.Override
      public Tile parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Tile(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Tile> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Tile> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public DnD.Terrain.TileOuterClass.Tile getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Dnd_Terrain_Tile_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Dnd_Terrain_Tile_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nTile.proto\022\013Dnd.Terrain\032\016TileType.prot" +
      "o\"G\n\004Tile\022\014\n\004posX\030\001 \001(\002\022\014\n\004posY\030\002 \001(\002\022#\n" +
      "\004type\030\003 \001(\0162\025.Dnd.Terrain.TileTypeB\035\n\013Dn" +
      "D.TerrainB\016TileOuterClassb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          DnD.Terrain.TileTypeOuterClass.getDescriptor(),
        });
    internal_static_Dnd_Terrain_Tile_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Dnd_Terrain_Tile_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Dnd_Terrain_Tile_descriptor,
        new java.lang.String[] { "PosX", "PosY", "Type", });
    DnD.Terrain.TileTypeOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
