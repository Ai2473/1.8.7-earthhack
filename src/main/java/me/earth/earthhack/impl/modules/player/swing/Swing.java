package me.earth.earthhack.impl.modules.player.swing;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.settings.BooleanSetting;
import me.earth.earthhack.api.setting.settings.EnumSetting;
import me.earth.earthhack.api.setting.settings.NumberSetting;
import me.earth.earthhack.impl.event.events.misc.UpdateEvent;
import me.earth.earthhack.impl.event.events.network.PacketEvent;
import me.earth.earthhack.impl.event.listeners.LambdaListener;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.util.EnumHand;

public class Swing extends Module {
    public final NumberSetting<Integer> swingSpeed =
            register(new NumberSetting<>("Mode", 6, 0, 20));

    public final BooleanSetting clientside =
            register(new BooleanSetting("ClientSide", false));

    protected final EnumSetting<SwingEnum> hand =
            register(new EnumSetting<>("Hand", SwingEnum.MAINHAND));

    public Swing() {
        super("Swing", Category.Player);
        this.setData(new SwingData(this));
        this.listeners.add(new LambdaListener<>(UpdateEvent.class, e -> {
            if(mc.player == null && mc.world == null) return;

            if(hand.getValue() == SwingEnum.MAINHAND) {
                mc.player.swingingHand = EnumHand.MAIN_HAND;
            } else if(hand.getValue() == SwingEnum.OFFHAND) {
                mc.player.swingingHand = EnumHand.OFF_HAND;
            }
        }));
        this.listeners.add(new LambdaListener<>(PacketEvent.Send.class,e -> {
            if (!clientside.getValue()) {
                if (e.getPacket() instanceof CPacketAnimation) {
                    if (hand.getValue() == SwingEnum.MAINHAND) {
                        if (((CPacketAnimation) e.getPacket()).getHand() != EnumHand.MAIN_HAND) {
                            e.setCancelled(true);
                            mc.player.swingArm(EnumHand.MAIN_HAND);
                        }
                    } else if (hand.getValue() == SwingEnum.OFFHAND) {
                        if (((CPacketAnimation) e.getPacket()).getHand() != EnumHand.OFF_HAND) {
                            e.setCancelled(true);
                            mc.player.swingArm(EnumHand.OFF_HAND);
                        }
                    } else {
                        e.setCancelled(true);
                    }

                }
            }
        }));
    }

}
