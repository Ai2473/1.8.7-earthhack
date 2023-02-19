package me.earth.earthhack.impl.modules.client.rpc;

import me.earth.earthhack.api.module.data.DefaultData;

final class RPCData extends DefaultData<RPC>
{
    public RPCData(RPC module)
    {
        super(module);
        this.descriptions.put(module.logoBig,
                "The large image.");
        this.descriptions.put(module.logoSmall,
                "The small image.");
        this.descriptions.put(module.Line1,
                "Text in the first line.");
        this.descriptions.put(module.Line2,
                "Text in the second line.");
        this.descriptions.put(module.showIP,
                "Shows the server ip you're playing on.");
        this.descriptions.put(module.join,
                "Discord join button.");
        this.descriptions.put(module.partyMax,
                "The party size.");
        this.descriptions.put(module.custom,
                "Your application ID.");
        this.descriptions.put(module.assetLarge,
                "The name of the large image.");
        this.descriptions.put(module.assetLargeText,
                "The displayed text of the large image.");
        this.descriptions.put(module.smallImage,
                "Select if you want the small image or no.");
        this.descriptions.put(module.assetSmall,
                "The name of the small image.");
        this.descriptions.put(module.assetSmallText,
                "The displayed text of the small image.");
    }

    @Override
    public int getColor()
    {
        return 0xff815bff;
    }

    @Override
    public String getDescription()
    {
        return "TTS notifications for some stuff.";
    }

}
