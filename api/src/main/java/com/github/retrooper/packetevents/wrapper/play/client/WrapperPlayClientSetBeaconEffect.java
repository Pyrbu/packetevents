/*
 * This file is part of packetevents - https://github.com/retrooper/packetevents
 * Copyright (C) 2021 retrooper and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.retrooper.packetevents.wrapper.play.client;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.manager.server.ServerVersion;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.PacketWrapper;

public class WrapperPlayClientSetBeaconEffect extends PacketWrapper<WrapperPlayClientSetBeaconEffect> {
    private int primaryEffect;
    private int secondaryEffect;

    public WrapperPlayClientSetBeaconEffect(PacketReceiveEvent event) {
        super(event);
    }

    public WrapperPlayClientSetBeaconEffect(int primaryEffect, int secondaryEffect) {
        super(PacketType.Play.Client.SET_BEACON_EFFECT);
        this.primaryEffect = primaryEffect;
        this.secondaryEffect = secondaryEffect;
    }

    @Override
    public void read() {
        if (serverVersion.isOlderThanOrEquals(ServerVersion.V_1_19)) {
            if (readBoolean()) {
                this.primaryEffect = readVarInt();
            } else {
                this.primaryEffect = -1;
            }

            if (readBoolean()) {
                this.secondaryEffect = readVarInt();
            } else {
                this.secondaryEffect = -1;
            }
        } else {
            this.primaryEffect = readVarInt();
            this.secondaryEffect = readVarInt();
        }
    }

    @Override
    public void write() {
        boolean v1_19 = serverVersion.isNewerThanOrEquals(ServerVersion.V_1_19);
        if (serverVersion.isOlderThanOrEquals(ServerVersion.V_1_19)) {
            writeBoolean(primaryEffect != -1);
            if (primaryEffect != -1) {
                writeVarInt(primaryEffect);
            }
            writeBoolean(secondaryEffect != -1);
            if (secondaryEffect != -1) {
                writeVarInt(secondaryEffect);
            }
        } else {
            writeVarInt(primaryEffect);
            writeVarInt(secondaryEffect);
        }
    }

    @Override
    public void copy(WrapperPlayClientSetBeaconEffect wrapper) {
        this.primaryEffect = wrapper.primaryEffect;
        this.secondaryEffect = wrapper.secondaryEffect;
    }

    public int getPrimaryEffect() {
        return primaryEffect;
    }

    public void setPrimaryEffect(int primaryEffect) {
        this.primaryEffect = primaryEffect;
    }

    public int getSecondaryEffect() {
        return secondaryEffect;
    }

    public void setSecondaryEffect(int secondaryEffect) {
        this.secondaryEffect = secondaryEffect;
    }
}