package me.earth.earthhack.impl.modules.render.targethud;

import me.earth.earthhack.api.module.data.DefaultData;

final class TargetHudData extends DefaultData<TargetHud>
{
    public TargetHudData(TargetHud module)
    {
        super(module);
        this.descriptions.put(module.maxSetting,
                "Player detect range.");
        this.descriptions.put(module.posX,
                "The x position of the TargetHud.");
        this.descriptions.put(module.posY,
                "The x position of the TargetHud.");
        this.descriptions.put(module.ping,
                "Shows the player ping.");
        this.descriptions.put(module.distance,
                "Shows the player distance.");
        this.descriptions.put(module.phase,
                "Shows if the player is phased.");
    }

    @Override
    public int getColor()
    {
        return 0xffffffff;
    }

    @Override
    public String getDescription()
    {
        return "Searches for certain blocks in render distance.";
    }

}
