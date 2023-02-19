package me.earth.earthhack.impl.modules.client.xcrash;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.BooleanSetting;
import me.earth.earthhack.impl.util.client.SimpleData;
import me.earth.earthhack.impl.util.render.framebuffer.Framebuffer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.ShaderGroup;
import org.lwjgl.opengl.GL11;

public class XCrash extends Module
{
    // X so it is the last module lmao
    protected final Setting<Boolean> saveFiles =
            register(new BooleanSetting("Save", false));
    public XCrash()
    {
        super("XCrash", Category.Client);
        if (mc.player == null || mc.world == null) {this.toggle();}
        SimpleData data = new SimpleData(
                this, "Ez log!");
        this.setData(data);
    }

    @Override
    protected void onEnable()
    {
        this.toggle();
        if (mc.player == null || mc.world == null) {return;}
        super.onEnable();
        if (!saveFiles.getValue()) {
            throw new RuntimeException("Crash");
        } else {
            mc.shutdown();
        }
    }

    @Override
    protected void onDisable()
    {

    }

}