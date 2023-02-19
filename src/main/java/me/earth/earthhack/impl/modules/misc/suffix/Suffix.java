package me.earth.earthhack.impl.modules.misc.suffix;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.BooleanSetting;
import me.earth.earthhack.api.setting.settings.EnumSetting;
import me.earth.earthhack.api.setting.settings.StringSetting;
import me.earth.earthhack.impl.Earthhack;
import me.earth.earthhack.impl.event.events.network.PacketEvent;
import me.earth.earthhack.impl.event.listeners.LambdaListener;
import me.earth.earthhack.impl.modules.misc.suffix.util.Mode;
import me.earth.earthhack.impl.util.render.fonts.FontEnum;
import me.earth.earthhack.impl.util.render.fonts.FontFinder;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

import static me.earth.earthhack.impl.modules.client.commands.Commands.getPrefix;
import static me.earth.earthhack.impl.util.render.fonts.FontEnum.Small;


public class Suffix extends Module
{
    protected final EnumSetting<Mode> mode =
            register(new EnumSetting<>("Mode", Mode.Earth));
    protected final StringSetting custom =
            register(new StringSetting("Custom", "3arthh4ck"));
    /*
    protected final EnumSetting<FontEnum> customfont =
            register(new EnumSetting<>("CustomFont", Small));
     */
    protected final BooleanSetting msgs =
            register(new BooleanSetting("Messages", false));

    public Suffix()
    {
        super("Suffix", Category.Misc);
        this.setData(new SuffixData(this));
        this.listeners.add(new LambdaListener<>(PacketEvent.Send.class, e -> {
            if (e.getPacket() instanceof CPacketChatMessage) {
                String message = ((CPacketChatMessage) e.getPacket()).getMessage();
                if (!message.startsWith("/") && !message.startsWith(getPrefix()) && !message.endsWith(EARTH) && !message.endsWith(CUTEEARTH) && !message.endsWith(PHOBOS) && !message.endsWith(RUSHER) && !message.endsWith(custom.getValue()) && !message.endsWith(FUTURE) && !message.endsWith(KONAS) && !message.endsWith(GAMESENSE) && !message.endsWith(KAMIBLUE)) {
                    e.setCancelled(true);
                    mc.player.connection.sendPacket(new CPacketChatMessage(getSuffix(message)));
                } else if (msgs.getValue() && !message.startsWith(getPrefix()) &&!message.startsWith(getPrefix()) && !message.endsWith(EARTH) && !message.endsWith(CUTEEARTH) && !message.endsWith(PHOBOS) && !message.endsWith(RUSHER) && !message.endsWith(custom.getValue()) && !message.endsWith(FUTURE) && !message.endsWith(KONAS) && !message.endsWith(GAMESENSE) && !message.endsWith(KAMIBLUE)) {
                    if (message.startsWith("/msg") || message.startsWith("/r") || message.startsWith("/w") || message.startsWith("/tell")) {
                        e.setCancelled(true);
                        mc.player.connection.sendPacket(new CPacketChatMessage(getSuffix(message)));
                    }
                }
            }
        }));
    }

    protected static final String EARTH = "\u00B3\u1D00\u0280\u1D1B\u029C\u029C\u2074\u1D04\u1D0B";
    protected static final String CUTEEARTH = "(\u3063\u25D4\u25E1\u25D4)\u3063 \u2665 3arthh4ck " + Earthhack.VERSION + " \u2665";
    protected static final String PHOBOS = "\u1D18\u029C\u1D0F\u0299\u1D0F\uA731";
    protected static final String RUSHER = "\u02B3\u1D58\u02E2\u02B0\u1D49\u02B3\u02B0\u1D43\u1D9C\u1D4F";
    protected static final String FUTURE = "\uA730\u1D1C\u1D1B\u1D1C\u0280\u1D07";
    protected static final String KONAS = "Konas owns me and all \u2022\u1D17\u2022";
    protected static final String GAMESENSE = "\u0262\u1D00\u1D0D\u1D07\uA731\u1D07\u0274\uA731\u1D07";
    protected static final String KAMIBLUE = "\u1D0B\u1D00\u1D0D\u026A \u0299\u029F\u1D1C\u1D07";


    @Override
    protected void onEnable() {
        super.onEnable();
    }

    @Override
    protected void onDisable() {

    }


    public String getSuffix(String message)
    {
        String suffix = "";

        if (mode.getValue() == Mode.Earth) {
            suffix = EARTH;
        } else if (mode.getValue() == Mode.CuteEarth) {
            suffix = CUTEEARTH;
        } else if (mode.getValue() == Mode.Phobos) {
            suffix = PHOBOS;
        } else if (mode.getValue() == Mode.Rusher) {
            suffix = RUSHER;
        } else if (mode.getValue() == Mode.Future) {
            suffix = FUTURE;
        } else if (mode.getValue() == Mode.Konas) {
            suffix = KONAS;
        } else if (mode.getValue() == Mode.Gamesense) {
            suffix = GAMESENSE;
        } else if (mode.getValue() == Mode.KamiBlue) {
            suffix = KAMIBLUE;
        } else if (mode.getValue() == Mode.Custom) {
            suffix = custom.getValue();
        }
        return message + " | " + suffix;
    }
}
