package me.purplex.packetevents.events.packetevent.packet.impl.in._1_8_8;

import me.purplex.packetevents.enums.PlayerDigType;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;

public class WrappedPacketPlayInBlockDig_1_8_8 {
    private final Object packet;
    public WrappedPacketPlayInBlockDig_1_8_8(Object packet) {
        this.packet = packet;
        setupDigType();
    }

    private void setupDigType() {
        PacketPlayInBlockDig p = (PacketPlayInBlockDig)packet;
        String name = p.c().name();
        this.digType = PlayerDigType.valueOf(name);
    }

    public PlayerDigType digType;

}
