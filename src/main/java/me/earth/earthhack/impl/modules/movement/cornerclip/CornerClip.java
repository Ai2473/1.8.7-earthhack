package me.earth.earthhack.impl.modules.movement.cornerclip;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.BooleanSetting;
import me.earth.earthhack.api.setting.settings.NumberSetting;
import me.earth.earthhack.impl.event.events.misc.UpdateEvent;
import me.earth.earthhack.impl.event.listeners.LambdaListener;
import me.earth.earthhack.impl.util.client.SimpleData;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.MathHelper;


// TODO: thingy that makes crystals fall on us
public class CornerClip extends Module
{

    protected final Setting<Integer> delay =
            register(new NumberSetting<>("Delay", 5, 1, 10));

    protected final Setting<Boolean> disable =
            register(new BooleanSetting("Disable", false));

    protected final Setting<Integer> updates =
            register(new NumberSetting<>("Updates", 10, 1, 30));

    int disabletime = 0;

    public CornerClip()
    {
        super("CornerClip", Category.Movement);
        SimpleData data = new SimpleData(
                this, "Slightly clip inside block to take less dmg.");
        this.setData(data);
        this.listeners.add(new LambdaListener<>(UpdateEvent.class, e -> {
            if (mc.gameSettings.keyBindForward.isKeyDown()
                    || mc.gameSettings.keyBindBack.isKeyDown()
                    || mc.gameSettings.keyBindLeft.isKeyDown()
                    || mc.gameSettings.keyBindRight.isKeyDown()) {
                disable();
                return;
            }

            if (mc.world.getCollisionBoxes(mc.player, mc.player.getEntityBoundingBox().grow(0.01, 0, 0.01)).size() < 2) {
                mc.player.setPosition(roundToClosest(mc.player.posX, Math.floor(mc.player.posX) + 0.301, Math.floor(mc.player.posX) + 0.699), mc.player.posY, roundToClosest(mc.player.posZ, Math.floor(mc.player.posZ) + 0.301, Math.floor(mc.player.posZ) + 0.699));

            } else if (mc.player.ticksExisted % delay.getValue() == 0) {
                mc.player.setPosition(mc.player.posX + MathHelper.clamp(roundToClosest(mc.player.posX, Math.floor(mc.player.posX) + 0.241, Math.floor(mc.player.posX) + 0.759) - mc.player.posX, -0.03, 0.03), mc.player.posY, mc.player.posZ + MathHelper.clamp(roundToClosest(mc.player.posZ, Math.floor(mc.player.posZ) + 0.241, Math.floor(mc.player.posZ) + 0.759) - mc.player.posZ, -0.03, 0.03));
                mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, true));
                mc.player.connection.sendPacket(new CPacketPlayer.Position(roundToClosest(mc.player.posX, Math.floor(mc.player.posX) + 0.23, Math.floor(mc.player.posX) + 0.77), mc.player.posY, roundToClosest(mc.player.posZ, Math.floor(mc.player.posZ) + 0.23, Math.floor(mc.player.posZ) + 0.77), true));
            }
            if (disable.getValue()) {
                if (disabletime >= updates.getValue()) {
                    disable();
                }
                disabletime++;
            }
        }));
    }

    public static double roundToClosest(double num, double low, double high) {
        double d2 = high - num;
        double d1 = num - low;
        if (d2 > d1) {
            return low;
        }
        return high;
    }
    @Override
    protected void onDisable()
    {
        disabletime = 0;
    }
}