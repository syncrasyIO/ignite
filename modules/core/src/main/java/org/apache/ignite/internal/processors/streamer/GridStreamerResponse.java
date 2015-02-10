/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.processors.streamer;

import org.apache.ignite.internal.util.typedef.internal.*;
import org.apache.ignite.lang.*;
import org.apache.ignite.plugin.extensions.communication.*;
import org.jetbrains.annotations.*;

import java.nio.*;

/**
 *
 */
public class GridStreamerResponse extends MessageAdapter {
    /** */
    private static final long serialVersionUID = 0L;

    /** */
    private IgniteUuid futId;

    /** */
    private byte[] errBytes;

    /**
     *
     */
    public GridStreamerResponse() {
        // No-op.
    }

    /**
     * @param futId Future ID.
     * @param errBytes Serialized error, if any.
     */
    public GridStreamerResponse(IgniteUuid futId, @Nullable byte[] errBytes) {
        assert futId != null;

        this.futId = futId;
        this.errBytes = errBytes;
    }

    /**
     * @return Future ID.
     */
    public IgniteUuid futureId() {
        return futId;
    }

    /**
     * @return Serialized error.
     */
    public byte[] errorBytes() {
        return errBytes;
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return S.toString(GridStreamerResponse.class, this);
    }

    /** {@inheritDoc} */
    @SuppressWarnings({"CloneDoesntCallSuperClone", "CloneCallsConstructors"})
    @Override public MessageAdapter clone() {
        GridStreamerResponse _clone = new GridStreamerResponse();

        clone0(_clone);

        return _clone;
    }

    /** {@inheritDoc} */
    @Override protected void clone0(MessageAdapter _msg) {
        GridStreamerResponse _clone = (GridStreamerResponse)_msg;

        _clone.futId = futId;
        _clone.errBytes = errBytes;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("all")
    @Override public boolean writeTo(ByteBuffer buf) {
        writer.setBuffer(buf);

        if (!typeWritten) {
            if (!writer.writeByte(null, directType()))
                return false;

            typeWritten = true;
        }

        switch (state) {
            case 0:
                if (!writer.writeByteArray("errBytes", errBytes))
                    return false;

                state++;

            case 1:
                if (!writer.writeIgniteUuid("futId", futId))
                    return false;

                state++;

        }

        return true;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("all")
    @Override public boolean readFrom(ByteBuffer buf) {
        reader.setBuffer(buf);

        switch (state) {
            case 0:
                errBytes = reader.readByteArray("errBytes");

                if (!reader.isLastRead())
                    return false;

                state++;

            case 1:
                futId = reader.readIgniteUuid("futId");

                if (!reader.isLastRead())
                    return false;

                state++;

        }

        return true;
    }

    /** {@inheritDoc} */
    @Override public byte directType() {
        return 81;
    }
}
